package com.bank.transacoes.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.bank.transacoes.dto.TransacaoDto;
import com.bank.transacoes.service.TransacaoService;

@SpringBootTest
public class TransacaoControllerTest {

	@InjectMocks
	TransacaoController controller;
	
	@Mock
	TransacaoService service;
	
	@Test
	public void salvarTransacao() {
		HttpStatus response = controller.salvarTransacoes();
		assertThat(response).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void buscarTransacaoPorId() {
		TransacaoDto dto = new TransacaoDto();
		dto.setSaldo(10.0);
		
		Mockito.when(service.buscarTransacoesEstabelecimento(Mockito.anyLong())).thenReturn(dto);
		
		TransacaoDto resultado = controller.buscarTransacaoPorId(Long.valueOf(1));
		
		assertThat(resultado.getSaldo()).isEqualTo(10.0);
	}
}
