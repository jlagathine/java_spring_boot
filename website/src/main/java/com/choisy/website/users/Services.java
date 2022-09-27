package com.choisy.website.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Services {

	private final RepertoirUsers repertoirUsers;
	
	@Autowired
	public Services(RepertoirUsers repertoirUsers) {
		this.repertoirUsers = repertoirUsers;
	}

	public List<Utilisateurs> users() {
		return repertoirUsers.findAll(); 
	}
	public void  ajouterNewUser(Utilisateurs utilisateur) {
		Optional<Utilisateurs> utiOptional =  repertoirUsers.findUsersByEmail(utilisateur.getEmail());
		if(utiOptional.isPresent()) {
			throw new IllegalStateException("Email");
		}
		repertoirUsers.save(utilisateur);
	}
}
