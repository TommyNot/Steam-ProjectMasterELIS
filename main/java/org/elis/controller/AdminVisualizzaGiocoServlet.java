package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;

import com.mysql.cj.jdbc.Blob;

/**
 * Servlet implementation class AdminVisualizzaGiocoServlet
 */
public class AdminVisualizzaGiocoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminVisualizzaGiocoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gameName = request.getParameter("nome");
        List<Gioco> giochi = BusinessLogic.VisualizzaTuttiGiochi();
        
        for (Gioco gioco : giochi) {
            if (gioco.getNome().equals(gameName)) {
                Long id = gioco.getId();
                byte[] byteImmagine = gioco.getByteImmagine();
                
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + id + ".img");
                
                ServletOutputStream out = response.getOutputStream();
                out.write(byteImmagine);
                out.flush();
                break;
            }
        }
	}
}