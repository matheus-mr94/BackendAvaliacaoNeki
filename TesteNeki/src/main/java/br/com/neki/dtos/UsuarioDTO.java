package br.com.neki.dtos;

import java.time.LocalDate;
import java.util.List;

import br.com.neki.models.HabilidadeUsuario;

public class UsuarioDTO {

	private String usuario;
	private String senha;
	private LocalDate dataLogin;
	private List<HabilidadeUsuario> habilidades;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(LocalDate dataLogin) {
		this.dataLogin = dataLogin;
	}

	public List<HabilidadeUsuario> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<HabilidadeUsuario> habilidades) {
		this.habilidades = habilidades;
	}

}
