package com.bank.transacoes.service;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.transacoes.dto.EstabelecimentoDto;
import com.bank.transacoes.irepository.IEstabelecimentoRepository;
import com.bank.transacoes.model.Estabelecimento;
import com.bank.transacoes.model.PessoaFisica;

@SpringBootTest
public class EstabelecimentoServiceTest {
	
	@InjectMocks
	EstabelecimentoService service;
	
	@Mock
	IEstabelecimentoRepository dao;
	
	@Test
	public void buscarEstabelecimentos() {
		Estabelecimento estab = new Estabelecimento(Long.valueOf("1"), "teste", null, new PessoaFisica());
		List<Estabelecimento> lista = new ArrayList<>();
		lista.add(estab);
		
		Mockito.when(dao.findAll()).thenReturn(lista);
		
		List<EstabelecimentoDto> resultadoDtos = service.buscarEstabelecimentos();
		
		assertThat(resultadoDtos.size()).isEqualTo(1);
		assertThat(resultadoDtos.get(0).getIdEstabelecimento()).isEqualTo(Long.valueOf("1"));
	}
	
	@Test
	public void salvarEstabelecimento() {
		service.salvarEstabelecimento(new Estabelecimento());
	}
	
	@Test
	public void buscarEstabelecimentoNomeCPF() {
		Estabelecimento estab = new Estabelecimento();
		estab.setId(Long.valueOf("1"));
		Mockito.when(dao.procuraEstabelecimentoPorNomeCPF(Mockito.anyString(), Mockito.anyString())).thenReturn(estab);
		
		Estabelecimento resultado = service.buscarEstabelecimentoPorNomeCPF("teste", "teste");
		
		assertThat(resultado.getId()).isEqualTo(Long.valueOf("1"));
	}

}
