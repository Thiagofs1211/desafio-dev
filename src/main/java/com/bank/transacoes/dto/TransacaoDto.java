package com.bank.transacoes.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDto {

	private Double saldo;
	private List<DadosTransacaoDto> dadosTransacao;
}
