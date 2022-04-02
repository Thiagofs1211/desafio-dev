package com.bank.transacoes.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.transacoes.dto.EstabelecimentoDto;
import com.bank.transacoes.service.EstabelecimentoService;

@SpringBootTest
public class EstabelecimentoControllerTest {

	@InjectMocks
	private EstabelecimentoController controller;
	
	@Mock
	EstabelecimentoService service;
	
	@Test
	public void listarEstabelecimentos() {
		EstabelecimentoDto estabelecimento = new EstabelecimentoDto(Long.valueOf("1"), "teste", "teste2", "11111111111");
		List<EstabelecimentoDto> lista = new ArrayList<>();
		lista.add(estabelecimento);
		
		Mockito.when(service.buscarEstabelecimentos()).thenReturn(lista);
		
		List<EstabelecimentoDto> resultado = controller.listarEstabelecimento();
		
		assertThat(resultado.size()).isEqualTo(1);
		assertThat(resultado.get(0).getNomeDonoEstabelecimento()).isEqualTo("teste2");
	}
	
}
