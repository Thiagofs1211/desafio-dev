package com.bank.transacoes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.transacoes.service.TransacaoService;

@RestController
@RequestMapping(path = "/transacao")
public class TransacaoController {
	
	@Autowired
	TransacaoService service;

	@GetMapping("/teste")
	public @ResponseBody void salvarTransacoes() {
		service.salvarTransacao(getMock());
	}
	
	private List<String> getMock() {
		List<String> mock = new ArrayList<String>();
		mock.add("5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ");
		return mock;
	}
}
