package br.com.neki.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.dtos.LoginRequisicaoDTO;
import br.com.neki.dtos.LoginRespostaDTO;
import br.com.neki.exceptions.EmailOrPasswordNotValidException;
import br.com.neki.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@Api("API - Login")
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@ApiOperation(value = "Requisição de login")
	@PostMapping
	public LoginRespostaDTO login(@RequestBody LoginRequisicaoDTO request) throws EmailOrPasswordNotValidException {
		return usuarioService.logar(request.getUsuario(), request.getSenha());
	}

}