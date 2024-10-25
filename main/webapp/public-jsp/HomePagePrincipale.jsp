<%@page import="org.elis.model.Ruolo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <title>SteamPezzotto Homepage</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Css/HomePagePrincipaleCss.css">
    
    
</head>
<body>
    <header>
        <div class="container">
         <div id="logo">
         <span class="logo"><a href="<%= request.getContextPath() %>/public-jsp/HomePagePrincipale.jsp">
          <img alt="logo" src="<%= request.getContextPath() %>/risorse-media/img_homepage/logo.png" style="width: 200px; height: auto; margin: 10px;">
          </a>
         </span>
    </div>
            
            
  
  <nav>
    <ul>
        <li><a href="#">Homepage</a></li>
        <li>
            <a href="#" class="generi-link-giochi">Novità e Tendenze</a>
            <div class="dropdown-giochi">
                <ul class="styled-select-giochi">
                    <li><a href="<%=request.getContextPath() %>/GiocoListaCompletaServlet">Giochi</a></li>
                    <li><a href="<%=request.getContextPath() %>/OffertaVediTuttiGiochi">Offerte</a></li>
                    <li><a href="<%=request.getContextPath() %>/OffertaVediTuttiGiochi">Tendenza</a></li>
                </ul>
            </div>
        </li>
        <li>
            <a href="#" class="generi-link">Generi</a>
            <div class="dropdown">
                <ul class="styled-select">
                    <%
                        List<Genere> generi = BusinessLogic.VisalizzaTuttiGeneri();
                        Genere genereSelezionato = (Genere) request.getAttribute("genere");
                        for (Genere genere : generi) {
                    %>
                    <li>
                        <a href="<%=request.getContextPath() %>/GiocoGenereRicercaServlet?genereId=<%= genere.getId() %>" class="dropdown-option" value="genereId">
                            <%= genere.getNome() %>
                        </a>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </li>

        <!-- Mostra Libreria solo se l'utente NON è Publisher o Admin -->
        <%
            Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato");
            if (utenteLoggato != null && utenteLoggato.getRuolo() == Ruolo.UTENTE_BASE) {
        %>
        <li>
            <a href="<%=request.getContextPath() %>/LibreriaFindByIdUtenteServlet">Libreria</a>
        </li>
        <%
            }
        %>

        <li><a href="<%=request.getContextPath() %>/ControlloSessioniServlet">Dashboard</a></li>

        <div class="search-container">
            <form action="<%= request.getContextPath() %>/GiocoCercaServlet" method="get" class="d-flex">
                <input type="text" id="barraRicerca" name="barraRicerca" placeholder="Cerca gioco..." class="form-control me-2" aria-label="Search">
                <button type="submit" class="btn btn-outline-success">Cerca</button>
            </form>
        </div>
    </ul>
</nav>

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
        
        
 
    
    </header>
    
    
<section class="hero">
    <div class="container">
        <h1>Benvenuto su Steam</h1>
        <p>Trova i migliori giochi a prezzi incredibili!</p>
        <a href="<%=request.getContextPath() %>/GiocoListaCompletaServlet" class="btn-primary">Scopri di più</a>
    </div>

    <div class="slider-2">
        <div class="slides-2">
            <% List<Gioco> giochi = BusinessLogic.VisualizzaTuttiGiochi(); %>
            <% for (int i = 0; i < giochi.size(); i += 3) { %>
                <div class="slide-2">
                   
                    <% for (int j = 0; j < 3 && (i + j) < giochi.size(); j++) { %>
                        <img class="product__image" src="data:image/jpeg;base64,<%= giochi.get(i + j).getImmagine() %>" alt="Immagine Gioco" />
                    <% } %>

    
                </div>
            <% } %>
        </div>
    </div>
</section>



    
  
<section class="featured-games">
    <div class="container">
        <h2>Giochi in Offerta</h2>
        <div class="slider-wrapper">
             
            <div class="slider">
                <div class="slides">
                    <% 
                        List<Gioco> giochiInOfferta = BusinessLogic.VisualizzaGiochiInOfferta();
                        for (Gioco gioco : giochiInOfferta) { 
                        	Offerta offerta = gioco.getOffertaGioco(); 
                    %>
              <div class="slide-offerta">
				    <div class="discount-badge">Offerta Speciale: -<%= Math.round(offerta.getSconto()) %>%</div>

				    <img class="product__image_offerta" src="data:image/jpeg;base64,<%= gioco.getImmagine() %>" alt="Immagine Gioco" />
				    <div class="game-info">
				        <h4><%= gioco.getNome() %></h4>
				        
				    </div>
				</div>

                    <% } %>
                </div>
            </div>
             
        </div>
    </div>
</section>

    




 
	



    

      <footer class="footer">

        <div class="footer-container">
            <div class="footer-section">
                <h3>Contatti</h3>
                <p>Email: <a href="mailto:info@example.com">SteamPezzotto@example.com</a></p>
                <p>Phone: +39 3289998244</p>
                
            </div>

            <div class="footer-section">
                <h3>Seguici</h3>
                
                <a href="#" class="social-icon"><i class="bi bi-whatsapp"></i></a>
                <a href="#" target="_blank" class="social-icon"><i class="bi bi-instagram"></i></a>
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
	
	
   <script src="<%=request.getContextPath()%>/Js/HomePagePrincipaleScript.js"></script>
</body>
</html>
