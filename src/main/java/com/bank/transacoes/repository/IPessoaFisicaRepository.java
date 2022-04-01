package com.bank.transacoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.transacoes.model.PessoaFisica;

public interface IPessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

}
