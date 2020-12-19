package com.boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.model.Voo;
import com.boot.service.VooService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping(value = "/voo")
public class VooController {
	
	@Autowired
	private VooService service;

	@GetMapping("/findall")
	public Flux<Voo> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/findid/{id}")
	public Mono<Voo> findId(@PathVariable("id") Long id) {
		return service.findId(id);
	}
}
