package com.certisign.dossie.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.certisign.dossie.model.DossieAprovado;
import com.certisign.dossie.repository.DossieRepository;

@RestController
public class DossieController {

	@Autowired
	private DossieRepository dossieRepository;
	@PersistenceContext
	private EntityManager em;

	@RequestMapping("pesquisa")
	@ResponseBody
	public ModelAndView pesquisa() {

		List<DossieAprovado> listapedidos = (List<DossieAprovado>) dossieRepository.findAll();

		ModelAndView mv = new ModelAndView("dossiemanager/pesquisa");

		mv.addObject("pedidos", listapedidos);

		mv.addObject(new DossieAprovado());

		return mv;
	}

	@RequestMapping("registro")
	@ResponseBody
	public ModelAndView registro() {

		ModelAndView model = new ModelAndView("dossiemanager/registro");

		return model;
	}

	@RequestMapping("recebimento")
	@ResponseBody
	public ModelAndView recebimento() {

		ModelAndView model = new ModelAndView("dossiemanager/recebimento");

		return model;
	}

	@RequestMapping(value = "dossiemanager/recebimento", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView pesquisarecebimento(@RequestParam String numeroPedido, String cxInterna, String cxExterna) {

		String tipoTermo;
		String pedido;
		DossieAprovado dossie = new DossieAprovado();

		if (numeroPedido.contains("R") || numeroPedido.contains("T")) {
			String[] parts = numeroPedido.split("-");
			pedido = parts[0];
			tipoTermo = parts[1];
			dossie.setNumeroPedido(pedido);
			dossie.setIdStatusPedido("RECEBIDO");
			dossie.setTipoTermo(tipoTermo);
			dossie.setCaixaExterna(cxExterna);
			dossie.setCaixaInterna(cxInterna);

		} else {
			dossie.setNumeroPedido(numeroPedido);
			dossie.setCaixaExterna(cxExterna);
			dossie.setCaixaInterna(cxInterna);
			dossie.setIdStatusPedido("RECEBIDO");

		}

		dossieRepository.save(dossie);

		ModelAndView model = new ModelAndView("dossiemanager/recebimento");

		model.addObject("pedido", findPedidoByNumeroPedido(cxInterna, cxExterna));
		model.addObject(new DossieAprovado());

		return model;
	}

	public List<DossieAprovado> findPedidoByNumeroPedido(String cxInterna, String cxExterna ) {
		Query query = em.createNamedQuery(DossieAprovado.PROCURA_POR_PEDIDO);
		query.setParameter("cxExt", cxExterna);
		query.setParameter("cxInt", cxInterna);
		List<DossieAprovado> pedidos =  query.getResultList();
		return pedidos;
	}


}
