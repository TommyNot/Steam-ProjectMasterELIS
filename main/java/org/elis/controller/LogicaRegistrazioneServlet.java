package org.elis.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Libreria;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

/**
 * Servlet implementation class LogicaRegistrazioneServlet
 */
public class LogicaRegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogicaRegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("usernameFromInput");
    	
    	String email = request.getParameter("emailFromInput");
        
        String password = request.getParameter("passwordFromInput");

        
        if (email == null || username == null || password == null) {
            request.setAttribute("Error", "Errore nell'inserimento dei dati: tutti i campi sono obbligatori.");
            request.getRequestDispatcher("public-jsp/RegisterPage.jsp").forward(request, response);
            return;
        }

        if (!isValidEmail(email)) {
        	String failed = "Errore nell'inserimento dei dati: password non valida.";
            request.setAttribute("Error", failed );
            request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
            return;
        }

        if (!isValidPassword(password)) {
        	String failed = "Errore nell'inserimento dei dati: password non valida.";
            request.setAttribute("Error", failed );
            request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
            return;
        }
        
        // Controllo se lo username esiste già
        List<Utente> utentiLista = BusinessLogic.UtenteFindAll();
        for (Utente u1 : utentiLista) {
            if (u1.getUsername().equals(username)) {
                String failed = "Errore nell'inserimento del username: username già esistente";
                request.setAttribute("Error", failed);
                request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
                return; 
            }
        }
        
        for (Utente u1 : utentiLista) {
            if (u1.getEmail().equals(email)) {
                String failed = "Errore nell'inserimento del email: email già registrata";
                request.setAttribute("Error", failed);
                request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
                return; 
            }
        }
       

        // Logica per l'aggiunta dell'utente
        Utente u = new Utente(Ruolo.UTENTE_BASE,username,email,password);
        Utente uRestituito = BusinessLogic.UtenteAdd(u);
        
       
        		

        if (uRestituito == null) {
           
            request.getRequestDispatcher("public-jsp/ErrorAccessoNeagtoPage.jsp").forward(request, response);
            return;
        }
        Libreria libreriaUtente = new Libreria();
	    libreriaUtente.setNome("Giochi di " + uRestituito.getUsername());
	    libreriaUtente.setLibreriaUtente(uRestituito);
	    BusinessLogic.LibreriaAdd(libreriaUtente);
	    
        request.setAttribute("Success", "Registrazione avvenuta con successo! Benvenuto, " + username + " esegui il login con le credenziali appena inserite");
        request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
    }

    private boolean isValidEmail(String email) {
		// Regex prese da 101 per validare un indirizzo email
		String emailRegex = "^[a-zA-Z0-9._%+-]+" + // Deve iniziare con uno o più caratteri alfanumerici o i seguenti simboli: ., _, %, +, -
		                       "@" + // Deve contenere il simbolo '@'
		                       "[a-zA-Z0-9.-]+" + // Dopo '@', deve esserci uno o più caratteri alfanumerici, punto o trattino
		                       "\\." + // Deve contenere un punto ('.') che separa il dominio dall'estensione
		                       "[a-zA-Z]{2,6}$"; // L'estensione deve essere di 2 a 6 lettere (es. com, org, it, etc.)

	    return email.matches(emailRegex);
	}
	
	private boolean isValidPassword(String password) {
		
		// Regex prese da regrex 101 per validare una password
		String passwordRegex = "^(?=.*[a-z])" + // Deve contenere almeno una lettera minuscola
		                       "(?=.*[A-Z])" + // Deve contenere almeno una lettera maiuscola
		                       "(?=.*\\d)" + // Deve contenere almeno un numero
		                       "(?=.*[@$!%*?&])" + // Deve contenere almeno un carattere speciale (es: @, $, !, %, *, ?, &)
		                       ".{8,}$"; // La lunghezza totale della password deve essere di almeno 8 caratteri
		
		return password.matches(passwordRegex);
    }



}
