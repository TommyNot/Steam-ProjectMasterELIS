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
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Recensione;
import org.hibernate.internal.build.AllowSysOut;


@WebServlet("/GenereOffertaAddServlet")
public class GenereOffertaAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GenereOffertaAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("idOfferta");
        String[] idGenere = request.getParameterValues("idGenere");

        if (id == null || id.isEmpty() || idGenere == null || idGenere.length == 0) {
            System.out.println("primo if");
            request.setAttribute("Error", "Campi idOfferta e idGenere obbligatori.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
        }

        long idOfferta;
        try {
            idOfferta = Long.parseLong(id);
            System.out.println("pars di offerta");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("Error", "Errore nel formato dell'id offerta.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
        }

        Offerta offerta = BusinessLogic.findOffertaById(idOfferta);
        if (offerta == null) {
            request.setAttribute("Error", "Offerta non trovata.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
        }

        List<Long> idGeneri = new ArrayList<>();
        try {
            for (String idGenereStr : idGenere) {
                idGeneri.add(Long.parseLong(idGenereStr));
                System.out.println("pars di genere: " + idGenereStr);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("Error", "Errore nel formato dell'id dei generi.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
        }

        System.out.println("id offerta: " + idOfferta + ", id generi: " + idGeneri);

        List<Genere> genereOfferta = new ArrayList<>();
        List<Gioco> giochiOfferta = new ArrayList<>();
        for (Long idGenere1 : idGeneri) {
            genereOfferta.addAll(BusinessLogic.genereOffertaAdd(idGenere1, idOfferta));
            giochiOfferta.addAll(BusinessLogic.addOffertaToGiochiByGenere(idGenere1, idOfferta));
        }

        if (genereOfferta == null || genereOfferta.isEmpty()) {
            request.setAttribute("Error", "Nessun genere trovato per l'offerta selezionata.");
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardUtente.jsp").forward(request, response);
        } else {
            response.getWriter().write("Offerta associata con successo.");
        }
	        
	}
}


