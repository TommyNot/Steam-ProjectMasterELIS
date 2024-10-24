package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

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
        // Recupera l'ID del gioco da eliminare
        String eliminaGiocoNome = request.getParameter("productId");

        // Verifica se la sessione esiste
        HttpSession sessione = request.getSession(false);
        if (sessione == null) {
            response.sendRedirect("public-jsp/PaginaLogin.jsp");
            return;
        }

        // Recupera l'utente loggato dalla sessione
        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
        
        // Verifica se l'utente ha un ruolo appropriato (deve essere almeno un PUBLISHER)
        if (utente.getRuolo() == Ruolo.UTENTE_BASE) {
            response.sendRedirect("public-jsp/ErrorAccessoNegatoPage.jsp");
            return;
        }

        // Controlla se l'ID del gioco è valido
        if (eliminaGiocoNome == null || eliminaGiocoNome.isBlank()) {
            String errore = "Il nome del gioco non può essere vuoto.";
            request.setAttribute("errore", errore); 
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardPublisher.jsp").forward(request, response);
            return;
        }

        long idGioco = 0;
        try {
            idGioco = Long.parseLong(eliminaGiocoNome);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            String errore = "Formato ID gioco non valido.";
            request.setAttribute("errore", errore);
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardPublisher.jsp").forward(request, response);
            return;
        }

        // Recupera il gioco dal database
        Gioco giocoEliminato = BusinessLogic.findGiocoById(idGioco);

        if (giocoEliminato == null) {
            String errore = "Il gioco con ID '" + eliminaGiocoNome + "' non è stato trovato.";
            request.setAttribute("errore", errore);
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardPublisher.jsp").forward(request, response);
            return;
        }

       
        switch (utente.getRuolo()) {
            case PUBLISHER:
               
                if (giocoEliminato.getIdUtente().getId() == utente.getId()) {
                    
                    BusinessLogic.eliminaGioco(idGioco);
                    String successo = "Il gioco '" + eliminaGiocoNome + "' è stato eliminato con successo.";
                    request.setAttribute("successo", successo);
                    request.getRequestDispatcher("WEB-INF/private-jsp/DashboardPublisher.jsp").forward(request, response);
                } else {
                    
                    String errore = "Non hai i permessi per eliminare questo gioco.";
                    request.setAttribute("errore", errore);
                    request.getRequestDispatcher("WEB-INF/private-jsp/DashboardPublisher.jsp").forward(request, response);
                }
                break;

            case ADMIN:
                
                BusinessLogic.eliminaGioco(idGioco);
                response.getWriter().write("Gioco eliminato con successo.");
                break;

            default:
                // Ruolo non autorizzato
                request.getRequestDispatcher("WEB-INF/public-jsp/ErrorAccessoNegatoPage.jsp").forward(request, response);
                break;
        }
    }

}
