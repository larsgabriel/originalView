package com.certisign.dossie.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.certisign.dossie.model.Pedido;
import com.certisign.dossie.repository.PedidoRepository;

@RestController
public class DossieController {

	@Autowired
	private PedidoRepository repository;

	@PersistenceContext
	EntityManager em;

	@RequestMapping("recebimento")
	@ResponseBody
	public ModelAndView recebimento() {

		ModelAndView model = new ModelAndView("dossiemanager/recebimento");
			
		model.addObject("pedidos", findPedidoByNumeroPedido());

		return model;
	}
	
	public List<Pedido> findPedidoByNumeroPedido() {
		Query query = em.createNamedQuery(Pedido.PROCURA_POR_PEDIDO);
		query.setParameter("pedid", 176l);
		List<Pedido> pedidos = query.getResultList();

		return pedidos;
	}
}
