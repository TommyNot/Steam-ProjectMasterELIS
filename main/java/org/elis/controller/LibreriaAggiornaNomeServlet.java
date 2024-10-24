package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Libreria;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

public class LibreriaAggiornaNomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LibreriaAggiornaNomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			request.getRequestDispatcher("public-jsp/LoginPage.jsp");
			return;
		}
		
		String nomeNuovo = request.getParameter("nomeNuovoInput");
		
		if(nomeNuovo == null || nomeNuovo.isEmpty()) {
			request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	        return;
		}
		long idLibreria = 0;
		String idLibreriaUtente = request.getParameter("idLibreria");
		try {
        	
        	idLibreria = Long.parseLong(idLibreriaUtente);
        	
        }catch(Exception e) {
        	
        	System.out.println("errore");
        }
		
		Utente utente = (Utente) session.getAttribute("utenteLoggato");
		if(utente != null) {
			long idUtente = utente.getId();
			
			Utente u = BusinessLogic.UtenteFindById(idUtente);
			if(u != null) {
				boolean isUtenteBase = u.getRuolo() == Ruolo.UTENTE_BASE;
				if(isUtenteBase) {
					Libreria libreriaNuovoNome = BusinessLogic.updateLibreriaNome(idLibreria, nomeNuovo);
					
					if(libreriaNuovoNome != null) {
						System.out.println("Il nome della libreria è stato aggiornato con successo.");
						response.sendRedirect("LibreriaFindByIdUtenteServlet");
					}else {
						request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
						return;
					}
				}else {
					System.out.println("L'utente non è un utente base.");
					request.getRequestDispatcher("public-jsp/ErrorAccessoNegatoPage.jsp").forward(request, response);
				}
			}else {
				System.out.println("Utente non trovato con id " + idUtente);
				request.getRequestDispatcher("public-jsp/ErrorAccessoNegatoPage.jsp").forward(request, response);
			}
		}else {
			System.out.println("Nessun utente trovato nella sessione.");
			request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
		}
	}

}
