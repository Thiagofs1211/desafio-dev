package com.bank.transacoes.service;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.transacoes.dto.TransacaoDto;
import com.bank.transacoes.enums.TipoTransacaoEnum;
import com.bank.transacoes.irepository.ITransacao;
import com.bank.transacoes.model.Estabelecimento;
import com.bank.transacoes.model.PessoaFisica;
import com.bank.transacoes.model.Transacao;

@SpringBootTest
public class TransacaoServiceTest {

	@InjectMocks
	private TransacaoService service;
	
	@Mock
	PessoaFisicaService pessoaService;
	
	@Mock
	EstabelecimentoService estabService;
	
	@Mock
	ITransacao dao;
	
	@Test
	public void salvarTransacaoPessoaEstabelecimentoExiste() {
		List<String> transacoes = new ArrayList<>();
		transacoes.add("5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ");
		Mockito.when(pessoaService.buscarPessoaPorCPF(Mockito.anyString())).thenReturn(new PessoaFisica());
		Mockito.when(estabService.buscarEstabelecimentoPorNomeCPF(Mockito.anyString(), Mockito.anyString())).thenReturn(new Estabelecimento());
		service.salvarTransacao(transacoes);
	}
	
	@Test
	public void salvarTransacaoPessoaEstabelecimentoNaoExiste() {
		List<String> transacoes = new ArrayList<>();
		transacoes.add("5201903010000013200556418150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ");
		Mockito.when(pessoaService.buscarPessoaPorCPF(Mockito.anyString())).thenReturn(null);
		Mockito.when(estabService.buscarEstabelecimentoPorNomeCPF(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		service.salvarTransacao(transacoes);
	}
	
	@Test
	public void salvarTransacaoIncorreta() {
		List<String> transacoes = new ArrayList<>();
		transacoes.add("520190301000001320055641150633123****7687145607MARIA JOSEFINALOJA DO Ó - MATRIZ");
		service.salvarTransacao(transacoes);
	}
	
	@Test
	public void buscarTransacoesEstabelecimento() {
		List<Transacao> list = new ArrayList<Transacao>();
		
		Transacao transacao = new Transacao();
		transacao.setTipo(TipoTransacaoEnum.CREDITO);
		transacao.setValor(10.0);
		
		Transacao transacao2 = new Transacao();
		transacao2.setTipo(TipoTransacaoEnum.ALUGUEL);
		transacao2.setValor(20.0);
		
		list.add(transacao);
		list.add(transacao2);
		Mockito.when(dao.procuraTransacoesEstabelecimento(Mockito.anyLong())).thenReturn(list);
		TransacaoDto resultado = service.buscarTransacoesEstabelecimento(Long.valueOf("1"));
		
		assertThat(resultado.getDadosTransacao().get(0).getValor()).isEqualTo(10.0);
		assertThat(resultado.getDadosTransacao().get(1).getValor()).isEqualTo(20.0);
	}
}
