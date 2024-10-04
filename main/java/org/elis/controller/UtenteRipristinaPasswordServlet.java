package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Utente;

/**
 * Servlet implementation class UtenteRipristinaPasswordServlet
 */
public class UtenteRipristinaPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtenteRipristinaPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("password");
        
        if (username == null || email == null || newPassword == null || newPassword.isEmpty()) {
            request.setAttribute("errorMessage", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
            return;
        }
        
        Utente nuovaPassword=BusinessLogic.RipristinaPassword(username, email, newPassword);
        if (nuovaPassword != null) {
			response.getWriter().write("Password aggiornata con successo!");
		} else {
			request.getRequestDispatcher("public-jsp/error.jsp").forward(request, response);
		}
		 request.getRequestDispatcher("WEB-INF/private-jsp/PaginaLogin.jsp").forward(request, response);
	}

}
