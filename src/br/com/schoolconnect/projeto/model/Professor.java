package br.com.schoolconnect.projeto.model;

public class Professor {
	private String matricula;
	private String nome;
	private String email;
	private String graus;
	private String curriculo;
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
	public String getGraus() {
		return graus;
	}
	public void setGraus(String graus) {
		this.graus = graus;
	}
	public String getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(String curriculo) {
		this.curriculo = curriculo;
	}
	@Override
	public String toString() {
		return "" + matricula + "\t\t" + nome + "\t\t" + email;
	}
	public void atualizarInformacoes(String grausAcademicos, String curriculo) {
		this.graus = grausAcademicos;
		this.curriculo = curriculo;
	}


}
