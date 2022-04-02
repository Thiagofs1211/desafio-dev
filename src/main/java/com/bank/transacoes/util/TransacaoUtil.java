package com.bank.transacoes.util;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.bank.transacoes.enums.TipoTransacaoEnum;

public class TransacaoUtil {

	public static List<String> verificaTransacaoValida(String transacao) {
		if(transacao.length() != 80) {
			//Tamanho da Transacao Incorreto
			return null;
		}
		
		List<String> transacaoDividida = new ArrayList<String>();
		transacaoDividida.add(transacao.substring(0, 1)); //Tipo da Transacao
		transacaoDividida.add(transacao.substring(1, 9)); //Data da Ocorrencia
		transacaoDividida.add(transacao.substring(9, 19)); //Valor da Transacao
		transacaoDividida.add(transacao.substring(19, 30)); //CPF
		transacaoDividida.add(transacao.substring(30, 42)); //Cartao
		transacaoDividida.add(transacao.substring(42, 48)); //Hora
		transacaoDividida.add(transacao.substring(48, 62)); //Nome do dono da Loja
		transacaoDividida.add(transacao.substring(62, 80)); //Nome da Loja
		
		//As validações ficaram separadas pois se quisesse retornar especificando onde está o erro, será mais fácil
		if(TipoTransacaoEnum.getTipoTransacaoPorCodigo(transacaoDividida.get(0)) == null) { //Verifica se o tipo de transacao é valida
			//Tipo de Transacao Invalida
			return null;
		}
		if(!verificaValorNumero(transacaoDividida.get(1)) && !verificaFormatoDataValida(transacaoDividida.get(1))) { //Verifica se a data está em um formato valido
			//Data Invalida
			return null;
		}
		if(!verificaValorNumero(transacaoDividida.get(2))) {
			//Valor Invalido
			return null;
		}
		if(!verificaValorNumero(transacaoDividida.get(3))) { //Tambem é possivel adicionar um validador para verificar se o CPF informado é válido
			//CPF Invalido
			return null;
		}
		if(!verificaValorNumero(transacaoDividida.get(4).substring(0,4))
				&& !"****".equals(transacaoDividida.get(4).substring(4,8)) && !verificaValorNumero(transacaoDividida.get(4).substring(8,12))) {
			//Cartao Invalido
			return null;
		}
		if(!verificaFormatoHora(transacaoDividida.get(5))) {
			//Hora Invalida
			return null;
		}
		if(transacaoDividida.get(6).isBlank()) {
			//Nome do Dono da loja em branco
			return null;
		}
		if(transacaoDividida.get(7).isBlank()) {
			//Nome do Dono em branco
			return null;
		}
		return transacaoDividida;
	}
	
	public static boolean verificaValorNumero(String valor) {
		if(valor == null) {
			return false;
		} else {
			int tamanho = valor.length();
			for(int i = 0; i < tamanho; i++) {
				if(!Character.isDigit(valor.charAt(i))) {
					return false;
				}
			}
			return true;
		}
	}
	
	public static boolean verificaFormatoDataValida(String valor) {
		int ano = Integer.valueOf(valor.substring(0, 4));
		int mes = Integer.valueOf(valor.substring(4,6));
		int dia = Integer.valueOf(valor.substring(6,8));
		//Verifica se o ano é válido, considerando o minimo de 1500 e máximo a data atual
		if(ano < 1500 || ano > Year.now().getValue()) {
			return false;
		}
		//Verifica se o mês é válido
		if(mes < 1 ||  mes > 12) {
			return false;
		}
		//Verifica se o dia é válido
		if(dia < 1 ||  dia > 31) {
			return false;
		}
		return true;
	}
	
	public static boolean verificaFormatoHora(String valor) {
		int hora = Integer.valueOf(valor.substring(0,2));
		int minuto = Integer.valueOf(valor.substring(2,4));
		int segundo = Integer.valueOf(valor.substring(4,6));
		
		if(hora > 23) {
			return false;
		}
		if(minuto > 59) {
			return false;
		}
		if(segundo > 59) {
			return false;
		}
		return true;
	}
}
