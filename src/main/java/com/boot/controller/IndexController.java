package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boot.model.Passagem;
import com.boot.model.Usuario;
import com.boot.service.IndexService;
import com.boot.service.PassagemService;

@Controller
public class IndexController {

	@Autowired
	private IndexService service;
	
	@Autowired
	private PassagemService servicePassagem;

	@GetMapping("/")
	public String login(Usuario usuario) {
		return "login";
	}

	@GetMapping("/cadastrar")
	public String cadastro(Usuario usuario) {
		return "cadastro";
	}

	@GetMapping("/home")
	public String home(HttpSession session) {
		session.getAttribute("userlogado");
		return "home";
	}

	@GetMapping("/logout")
	public String Logout(Usuario usuario, HttpSession session) {
		session.removeAttribute("userlogado");
		session.removeAttribute("token");
		session.removeAttribute("iduser");
		return "login";
	}

	@PostMapping("/home")
	public ModelAndView logar(Usuario usuario, HttpSession session) {
		Usuario user = service.login(usuario);

		if (user == null) {
			return new ModelAndView("login").addObject("fail", "E-mail e/ou senha incorreto");
		}

		session.setAttribute("iduser", user.getId());
		session.setAttribute("userlogado", user.getEmail());
		session.setAttribute("token", user.getToken());
		ModelAndView mv = new ModelAndView("home").addObject("logado", session.getAttribute("userlogado"));

		return mv;

	}

	@PostMapping("/login")
	public ModelAndView cadastrar(Usuario usuario) {
		Usuario user = service.save(usuario);

		ModelAndView mv = new ModelAndView("login");

		if (user != null) {
			mv.addObject("sucess", "Usuário cadastrado com sucesso");
			mv.addObject("usuario", user);
		} else
			mv.addObject("fail", "Erro ao cadastrar usuário");

		return mv;
	}

	@GetMapping("/minhasreservas")
	public ModelAndView minhasReservas(HttpSession session) {

		Passagem[] passagens = servicePassagem.passagemUser((String) session.getAttribute("userlogado"));
		ModelAndView mv;

		if (passagens.length != 0)
			mv = new ModelAndView("reservas").addObject("passagens", passagens);
		else
			mv = new ModelAndView("home").addObject("alert", "Você não tem reservas");
		return mv;
	}
	
}