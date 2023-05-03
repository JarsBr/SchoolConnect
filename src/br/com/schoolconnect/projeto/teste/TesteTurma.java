package br.com.schoolconnect.projeto.teste;

import br.com.schoolconnect.projeto.model.Turma;

public class TesteTurma {
public static void main(String[] args) {
	Turma turmateste = new Turma(01, 2023, 10);
	turmateste.cadastrarDisciplinaTurma();
	turmateste.mostrarDisciplinaTurma();
}
}
