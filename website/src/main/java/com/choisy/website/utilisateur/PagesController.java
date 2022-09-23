package com.choisy.website.utilisateur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = "api/v1/users")
public class PagesController {
	
	private final services services;
	
	@Autowired
	public PagesController(services serv) {
		super();
		this.services = serv;
	}
	
	@GetMapping
	public List<utilisateurs> users(){
		return services.users();	
	}
}
