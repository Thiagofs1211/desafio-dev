package com.bank.transacoes.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.transacoes.model.PessoaFisica;

public interface IPessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

	@Query("SELECT pes FROM PessoaFisica pes WHERE cpf=(:cpf)")
	PessoaFisica procuraPessoaPorCPF(@Param("cpf") String cpf);
}
