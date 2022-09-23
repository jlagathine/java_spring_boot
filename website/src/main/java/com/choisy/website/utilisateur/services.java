package com.choisy.website.utilisateur;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class services {

	public List<utilisateurs> users() {
		return List.of(new utilisateurs(
				1, 
				"GIRALDO",
				"Emmanuel", 
				"giraldo@mail.com", 
				LocalDate.of(1985, Month.MARCH, 12), 
				36)); 
	}
}
