package com.original.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.original.clientes.model.Address;
import com.original.clientes.model.Customer;
import com.original.clientes.service.ClienteService;

@RestController
public class CustomerController {
	
	@Autowired
	private ClienteService service;

	@RequestMapping("inicial")
	@ResponseBody
	public ModelAndView registro() {

		ModelAndView model = new ModelAndView("originalcadastro/registro");
		return model;
	}
	
	@RequestMapping("recebimento")
	@ResponseBody
	public ModelAndView recebimento() {

		ModelAndView model = new ModelAndView("originalcadastro/recebimento");
		return model;
	}
	
	@RequestMapping("/registro")
	@ResponseBody
	public ModelAndView cadastro() {

		ModelAndView model = new ModelAndView("originalcadastro/registro");
		return model;
	}
	
	@PostMapping(value = "/addCustomer")
	@ResponseBody
	public ModelAndView addCustomer(@ModelAttribute("customer") Customer customer) {

		ModelAndView model = new ModelAndView("originalcadastro/registro");
		service.addCustomer(customer);
		model.addObject("customers", service.findAll());
		return model;
	}
	@PostMapping(value = "/addAdress")
	@ResponseBody
	public ModelAndView pesquisaRegistro(@ModelAttribute("customer") Address address) {
		
		ModelAndView model = new ModelAndView("originalcadastro/recebimento");
		service.addAdress(address);
		model.addObject("customers", service.findAll());
		
		return model;
	}
	
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public ModelAndView delete(@RequestParam Long id) {
	    
		Customer customer = service.findById(id);
		service.deleteCustomer(customer);
		ModelAndView model = new ModelAndView("originalcadastro/registro");
		model.addObject("customers", service.findAll());
		model.addObject(new Customer());
		return model;
	}
}
