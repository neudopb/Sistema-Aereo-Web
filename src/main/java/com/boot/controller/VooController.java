package com.boot.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boot.model.Voo;
import com.boot.service.VooService;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping(value = "/voo")
public class VooController {

	@Autowired
	private VooService service;

	@GetMapping("/findall")
	public ModelAndView findAll() {
		Voo[] voos = service.findAll();
		ModelAndView mv = new ModelAndView("passagens");
		mv.addObject("voosIda", voos);

		return mv;
	}

	@GetMapping("/findid/{id}")
	public Mono<Voo> findId(@PathVariable("id") Long id) {
		return service.findId(id);
	}

	@RequestMapping(value = "/findvoo", method = RequestMethod.GET)
	public ModelAndView findVoo(@RequestParam("origem") String origem, @RequestParam("destino") String destino,
			@RequestParam("dataIda") String dataIda, @RequestParam("dataVolta") String dataVolta,
			@RequestParam("classe") String classe, HttpSession session) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataI = LocalDate.parse(dataIda, formatter);

		Voo[] voosI = service.findVoo(origem, destino, dataI, classe);
		
		if(voosI.length  ==  0) {
			return new ModelAndView("home").addObject("alert", "Não há voos para essa data!");
		}

		ModelAndView mv = new ModelAndView("passagens");
		mv.addObject("voosIda", voosI);

		if (!dataVolta.isEmpty()) {
			LocalDate dataV = LocalDate.parse(dataVolta, formatter);
			Voo[] voosV = service.findVoo(destino, origem, dataV, classe);
			
			if(voosV.length  ==  0) {
				return new ModelAndView("home").addObject("alert", "Não há voos de volta para essa data!");
			}
			
			mv.addObject("voosVolta", voosV);
		}

		String preco;
		if (classe.equals("Economica"))
			preco = "R$ 1000.00";
		else
			preco = "R$ 2000.00";

		mv.addObject("preco", preco);
		mv.addObject("user", session.getAttribute("userlogado"));

		return mv;
	}
}
