package com.choisy.website.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "api/v1/users")
public class PagesController {
	
	private final Services services;
	
	@Autowired
	public PagesController(Services services) {
		this.services = services;
	}
	
	@GetMapping (path = "{utilisateurId}") 
	public Optional<Utilisateurs> users(@PathVariable("utilisateurId") Long userid){
		return services.getUilisateur(userid);
//		return STR.stream()
//				.filter(user -> userid.equals(user.getId()))
//				.findFirst()
//				.orElseThrow(() -> new IllegalStateException("Cet utilisateur n'existe pas"));
	}
	
	@GetMapping 
	public List<Utilisateurs> Allusers(){
		return services.users();
	}
	
}

