package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

/**
 * Servlet implementation class AdminCercaRuoloServlet
 */
public class AdminCercaRuoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCercaRuoloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String ruoloSelezionato = request.getParameter("ruoloSelezionato");
        System.out.println("sono dentro al filtro");
        List<Utente> utentiFiltrati;
        if (ruoloSelezionato == null || ruoloSelezionato.isEmpty()) {
            utentiFiltrati = BusinessLogic.UtenteFindAll();
            System.out.println("dentro findAll");
        } else {
            utentiFiltrati = BusinessLogic.findByRuolo(Ruolo.valueOf(ruoloSelezionato));
            System.out.println("dentro findRuolo");
        }

        StringBuilder responseText = new StringBuilder();
        for (Utente utente : utentiFiltrati) {
            responseText.append(utente.getId()).append(",")
                        .append(utente.getUsername()).append(",")
                        .append(utente.getRuolo().name()).append("\n");
        }
        response.getWriter().write(responseText.toString());
	}


}
