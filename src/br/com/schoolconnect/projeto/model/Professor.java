package br.com.schoolconnect.projeto.model;

public class Professor extends Pessoa{
	private String historico;
	private String diplomas;
	//Disciplinas dadas por professor
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	public String getDiplomas() {
		return diplomas;
	}
	public void setDiplomas(String diplomas) {
		this.diplomas = diplomas;
	}
	public Professor(int matricula, String nome, int idade, String cpf, String telefone, String email, String historico,
			String diplomas) {
		super(matricula, nome, idade, cpf, telefone, email);
		this.historico = historico;
		this.diplomas = diplomas;
	}
	@Override
	public String toString() {
		return "Professor [historico=" + historico + ", diplomas=" + diplomas + ", getMatricula()=" + getMatricula()
				+ ", getNome()=" + getNome() + ", getIdade()=" + getIdade() + ", getCpf()=" + getCpf()
				+ ", getTelefone()=" + getTelefone() + ", getEmail()=" + getEmail() + "]";
	}

	
	
}
