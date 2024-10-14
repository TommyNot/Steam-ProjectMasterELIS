package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Offerta;

@WebServlet("/OffertaListaCompletaServlet")
public class OffertaListaCompletaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OffertaListaCompletaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Offerta> listaOfferte = BusinessLogic.offertaVisualizzaTutto();
		if(listaOfferte == null || listaOfferte.isEmpty()) {
			request.setAttribute("errorMessage", "Nessuna Offerta disponibile.");
			request.getRequestDispatcher("WEB-INF/private-jsp/error.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("offerte", listaOfferte);
        request.getRequestDispatcher("public-jsp/PageOfferta.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
