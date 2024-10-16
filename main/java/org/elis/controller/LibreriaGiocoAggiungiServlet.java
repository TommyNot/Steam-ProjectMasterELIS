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
 * Servlet implementation class LibreriaGiocoAggiungiServlet
 */
public class LibreriaGiocoAggiungiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibreriaGiocoAggiungiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null) {
			request.getRequestDispatcher("public-jsp/LoginPage.jsp");
			return;
		}
		String idGiocoString = request.getParameter("idGioco");
		if(idGiocoString == null || idGiocoString.isEmpty()) {
			response.sendRedirect("public-jsp/ErrorPage.jsp");
			return;
		}
		
		long idGioco = 0;
		try {
			idGioco = Long.parseLong(idGiocoString);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		Utente utente = (Utente) session.getAttribute("utenteLoggato");
		
		if(utente != null) {
			long idUtente = utente.getId();
			Utente u = BusinessLogic.UtenteFindById(idUtente);
			if(u != null) {
				boolean isUtenteBase = u.getRuolo() == Ruolo.UTENTE_BASE;
				
				if(isUtenteBase) {
					List<Libreria> librerie = BusinessLogic.findLibreriaByIdUtente(idUtente);
					System.out.println(librerie);
					if(librerie == null || librerie.isEmpty()) {
						response.sendRedirect("public-jsp/ErrorPage.jsp");
						return;
					}else {
						Gioco g = new Gioco();
						BusinessLogic.aggiungiGiocoALibreria(idGioco, g);
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
