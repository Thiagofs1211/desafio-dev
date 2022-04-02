package com.bank.transacoes.model;


import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bank.transacoes.enums.TipoTransacaoEnum;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transacao")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data")
	@NotNull
	private Date data;
	
	@Column(name = "valor")
	@NotNull
	private Double valor;
	
	@Column(name = "cartao")
	private String cartao;
	
	@Column(name = "hora")
	@NotNull
	private Time hora;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	@NotNull
	private TipoTransacaoEnum tipo;
	
	@ManyToOne
	@JoinColumn(name = "estabelecimento", referencedColumnName = "id")
	private Estabelecimento estabelecimento;
}
