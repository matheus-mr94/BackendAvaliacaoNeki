package br.com.neki.mappers;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.com.neki.dtos.HabilidadeUsuarioDTO;
import br.com.neki.models.HabilidadeUsuario;

@Component
public class HabilidadeUsuarioMapper {

	public HabilidadeUsuario toEntity(HabilidadeUsuarioDTO dto) {
		HabilidadeUsuario userSkill = new HabilidadeUsuario();
		userSkill.setUsuario(dto.getUsuario());
		userSkill.setHabilidade(dto.getHabilidade());
		userSkill.setNivelHabilidade(dto.getNivelHabilidade());
		userSkill.setDataCriacao(LocalDate.now());
		userSkill.setDataAtualizacao(LocalDate.now());
		return userSkill;
	}

	public HabilidadeUsuarioDTO toDTO(HabilidadeUsuario userSkill) {
		HabilidadeUsuarioDTO dto = new HabilidadeUsuarioDTO();
		dto.setUsuario(userSkill.getUsuario());
		dto.setHabilidade(userSkill.getHabilidade());
		dto.setNivelHabilidade(userSkill.getNivelHabilidade());
		dto.setDataCriacao(userSkill.getDataCriacao());
		dto.setDataAtualizacao(LocalDate.now());
		return dto;
	}

}
