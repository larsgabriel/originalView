package com.certisign.dossie.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.certisign.dossie.config.property.IntegracaoApiProperty;
import com.certisign.dossie.model.DossieAprovado;
import com.certisign.dossie.model.FormPesquisa;
import com.certisign.dossie.model.FormRecebimento;
import com.certisign.dossie.model.FormRegistro;
import com.certisign.dossie.repository.DossieRepository;
import com.certisign.dossie.utils.DossieUtils;

@Service
public class DossieService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DossieService.class);

	@Autowired
	IntegracaoApiProperty integracaoApiPropert;

	@Autowired
	DossieUtils dossieUtils;

	Timestamp dataInsercao = transformaStringParaData();

	public void recebimento(FormRecebimento formRecebimento) {

		Long numPedido = tratarPedido(formRecebimento.getNumeroPedido());
		String pedidoUrl = integracaoApiPropert.getContexto().getContextoPedido();
		RestTemplate restTemplate = new RestTemplate();
		String url = pedidoUrl + "/dossie-api/recebimento/";
		formRecebimento.setPedidoTratado(numPedido);
		DossieAprovado dossie = null;
		String tipoTermo = tratarTermo(formRecebimento.getNumeroPedido());

		try {
			dossie = buscarPedido(formRecebimento.getPedidoTratado());
			if (dossie != null) {
				dossie.setStatusCertisign("RECEBIDO");
				dossie.setTipoTermo(tipoTermo);
				dossie.setDataUltAtualizacao(dataUltimaAtualizacao());
			}
			restTemplate.postForEntity(url, dossie, Object.class);
		} catch (Exception e) {
			LOGGER.error("Pedido" + formRecebimento.getNumeroPedido() + "não encontrado!");
		}
	}

	public Page<DossieAprovado> pesquisa(Pageable pageable, FormPesquisa formPesquisa) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		Page<DossieAprovado> dossiePage = null;
		List<DossieAprovado> pedidos = null;
		List<DossieAprovado> dossies;

		String pedidoUrl = integracaoApiPropert.getContexto().getContextoPedido();
		RestTemplate restTemplate = new RestTemplate();

		if (formPesquisa.getNumeroPedido() != null && !formPesquisa.getNumeroPedido().isEmpty()) {
			formPesquisa.setPedidoTratado(tratarPedido(formPesquisa.getNumeroPedido()));
		}

		try {

			String url = pedidoUrl + "/dossie-api/pesquisaPorFiltros/";
			ResponseEntity<Object> pedidosObj = restTemplate.postForEntity(url, formPesquisa, Object.class);

			if (pedidosObj != null) {
				pedidos = dossieUtils.translateObjets((List) pedidosObj.getBody());
			}

			if (startItem > pedidos.size()) {
				startItem = 0;
			}

			if (pedidos.size() < 1) {
				dossies = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, pedidos.size());
				dossies = pedidos.subList(startItem, toIndex);
			}

			dossiePage = new PageImpl<DossieAprovado>(dossies, PageRequest.of(currentPage, pageSize), pedidos.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dossiePage;

	}

	public void desvinculoDeCaixas(Long numeroPedido) {
		DossieAprovado dossie = null;
		String msg = null;
		String pedidoUrl = integracaoApiPropert.getContexto().getContextoPedido();
		RestTemplate restTemplate = new RestTemplate();
		String url = pedidoUrl + "/dossie-api/desvincularCaixas/";
		try {
			dossie = buscarPedido(numeroPedido);
			if (dossie != null) {
				dossie.setStatusCertisign("PENDENTE");
				dossie.setCaixaExterna("");
				dossie.setCaixaInterna("");
				dossie.setDataUltAtualizacao(dataUltimaAtualizacao());
				
				restTemplate.postForEntity(url, dossie, Object.class);
			} else {
				msg = "Pedido não encontrado";
				LOGGER.error(msg);
			}
		} catch (Exception e) {
			LOGGER.error("Pedido" + numeroPedido + "não encontrado!");
		}
	}

	public void vincularCaixas(FormRegistro formRegistro) {
		DossieAprovado dossie = null;
		String msg = null;
		String pedidoUrl = integracaoApiPropert.getContexto().getContextoPedido();
		RestTemplate restTemplate = new RestTemplate();
		String url = pedidoUrl + "/dossie-api/vincularCaixas/";

		try {
			String tipoTermo = tratarTermo(formRegistro.getNumeroPedido());
			formRegistro.setPedidoTratado(tratarPedido(formRegistro.getNumeroPedido()));
			dossie = buscarPedido(formRegistro.getPedidoTratado());
			if (dossie != null) {
				dossie.setStatusCertisign("ESTOCADO");
				dossie.setTipoTermo(tipoTermo);
				dossie.setCaixaExterna(formRegistro.getCxExterna());
				dossie.setCaixaInterna(formRegistro.getCxInterna());
				dossie.setDataUltAtualizacao(dataUltimaAtualizacao());

				restTemplate.postForEntity(url, dossie, Object.class);

			} else {
				msg = "Pedido não encontrado";
				LOGGER.error(msg);
			}

		} catch (Exception e) {
			LOGGER.error("Pedido" + formRegistro.getPedidoTratado() + "não encontrado!");
		}
	}

	public List<DossieAprovado> findByCaixaExternaAndCaixaInterna(DossieAprovado dossie) {
		RestTemplate restTemplate = new RestTemplate();
		String pedidoUrl = integracaoApiPropert.getContexto().getContextoPedido();
		String url = pedidoUrl + "/dossie-api/buscaPorCaixas/";
		List<DossieAprovado> pedidos = null;
		try {
			ResponseEntity<Object> pedidosObj = restTemplate.postForEntity(url, dossie, Object.class);
			if (pedidosObj != null) {
				pedidos = dossieUtils.translateObjets((List) pedidosObj.getBody());
			}
		} catch (Exception e) {
			LOGGER.error("Pedidos não encontrado!" + e);
		}

		return pedidos;
	}

	public List<DossieAprovado> findPorDataDeUltimaAtualizacao() {
		List<DossieAprovado> pedidos = null;
		LocalDateTime date = LocalDateTime.now();
		Timestamp startOfDay = Timestamp.valueOf(date.with(LocalTime.MIDNIGHT));
		Timestamp endOfDay = Timestamp.valueOf(date.with(LocalTime.MAX));
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			String pedidoUrl = integracaoApiPropert.getContexto().getContextoPedido();
			String url = pedidoUrl + "/dossie-api/findAllDataUltimaAtualizacao/" + startOfDay + "/" + endOfDay;
			Object pedidosObj = restTemplate.getForObject(url, Object.class);
			
			if (pedidosObj != null) {
				pedidos = dossieUtils.translateObjets((List) pedidosObj);
			}

		} catch (Exception e) {
			LOGGER.error("Pedidos não encontrado!" + e);
		}
		return pedidos;
	}

	public DossieAprovado buscarPedido(Long numeroPedido) {
		DossieAprovado pedido = null;
		String pedidoUrl = integracaoApiPropert.getContexto().getContextoPedido();
		RestTemplate restTemplate = new RestTemplate();
		String url = pedidoUrl + "/dossie-api/buscarPedido/" + numeroPedido ;
		try {

			Object pedidosObj = restTemplate.getForObject(url, Object.class);
			
			if (pedidosObj != null) {
				pedido = dossieUtils.translateDossie(pedidosObj);
			}

		} catch (Exception e) {
			LOGGER.error("Pedido " + numeroPedido + " não encontrado!");
		}
		return pedido;
	}

	public Timestamp transformaStringParaData() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		Timestamp data = new Timestamp(cal.getTimeInMillis());
		return data;

	}

	public Timestamp dataUltimaAtualizacao() {
		Calendar cal = Calendar.getInstance();
		Timestamp data = new Timestamp(cal.getTimeInMillis());
		return data;

	}

	public Long tratarPedido(String numeroPedido) {

		String pedido = "";
		if (numeroPedido.contains("R") || numeroPedido.contains("T")) {
			String[] parts = numeroPedido.split("-");
			pedido = parts[0];
		} else {
			return Long.parseLong(numeroPedido);
		}

		return Long.parseLong(pedido);
	}

	public String tratarTermo(String numeroPedido) {

		String termo = "";
		if (numeroPedido.contains("R") || numeroPedido.contains("T")) {
			String[] parts = numeroPedido.split("-");
			termo = parts[1];
		}
		return termo;
	}
}
