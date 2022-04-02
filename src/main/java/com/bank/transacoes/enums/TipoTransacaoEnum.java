package com.bank.transacoes.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/*Representa os tipos de transacoes possiveis
 * 
 * 1 - Debito
 * 2 - Boleto
 * 3 - Financiamento
 * 4 - Credito
 * 5 - Recebimento Emprestimo
 * 6 - Vendas
 * 7 - Recebimento TED
 * 8 - Recebimento DOC
 * 9 - Aluguel
 */
@Getter
public enum TipoTransacaoEnum {
	DEBITO("1", "Débito", "Entrada"),
	BOLETO("2", "Boleto", "Saida"),
	FINANCIAMENTO("3", "Financiamento", "Saida"),
	CREDITO("4", "Credito", "Entrada"),
	RECEBIMENTO_EMPRESTIMO("5", "Recebimento Emprestimo", "Entrada"),
	VENDAS("6", "Vendas", "Entrada"),
	RECEBIMENTO_TED("7", "Recebimento TED", "Entrada"),
	RECEBIMENTO_DOC("8", "Recebimento_DOC", "Entrada"),
	ALUGUEL("9", "Aluguel", "Saida");
	
	private String tipo;
	private String descricao;
	private String natureza;
	
	TipoTransacaoEnum(String tipo, String descricao, String natureza) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.natureza = natureza;
	}
	
	/*
	 * Método para mostrar todos os tipos de transacao possivel
	 * Provavelmente não irei utilizar mas em uma aplicação real pode ser interessante para mostrar em algum campo por exemplo
	 */
	public static List<TipoTransacaoEnum> getTipos() {
		List<TipoTransacaoEnum> tipos = new ArrayList<TipoTransacaoEnum>();
		tipos.addAll(Arrays.asList(values()));
		return tipos;
	}
	
	/**
	 * Método para recuperar um Tipo pelo codigo passado, caso não encontre retorne null
	 * @param tipo
	 * @return
	 */
	public static TipoTransacaoEnum getTipoTransacaoPorCodigo(String tipo) {
		TipoTransacaoEnum tipoTransacao = null;
		for(TipoTransacaoEnum transacao : values()) {
			if(tipo.equals(transacao.getTipo())) {
				tipoTransacao = transacao;
				break;
			}
		}
		return tipoTransacao;
	}
}
