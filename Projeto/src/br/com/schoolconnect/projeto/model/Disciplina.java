package br.com.schoolconnect.projeto.model;

public class Disciplina {
	private String nome;
	private int cargaHoraria;
	private int periodo;
	private String conteudo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Disciplina(String nome, int cargaHoraria, int periodo, String conteudo) {
		super();
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.periodo = periodo;
		this.conteudo = conteudo;
	}

}
