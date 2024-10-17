package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Offerta;
import org.elis.model.Recensione;


@WebServlet("/RecensioneFindByIdServlet")
public class RecensioneFindByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RecensioneFindByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ricerca = request.getParameter("idRecensione");
		
	      
		if(ricerca == null || ricerca.isBlank()) {
			String errore = "campo di ricerca errato o vuoto";
			request.setAttribute("errore", errore);
			return;
		}
        
		long idRecensione = 0;
		
		try {
		idRecensione = Long.parseLong(ricerca);
		}catch(NumberFormatException e) {
			 String error = "ID Recensione non valido. Deve essere un numero.";
		        response.setContentType("text/html");
		        response.getWriter().write("<p style=\"color:red;\">" + error + "</p>");
		        return;
		}
		
		Recensione r = BusinessLogic.TrovaRecensioneById(idRecensione);
		
	    if (r == null) {
	        response.setContentType("text/html");
	        response.getWriter().write("<p style=\"color:red;\">Nessuna recensione trovata con questo id</p>");
	        return;
	    }
	}

}
