package com.choisy.website.users;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

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
			throw new IllegalStateException("Email déjà enregistré");
		}
		repertoirUsers.save(utilisateur);
	}
	
	public void deleteUser(long userId) {
		Optional<Utilisateurs> exist =  repertoirUsers.findById(userId);
		if(exist.isEmpty()) {
			throw new IllegalStateException("Utilisateur non enregistré");
		}
		repertoirUsers.deleteById(userId);
	}
	
	@Transactional
	public void updateUser(long userid, String nom, String email) {
		Utilisateurs uti = repertoirUsers.findById(userid).orElseThrow(() -> new IllegalStateException("Utilisateur non enregistré"));
		
		if(nom != null && nom.length() > 0 && !Objects.equals(uti.getNom(), nom)) {
			uti.setNom(nom);
			}
			
		if(email != null && nom.length() > 0 && !Objects.equals(uti.getEmail(), email)) {
			Optional<Utilisateurs> utiOptional =  repertoirUsers.findUsersByEmail(email);
			if(utiOptional.isPresent()) {
				throw new IllegalStateException("Email déjà enregistré");
				}
			}
			uti.setEmail(email);
	}
	
//	public Optional<Utilisateurs> getUilisateur(long id) {
//		Optional<Utilisateurs> uti = repertoirUsers.findUsersById1(id);
//				if(uti.isEmpty()) {
//					throw new IllegalStateException("User non enregistré");	
//				};
//				return uti;
//	}

}
