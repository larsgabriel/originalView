package com.certisign.dossie.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.certisign.dossie.model.DossieAprovado;
import com.certisign.dossie.repository.DossieRepository;
import com.certisign.dossie.service.DossieService;

@RestController
@Transactional
public class DossieController {
	
	@Autowired
	private DossieRepository dossieRepository;
	@Autowired
	private DossieService service;
	@PersistenceContext
	private EntityManager em;

	@RequestMapping("pesquisa")
	@ResponseBody
	public ModelAndView pesquisa() {
				
		List<DossieAprovado> listapedidos = (List<DossieAprovado>) service.getAll();
		ModelAndView mv = new ModelAndView("dossiemanager/pesquisa");
		mv.addObject("pedidos", listapedidos);

		mv.addObject(new DossieAprovado());

		return mv;
	}
	

	@RequestMapping("recebimento")
	@ResponseBody
	public ModelAndView recebimento() {

		ModelAndView model = new ModelAndView("dossiemanager/recebimento");

		return model;
	}

	@RequestMapping("registro")
	@ResponseBody
	public ModelAndView registro() {

		ModelAndView model = new ModelAndView("dossiemanager/registro");

		return model;
	}

	@RequestMapping(value = "dossiemanager/inserir", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView pesquisaregistro(@RequestParam String numeroPedido, String cxInterna, String cxExterna) {
		
		
		DossieAprovado dossie = service.getDossie(numeroPedido, cxInterna, cxExterna );
		dossieRepository.save(dossie);
		
		ModelAndView model = new ModelAndView("dossiemanager/registro");

		model.addObject("pedidos", service.findPedidoByNumeroPedido(cxInterna, cxExterna));
		model.addObject(new DossieAprovado());

		return model;
}
	
	@RequestMapping(value = "dossiemanager/pesquisar", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView pesquisa(@RequestParam String numeroPedido, String cxInterna, String cxExterna, String statusAr) {
		
		List<DossieAprovado> dossie = (List<DossieAprovado>) service.pesquisa(numeroPedido, cxInterna, cxExterna, statusAr.toUpperCase() );
		
		ModelAndView model = new ModelAndView("dossiemanager/pesquisa");
		
		if(dossie != null){
			model.addObject("pedidos", dossie);
		}
		model.addObject(new DossieAprovado());
		
		return model;
	}

}
