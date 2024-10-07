package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Utente;

/**
 * Servlet implementation class TrovaTuttiUtentiServlet
 */
public class TrovaTuttiUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrovaTuttiUtentiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utente> listaUtenti = BusinessLogic.UtenteFindAll();
		
		StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        sb.append("<thead>");
        sb.append("<tr>");
        sb.append("<th>Ruolo</th>");
        sb.append("<th>Id</th>");
        sb.append("<th>Username</th>");
        sb.append("<th>Email</th>");
        sb.append("<th>Data_creazione</th>");
        sb.append("<th>Data_ultima_modifica</th>");
    
        sb.append("</tr>");
        sb.append("</thead>");
        sb.append("<tbody>");
        for (Utente utente : listaUtenti) {
            sb.append("<tr>");
            sb.append("<td>" + utente.getRuolo() + "</td>");
            sb.append("<td>" + utente.getId() + "</td>");
            sb.append("<td>" + utente.getUsername() + "</td>");
            sb.append("<td>" + utente.getEmail() + "</td>");
            sb.append("<td>" + utente.getData_creazione() + "</td>");
            sb.append("<td>" + utente.getData_modifica() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</tbody>");
        sb.append("</table>");

        response.setContentType("text/html");
        response.getWriter().write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
