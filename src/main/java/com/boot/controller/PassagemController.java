package com.boot.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.boot.model.Passagem;
import com.boot.service.PassagemService;

@Controller
@RequestMapping(value = "/passagem")
public class PassagemController {

	@Autowired
	private PassagemService service;
	
	@RequestMapping(value = "/reserva", method = RequestMethod.GET)
	public ModelAndView reservar(@RequestParam("assentoIda") Long assentoI, @RequestParam("assentoVolta") Long assentoV, @RequestParam("usuario") String usuario, HttpSession session) {

		Passagem[] passagens = service.passagemSave(usuario, assentoI);
		
		if(assentoV != null) {
			passagens = service.passagemSave(usuario, assentoV);
		}
		ModelAndView mv = new ModelAndView("reservas").addObject("passagens", passagens);
		return mv;
	}
	
	@GetMapping("/pagar/{idpas}")
	public RedirectView pagar(@PathVariable("idpas") Long idPas, HttpSession session) {

		String id = String.valueOf(session.getAttribute("iduser"));
		Long idUser = Long.parseLong(id);
		String token = (String) session.getAttribute("token");
		
		String link = service.passagemPaga(idUser, idPas, token);

		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(link);
		return redirectView;
	}

}
