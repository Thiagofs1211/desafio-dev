package com.bank.transacoes.service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.transacoes.enums.TipoTransacaoEnum;
import com.bank.transacoes.irepository.IEstabelecimentoRepository;
import com.bank.transacoes.irepository.IPessoaFisicaRepository;
import com.bank.transacoes.irepository.ITransacao;
import com.bank.transacoes.model.Estabelecimento;
import com.bank.transacoes.model.PessoaFisica;
import com.bank.transacoes.model.Transacao;
import com.bank.transacoes.util.TransacaoUtil;

@Service
public class TransacaoService {
	
	@Autowired
	private IPessoaFisicaRepository daoPessoaFisica;
	
	@Autowired
	private IEstabelecimentoRepository daoEstabelecimento;
	
	@Autowired
	private ITransacao daoTransacao;

	public void salvarTransacao(List<String> transacoes) {
		for(String transacao : transacoes) {
			
			//Verifica se a transacao é válida e ja retorna uma lista com a transacao separada, se for nula a transacao não esta correta
			List<String> transacaoDividida = TransacaoUtil.verificaTransacaoValida(transacao);
			if(transacaoDividida == null) {
				//TODO: Transacao Invalida
				return;
			}
			
			//Busca pessoa física por cpf
			//Verifica se existe: se não existir, crie uma pessoa nova
			PessoaFisica pessoa = daoPessoaFisica.procuraPessoaPorCPF(transacaoDividida.get(3));
			if(pessoa == null) {	//Caso a pessoa não exista
				pessoa = new PessoaFisica();
				pessoa.setCpf(transacaoDividida.get(3));
				pessoa.setNome(transacaoDividida.get(6).trim());
				daoPessoaFisica.save(pessoa);
				pessoa = daoPessoaFisica.procuraPessoaPorCPF(transacaoDividida.get(3));
			}
			
			//Busca estabelecimento por nome e cpf do dono
			//Verifica se existe: se não existir, crie um estabelecimento novo
			Estabelecimento estabelecimento = daoEstabelecimento.procuraEstabelecimentoPorNomeCPF(transacaoDividida.get(7), transacaoDividida.get(3));
			if(estabelecimento == null) {
				estabelecimento = new Estabelecimento();
				estabelecimento.setNome(transacaoDividida.get(7).trim());
				estabelecimento.setDono(pessoa);
				daoEstabelecimento.save(estabelecimento);
				estabelecimento = daoEstabelecimento.procuraEstabelecimentoPorNomeCPF(transacaoDividida.get(7), transacaoDividida.get(3));
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
				//TODO: ajustar a excessao
				e.printStackTrace();
			}
			daoTransacao.save(modelo);
		}
	}
}
