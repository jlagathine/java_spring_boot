package com.choisy.website.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "api/v1/users")
public class PagesController {
	
	private final Services services;
	
	@Autowired
	public PagesController(Services services) {
		super();
		this.services = services;
	}
	
	@GetMapping (path = "{utilisateurId}") 
	public List<Utilisateurs> users(@PathVariable("utilisateurId") long userid){
		return services.users();
//		return ConfigUtilisateur.stream()
//				.filter(user -> userid.equals(user.getId()))
//				.findFirst()
//				.orElseThrow(() -> new IllegalStateException("Cet utilisateur n'existe pas"));
	}
	
	@PostMapping
	public void  ajouterNewUser(@RequestBody Utilisateurs uti) {
	services.ajouterNewUser(uti);	
		
	}
	
	@DeleteMapping (path = "{utilisateurId}")
	public void deleteUser(@PathVariable("utilisateurId") long userid) {
		services.deleteUser(userid);
	}
	
	@PutMapping (path = "{utilisateurId}")
	public void updateUser (
			@PathVariable("utilisateurId") long userid,
			@RequestParam (required = false) String nom,
			@RequestParam (required = false) String email
			) {
		services.updateUser(userid, nom, email);
	}
	
}

