package Mains;

import br.com.schoolconnect.projeto.model.Aluno;

public class MainLibrary {
public static void main(String[] args) {
	Aluno aluno01 = new Aluno(001, "jao", 24, 2, "99228922", "jao@gmail.com", "faz programa", "2022", 8, 3);
	System.out.println(aluno01.toString());
}
}
