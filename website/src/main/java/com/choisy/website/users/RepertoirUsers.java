package com.choisy.website.users;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepertoirUsers extends JpaRepository<Utilisateurs, Long> {

//	SELECT * FROM utilisteurs WHERE email = ?
	@Query("SELECT s FROM Utilisateurs s WHERE s.email = ?1")
	Optional<Utilisateurs> findUsersByEmail (String email);
}
