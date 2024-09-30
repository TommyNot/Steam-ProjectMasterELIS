package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;

/**
 * Servlet implementation class EliminaGioco
 */
public class GiocoEliminaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiocoEliminaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String eliminaGiocoNome = request.getParameter("nome");
        
        
        if (eliminaGiocoNome == null || eliminaGiocoNome.isBlank()) {
            String errore = "Il nome del gioco non può essere vuoto.";
            request.setAttribute("errore", errore); 
            request.getRequestDispatcher("private-jsp/DashboardPublisher.jsp").forward(request, response);
            return; 
        }

        
        Gioco giocoEliminato = BusinessLogic.eliminaGioco(eliminaGiocoNome);

        
        if (giocoEliminato != null) {
        	
            String successo = "Il gioco '" + eliminaGiocoNome + "' è stato eliminato con successo.";
            request.setAttribute("successo", successo);
        } else {
            String errore = "Il gioco con nome '" + eliminaGiocoNome + "' non è stato trovato.";
            request.setAttribute("errore", errore);
        }

       
        request.getRequestDispatcher("private-jsp/DashboardPublisher.jsp").forward(request, response);
    }

}
