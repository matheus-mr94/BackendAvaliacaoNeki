package br.com.neki.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.dtos.HabilidadeDTO;
import br.com.neki.exceptions.HabilidadeException;
import br.com.neki.exceptions.HabilidadeNotFoundException;
import br.com.neki.mappers.HabilidadeMapper;
import br.com.neki.models.Habilidade;
import br.com.neki.repositories.HabilidadeRepository;

@Service
public class HabilidadeService {

	@Autowired
	HabilidadeMapper habilidadeMapper;

	@Autowired
	HabilidadeRepository habilidadeRepository;

	public Habilidade findById(Integer id) throws HabilidadeNotFoundException {
		return habilidadeRepository.findById(id)
				.orElseThrow(() -> new HabilidadeNotFoundException("Habilidade com " + id + " não encontrada"));
	}

	public Habilidade create(HabilidadeDTO dto) throws HabilidadeException {
		try {
			Habilidade skill = habilidadeMapper.toEntity(dto);
			return habilidadeRepository.save(skill);
		} catch (Exception e) {
			throw new HabilidadeException("Erro ao cadastrar habilidade");
		}
	}

	public List<Habilidade> getAll() {
		return habilidadeRepository.findAll();
	}

	public HabilidadeDTO getById(Integer id) throws HabilidadeNotFoundException {
		return habilidadeRepository.findById(id).map(habilidadeMapper::toDTO)
				.orElseThrow(() -> new HabilidadeNotFoundException("Habilidade com " + id + " não encontrada"));
	}

	public Habilidade update(Integer id, HabilidadeDTO dto) throws HabilidadeException {
		try {
			Habilidade skill = this.findById(id);
			skill.setNome(dto.getNome());
			skill.setDescricao(dto.getDescricao());
			skill.setImagem(dto.getImagem());
			skill.setVersao(dto.getVersao());
			return habilidadeRepository.save(skill);

		} catch (Exception e) {
			throw new HabilidadeException("Não foi possível atualizar as informações da habilidade");
		}
	}

	public String delete(Integer id) throws HabilidadeException {
		try {
			habilidadeRepository.deleteById(id);
			return "Habilidade excluída";
		} catch (Exception e) {
			throw new HabilidadeException("Não foi remover a habilidade do sistema");
		}
	}
}
