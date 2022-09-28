package com.choisy.website.users;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepertoirUsers extends JpaRepository<Utilisateurs, Long> {

//	 SELECT * FROM utilisteurs WHERE email = ?
	@Query("SELECT s FROM Utilisateurs s WHERE s.email = ?1")
	Optional<Utilisateurs> findUsersByEmail (String email);
	
	@Query("DELETE s FROM Utilisateurs s WHERE s.id = ?1")
	Optional<Utilisateurs> findUsersById (Long id);
	
	@Query("SELECT u FROM Utilisateurs u WHERE u.id = ?1")
	Optional<Utilisateurs> findById (Long id);
	
}


