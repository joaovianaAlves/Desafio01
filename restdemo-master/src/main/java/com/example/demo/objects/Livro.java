package com.example.demo.objects;

import org.springframework.stereotype.Component;

@Component
public class Livro {

	private String titulo;
	private String autor;
	private String editora;
	private String anoedicao;
	private String localizacao;

	public Livro() {
		super();
	}

	public void setTitulo(String _titulo) {
		titulo = _titulo;
	}

	public void setAutor(String _autor) {
		autor = _autor;
	}

	public void setEditora(String _editora) {
		editora = _editora;
	}

	public void setAnoEdicao(String _anoedicao) {
		anoedicao = _anoedicao;
	}

	public void setLocalizacao(String _localizacao) {
		localizacao = _localizacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditora() {
		return editora;
	}

	public String getAnoEdicao() {
		return anoedicao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	@Override
	public String toString() {
		return "Livro{" +
				"titulo='" + titulo + '\'' +
				", autor='" + autor + '\'' +
				", editora='" + editora + '\'' +
				", anoedicao='" + anoedicao + '\'' +
				", localizacao='" + localizacao + '\'' +
				'}';
	}
}
