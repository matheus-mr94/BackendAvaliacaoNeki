package br.com.neki.mappers;

import org.springframework.stereotype.Component;

import br.com.neki.dtos.HabilidadeDTO;
import br.com.neki.models.Habilidade;

@Component
public class HabilidadeMapper {

	public Habilidade toEntity(HabilidadeDTO dto) {
		Habilidade skill = new Habilidade();
		skill.setNome(dto.getNome());
		skill.setDescricao(dto.getDescricao());
		skill.setImagem(dto.getImagem());
		skill.setVersao(dto.getVersao());
		return skill;
	}

	public HabilidadeDTO toDTO(Habilidade skill) {
		HabilidadeDTO dto = new HabilidadeDTO();
		dto.setNome(skill.getNome());
		dto.setDescricao(skill.getDescricao());
		dto.setImagem(skill.getImagem());
		dto.setVersao(skill.getVersao());
		return dto;
	}

}
