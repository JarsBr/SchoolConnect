package br.com.schoolconnect.projeto.model;

/**
 * Classe que representa uma disciplina.
 */
public class Disciplina {
	private String codDisciplina;
	private String nome;
	private String descricao;
	private String conteudo;
	private String cargaHoraria;
	
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
	@Override
	public String toString() {
		return "" + codDisciplina + "\t\t" + nome + "\t\t" + descricao;
	}
	
	
	
	
}
