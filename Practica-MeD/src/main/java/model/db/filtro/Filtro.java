/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.model.db.filtro;

/**
 *
 * @author Urist
 */
public class Filtro {

	private Long id;
	private String nome;
	private String expresion;

	public Filtro() {

	}

	public Filtro(String nome, String expresion) {
		this.nome = nome;
		this.expresion = expresion;
	}

	public Filtro(Long id, String nome, String expresion) {
		this(nome, expresion);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getExpresion() {
		return expresion;
	}

	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}

}
