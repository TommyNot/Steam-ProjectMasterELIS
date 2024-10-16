package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;
import org.elis.model.Libreria;
import org.elis.model.Ruolo;
import org.elis.model.Utente;


public class LibreriaFindByIdUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public LibreriaFindByIdUtenteServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			request.getRequestDispatcher("public-jsp/LoginPage.jsp");
			return;
		}
		
		Utente utente = (Utente) session.getAttribute("utenteLoggato");
		if(utente != null) {
			long idUtente = utente.getId();
			Utente u = BusinessLogic.UtenteFindById(idUtente);
			System.out.println("PIPPO");
			if(u != null) {
				boolean isUtenteBase = u.getRuolo() == Ruolo.UTENTE_BASE;
				
				if(isUtenteBase) {
					List<Libreria> librerie = BusinessLogic.findLibreriaByIdUtente(idUtente);
					List<Gioco> giochiUtente = BusinessLogic.VisualizzaTuttiGiochi(idUtente);
					System.out.println(librerie);
					if(librerie == null || librerie.isEmpty()) {
						request.setAttribute("giochi", giochiUtente);
						request.setAttribute("librerieUtente", librerie);
						request.setAttribute("errorMessage", "Nessun gioco disponibile.");
						request.getRequestDispatcher("public-jsp/LibreriaGiochi.jsp").forward(request, response);
						return;
					}else {
						request.setAttribute("giochi", giochiUtente);
						request.setAttribute("librerieUtente", librerie);
						System.out.println("Lista libreria trovata con successo dell'utente con id " + idUtente);
						request.getRequestDispatcher("public-jsp/LibreriaGiochi.jsp").forward(request, response);
					}
				}else {
					System.out.println("L'utente non è un utente base.");
				}
			}else {
				System.out.println("Utente non trovato con id " + idUtente);
			}
		}else {
			System.out.println("Nessun utente trovato nella sessione.");
		}
	}

}
