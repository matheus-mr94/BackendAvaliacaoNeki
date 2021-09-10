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

import br.com.neki.dtos.HabilidadeUsuarioDTO;
import br.com.neki.exceptions.HabilidadeUsuarioException;
import br.com.neki.exceptions.HabilidadeUsuarioNotFoundException;
import br.com.neki.models.HabilidadeUsuario;
import br.com.neki.services.HabilidadeUsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin("*")
@Api("API - Relação Habilidade Usuário")
@RestController
@RequestMapping("/userskill")
public class HabilidadeUsuarioController {
	
	@Autowired
	HabilidadeUsuarioService habilidadeUsuarioService;
	
	@ApiOperation(value = "Criação da relação entre usuário e habilidade")
	@PostMapping
	public ResponseEntity<String> create(@Validated @RequestBody HabilidadeUsuarioDTO dto) throws HabilidadeUsuarioException{
		habilidadeUsuarioService.create(dto);
		return new ResponseEntity<>("Relação feita com sucesso", HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Busca por todas relações")
	@GetMapping("/all")
	public ResponseEntity<List<HabilidadeUsuario>> getAll(){
		return new ResponseEntity<List<HabilidadeUsuario>>(habilidadeUsuarioService.getAll(),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Busca por relação específica por ID")
	@GetMapping("/{id}")
	public ResponseEntity<HabilidadeUsuario> getById(@PathVariable Integer id) throws HabilidadeUsuarioNotFoundException{
		return new ResponseEntity<HabilidadeUsuario>(habilidadeUsuarioService.getById(id),HttpStatus.OK);
	}
	
	@ApiOperation(value = "Atualização da relação entre usuário e habilidade")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Integer id,@Validated @RequestBody HabilidadeUsuarioDTO dto) throws HabilidadeUsuarioException{
		habilidadeUsuarioService.update(id, dto);
		return new ResponseEntity<>("Relação atualizada com sucesso",HttpStatus.ACCEPTED);
	}
	
	@ApiOperation(value = "Desassociação de relação entre habilidade e usuário")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) throws HabilidadeUsuarioNotFoundException{
		return new ResponseEntity<String>(habilidadeUsuarioService.delete(id) ,HttpStatus.ACCEPTED);
	}
}
