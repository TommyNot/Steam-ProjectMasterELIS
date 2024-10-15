package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;

/**
 * Servlet implementation class GiocoCercaServlet
 */
public class GiocoCercaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiocoCercaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ricerca = request.getParameter("barraRicerca");
		

		if(ricerca.isBlank()) {
			String error = "campo vuoto";
			request.setAttribute("campo vuoto", error);
			request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
			System.out.println("errore qui ricerca ");
			return;
		}
		
		List<Gioco> search = BusinessLogic.TrovaByName(ricerca);
		
		if(search == null) {
	        String errorMessage = "Nessun gioco trovato con il nome: " + ricerca;
	        request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
			
			return;
		}else {
			
			request.setAttribute("gioco", search);
			
		}
		
		
		request.getRequestDispatcher("public-jsp/PageGiochi.jsp").forward(request, response);
		
		
		
	}

}
