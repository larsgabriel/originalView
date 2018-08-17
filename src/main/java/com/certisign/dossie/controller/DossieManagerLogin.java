/**
 * 
 */
package com.certisign.dossie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Marco Gabriel
 *
 */
@RestController
@RequestMapping("dossiemanagerlogin")
public class DossieManagerLogin {
	
	@Autowired
	private Environment environment;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/")
	public ModelAndView efetuarLogin() {
		ModelAndView model = new ModelAndView("login");
		prepararPagina(model);
		return model;
	}

	/**
	 * @param model
	 */
	private void prepararPagina(ModelAndView model) {
		model.addObject("urlLogin", environment.getProperty("certificado.url.login"));
		model.addObject("id", environment.getProperty("certificado.id"));
		model.addObject("nomeSistema", environment.getProperty("certificado.nome"));
		model.addObject("urlRetorno", environment.getProperty("certificado.url.retorno"));
		model.addObject("certificadoPk", environment.getProperty("certificado.pk"));
	}
	
	

}
