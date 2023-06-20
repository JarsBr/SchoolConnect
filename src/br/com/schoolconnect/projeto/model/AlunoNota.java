package br.com.schoolconnect.projeto.model;

public class AlunoNota {

	
	private String nomeAluno;
	private String codAluno;
	private String nomeDisciplina;
	private String codDisciplina;
	private String codDisciplinaOfertada;
	private float nota;
	
	
	public String getCodDisciplinaOfertada() {
		return codDisciplinaOfertada;
	}
	public void setCodDisciplinaOfertada(String codDisciplinaOfertada) {
		this.codDisciplinaOfertada = codDisciplinaOfertada;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getCodAluno() {
		return codAluno;
	}
	public void setCodAluno(String codAluno) {
		this.codAluno = codAluno;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	public String getCodDisciplina() {
		return codDisciplina;
	}
	public void setCodDisciplina(String codDisciplina) {
		this.codDisciplina = codDisciplina;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float f) {
		this.nota = f;
	}
	@Override
	public String toString() {
		return codDisciplinaOfertada + "\t\t" + nomeAluno + "\t\t" + nomeDisciplina
				+ "\t\t" + nota;
	}

	
	
}
