package com.bank.transacoes.service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.transacoes.dto.TransacaoDto;
import com.bank.transacoes.enums.TipoTransacaoEnum;
import com.bank.transacoes.irepository.ITransacao;
import com.bank.transacoes.model.Estabelecimento;
import com.bank.transacoes.model.PessoaFisica;
import com.bank.transacoes.model.Transacao;
import com.bank.transacoes.util.TransacaoUtil;

@Service
public class TransacaoService {
	
	@Autowired
	private PessoaFisicaService pessoaService;
	
	@Autowired
	private EstabelecimentoService estabelecimentoService;
	
	@Autowired
	private ITransacao daoTransacao;
	
	private static final Logger logger = LoggerFactory.getLogger(TransacaoUtil.class);

	/**
	 * Método para salvar uma lista de transacao recebida
	 * @param transacoes
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void salvarTransacao(List<String> transacoes) {
		for(String transacao : transacoes) {
			
			//Verifica se a transacao é válida e ja retorna uma lista com a transacao separada, se for nula a transacao não esta correta
			List<String> transacaoDividida = TransacaoUtil.verificaTransacaoValida(transacao);
			if(transacaoDividida == null) {
				logger.error("Transacao Invalida: "+transacao);
				continue; //Continua inserindo as outras transacoes mesmo se uma estiver errado
			}
			
			//Busca pessoa física por cpf
			//Verifica se existe: se não existir, crie uma pessoa nova
			PessoaFisica pessoa = pessoaService.buscarPessoaPorCPF(transacaoDividida.get(3));
			if(pessoa == null) {	//Caso a pessoa não exista
				pessoa = new PessoaFisica();
				pessoa.setCpf(transacaoDividida.get(3));
				pessoa.setNome(transacaoDividida.get(6).trim());
				pessoaService.salvarPessoa(pessoa);
				pessoa = pessoaService.buscarPessoaPorCPF(transacaoDividida.get(3));
			}
			
			//Busca estabelecimento por nome e cpf do dono
			//Verifica se existe: se não existir, crie um estabelecimento novo
			Estabelecimento estabelecimento = estabelecimentoService.buscarEstabelecimentoPorNomeCPF(transacaoDividida.get(7).trim(), transacaoDividida.get(3));
			if(estabelecimento == null) {
				estabelecimento = new Estabelecimento();
				estabelecimento.setNome(transacaoDividida.get(7).trim());
				estabelecimento.setDono(pessoa);
				estabelecimentoService.salvarEstabelecimento(estabelecimento);
				estabelecimento = estabelecimentoService.buscarEstabelecimentoPorNomeCPF(transacaoDividida.get(7).trim(), transacaoDividida.get(3));
			}
			
			//Crie o objeto de transacao e realiza a insercao
			Transacao modelo = new Transacao();
			modelo.setCartao(transacaoDividida.get(4));
			modelo.setEstabelecimento(estabelecimento);
			modelo.setTipo(TipoTransacaoEnum.getTipoTransacaoPorCodigo(transacaoDividida.get(0)));
			modelo.setValor(Double.valueOf(transacaoDividida.get(2))/100.0);
			try {
				modelo.setData(new SimpleDateFormat("yyyyMMdd").parse(transacaoDividida.get(1)));
				modelo.setHora(new Time(new SimpleDateFormat("HHmmss").parse(transacaoDividida.get(5)).getTime()));
			} catch (ParseException e) {
				logger.error("Erro ao converter data ou hora");
				e.printStackTrace();
			}
			daoTransacao.save(modelo);
		}
	}
	
	/**
	 * Método para buscar os dados das transacoes de um estabelecimento
	 * @param idEstabelecimento
	 * @return
	 */
	public TransacaoDto buscarTransacoesEstabelecimento(Long idEstabelecimento) {
		List<Transacao> transacoes = daoTransacao.procuraTransacoesEstabelecimento(idEstabelecimento);
		return TransacaoUtil.converteModelDto(transacoes);
	}
}
