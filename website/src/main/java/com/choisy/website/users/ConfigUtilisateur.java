package com.choisy.website.users;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigUtilisateur {
		@Bean
		CommandLineRunner commandLinerRunner(RepertoirUsers repertoire) {
			return args -> {
				Utilisateurs Emmanuel = new Utilisateurs( 
						"GIRALDO",
						"Emmanuel", 
						"giraldo@mail.com", 
						LocalDate.of(1985, Month.MARCH, 12) 
						);
				Utilisateurs Danielle = new Utilisateurs( 
						"NGOMA",
						"Danielle", 
						"daniel@mail.com", 
						LocalDate.of(2003, Month.AUGUST, 5) 
						);
				Utilisateurs David = new Utilisateurs( 
						"JASON",
						"David", 
						"david@mail.com", 
						LocalDate.of(2003, Month.JULY, 18) 
						);
				
				repertoire.saveAll(List.of(Emmanuel, Danielle, David));
				
			};
		}
		
}
