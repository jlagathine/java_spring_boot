package com.choisy.website.users;

import java.util.List;

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
@RequestMapping (path = "gestion/api/v1/users")
public class GestionUtilisateur {
		
		private final Services services;
		
		@Autowired
		public GestionUtilisateur(Services services) {
			this.services = services;
		}
		
		@GetMapping 
		public List<Utilisateurs> Allusers(){
			return services.users();
		}
		

		@PostMapping
		public void  ajouterNewUser(@RequestBody Utilisateurs uti) {
		services.ajouterNewUser(uti);	
			
		}
		
		@DeleteMapping (path = "{utilisateurId}")
		public void deleteUser(@PathVariable("utilisateurId") Long userid) {
			services.deleteUser(userid);
		}
		
		@PutMapping (path = "{utilisateurId}")
		public void updateUser (
				@PathVariable("utilisateurId") Long userid,
				@RequestParam (required = false) String nom,
				@RequestParam (required = false) String email
				) {
			services.updateUser(userid, nom, email);
		}
}
