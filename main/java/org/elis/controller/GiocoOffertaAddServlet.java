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


@WebServlet("/GiocoOffertaAddServlet")
public class GiocoOffertaAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GiocoOffertaAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String idOfferta = request.getParameter("idGioco");
        String[] idGioco = request.getParameterValues("idGioco");
        
        if (idOfferta == null || idOfferta.isEmpty() || idGioco == null || idGioco.length == 0) {
            System.out.println("primo if");
            request.setAttribute("Error", "Campi idOfferta e idGenere obbligatori.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
        }

        long idOffertaLong;
        try {
            idOffertaLong = Long.parseLong(idOfferta);
            System.out.println("pars di offerta");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("Error", "Errore nel formato dell'id offerta.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
        }

        Offerta offerta = BusinessLogic.findOffertaById(idOffertaLong);
        if (offerta == null) {
            request.setAttribute("Error", "Offerta non trovata.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
        }
        
        List<Long> idGiochi = new ArrayList<>();
        try {
            for (String idGiocoStr : idGioco) {
                idGiochi.add(Long.parseLong(idGiocoStr));
                System.out.println("pars di gioco: " + idGiocoStr);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("Error", "Errore nel formato dell'id dei giochi.");
            request.getRequestDispatcher("public-jsp/ErrorPage.jsp").forward(request, response);
            return;
        }

        System.out.println("id offerta: " + idOffertaLong + ", id giochi: " + idGiochi);

        List<Gioco> giocoOfferta = new ArrayList<>();
        for (Long idGioco1 : idGiochi) {
            giocoOfferta.addAll(BusinessLogic.giocoOffertaAdd(idGioco1, idOffertaLong)); 
        }

        if (giocoOfferta == null || giocoOfferta.isEmpty()) {
            request.setAttribute("Error", "Nessun gioco trovato per l'offerta selezionata.");
            request.getRequestDispatcher("public-jsp/DashboardAdmin.jsp").forward(request, response);
        } else {
            response.getWriter().write("Offerta associata con successo.");
        }
        
        }
	}


