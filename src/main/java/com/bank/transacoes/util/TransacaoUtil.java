package com.bank.transacoes.util;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bank.transacoes.dto.DadosTransacaoDto;
import com.bank.transacoes.dto.TransacaoDto;
import com.bank.transacoes.enums.TipoTransacaoEnum;
import com.bank.transacoes.model.Transacao;

public class TransacaoUtil {
	
	private final static String ENTRADA = "Entrada";
	
	private static final Logger logger = LoggerFactory.getLogger(TransacaoUtil.class);

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
			logger.error("Tipo da transacao Invalida: "+transacaoDividida.get(0));
			return null;
		}
		if(!verificaValorNumero(transacaoDividida.get(1)) || !verificaFormatoDataValida(transacaoDividida.get(1))) { //Verifica se a data está em um formato valido
			//Data Invalida
			logger.error("data da transacao Invalida: "+transacaoDividida.get(1));
			return null;
		}
		if(!verificaValorNumero(transacaoDividida.get(2))) {
			//Valor Invalido
			logger.error("Valor da transacao invalido: "+transacaoDividida.get(2));
			return null;
		}
		if(!verificaValorNumero(transacaoDividida.get(3))) { //Tambem é possivel adicionar um validador para verificar se o CPF informado é válido
			//CPF Invalido
			logger.error("CPF da transacao Invalida: "+transacaoDividida.get(3));
			return null;
		}
		if(!verificaValorNumero(transacaoDividida.get(4).substring(0,4))
				|| !"****".equals(transacaoDividida.get(4).substring(4,8)) || !verificaValorNumero(transacaoDividida.get(4).substring(8,12))) {
			//Cartao Invalido
			logger.error("Cartao da transacao Invalida: "+transacaoDividida.get(4));
			return null;
		}
		if(!verificaFormatoHora(transacaoDividida.get(5))) {
			//Hora Invalida
			logger.error("Hora da transacao Invalida: "+transacaoDividida.get(5));
			return null;
		}
		if(transacaoDividida.get(6).isBlank()) {
			//Nome do Dono da loja em branco
			logger.error("Nome do dono da loja da transacao em branco");
			return null;
		}
		if(transacaoDividida.get(7).isBlank()) {
			//Nome do Dono em branco
			logger.error("Nome da loja da transacao em branco");
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
	
	/**
	 * Método para fazer a conversão de um modelo Transacao para um dto Transacao
	 * @param listModel
	 * @return
	 */
	public static TransacaoDto converteModelDto(List<Transacao> listModel) {
		List<DadosTransacaoDto> listDto = new ArrayList<DadosTransacaoDto>();
		TransacaoDto transacao = new TransacaoDto();
		Double saldo = 0.0;
		for(Transacao model : listModel) {
			DadosTransacaoDto dto = new DadosTransacaoDto();
			dto.setCartao(model.getCartao());
			dto.setData(model.getData());
			dto.setHora(model.getHora());
			dto.setIdTransacao(model.getId());
			dto.setTipoTransacao(model.getTipo().getDescricao());
			dto.setValor(model.getValor());
			dto.setNaturezaTransacao(model.getTipo().getNatureza());
			listDto.add(dto);
			if(ENTRADA.equals(model.getTipo().getNatureza())) {
				saldo += model.getValor();
			} else {
				saldo -= model.getValor();
			}
		}
		transacao.setDadosTransacao(listDto);
		transacao.setSaldo(saldo);
		return transacao;
	}
}
