package com.boot.controller;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		System.out.println("dfhjsdfhvshavskhvahskvahkdva " + voos);
		ModelAndView mv = new ModelAndView("passagens");
		mv.addObject("voosIda", voos);
		
		return mv;
	}
	
	@GetMapping("/findid/{id}")
	public Mono<Voo> findId(@PathVariable("id") Long id) {
		return service.findId(id);
	}
	
	//@GetMapping("/findvoo/{origem}/{destino}/{dataIda}/{dataVolta}/{classe}/")
	@RequestMapping(value="/findvoo", method = RequestMethod.GET)
	public ModelAndView findVoo(@RequestParam("origem") String origem, @RequestParam("destino") String destino,
			@RequestParam("dataIda") String dataIda, @RequestParam("dataVolta") String dataVolta, 
			@RequestParam("classe") String classe) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataI = LocalDate.parse(dataIda, formatter);
		LocalDate dataV = LocalDate.parse(dataIda, formatter);

		Voo[] voos = service.findVoo(origem, destino, dataI, classe);
		ModelAndView mv = new ModelAndView("passagens");
		mv.addObject("voosIda", voos);
		
		return mv;
	}
}
