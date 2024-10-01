package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;
import org.elis.model.Offerta;

/**
 * Servlet implementation class GiocoOffertaServlet
 */
public class GiocoOffertaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiocoOffertaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] offertaIds = request.getParameterValues("offertaIds");
        
        if (offertaIds == null || offertaIds.length == 0) {
            request.setAttribute("error", "Nessuna offerta presente.");
            request.getRequestDispatcher("public-jsp/HomePagePrincipale.jsp").forward(request, response);
            return;
        }

        List<Gioco> tuttiGiochi = new ArrayList<>();
        
        //tommy che giocata!
        
        for (String id : offertaIds) {
            Offerta offerta = BusinessLogic.findOffertaById(Long.parseLong(id));
            if (offerta != null) {
                List<Gioco> giochi = BusinessLogic.GiocoOfferta(offerta);
                tuttiGiochi.addAll(giochi);
            }
        }
        
        request.setAttribute("giochi", tuttiGiochi);
        request.getRequestDispatcher("public-jsp/ListaGiochi.jsp").forward(request, response);
    }



}
