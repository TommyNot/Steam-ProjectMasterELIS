package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;

import org.elis.model.Ruolo;
import org.elis.model.Utente;


/**
 * Servlet implementation class LogicaLogin
 */
public class LogicaLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogicaLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("emailLogin");
        String password = request.getParameter("passwordLogin");
        String ricordami = request.getParameter("checkboxFormInput");

        
        if (email == null || email.isBlank() || password == null || password.isBlank()) {
        	
        	 String failed = "errore o meail o password.";
               
            request.setAttribute("Error", failed);
            request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
            return;
        }

        
        Utente u1 = BusinessLogic.UtenteLogin(email, password);
        
        if (u1 != null) {
           
            HttpSession sessione = request.getSession();
            sessione.setAttribute("utenteLoggato", u1);
            
            
    	    if(ricordami != null) {
		        sessione.setAttribute("utenteLoggato", u1);
		    }

            
            switch (u1.getRuolo()) {
                case ADMIN:
                	request.setAttribute("utenteLoggato", u1);
                    request.getRequestDispatcher("public-jsp/DashboardAdmin.jsp").forward(request, response);
                    break;
                case PUBLISHER:
                	request.setAttribute("utenteLoggato", u1);
                    request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                    break;
                case UTENTE_BASE:
                	request.setAttribute("utenteLoggato", u1);
                    request.getRequestDispatcher("public-jsp/DashboardUtente.jsp").forward(request, response);
                    break;
                default:
                    request.setAttribute("Error", "Ruolo utente non riconosciuto.");
                    request.getRequestDispatcher("public-jsp/LoginPage.jsp").forward(request, response);
            }
        } else {
            
            request.setAttribute("Error", "Email o password non validi.");
            request.getRequestDispatcher("public-jsp/LoginPage.jsp").forward(request, response);
        }
    }


}
