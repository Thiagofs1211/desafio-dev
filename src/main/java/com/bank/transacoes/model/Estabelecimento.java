package com.bank.transacoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_estabelecimento")
	@NotNull
	private String nome;
	
	@Column(name = "cnpj")
	private String cnpj;	//Não sera utilizado mas achei importante indicar que tem esse campo
	
	@ManyToOne
	@JoinColumn(name = "dono_estabelecimento", referencedColumnName = "id")
	private PessoaFisica dono;
}
