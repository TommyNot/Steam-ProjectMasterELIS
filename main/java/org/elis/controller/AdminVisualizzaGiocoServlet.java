package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Gioco;
import org.hibernate.internal.build.AllowSysOut;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gameName = request.getParameter("name");
        System.out.println("Nome del gioco ricevuto: " + gameName);
        System.out.println("prima metodo");
        
        List<Gioco> giochi = BusinessLogic.VisualizzaTuttiGiochi();
        System.out.println("Numero di giochi trovati: " + giochi.size());
        
        System.out.println("prima for");
        
        for (Gioco gioco : giochi) {
            if (gioco.getNome().equals(gameName)) {
                System.out.println("Gioco trovato: " + gioco.getNome());

                Long id = gioco.getId();
                byte[] byteImmagine = gioco.getByteImmagine();

                response.setContentType("application/octet-stream");
                if (byteImmagine == null || byteImmagine.length == 0) {
                    byteImmagine = Files.readAllBytes(Paths.get(getServletContext().getRealPath("/risorse-media/img_giochi/Default.jpg")));
                    System.out.println("Usando immagine di default");
                }
                response.setHeader("Content-Disposition", "attachment;filename=" + id + ".img");
                response.setHeader("Game-ID", String.valueOf(id));

                ServletOutputStream out = response.getOutputStream();
                out.write(byteImmagine);
                out.flush();
                break;
            }
        }
    }
}