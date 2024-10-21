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

public class LibreriaAggiungiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LibreriaAggiungiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession(false);
		
		String nome = request.getParameter("nomeLibreriaInput");
		
		if(nome == null || nome.isEmpty()) {
			 request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
		     return;
		}
		
		if(sessione == null) {
			response.sendRedirect("public-jsp/LoginPage.jsp");
			return;
		}
		
		Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
		
		if(utente != null) {
			long idUtente = utente.getId();
			System.out.println("Id utente trovato: " + idUtente);
			
			Utente u = BusinessLogic.UtenteFindById(idUtente);
			if(u != null) {
				boolean isUtenteBase = u.getRuolo() == Ruolo.UTENTE_BASE;
				if(isUtenteBase) {
					System.out.println("L'utente selezionato è un utente base.");
					Libreria l = new Libreria(nome, u);
					Libreria nuovaLibreria = BusinessLogic.LibreriaAdd(l);
					
					if(nuovaLibreria != null) {
						System.out.println("Libreria aggiunta con successo.");
						response.sendRedirect("LibreriaFindByIdUtenteServlet");
					}else {
						request.getRequestDispatcher("public-jsp/ErrorPage.jsp");
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
