package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Utente;

/**
 * Servlet implementation class UtenteRipristinaPasswordServlet
 */
public class UtenteRipristinaPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteRipristinaPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("passwordNuova");
        String passwordConferma = request.getParameter("passwordConferma");
        
        if (username == null || email == null || newPassword == null || newPassword.isEmpty() || !isValidPassword(newPassword)) {
            request.setAttribute("errorMessage", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
            System.out.println("Errore qui nel orimo if");
            return;
        }
        
        if(!matchOldPass(newPassword, passwordConferma)) {
        	
            request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
            System.out.println("Errore qui nel match password if");
        }
        
        Utente nuovaPassword=BusinessLogic.RipristinaPassword(username, email, newPassword);
        if (nuovaPassword != null) {
			response.getWriter().write("Password aggiornata con successo!");
			
		} else {
			System.out.println("Errrore qui 2 if");
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
		}
        
        
		 request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
		 
		 
	
	}
	
		 private boolean matchOldPass(String passwordOld, String PasswordConfirm) {
			 
			 
			 String regrex = PasswordConfirm;
			 
			 return passwordOld.matches(regrex);
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
