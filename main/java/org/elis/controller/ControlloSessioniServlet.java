package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.elis.model.Utente;


/**
 * Servlet implementation class ControlloSessioniServlet
 */
public class ControlloSessioniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloSessioniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Utente userInSession = (Utente)request.getSession().getAttribute("utenteLoggato");
		
		if(userInSession != null) {
			
			switch(userInSession.getRuolo()){
				
				case UTENTE_BASE:
					request.getRequestDispatcher("public-jsp/DashboardUtente.jsp").forward(request, response);
					break;
				case ADMIN:
					
					request.getRequestDispatcher("WEB-INF/private-jsp/DashboardAdmin.jsp").forward(request, response);
					break;
				
				case PUBLISHER:
					request.getRequestDispatcher("WEB-INF/private-jsp/DashboardPublisher.jsp").forward(request, response);
					break;
					
					default:
						request.getRequestDispatcher("public-jsp/ErrorAccessoNegatoPage.jsp").forward(request, response);
							break;
				
			}
		}
		
		request.getRequestDispatcher("public-jsp/PaginaLogin.jsp").forward(request, response);
	}

}
