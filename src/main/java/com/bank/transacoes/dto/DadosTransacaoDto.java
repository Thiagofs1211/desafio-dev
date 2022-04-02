package com.bank.transacoes.dto;

import java.sql.Time;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosTransacaoDto {

	private Long idTransacao;
	private Date data;
	private Double valor;
	private String cartao;
	private Time hora;
	private String tipoTransacao;
	private String naturezaTransacao;
}
