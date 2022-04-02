package com.bank.transacoes.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.transacoes.irepository.IPessoaFisicaRepository;
import com.bank.transacoes.model.PessoaFisica;

@SpringBootTest
public class PessoaFisicaControllerTest {
	
	@InjectMocks
	private PessoaFisicaController controller;
	
	@Mock
	IPessoaFisicaRepository dao;
	
	@Test
	public void listarPessoaFisica() {
		
		PessoaFisica pessoa = new PessoaFisica(Long.valueOf("1"), "teste", "11111111111");
		List<PessoaFisica> lista = new ArrayList<PessoaFisica>();
		lista.add(pessoa);
		
		Mockito.when(dao.findAll()).thenReturn(lista);
		
		List<PessoaFisica> resultado = controller.listar();
		
		assertThat(resultado.size()).isEqualTo(1);
		assertThat(resultado.get(0).getNome()).isEqualTo("teste");
	}
}
