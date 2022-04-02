package com.bank.transacoes.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.transacoes.irepository.IPessoaFisicaRepository;
import com.bank.transacoes.model.PessoaFisica;

@SpringBootTest
public class PessoaFisicaServiceTest {
	
	@InjectMocks
	private PessoaFisicaService service;

	@Mock
	IPessoaFisicaRepository dao;
	
	@Test
	public void salvarPessoa() {
		service.salvarPessoa(new PessoaFisica());
	}
	
	@Test
	public void buscarPessoaPorCPF() {
		PessoaFisica pessoa = new PessoaFisica();
		pessoa.setId(Long.valueOf("1"));
		
		Mockito.when(dao.procuraPessoaPorCPF(Mockito.anyString())).thenReturn(pessoa);
		
		PessoaFisica retorno = service.buscarPessoaPorCPF("teste");
		
		assertThat(retorno.getId()).isEqualTo(Long.valueOf("1"));
	}
}
