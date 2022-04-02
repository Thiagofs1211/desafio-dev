package com.bank.transacoes.util;

import java.util.ArrayList;
import java.util.List;

import com.bank.transacoes.dto.EstabelecimentoDto;
import com.bank.transacoes.model.Estabelecimento;

public class EstabelecimentoUtil {

	public static List<EstabelecimentoDto> converteModelDto(List<Estabelecimento> listModel) {
		List<EstabelecimentoDto> listDto = new ArrayList<EstabelecimentoDto>();
		for(Estabelecimento model : listModel) {
			EstabelecimentoDto dto = new EstabelecimentoDto();
			dto.setCpfDonoEstabelecimento(model.getDono().getCpf());
			dto.setNomeDonoEstabelecimento(model.getDono().getNome());
			dto.setNomeEstabelecimento(model.getNome());
			dto.setIdEstabelecimento(model.getId());
			listDto.add(dto);
		}
		return listDto;
	}
}
