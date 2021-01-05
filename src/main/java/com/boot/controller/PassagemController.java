package com.boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boot.model.Passagem;
import com.boot.service.PassagemService;

@Controller
@RequestMapping(value = "/passagem")
public class PassagemController {

	@Autowired
	private PassagemService service;

	/*@GetMapping("/minhasreservas")
	public ModelAndView minhasReservas(HttpSession session) {
		String usuario = (String) session.getAttribute("userlogado");

		Passagem[] passagem = service.passagemUser(usuario);

		ModelAndView mv = new ModelAndView("reservas").addObject("passagens", passagem);

		return mv;
	}*/

	@RequestMapping(value = "/reserva", method = RequestMethod.GET)
	public ModelAndView reservar(@RequestParam("assentoIda") Long assentoI, @RequestParam("assentoVolta") Long assentoV, @RequestParam("usuario") String usuario) {

		Passagem[] passagens = service.passagemSave(usuario, assentoI);
		if(assentoV != null) {
			passagens = service.passagemSave(usuario, assentoV);
		}
		ModelAndView mv = new ModelAndView("reservas").addObject("passagens", passagens);
		return mv;

	}

}
