package br.com.schoolconnect.projeto.model;

/**
 * Classe que representa uma disciplina a cadastrar nota.
 */

public class DisciplinaNota {
	private String codDisciplina;
	private String nome;
	private String descricao;
	private String conteudo;
	private String cargaHoraria;
	private float nota;
	
	// métodos getters e setters
	public String getCodDisciplina() {
		return codDisciplina;
	}
	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(String string) {
		this.cargaHoraria = string;
	}
	
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	@Override
	public String toString() {
		return "  " + nota + "\t\t\t" + codDisciplina + "\t\t\t" + nome;
	}
	
	
	
	
}
