package com.bank.transacoes.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.transacoes.model.Estabelecimento;

public interface IEstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

	@Query("SELECT est FROM Estabelecimento est JOIN est.dono d WHERE est.nome = :nome AND d.cpf = :cpf")
	Estabelecimento procuraEstabelecimentoPorNomeCPF(@Param("nome") String nome, @Param("cpf") String cpf);
}
