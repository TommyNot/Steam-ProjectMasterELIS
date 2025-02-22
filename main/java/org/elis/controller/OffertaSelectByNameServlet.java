package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Offerta;

@WebServlet("/OffertaSelectByNameServlet")
public class OffertaSelectByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OffertaSelectByNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ricerca = request.getParameter("nomeOfferta");
		
		if(ricerca == null || ricerca.isBlank()) {
			String error = "Il campo di ricerca è vuoto.";
	        response.setContentType("text/html");
	        response.getWriter().write("<p style=\"color:red;\">" + error + "</p>");
	        return;
		}
		
		Offerta o = BusinessLogic.findOffertaByNome(ricerca);
		
		if(o == null) {
			response.setContentType("text/html");
	        response.getWriter().write("<p style=\"color:red;\">Nessuna offerta trovata con questo nome</p>");
	        return;
		}
	}

}
