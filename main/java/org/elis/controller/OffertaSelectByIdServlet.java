package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Offerta;


@WebServlet("/OffertaSelectByIdServlet")
public class OffertaSelectByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public OffertaSelectByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idInput = request.getParameter("idOfferta");
		
      
		if(idInput == null || idInput.isBlank()) {
			String errore = "campo di ricerca errato o vuoto";
			request.setAttribute("errore", errore);
			return;
		}
        
		long idOfferta = 0;
		
		try {
		idOfferta = Long.parseLong(idInput);
		}catch(NumberFormatException e) {
			 String error = "ID Utente non valido. Deve essere un numero.";
		        response.setContentType("text/html");
		        response.getWriter().write("<p style=\"color:red;\">" + error + "</p>");
		        return;
		}
		
		Offerta o = BusinessLogic.findOffertaById(idOfferta);
		
	    if (o == null) {
	        response.setContentType("text/html");
	        response.getWriter().write("<p style=\"color:red;\">Nessuna offerta trovata con questo id</p>");
	        return;
	    }

        
        
	}

}
