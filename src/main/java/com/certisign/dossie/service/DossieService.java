package com.certisign.dossie.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.certisign.dossie.model.DossieAprovado;
import com.certisign.dossie.repository.DossieRepository;

@Service
public class DossieService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DossieService.class);
	
	@PersistenceContext
	EntityManager em;

	@Autowired
	DossieRepository repository;

	Timestamp dataInsercao = transformaStringParaData();
		
	public DossieAprovado recebimento(String numeroPedido) {
		DossieAprovado dossie = null;
		String msg = null;
		try {
			String tipoTermo = tratarTermo(numeroPedido);
			dossie = findPedido(numeroPedido);
			if(dossie != null) {
				dossie.setStatusCertisign("RECEBIDO");
				dossie.setTipoTermo(tipoTermo);
				dossie.setDataUltAtualizacao(dataUltimaAtualizacao());
			}else{
				msg = "Pedido não encontrado";
				LOGGER.error(msg);
				return null;
			}
		} catch (Exception e) {
			LOGGER.error("Pedido" + numeroPedido + "não encontrado!");
		}
		return dossie;
	}
	
	public DossieAprovado desvinculoDeCaixas(String numeroPedido) {
		DossieAprovado dossie = null;
		String msg = null;
		try {
			String tipoTermo = tratarTermo(numeroPedido);
			dossie = findPedido(numeroPedido);
			if(dossie != null) {
				dossie.setStatusCertisign("PENDENTE");
				dossie.setTipoTermo(tipoTermo);
				dossie.setCaixaExterna("");
				dossie.setCaixaInterna("");
				dossie.setDataUltAtualizacao(dataUltimaAtualizacao());
			}else{
				msg = "Pedido não encontrado";
				LOGGER.error(msg);
				return null;
			}
		} catch (Exception e) {
			LOGGER.error("Pedido" + numeroPedido + "não encontrado!");
		}
		return dossie;
	}
	
	
	public DossieAprovado vincularCaixas(String numeroPedido, String cxInterna, String cxExterna) {
		DossieAprovado dossie = null;
		String msg = null;
		try {
			String tipoTermo = tratarTermo(numeroPedido);
			dossie = findPedido(numeroPedido);
			if(dossie != null) {
				dossie.setStatusCertisign("ESTOCADO");
				dossie.setTipoTermo(tipoTermo);
				dossie.setCaixaExterna(cxExterna);
				dossie.setCaixaInterna(cxInterna);
				dossie.setDataUltAtualizacao(dataUltimaAtualizacao());
			}else{
				msg = "Pedido não encontrado";
				LOGGER.error(msg);
				return null;
			}
		} catch (Exception e) {
			LOGGER.error("Pedido" + numeroPedido + "não encontrado!");
		}
		return dossie;
	}

	public Page<DossieAprovado> findAll(Pageable pageable) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<DossieAprovado> dossies;
		Page<DossieAprovado> dossiePage = null;

		List<DossieAprovado> pedidos = repository.findAllByDataInsercaoAfter(dataInsercao);

		if (pedidos.size() < startItem) {
			dossies = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, pedidos.size());
			dossies = pedidos.subList(startItem, toIndex);
		}

		dossiePage = new PageImpl<DossieAprovado>(dossies, PageRequest.of(currentPage, pageSize), pedidos.size());

		return dossiePage;
	}

	public List<DossieAprovado> findByCaixaExternaAndCaixaInterna(String cxInterna, String cxExterna) {
		List<DossieAprovado> pedidos = repository.findByCaixaExternaAndCaixaInterna(cxExterna, cxInterna);
		return pedidos;
	}

	public DossieAprovado findPedido(String numeroPedido) {
		DossieAprovado pedidos = null;
		Long pedido = tratarPedido(numeroPedido);
		try {
			pedidos = repository.findByNumeroPedido(pedido);
		} catch (Exception e) {
			LOGGER.error("Pedido " + numeroPedido + " não encontrado!");
		}
		return pedidos;
	}
	
	public List<DossieAprovado> findPorDataDeUltimaAtualizacao() {
		List<DossieAprovado> pedidos = null;
		LocalDateTime  date = LocalDateTime.now();
		Timestamp  startOfDay = Timestamp.valueOf(date.with(LocalTime.MIDNIGHT));
		Timestamp  endOfDay = Timestamp.valueOf(date.with(LocalTime.MAX));
	
		try {
			pedidos = repository.findAllByDataUltAtualizacaoAfterAndDataUltAtualizacaoBefore(startOfDay, endOfDay);
		} catch (Exception e) {
			LOGGER.error("Pedidos não encontrado!" + e);
		}
		return pedidos;
	}

	public Page<DossieAprovado> pesquisa(Pageable pageable, String numeroPedido, String cxInterna, String cxExterna,
			String statusAr, String statusCerti) {

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		Long pedido = null;
		Page<DossieAprovado> dossiePage = null;
		List<DossieAprovado> pedidos = null;
		List<DossieAprovado> dossies;

		if (!numeroPedido.equals("")) {
			pedido = tratarPedido(numeroPedido);
		}

		try {

			if (!statusAr.equals("Status Ar") || !statusCerti.equals("Status Certisign") || !cxExterna.equals("")
					|| !cxInterna.equals("") || pedido != null) {
				pedidos = buscaPedidosPorFiltros(pedido, cxInterna, cxExterna, statusAr, statusCerti);
			} else {
				pedidos = repository.findAllByDataInsercaoAfter(dataInsercao);
			}

			if (pedidos.size() < startItem) {
				dossies = Collections.emptyList();
			} else {
				int toIndex = Math.min(startItem + pageSize, pedidos.size());
				dossies = pedidos.subList(startItem, toIndex);
			}

			dossiePage = new PageImpl<DossieAprovado>(dossies, PageRequest.of(currentPage, pageSize), dossies.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dossiePage;

	}

	public List<DossieAprovado> buscaPedidosPorFiltros(Long numeroPedido, String cxInterna, String cxExterna,
			String statusAr, String statusCerti) {

		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<DossieAprovado> cq = qb.createQuery(DossieAprovado.class);
		Root<DossieAprovado> dossie = cq.from(DossieAprovado.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		List<DossieAprovado> pedidos;
		Timestamp dataInsercao = transformaStringParaData();

		if (numeroPedido != null) {
			predicates.add(qb.equal(dossie.get("numeroPedido"), numeroPedido));
		}
		if (!cxInterna.equals("")) {
			predicates.add(qb.equal(dossie.get("caixaInterna"), cxInterna));
		}
		if (!cxExterna.equals("")) {
			predicates.add(qb.equal(dossie.get("caixaExterna"), cxExterna));
		}
		if (!statusAr.equals("Status Ar")) {
			predicates.add(qb.equal(dossie.get("statusAr"), statusAr.toUpperCase()));
			predicates.add(qb.greaterThan(dossie.get("dataInsercao"), dataInsercao));
		}
		if (!statusCerti.equals("Status Certisign")) {
			predicates.add(qb.equal(dossie.get("statusCertisign"), statusCerti.toUpperCase()));
			predicates.add(qb.greaterThan(dossie.get("dataInsercao"), dataInsercao));
		}

		cq.select(dossie).where(predicates.toArray(new Predicate[] {}));
		pedidos = em.createQuery(cq).getResultList();

		return pedidos;
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
