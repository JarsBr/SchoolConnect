package br.com.schoolconnect.projeto.model;

/**
 * Classe que cria uma disciplina para ser cadastrada em uma turma, ate o momento nao e possivel ter a mesma disciplina em 
 * mais de um turma.
 * @author Jars
 */
public class Disciplina {
	private int codDisciplina;
	private String nome;
	private String descricao;
	private String conteudo;
	private int cargaHoraria;
	public int getCodDisciplina() {
		return codDisciplina;
	}
	public void setCodDisciplina(int codDisciplina) {
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
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public Disciplina(int codDisciplina, String nome, String descricao, String conteudo, int cargaHoraria) {
		super();
		this.codDisciplina = codDisciplina;
		this.nome = nome;
		this.descricao = descricao;
		this.conteudo = conteudo;
		this.cargaHoraria = cargaHoraria;
	}
	
	
}
