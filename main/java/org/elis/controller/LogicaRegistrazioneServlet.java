package org.elis.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import org.elis.businesslogic.BusinessLogic;
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
            request.setAttribute("Error", "Errore nell'inserimento dei dati: email non valida.");
            request.getRequestDispatcher("public-jsp/RegisterPage.jsp").forward(request, response);
            return;
        }

        if (!isValidPassword(password)) {
            request.setAttribute("Error", "Errore nell'inserimento dei dati: password non valida.");
            request.getRequestDispatcher("public-jsp/RegisterPage.jsp").forward(request, response);
            return;
        }

        // Logica per l'aggiunta dell'utente
        Utente u = BusinessLogic.UtenteAdd(email, username, password);
        if (u == null) {
           
            request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("Success", "Registrazione avvenuta con successo! Benvenuto, " + username);
        request.getRequestDispatcher("WEB-INF/private-jsp/HomePagePrincipale.jsp").forward(request, response);
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
