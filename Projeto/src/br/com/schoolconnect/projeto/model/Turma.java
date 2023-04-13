package br.com.schoolconnect.projeto.model;

import java.util.ArrayList;

public class Turma {
	ArrayList<Disciplina> disciplinaTurma = new ArrayList<Disciplina>();
	private int idTurma;
	private int numero;
	private int QuantAluno;
	public int getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getQuantAluno() {
		return QuantAluno;
	}
	public void setQuantAluno(int quantAluno) {
		QuantAluno = quantAluno;
	}
	public Turma(int idTurma, int numero, int quantAluno) {
		super();
		this.idTurma = idTurma;
		this.numero = numero;
		QuantAluno = quantAluno;
	}
	public void cadastrarDisciplinaTurma() {
		String nome = "disciplina01", conteudo = "conteudo";
		int carga = 123, periodo = 1;
		disciplinaTurma.add(new Disciplina(nome, carga, periodo, conteudo));
		disciplinaTurma.add(new Disciplina(nome, carga, periodo, conteudo));
		disciplinaTurma.add(new Disciplina(nome, carga, periodo, conteudo));
		disciplinaTurma.add(new Disciplina(nome, carga, periodo, conteudo));
	}
	public void mostrarDisciplinaTurma() {
		for(Disciplina p: disciplinaTurma) {
			System.out.println(p.getNome() + "" + p.getCargaHoraria());
		}
		
		
		
	}
	
	
	
}
