package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Recensione;


@WebServlet("/RecensioneFindByGiocoServlet")
public class RecensioneFindByGiocoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RecensioneFindByGiocoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("idGioco");
		  if (id == null || id.isEmpty()) {
	            request.setAttribute("Error", "Campo idGioco obbligatorio.");
	            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	            return;               
	        }
	        long idGioco;
	        
	        try {
	            idGioco = Long.parseLong(id);
	        } catch (NumberFormatException e) {
	            e.printStackTrace(); 
	            request.setAttribute("Error", "Errore nel formato dell'id selezionato.");
	            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	            return;
	        }
	        
	        Gioco gioco = BusinessLogic.findGiocoById(idGioco);
	        if (gioco == null) {
	            request.setAttribute("Error", "Gioco non trovato.");
	            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
	            return;
	        }
	        
	        List<Recensione> recensioniGioco = BusinessLogic.TrovaRecensioneByIdGioco(idGioco);

	        if (recensioniGioco == null || recensioniGioco.isEmpty()) {
	            request.setAttribute("Error", "Nessuna recensione trovata per il gioco selezionato.");
	            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardUtente.jsp").forward(request, response);
	            return;
	        }
	       
	}

}
