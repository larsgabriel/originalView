package com.certisign.dossie.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.certisign.dossie.model.DossieAprovado;
import com.certisign.dossie.model.FormPesquisa;
import com.certisign.dossie.model.FormRecebimento;
import com.certisign.dossie.model.FormRegistro;
import com.certisign.dossie.service.DossieService;

@RestController
@Transactional
public class DossieController {

	@Autowired
	private DossieService service;

	private static int currentPage = 1;
	private static int pageSize = 10; 

	@RequestMapping("inicial")
	@ResponseBody
	public ModelAndView pesquisa(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		ModelAndView model = new ModelAndView("dossiemanager/pesquisa");
		return model;
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
		
	@RequestMapping(value = "/pesquisa")
	@ResponseBody
	public ModelAndView pesquisa(@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size, @ModelAttribute("pedido") FormPesquisa pedido) {
			
		page.ifPresent(p -> currentPage = p);
		size.ifPresent(s -> pageSize = s);
		
		ModelAndView model = new ModelAndView("dossiemanager/pesquisa");

		Page<DossieAprovado> dossiePage = service.pesquisa(PageRequest.of(currentPage - 1, pageSize), pedido);

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
	
	@RequestMapping("/receber")
	@ResponseBody
	public ModelAndView receber(@ModelAttribute("pedido") FormRecebimento recebimento) {

		ModelAndView model = new ModelAndView("dossiemanager/recebimento");
		service.recebimento(recebimento);
		model.addObject("pedidos", service.findPorDataDeUltimaAtualizacao());
		model.addObject(new DossieAprovado());

		return model;
	}
	
	@RequestMapping(value = "/registrar")
	@ResponseBody
	public ModelAndView pesquisaRegistro(@ModelAttribute("pedido") FormRegistro registro) {

		ModelAndView model = new ModelAndView("dossiemanager/registro");
		service.vincularCaixas(registro);
		DossieAprovado dossie = service.buscarPedido(registro.getPedidoTratado());
		model.addObject("pedidos", service.findByCaixaExternaAndCaixaInterna(dossie));
		model.addObject(new DossieAprovado());

		return model;
	}
	
	@RequestMapping(value = "/excluir/{numeroPedido}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView excluiRegistro(@RequestParam Long numeroPedido) {
		
		DossieAprovado dossieOld = service.buscarPedido(numeroPedido);
		service.desvinculoDeCaixas(numeroPedido);
		ModelAndView model = new ModelAndView("dossiemanager/registro");

		model.addObject("pedidos", service.findByCaixaExternaAndCaixaInterna(dossieOld));
		model.addObject(new DossieAprovado());

		return model;
	}
	
}
