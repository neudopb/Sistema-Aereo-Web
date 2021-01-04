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
	
	private String emailUser;

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
		return "login";
	}

	@PostMapping("/home")
	public ModelAndView logar(Usuario usuario, HttpSession session) {
		String token = service.findEmailSenha(usuario);

		if (token == null) {
			return new ModelAndView("login").addObject("erro", "E-mail e/ou senha incorreto");
		}

		session.setAttribute("userlogado", usuario.getEmail());
		session.setAttribute("token", token);
		ModelAndView mv = new ModelAndView("home").addObject("logado", session.getAttribute("userlogado"));
		
		this.emailUser = usuario.getEmail();
		
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
			mv.addObject("erro", "Erro ao cadastrar usuário");

		return mv;
	}
	
	@GetMapping("/minhasreservas")
	public ModelAndView minhasReservas(HttpSession session) {

		Passagem[] passagens = servicePassagem.passagemUser(this.emailUser);

		ModelAndView mv = new ModelAndView("reservas").addObject("passagens", passagens);

		return mv;
	}

}