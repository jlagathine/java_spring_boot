package utilisateurs;

import java.time.LocalDate;

public class UserDatas {

	private long id;
	private String nom;
	private String prenom;
	private String email;
	private LocalDate date;
	private Integer age;
	
	public UserDatas(long id, String nom, String prenom, String email, LocalDate date, Integer age) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.date = date;
		this.age = age;
	}

	public UserDatas(String nom, 
			String prenom, 
			String email, 
			LocalDate date, 
			Integer age) {
		super();
		this.nom = nom;
		this.prenom = prenom;
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
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
		return "userDatas [id=" + id + ","
				+ " nom=" + nom + ", "
				+ "prenom=" + prenom + ", "
				+ "email=" + email + ", "
				+ "date=" + date
				+ ",age=" + age + "]";
	}
	
}
