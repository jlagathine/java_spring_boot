package com.choisy.website.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping
	public List<Utilisateurs> users(){
		return services.users();	
	}
	
	@PostMapping
	public void  ajouterNewUser(@RequestBody Utilisateurs uti) {
	services.ajouterNewUser(uti);	
		
	}
}
