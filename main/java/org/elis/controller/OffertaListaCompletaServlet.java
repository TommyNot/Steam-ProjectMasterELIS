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
			request.setAttribute("error", "Nessuna Offerta disponibile.");
			request.getRequestDispatcher("public-jsp/PageGiochiOfferta.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("offerte", listaOfferte);
        request.getRequestDispatcher("public-jsp/PageGiochiOfferta.jsp").forward(request, response);
	}

	
	

}
