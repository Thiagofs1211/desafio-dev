package com.bank.transacoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.transacoes.irepository.IPessoaFisicaRepository;
import com.bank.transacoes.model.PessoaFisica;

@Service
public class PessoaFisicaService {
	
	@Autowired
	IPessoaFisicaRepository dao;

	/**
	 * Método para salvar uma pessoa física
	 * @param pessoa
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void salvarPessoa(PessoaFisica pessoa) {
		dao.save(pessoa);
	}
	
	/**
	 * Método para recuperar uma pessoa pelo cpf
	 * @param cpf
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PessoaFisica buscarPessoaPorCPF(String cpf) {
		return dao.procuraPessoaPorCPF(cpf);
	}
}
