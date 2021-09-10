package br.com.neki.dtos;

import java.time.LocalDate;
import java.util.List;

import br.com.neki.models.HabilidadeUsuario;

public class UsuarioDetalheDTO {

	private Integer id;
	private String usuario;
	private LocalDate dataLogin;
	private List<HabilidadeUsuario> habilidades;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
