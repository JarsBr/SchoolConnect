package br.com.schoolconnect.projeto.model;

public class Aluno {

	private String matricula;
	private String nome;
	private String email;
	private String id_curso_periodo;
	private String data_inicio;
	private String situacao;

	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula2) {
		this.matricula = matricula2;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId_curso_periodo() {
		return id_curso_periodo;
	}
	public void setId_curso_periodo(String id_curso_periodo) {
		this.id_curso_periodo = id_curso_periodo;
	}
	public String getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	@Override
	public String toString() {
		return "" + matricula + "\t" + nome + "\t" + email;
	}
	
	
}
