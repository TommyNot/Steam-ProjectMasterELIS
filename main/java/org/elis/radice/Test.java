package org.elis.radice;

import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.jdbc.UtenteDaoJDBC;
import org.elis.model.Utente;

public class Test {

	public static void main(String[] args) {
		
		//Stampa tutti utenti nel database
		List<Utente> u;
		u = BusinessLogic.UtenteFindAll();
		for(Utente u1 : u) {
			
			System.out.println(u1.getUsername() + " " + u1.getRuolo() + " " + u1.getId());
		}
		
		
		//trova utente per username = avvenuto con usccesso
		try {
			
			BusinessLogic.UtenteFindByName("tumnus");
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		//test logica aggiunta admin o utente = avvenuta con successo  
		try {
			
			BusinessLogic.UtenteAdd(0,"tumnus", "email@email.com", "tommy1");
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		//test aggiorhnamento utente
		/*try {
			
			BusinessLogic.UtenteUpdateInfo(0, null, null,null);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}*/
		
		//test eliminazione utente = savvenuto con successo
		try {
			
			BusinessLogic.UtenteDeletByEmail("email@email.com", "tommy1");
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		//Stampa tutti utenti nel database
		
		u = BusinessLogic.UtenteFindAll();
		for(Utente u1 : u) {
			
			System.out.println(u1.getUsername() + " " + u1.getRuolo() + " " + u1.getId());
		}
		

		
		
	

	}

}
