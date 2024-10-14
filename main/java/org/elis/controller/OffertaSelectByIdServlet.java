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
        
		try {
		long idOfferta = Long.parseLong(idInput);
		Offerta o = BusinessLogic.findOffertaById(idOfferta);
		
		if(o != null) {
			response.getWriter().println("Offerta trovata: " + o);
		}else {
			String errore2 = "Non esiste nessuna offerta con questo id.";
			request.setAttribute("errore2", errore2);
		}
		}catch(NumberFormatException e){
			response.getWriter().println("Errore: ID non valido.");
		}
        
        
	}

}
