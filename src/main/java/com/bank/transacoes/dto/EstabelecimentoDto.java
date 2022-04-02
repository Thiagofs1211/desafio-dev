package com.bank.transacoes.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * Dto para buscar dados para a escolha de estabelecimento na tela
 */
@Getter
@Setter
public class EstabelecimentoDto {
	
	private Long idEstabelecimento;
	private String nomeEstabelecimento;
	private String nomeDonoEstabelecimento;
	private String cpfDonoEstabelecimento;

}
