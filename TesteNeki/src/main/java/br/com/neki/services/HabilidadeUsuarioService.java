package br.com.neki.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.dtos.HabilidadeUsuarioDTO;
import br.com.neki.exceptions.HabilidadeUsuarioException;
import br.com.neki.exceptions.HabilidadeUsuarioNotFoundException;
import br.com.neki.mappers.HabilidadeUsuarioMapper;
import br.com.neki.models.HabilidadeUsuario;
import br.com.neki.repositories.HabilidadeUsuarioRepository;

@Service
public class HabilidadeUsuarioService {

	@Autowired
	HabilidadeUsuarioRepository habilidadeUsuarioRepository;

	@Autowired
	HabilidadeUsuarioMapper habilidadeUsuarioMapper;

	@Autowired
	HabilidadeService habilidadeService;

	@Autowired
	UsuarioService usuarioService;

	public HabilidadeUsuario findById(Integer id) throws HabilidadeUsuarioNotFoundException {
		return habilidadeUsuarioRepository.findById(id)
				.orElseThrow(() -> new HabilidadeUsuarioNotFoundException("Relação não encontrada"));
	}

	public HabilidadeUsuario create(HabilidadeUsuarioDTO dto) throws HabilidadeUsuarioException {
		try {
			HabilidadeUsuario userSkill = habilidadeUsuarioMapper.toEntity(dto);
			return habilidadeUsuarioRepository.save(userSkill);
		} catch (Exception e) {
			throw new HabilidadeUsuarioException("Erro ao relacionar");
		}
	}

	public List<HabilidadeUsuario> getAll() {
		return habilidadeUsuarioRepository.findAll();
	}

	public HabilidadeUsuario getById(Integer id) throws HabilidadeUsuarioNotFoundException {
		return habilidadeUsuarioRepository.findById(id)
				.orElseThrow(() -> new HabilidadeUsuarioNotFoundException("Relação não encontrada"));
	}

	public HabilidadeUsuario update(Integer id, HabilidadeUsuarioDTO dto) throws HabilidadeUsuarioException {
		try {
			HabilidadeUsuario userSkill = this.findById(id);
			userSkill.setUsuario(dto.getUsuario());
			userSkill.setHabilidade(dto.getHabilidade());
			userSkill.setNivelHabilidade(dto.getNivelHabilidade());
			userSkill.setDataCriacao(dto.getDataCriacao());
			userSkill.setDataAtualizacao(LocalDate.now());
			return habilidadeUsuarioRepository.save(userSkill);
		} catch (Exception e) {
			throw new HabilidadeUsuarioException("Erro ao atualizar informações");
		}

	}

	public String delete(Integer id) throws HabilidadeUsuarioNotFoundException {
		try {
			habilidadeUsuarioRepository.deleteById(id);
			return "Habilidade desassociada com sucesso";
		} catch (Exception e) {
			throw new HabilidadeUsuarioNotFoundException("Relação não encontrada");
		}
	}
}
