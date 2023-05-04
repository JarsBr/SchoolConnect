package br.com.schoolconnect.projeto.model;

import java.util.ArrayList;

/**
 * Classe que cria uma turma, uma turma vai ter varias disciplinas e sera posivel cadastrar disciplinas depois
 * da classe ja ser criada.
 * @author Jars
 */
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
	public void cadastrarDisciplinaTurma(String nome, int carga, int periodo, String conteudo) {//METODO QUE CADASTRA A DISCIPLINA NA TURMA
		disciplinaTurma.add(new Disciplina(nome, carga, periodo, conteudo));
	}
	public void mostrarDisciplinaTurma(String nomeTurma) {//METODO QUE MOSTRAS TODAS AS DISCIPLINAS DE UM TURMA
		for(Disciplina p: disciplinaTurma) {
			
			System.out.println("\nREGISTRO DE DICIPLINA: <"+nomeTurma+">"+"\nNome: "+p.getNome() + "\nCarga Horaria: " + p.getCargaHoraria() + "\nPeriodo Referente: " + p. getPeriodo());
		}
		System.out.println("");
	}
	public void buscarDisciplinaTurma(String nomeBusca, String nomeTurma) {
		boolean find = false;
		System.out.println("Resultados da busca: "+ "\""+nomeBusca+ "\"");
		for(Disciplina p: disciplinaTurma) {
			if(p.getNome().equals(nomeBusca)) {
				find = true;
				System.out.println("\nREGISTRO DE DICIPLINA: <"+nomeTurma+">"+"\nNome: "+p.getNome() + "\nCarga Horaria: " + p.getCargaHoraria() + "\nPeriodo Referente: " + p. getPeriodo());
			}
		}
		if(!find) {
		System.out.println("\nNenhum Registro Encontrado");
		}
	}
	
	
	
}
