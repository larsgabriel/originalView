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

	@RequestMapping("pesquisa")
	@ResponseBody
	public ModelAndView pesquisa(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		page.ifPresent(p -> currentPage = p);
		size.ifPresent(s -> pageSize = s);

		ModelAndView model = new ModelAndView("dossiemanager/pesquisa");

		Page<DossieAprovado> listapedidos = service.getAll(PageRequest.of(currentPage - 1, pageSize));
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

	@RequestMapping("registro")
	@ResponseBody
	public ModelAndView registro() {

		ModelAndView model = new ModelAndView("dossiemanager/registro");

		return model;
	}

	@RequestMapping(value = "dossiemanager/inserir", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView pesquisaregistro(@RequestParam String numeroPedido, String cxInterna, String cxExterna) {

		DossieAprovado dossie = service.getDossie(numeroPedido, cxInterna, cxExterna);
		dossieRepository.save(dossie);

		ModelAndView model = new ModelAndView("dossiemanager/registro");

		model.addObject("pedidos", service.findPedidoByNumeroPedido(cxInterna, cxExterna));
		model.addObject(new DossieAprovado());

		return model;
	}

	@RequestMapping(value = "/pesquisar", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView pesquisa(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, @RequestParam String numeroPedido, String cxInterna,
			String cxExterna, String statusAr, RedirectAttributes attributes) {

		page.ifPresent(p -> currentPage = p);
		size.ifPresent(s -> pageSize = s);
		ModelAndView model = new ModelAndView("dossiemanager/pesquisa");

		Page<DossieAprovado> dossiePage = service.pesquisa(PageRequest.of(currentPage - 1, pageSize), numeroPedido,
				cxInterna, cxExterna, statusAr.toUpperCase());

		if (dossiePage != null) {
			model.addObject("dossiePage", dossiePage);
		}
		int totalPages = dossiePage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addObject("pageNumbers", pageNumbers);
			attributes.addFlashAttribute("mensagem", "Sucesso!!!");
		}

		return model;
	}

}
