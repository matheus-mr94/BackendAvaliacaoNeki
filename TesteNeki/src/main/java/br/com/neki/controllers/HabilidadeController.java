package br.com.neki.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.dtos.HabilidadeDTO;
import br.com.neki.exceptions.HabilidadeException;
import br.com.neki.exceptions.HabilidadeNotFoundException;
import br.com.neki.models.Habilidade;
import br.com.neki.services.HabilidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@Api("API - Habilidades")
@RestController
@RequestMapping("/skill")
public class HabilidadeController {

	@Autowired
	HabilidadeService habilidadeService;

	@ApiOperation(value = "Criação de Habilidades")
	@PostMapping
	public ResponseEntity<String> create(@Validated @RequestBody HabilidadeDTO dto) throws HabilidadeException {
		habilidadeService.create(dto);
		return new ResponseEntity<>("Habilidade criada com sucesso", HttpStatus.CREATED);
	}

	@ApiOperation(value = "Busca por todas as habilidades")
	@GetMapping("/all")
	public ResponseEntity<List<Habilidade>> getAll() {
		return new ResponseEntity<List<Habilidade>>(habilidadeService.getAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Busca de habilidade específica por ID")
	@GetMapping("/{id}")
	public ResponseEntity<HabilidadeDTO> getById(@PathVariable Integer id) throws HabilidadeNotFoundException {
		return new ResponseEntity<HabilidadeDTO>(habilidadeService.getById(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Atualização das informações da habilidade")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Integer id, @Validated @RequestBody HabilidadeDTO dto)
			throws HabilidadeException {
		habilidadeService.update(id, dto);
		return new ResponseEntity<>("Habilidade atualizada com sucesso", HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "Exclusão de habilidades")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) throws HabilidadeException {
		return new ResponseEntity<>(habilidadeService.delete(id), HttpStatus.ACCEPTED);
	}

}
