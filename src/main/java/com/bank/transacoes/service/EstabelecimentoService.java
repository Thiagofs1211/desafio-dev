package com.bank.transacoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.transacoes.dto.EstabelecimentoDto;
import com.bank.transacoes.irepository.IEstabelecimentoRepository;
import com.bank.transacoes.model.Estabelecimento;
import com.bank.transacoes.util.EstabelecimentoUtil;

@Service
public class EstabelecimentoService {
	
	@Autowired
	private IEstabelecimentoRepository dao;
	
	/**
	 * Método para buscar os estabelecimentos na base
	 * @return
	 */
	public List<EstabelecimentoDto> buscarEstabelecimentos() {
		List<Estabelecimento> estab = dao.findAll();
		return EstabelecimentoUtil.converteModelDto(estab);
	}
	
	/**
	 * Método para salvar um estabelecimento
	 * @param estabelecimento
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void salvarEstabelecimento(Estabelecimento estabelecimento) {
		dao.save(estabelecimento);
	}
	
	/**
	 * Método para buscar um estabelecimento peso seu nome e pelo cpf do dono
	 * @param nome
	 * @param cpf
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Estabelecimento buscarEstabelecimentoPorNomeCPF(String nome, String cpf) {
		return dao.procuraEstabelecimentoPorNomeCPF(nome,cpf);
	}

}
