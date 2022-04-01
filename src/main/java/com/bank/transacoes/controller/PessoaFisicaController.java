package com.bank.transacoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.transacoes.model.PessoaFisica;
import com.bank.transacoes.repository.IPessoaFisicaRepository;

@RestController
@RequestMapping(path = "/pessoaFisica")
public class PessoaFisicaController {
	
	@Autowired
	private IPessoaFisicaRepository dao;

	@GetMapping(path = "/listar")
	public @ResponseBody List<PessoaFisica> listar() {
		return dao.findAll();
	}
}
