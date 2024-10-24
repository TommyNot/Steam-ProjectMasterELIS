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

/**
 * Servlet implementation class LibreriaEliminaGiocoDaLibreriaServlet
 */
public class LibreriaEliminaGiocoDaLibreriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LibreriaEliminaGiocoDaLibreriaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			request.getRequestDispatcher("public-jsp/LoginPage.jsp");
			return;
		}
		
		String idLibreriaString = request.getParameter("idLibreria");
		String idGiocoString = request.getParameter("idGioco");
		
		if(idGiocoString == null || idGiocoString.isEmpty()) {
			response.sendRedirect("public-jsp/ErrorPage.jsp");
			System.out.println("Errore idgioco parse");
			return;
		}
		
		if(idLibreriaString == null || idLibreriaString.isEmpty()) {
			response.sendRedirect("public-jsp/ErrorPage.jsp");
			System.out.println("Errore idgioco parse");
			return;
		}
		
		long idGioco = 0;
		try {
			idGioco = Long.parseLong(idGiocoString);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		long idLibreria = 0;
		try {
			idLibreria = Long.parseLong(idLibreriaString);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		if(idGioco == 0 || idLibreria == 0) {
			System.out.println("Id libreria o id gioco non trovati.");
			response.sendRedirect("public-jsp/ErrorPage.jsp");
			return;
		}
		
		Utente utente = (Utente) session.getAttribute("utenteLoggato");
		
		if(utente != null) {
			long idUtente = utente.getId();
			Utente u = BusinessLogic.UtenteFindById(idUtente);
			if(u != null) {
				boolean isUtenteBase = u.getRuolo() == Ruolo.UTENTE_BASE;
				
				if(isUtenteBase) {
					Gioco g = BusinessLogic.deleteGiocoLibrary(idGioco, idLibreria); 
					if(g != null) {
						System.out.println("Gioco trovato con id " + g.getId());
						response.sendRedirect("LibreriaFindByIdUtenteServlet");
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
