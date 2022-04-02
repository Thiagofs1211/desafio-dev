package com.bank.transacoes.irepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.transacoes.model.Transacao;

public interface ITransacao extends JpaRepository<Transacao, Long>{

	@Query("SELECT trans FROM Transacao trans WHERE trans.estabelecimento.id = :id")
	public List<Transacao> procuraTransacoesEstabelecimento(@Param("id") Long id);
}
