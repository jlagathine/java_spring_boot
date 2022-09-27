package com.choisy.website.users;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity(name = "Utilisateurs")
@Table (name = "utilisateurs", uniqueConstraints = {
		@UniqueConstraint (name = "user_constraint_unique", columnNames = "email")
})
public class Utilisateurs {

	@Id
	@SequenceGenerator(
			name ="utilisateur_seq",
			sequenceName = "utilisateur_seq",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "utilisateur_seq"
			)
	@Column (name = "id", updatable = false)
	private long id;
	
	@Column (name = "nom", nullable = false, columnDefinition = "TEXT")
	private String nom;
	
	@Column (name = "prenom", nullable = false, columnDefinition = "TEXT")
	private String prénom;
	
	@Column (name = "email", nullable = false, columnDefinition = "TEXT")
	private String email;
	
	@Column (name = "date")
	private LocalDate date;
	
	@Column (name = "age", nullable = false)
	@Transient
	private Integer age;
	
	
	
	public Utilisateurs() {
		//Initialisation du bean
	}

	public Utilisateurs(long id, String nom, String prenom, String email, LocalDate date) {
		super();
		this.id = id;
		this.nom = nom;
		this.prénom = prenom;
		this.email = email;
		this.date = date;
	}

	public Utilisateurs(String nom, String prenom, String email, LocalDate date) {
		super();
		this.nom = nom;
		this.prénom = prenom;
		this.email = email;
		this.date = date;
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
		return Period.between(this.date, LocalDate.now()).getYears();
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



