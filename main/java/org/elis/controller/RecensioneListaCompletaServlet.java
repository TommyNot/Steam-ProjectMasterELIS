package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Recensione;

@WebServlet("/RecensioneListaCompletaServlet")
public class RecensioneListaCompletaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public RecensioneListaCompletaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Recensione> listaRecensioni = BusinessLogic.VisualizzaTutteRecensioni();
		System.out.println(listaRecensioni);
		
		  if (listaRecensioni == null || listaRecensioni.isEmpty()) {
	            request.setAttribute("errorMessage", "Nessuna recensione disponibile.");
	            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	            return;
		  }
		  
		  request.setAttribute("recensioni", listaRecensioni);
	      request.getRequestDispatcher("public-jsp/PageGiochi.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
