package br.com.neki.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.neki.dtos.LoginRespostaDTO;
import br.com.neki.dtos.UsuarioDTO;
import br.com.neki.dtos.UsuarioDetalheDTO;
import br.com.neki.exceptions.EmailOrPasswordNotValidException;
import br.com.neki.exceptions.UsuarioException;
import br.com.neki.exceptions.UsuarioNotFoundException;
import br.com.neki.mappers.UsuarioMapper;
import br.com.neki.models.Usuario;
import br.com.neki.repositories.UsuarioRepository;
import br.com.neki.security.JWTService;

@Service
public class UsuarioService {
	private static final String headerPrefix = "Bearer ";

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioMapper usuarioMapper;

	@Autowired
	JWTService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public Usuario findById(Integer id) throws UsuarioNotFoundException {
		return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));
	}

	public Usuario create(UsuarioDTO dto) throws UsuarioException {
		try {
			Usuario user = usuarioMapper.toEntity(dto);
			return usuarioRepository.save(user);
		} catch (Exception e) {
			throw new UsuarioException("Erro ao cadastrar usuário");
		}
	}

	public List<UsuarioDetalheDTO> getAll() {
		return usuarioRepository.findAll().stream().map(usuarioMapper::toDetalheDTO).collect(Collectors.toList());
	}

	public UsuarioDetalheDTO getById(Integer id) throws UsuarioNotFoundException {
		return usuarioRepository.findById(id).map(usuarioMapper::toDetalheDTO)
				.orElseThrow(() -> new UsuarioNotFoundException("Usuário não localizado"));
	}

	public Usuario update(Integer id, UsuarioDTO dto) throws UsuarioException {
		try {
			Usuario user = this.findById(id);
			user.setUsuario(dto.getUsuario());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCodificada = encoder.encode(user.getSenha());
			user.setSenha(senhaCodificada);
			user.setHabilidades(dto.getHabilidades());
			return usuarioRepository.save(user);
		} catch (Exception e) {
			throw new UsuarioException("Não foi possível atualizar o usuário");
		}
	}

	public String delete(Integer id) throws UsuarioException {
		try {
			usuarioRepository.deleteById(id);
			return "Usuário removido com sucesso";
		} catch (Exception e) {
			throw new UsuarioException("Não foi possível remover o usuário");
		}
	}

	public LoginRespostaDTO logar(String usuario, String senha) throws EmailOrPasswordNotValidException {

		Authentication autenticacao = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usuario, senha, Collections.emptyList()));

		SecurityContextHolder.getContext().setAuthentication(autenticacao);

		String token = headerPrefix + jwtService.gerarToken(autenticacao);

		var user = usuarioRepository.findByUsuario(usuario);
		user.get().setDataLogin(LocalDate.now());
		usuarioRepository.save(user.get());

		return new LoginRespostaDTO(token, user.get());
	}

}
