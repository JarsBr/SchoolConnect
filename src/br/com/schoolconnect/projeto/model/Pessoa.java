package br.com.schoolconnect.projeto.model;

import javax.sound.midi.Soundbank;

import org.w3c.dom.ls.LSOutput;

public class Pessoa {
	private int matricula;
	private String nome;
	private int idade;
	private String cpf;
	private String telefone;
	private String email;
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Pessoa(int matricula, String nome, int idade, String cpf, String telefone, String email) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
	}
 
	 
 }


