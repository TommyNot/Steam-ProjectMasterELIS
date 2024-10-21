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
 * Servlet implementation class OffertaVediTuttiGiochi
 */
public class OffertaVediTuttiGiochi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OffertaVediTuttiGiochi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Gioco> giochiOfferta = BusinessLogic.VisualizzaGiochiInOfferta();
		
		if(giochiOfferta == null || giochiOfferta.isEmpty()) {
			String errore = "Nessuna offerta al momento disponibile";
			request.setAttribute("errorNessunGioco", errore);
			request.getRequestDispatcher("public-jsp/PageGiochiOfferta.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("giochi", giochiOfferta);
		request.getRequestDispatcher("public-jsp/PageGiochiOfferta.jsp").forward(request, response);
	}

	

}
