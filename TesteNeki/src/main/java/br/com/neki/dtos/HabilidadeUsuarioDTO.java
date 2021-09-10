package br.com.neki.dtos;

import java.time.LocalDate;

import br.com.neki.models.Habilidade;
import br.com.neki.models.Usuario;

public class HabilidadeUsuarioDTO {

	private Usuario usuario;
	private Habilidade habilidade;
	private Integer nivelHabilidade;
	private LocalDate dataCriacao;
	private LocalDate dataAtualizacao;

	public Integer getNivelHabilidade() {
		return nivelHabilidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Habilidade getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(Habilidade habilidade) {
		this.habilidade = habilidade;
	}

	public void setNivelHabilidade(Integer nivelHabilidade) {
		this.nivelHabilidade = nivelHabilidade;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDate dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

}
