package br.com.neki.security;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.neki.models.Usuario;
import br.com.neki.repositories.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String usuario) {
		Usuario user = getUser(() -> usuarioRepository.findByUsuario(usuario));
		return user;
	}

	public UserDetails pegarUsuarioPorId(Integer id) {
		Usuario user = getUser(() -> usuarioRepository.findById(id));
		return user;
	}

	private Usuario getUser(Supplier<Optional<Usuario>> supplier) {
		return supplier.get().orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}

}
