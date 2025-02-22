<%@page import="org.elis.model.Ruolo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="org.elis.model.Utente" %> 
    <%@ page import="java.util.List" %>
<%@ page import="org.elis.model.Gioco" %>
<%@ page import="org.elis.model.Genere" %>
<%@ page import="org.elis.model.Offerta" %> 
<%@ page import="org.elis.model.Utente" %> 
<%@page import="org.elis.businesslogic.BusinessLogic"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SteamPezzotto Pagina Giochi</title>
     <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/PageGiochiCss.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" type="text/css">
</head>
<body>
  <!-- Barra di navigazione -->
  <div class="navbar">
    <div id="logo">
         <span class="logo"><a href="<%= request.getContextPath() %>/public-jsp/HomePagePrincipale.jsp">
          <img alt="logo" src="<%= request.getContextPath() %>/risorse-media/img_homepage/logo.png" style="width: 200px; height: auto; margin: 10px;">
          </a>
         </span>
    </div>
    <div class="menu">
      <a href="<%= request.getContextPath() %>/GiocoListaCompletaServlet">Homepage</a>
			 <a href="#" class="generi-link">Generi</a>
					 <div class="dropdown">
						     <ul class="styled-select">
						            <%
								    List<Genere> generi = BusinessLogic.VisalizzaTuttiGeneri();
								    if (generi != null && !generi.isEmpty()) {
								        for (Genere genere : generi) {
								%>
								        <li>
								            <a href="<%=request.getContextPath() %>/GiocoGenereRicercaServlet?genereId=<%= genere.getId() %>" class="dropdown-option">
								                <%= genere.getNome() %>
								            </a>
								        </li>
								<%
								        }
								    } else {
								        out.println("Nessun genere trovato");
								    }
								%>
						
						        </ul>
						    </div>
						
      <a href="<%=request.getContextPath() %>/OffertaVediTuttiGiochi">Offerte</a>
      		<!-- Mostra Libreria solo se l'utente NON è Publisher o Admin -->
								        <%
								            Utente utenteLoggatoLibre = (Utente) session.getAttribute("utenteLoggato");
								            if (utenteLoggatoLibre != null && utenteLoggatoLibre.getRuolo() == Ruolo.UTENTE_BASE) {
								        %>
								       
								            <a href="<%=request.getContextPath() %>/LibreriaFindByIdUtenteServlet">Libreria</a>
								        
								        <%
								            }
								        %>
      <a href="<%=request.getContextPath() %>/ControlloSessioniServlet">Dashboard </a>
 
    </div>
         <div class="search-container">
      <form action="<%= request.getContextPath() %>/GiocoCercaServlet" method="get" class="d-flex">
                <input type="text" id="barraRicerca" name="barraRicerca" placeholder="Cerca gioco..." class="form-control me-2" aria-label="Search">
                <button type="submit" class="btn btn-outline-success">Cerca</button>
            </form>
       </div>
       
       

       
       
    
    <div class="user-info">
                    
                    <%
                
                Utente username = (Utente) session.getAttribute("utenteLoggato");
                if (username != null) {
            %>
            	 <img src="<%=request.getContextPath() %>/risorse-media/img_giochi/profilo.jpeg" alt="User Profile Picture" id="imgUtente"> 
                <p>Welcome, <%= username.getUsername() %></p>
                <a href="<%= request.getContextPath() %>/LogoutServlet" class="btn btn-danger">Logout</a>
            <%
                } else {
            %>
                <a href="<%= request.getContextPath() %>/public-jsp/PaginaLogin.jsp" class="btn btn-primary">Accedi o Registrati</a>
            <%
                }
            %>
                </div>
  </div>


		


		
<div class="content">
    <%
        List<Gioco> giochi = (List<Gioco>) request.getAttribute("giochi");

        if (giochi == null || giochi.isEmpty()) {
            giochi = BusinessLogic.VisualizzaTuttiGiochi(); 
        }

        String messaggioErrore = (String) request.getAttribute("Error");
        if (messaggioErrore != null) { 
    %>
            <div class="errore">
                <%= messaggioErrore %>
            </div>
    <%
        }

        if (giochi == null || giochi.isEmpty()) {
    %>
            <div class="errore">
                <p>Nessun gioco disponibile al momento.</p>
            </div>
    <%
        } else {
    %>
            <div class="games-container"> 
                <%
                for (Gioco gioco : giochi) { 
                    Offerta offerta = gioco.getOffertaGioco(); 
                %>
                    <div class="game">
                      <a href="GiocoVediDettagli?barraRicerca=<%= gioco.getId() %>">
                        <img class="product__image" src="data:image/jpeg;base64,<%= gioco.getImmagine() %>" style="width: 220px; height: 300px;" />
                      </a>  
                        <h3 class="product-title"><%= gioco.getNome() %></h3>
                        
                        <div class="discount">
                            <% if (offerta != null) { %>
                                <h4 class="product-discount"><%= Math.round(offerta.getSconto()) %>% off</h4>
                                <h4 class="product-old-price">€<%= gioco.getPrezzo() %></h4>
                                <h4 class="product-price">Prezzo scontato: €<%= Math.round((gioco.getPrezzo() - (gioco.getPrezzo() * offerta.getSconto() / 100)) * 100.0) / 100.0 %></h4>
                            <% } else { %>
                                <h4 class="product-price">Prezzo: €<%= gioco.getPrezzo() %></h4>
                            <% } %>
                        </div>
                       
                        
                        <form action="<%= request.getContextPath() %>/GiocoVediDettagli" method="get">
                            <input type="hidden" value="<%= gioco.getId() %>" name="barraRicerca" id="barraRicerca">
                            <button class="btn">Visualizza dettagli</button>
                        </form>
                    </div>
                <%
                } 
                %>
            </div> 
    <%
        } 
    %>
</div>





       <footer class="footer">

        <div class="footer-container">
            <div class="footer-section">
                <h3>Contatti</h3>
                <p>Email: <a href="mailto:info@example.com">SteamPezzotto@example.com</a></p>
                <p>Phone: +39 328-9998244</p>
                
            </div>

            <div class="footer-section">
                <h3>Seguici</h3>
                
                <a href="#" class="social-icon"><i class="bi bi-whatsapp"></i></a>
                <a href="https://www.instagram.com/steampezzottofamily/profilecard/?igsh=dXNha3Q5NWZtOHV1" target="_blank" class="social-icon"><i class="bi bi-instagram"></i></a>
                <a href="#" class="social-icon"><i class="bi bi-tiktok"></i></a>
            </div>

            <div class="footer-section">
                <h3>Chi siamo?</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
            </div>

        </div>

        <div class="footer-bottom">
            <p>&copy; 2024 Steam Family Pezzotto. All rights reserved.</p>
            <p>Developed by Steam Family Pezzotto</p>
        </div>

    </footer>
  
  <script src="<%= request.getContextPath() %>/Js/PageGiochi.js"></script>
</body>
</html>
