package com.bank.transacoes.irepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.transacoes.model.Transacao;

public interface ITransacao extends JpaRepository<Transacao, Long>{

}
