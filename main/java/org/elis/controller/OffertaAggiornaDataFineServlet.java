package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
import org.elis.model.Utente;


@WebServlet("/OffertaAggiornaDataFineServlet")
public class OffertaAggiornaDataFineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public OffertaAggiornaDataFineServlet() {
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
			request.getRequestDispatcher("public-jsp/PaginaLogin.jsp");
			return;
		}
		

		String dataFine = request.getParameter("nuovaDataFine");
		String dataInizio = request.getParameter("dataInizio");
		
		if(dataFine == null || dataFine.isEmpty()) {
			request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	        return;
		}
		

		LocalDateTime dataFineFormatter = LocalDateTime.parse(dataFine);
		LocalDateTime dataInizioFormatter= LocalDateTime.parse(dataInizio);
		
		
		if (dataFineFormatter.isBefore(dataInizioFormatter)) {
            response.sendRedirect("ErrorPage.jsp");
            return;
        }
		
		Utente utente = (Utente) session.getAttribute("utenteLoggato");
		if(utente != null) {
			long idUtente = utente.getId();
			
			Utente u = BusinessLogic.UtenteFindById(idUtente);
			if(u != null) {
				boolean isAdmin = u.getRuolo() == Ruolo.ADMIN;
				if(isAdmin) {
					Offerta OffertaNuovaDataFine = BusinessLogic.updateDataInizioOfferta(idUtente, dataFineFormatter);
					
					if(OffertaNuovaDataFine != null) {
						System.out.println("La fine dell'offerta è stato aggiornato con successo.");
					}else {
						request.getRequestDispatcher("public-jsp/ErrorPage.jsp");
						return;
					}
				}else {
					System.out.println("L'utente non è un admin.");
				}
			}else {
				System.out.println("Utente non trovato con id " + idUtente);
			}
		}else {
			System.out.println("Nessun utente trovato nella sessione.");
		}
	}

}
