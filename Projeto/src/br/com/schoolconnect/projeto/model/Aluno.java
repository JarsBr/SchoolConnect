package br.com.schoolconnect.projeto.model;

public class Aluno extends Pessoa{
	private String curso;
	private String matriz;
	private int totalPeriodo;
	private int periodoAtual;
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getMatriz() {
		return matriz;
	}
	public void setMatriz(String matriz) {
		this.matriz = matriz;
	}
	public int getTotalPeriodo() {
		return totalPeriodo;
	}
	public void setTotalPeriodo(int totalPeriodo) {
		this.totalPeriodo = totalPeriodo;
	}
	public int getPeriodoAtual() {
		return periodoAtual;
	}
	public void setPeriodoAtual(int periodoAtual) {
		this.periodoAtual = periodoAtual;
	}
	public Aluno(int matricula, String nome, int idade, int cpf, String telefone, String email, String curso,
			String matriz, int totalPeriodo, int periodoAtual) {
		super(matricula, nome, idade, cpf, telefone, email);
		this.curso = curso;
		this.matriz = matriz;
		this.totalPeriodo = totalPeriodo;
		this.periodoAtual = periodoAtual;
	}
	@Override
	public String toString() {
		return "Aluno [curso=" + curso + ", matriz=" + matriz + ", totalPeriodo=" + totalPeriodo + ", periodoAtual="
				+ periodoAtual + ", getMatricula()=" + getMatricula() + ", getNome()=" + getNome() + ", getIdade()="
				+ getIdade() + ", getCpf()=" + getCpf() + ", getTelefone()=" + getTelefone() + ", getEmail()="
				+ getEmail() + "]";
	}

	
}
