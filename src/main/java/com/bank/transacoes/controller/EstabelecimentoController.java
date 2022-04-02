package com.bank.transacoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.transacoes.dto.EstabelecimentoDto;
import com.bank.transacoes.service.EstabelecimentoService;

@RestController
@RequestMapping(path = "/estabelecimento")
public class EstabelecimentoController {
	
	@Autowired
	private EstabelecimentoService service;
	
	@GetMapping(path = "/listar")
	public @ResponseBody List<EstabelecimentoDto> listarEstabelecimento() {
		return service.buscarEstabelecimentos();
	}
}
