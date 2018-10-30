package com.certisign.dossie.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	private static int currentPage = 1;
	private static int pageSize = 10;

	@RequestMapping("inicial")
	@ResponseBody
	public ModelAndView pesquisa(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		page.ifPresent(p -> currentPage = p);
		size.ifPresent(s -> pageSize = s);

		ModelAndView model = new ModelAndView("dossiemanager/pesquisa");

		Page<DossieAprovado> listapedidos = service.findAll(PageRequest.of(currentPage - 1, pageSize));
		model.addObject("pedidos", listapedidos);

		int totalPages = listapedidos.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addObject("pageNumbers", pageNumbers);
		}

		return model;
	}

	@RequestMapping("recebimento")
	@ResponseBody
	public ModelAndView recebimento() {

		ModelAndView model = new ModelAndView("dossiemanager/recebimento");

		return model;
	}
	
	@RequestMapping("/receber")
	@ResponseBody
	public ModelAndView receber(@RequestParam String numeroPedido) {

		ModelAndView model = new ModelAndView("dossiemanager/recebimento");
		DossieAprovado dossie = service.recebimento(numeroPedido);
		if(dossie != null) {
			dossieRepository.save(dossie);
		}

		model.addObject("pedidos", service.findPorDataDeUltimaAtualizacao());
		model.addObject(new DossieAprovado());

		return model;
	}
	
	@RequestMapping("registro")
	@ResponseBody
	public ModelAndView registro() {

		ModelAndView model = new ModelAndView("dossiemanager/registro");

		return model;
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView pesquisaRegistro(@RequestParam String numeroPedido, String cxInterna, String cxExterna) {

		ModelAndView model = new ModelAndView("dossiemanager/registro");
		DossieAprovado dossie = service.vincularCaixas(numeroPedido, cxInterna, cxExterna);
		if(dossie != null) {
			dossieRepository.save(dossie);
		}

		model.addObject("pedidos", service.findByCaixaExternaAndCaixaInterna(cxInterna, cxExterna));
		model.addObject(new DossieAprovado());

		return model;
	}
	
	@RequestMapping(value = "/excluir/{numeroPedido}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView excluiRegistro(@RequestParam String numeroPedido) {
		
		DossieAprovado dossieOld = service.findPedido(numeroPedido);
		String cxInterna = dossieOld.getCaixaInterna();
		String cxExterna = dossieOld.getCaixaExterna();
		
		DossieAprovado dossie = service.desvinculoDeCaixas(numeroPedido);
		
		dossieRepository.save(dossie);

		ModelAndView model = new ModelAndView("dossiemanager/registro");

		model.addObject("pedidos", service.findByCaixaExternaAndCaixaInterna(cxInterna, cxExterna));
		model.addObject(new DossieAprovado());

		return model;
	}

	@RequestMapping(value = "/pesquisa")
	@ResponseBody
	public ModelAndView pesquisa(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam String numeroPedido, String cxInterna,
			String cxExterna, String statusAr, String statusCertisign,  RedirectAttributes attributes) {

		page.ifPresent(p -> currentPage = p);
		size.ifPresent(s -> pageSize = s);
		ModelAndView model = new ModelAndView("dossiemanager/pesquisa");

		Page<DossieAprovado> dossiePage = service.pesquisa(PageRequest.of(currentPage - 1, pageSize), numeroPedido,
				cxInterna, cxExterna, statusAr, statusCertisign);

		if (dossiePage != null) {
			model.addObject("pedidos", dossiePage);
		}
		int totalPages = dossiePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addObject("pageNumbers", pageNumbers);
		}

		return model;
	}

}
