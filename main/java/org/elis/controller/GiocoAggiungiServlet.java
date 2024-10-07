package org.elis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

/**
 * Servlet implementation class addGiocoServlet
 */
@MultipartConfig(maxFileSize=1024*1024*20, maxRequestSize=1024*1024*50)
public class GiocoAggiungiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GiocoAggiungiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessione = request.getSession(false);

        
        String nome = request.getParameter("nome");
        String dataRilascio = request.getParameter("dataRilascio"); 
        String descrizione = request.getParameter("descrizione");
        Part immaginePart = request.getPart("immagine"); 
        
     
        salvaImmagineSuFile(immaginePart, request);
        
        String nomeFile = immaginePart.getSubmittedFileName();

        String prezzo = request.getParameter("prezzo");
        String offerta = request.getParameter("offerta"); 
        String generi = request.getParameter("genere");
        
        System.out.println(offerta);
        System.out.println(generi);

       
        

        
        if (nome == null || nome.isEmpty() || dataRilascio == null || descrizione == null || immaginePart == null || prezzo == null) {
            request.setAttribute("errore", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("WEB-INF/private-jsp/DashboardPublisher.jsp").forward(request, response);
            return;
        }
        
        LocalDate data = null;
        if (dataRilascio != null && !dataRilascio.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                data = LocalDate.parse(dataRilascio, formatter); 
            } catch (DateTimeParseException e) {
                request.setAttribute("errore", "Errore nella formattazione della data: " + e.getMessage());
                request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                System.out.println("errore nella data");
                return; 
            }
        }

        double prezzoDouble;
        try {
            prezzoDouble = Double.parseDouble(prezzo);
            if (prezzoDouble <= 0) {
                System.out.println("errore prezzo minore di 0");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "Errore nel formato del prezzo: " + e.getMessage());
            request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
            System.out.println("Errore nel prezzo");
            return;
        }

      
        
        Genere genereSelezionato = null;
        
        if(generi == null) {
        	
        	System.out.println("errore");
        }
        
        long genereId;
        
        try {
        	genereId= Long.parseLong(generi);
        	genereSelezionato = BusinessLogic.getGenereById(genereId);
        	System.out.println("fin qui tutto bene");
        	
        }catch(Exception e) {
        	
        	e.printStackTrace();
        }
        
 
        
        
        
        Offerta offertaSelezionata = null;

        if (offerta == null) {
        	
        	System.out.println("errore offerta");
            
            
        }
        
        long offertaId;
        
        try {
        	
        	offertaId = Long.parseLong(offerta);
        	offertaSelezionata = BusinessLogic.findOffertaById(offertaId);
        	System.out.println("qui tutto bene");
        	
        }catch(NumberFormatException e ){
        	
        	e.printStackTrace();
        	
        }
        
        

        // Controllo sessione
        if (sessione == null) {
            response.sendRedirect("public-jsp/LoginPage.jsp");
            return;
        }

        Utente utente = (Utente) sessione.getAttribute("utenteLoggato");
        
        

        if (utente != null) {
            long idUtente1 =  utente.getId();
            System.out.println("ID Utente loggato: " + idUtente1);
            
            Utente u = BusinessLogic.UtenteFindById(idUtente1);
            if (u != null) {
                boolean isPublisher = u.getRuolo() == Ruolo.PUBLISHER;
                if (isPublisher) {
                    System.out.println("L'utente è un Publisher.");
                    Gioco aggiunto = BusinessLogic.GiocoAdd(nome, data, descrizione,nomeFile , prezzoDouble, genereSelezionato , offertaSelezionata, u);
                    if (aggiunto != null) {
                        response.sendRedirect("successPage.jsp");
                    } else {
                        request.setAttribute("errore", "Errore nell'aggiunta del gioco.");
                        request.getRequestDispatcher("public-jsp/DashboardPublisher.jsp").forward(request, response);
                    }
                } else {
                    System.out.println("L'utente non è un Publisher.");
                }
            } else {
                System.out.println("Errore: utente non trovato con ID: " + idUtente1);
            }
        } else {
            System.out.println("Nessun utente loggato trovato nella sessione.");
        }

    }
    
    void salvaImmagineSuFile(Part filePart, HttpServletRequest request) {
    	File file = new File(request.getServletContext().getRealPath("/")+"risorse-media/img_giochi/"+filePart.getSubmittedFileName());
    	if(!file.exists()) {
    		try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	try(InputStream fis= filePart.getInputStream();
    		OutputStream os = new FileOutputStream(file)){
    		byte[] buffer = new byte[1024];
    		int byteRead = 0;
    		while((byteRead=fis.read(buffer))!=-1) {
    			os.write(buffer);
    		}
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
