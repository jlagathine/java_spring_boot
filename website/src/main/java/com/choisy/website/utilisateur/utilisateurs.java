package com.choisy.website.utilisateur;

import java.time.LocalDate;

import javax.annotation.sql.DataSourceDefinitions;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table
@Entity
public class utilisateurs {

	@Id
	private long id;
	private String nom;
	private String prénom;
	private String email;
	private LocalDate date;
	private Integer age;
	
	public utilisateurs(long id, String nom, String prenom, String email, LocalDate date, Integer age) {
		super();
		this.id = id;
		this.nom = nom;
		this.prénom = prenom;
		this.email = email;
		this.date = date;
		this.age = age;
	}

	public utilisateurs(String nom, String prenom, String email, LocalDate date, Integer age) {
		super();
		this.nom = nom;
		this.prénom = prenom;
		this.email = email;
		this.date = date;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prénom;
	}

	public void setPrenom(String prenom) {
		this.prénom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "utilisateurs [id=" + id + ", nom=" + nom + ", prénom=" + prénom + ", email=" + email + ", date=" + date
				+ ", age=" +age+ "]";
	}

	
}



