<%@page import="org.elis.model.Ruolo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="org.elis.model.Utente" %> 
    <%@ page import="java.util.List" %>
<%@ page import="org.elis.model.Gioco" %>
<%@ page import="org.elis.model.Genere" %>
<%@ page import="org.elis.model.Offerta" %> 
<%@ page import="org.elis.model.Libreria" %> 
<%@ page import="org.elis.model.Utente" %> 
<%@ page import="java.time.format.DateTimeFormatter" %>

<%@ page import="org.elis.model.Recensione" %> 
<%@ page import="java.text.SimpleDateFormat" %>

<%@page import="org.elis.businesslogic.BusinessLogic"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Steam Like - Games Page</title>
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
                <a href="<%= request.getContextPath() %>/public-jsp/PaginaLogin.jsp" class="btn btn-primary">Login</a>
            <%
                }
            %>
                </div>
  </div>

   <% 
		String messaggioErrore = (String) request.getAttribute("errorMessage");
		if (messaggioErrore != null) { 
		%>
		    <div class="errore">
		        <%= messaggioErrore %>
		    </div>
		<% 
		} 
		%>
		


		
<div class="game-details">
    <% 
        Gioco gioco = (Gioco) request.getAttribute("giochi");
        Utente u = (Utente) session.getAttribute("utenteLoggato");
        Offerta offerta = gioco.getOffertaGioco(); 
        

        if (gioco != null) {
    %>
    <div class="image-container" >
    <img class="product__image" src="data:image/jpeg;base64,<%= gioco.getImmagine() %>" />
    <!-- <iframe width="560" height="315"  src="https://www.youtube.com/embed/8X2kIfS6fb8?si=LSKF12jdeXRg9Vwz?autoplay=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe> -->
</div>
  
    <h1>Nome: <%= gioco.getNome() %></h1>
    <p>Descrizione: <%= gioco.getDescrzione() %></p>

    <div class="discount">
        <% if (offerta != null) { %>
            <h4 class="product-discount">Sconto: <%= Math.round(offerta.getSconto()) %>% off</h4>
            <h4 class="product-old-price" style="text-decoration: line-through; color: #999;">€<%= gioco.getPrezzo() %></h4>
            <h4 class="product-price" style="color: #f39c12; font-weight: bold;">
                Prezzo scontato: €<%= Math.round((gioco.getPrezzo() - (gioco.getPrezzo() * offerta.getSconto() / 100)) * 100.0) / 100.0 %>
            </h4>
        <% } else { %>
            <h4 class="product-price">Prezzo: €<%= gioco.getPrezzo() %></h4>
        <% } %>
    </div>

    <%
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>
<p>Data di rilascio: <%= gioco.getData_rilascio().format(formatter) %></p>
	<%
    
    List<Recensione> recensioniMax = BusinessLogic.TrovaRecensioneByIdGioco(gioco.getId());
    
    
   
   
    boolean haRecensioni = false;
    if (recensioniMax != null && u != null) {
        for (Recensione recensione : recensioniMax) {
            if (recensione.getRecensioneUtente().getId() == u.getId()) {
            	haRecensioni = true;
                break;
            }
        }
    }
%>

<div class="review-section">

		
<% if (u != null) {  %>
    <% if (u.getRuolo() == Ruolo.ADMIN || u.getRuolo() == Ruolo.PUBLISHER) { %>
        <p></p>
    <% } else { %>
        <% if (!haRecensioni) { %>
            <button class="btn-recensione" id="showReviewFormBtn" style="display: inline;">Lascia una recensione</button>
            <form action="<%=request.getContextPath() %>/RecensioneAggiungiServlet" class="review-form" id="reviewForm" style="display: none;" method="post">
                <h2>Lascia una recensione</h2>
                
                <label for="voto">Valutazione:</label>
                <select name="voto" id="voto" required>
                    <option value="" disabled selected>Seleziona un voto</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>

                <textarea name="recensione" placeholder="Scrivi la tua recensione qui..." required></textarea>
                <input type="hidden" value="<%=gioco.getId()%>" name="idGioco" id="idGioco">
                <button class="btn" type="submit">Invia</button>
            </form>
        <% } else { %>
            <p>Hai già lasciato una recensione per questo gioco.</p>
        <% } %>
    <% } %>
<% } else { %>
    <p>Accedi per lasciare una recensione.</p>
<% } %>

</div>



    
    
<div class="library-section">
    <% 
    if (u != null) { 
        List<Libreria> librerieUtente = ( List<Libreria>) request.getAttribute("libreriaUtente");
        boolean giocoPresente = false;
        boolean publisherAdmin = false;

        // Verifica se l'utente è PUBLISHER o ADMIN
        if (u.getRuolo() == Ruolo.PUBLISHER || u.getRuolo() == Ruolo.ADMIN) {
        	publisherAdmin = true; 
        } else {
            
            for (Libreria libreria : librerieUtente) {
                List<Gioco> giochiNellaLibreria = BusinessLogic.findGiochiByIdLibreria(libreria.getId());
                for (Gioco g : giochiNellaLibreria) {
                    if (g.getId() == gioco.getId()) {
                        giocoPresente = true;
                        break;  
                    }
                }
                if (giocoPresente) break;  
            }
        }

        // Se il gioco non è presente e l'utente non è un publisher o admin, mostra il form
        if (!giocoPresente && !publisherAdmin) { %>
            

            
            <form action="<%=request.getContextPath() %>/LibreriaGiocoAggiungiServlet" class="library-form" id="addForm" method="post">
                <h2>Scegli la libreria in cui aggiungere il gioco</h2>

                <label for="nomeLibreria">Seleziona una libreria:</label>
                <select name="nomeLibreria" id="nomeLibreria" required>
                    <% for (Libreria libreria : librerieUtente) { %>
                        <option value="<%= libreria.getNome() %>">
                            <%= libreria.getNome() %>
                        </option>
                    <% } %>
                </select>

                
                <input type="hidden" name="idGioco" value="<%= gioco.getId() %>">

               
                <button type="submit" class="btn-primary">Aggiungi</button>
            </form>
        <% } else if (giocoPresente) { %>
            <p>Il gioco è già nella tua libreria.</p>
        <% } else if (publisherAdmin) { %>
            <p></p>
        <% } 
    } %>
</div>






   <!-- Recensioni -->

<div class="user-reviews">
    <h2>Recensioni degli utenti</h2>
    <%
        List<Recensione> recensioni = BusinessLogic.TrovaRecensioneByIdGioco(gioco.getId());
        Utente utenteLoggato = (Utente) session.getAttribute("utenteLoggato"); 
        
        double sommaVoti = 0;
        int numeroRecensioni = 0;

        if (recensioni != null) {
            numeroRecensioni = recensioni.size();
            for (Recensione recensione : recensioni) {
                sommaVoti += recensione.getVoto();
            }
        }
        
        double mediaVoti = (numeroRecensioni > 0) ? (sommaVoti / numeroRecensioni) : 0;
    %>
    
    <h3>Numero totale di recensioni: <%= numeroRecensioni %></h3>
    <h4>Gioco Media valutazione: 
        <%
            for (int i = 1; i <= 5; i++) {
            	if (i <= Math.round(mediaVoti)) {
        %>
                    <i class="bi bi-star-fill" style="color: #f39c12;"></i>
        <%
                } else {
        %>
                    <i class="bi bi-star" style="color: #ddd;"></i>
        <%
                }
            }
    		double mediaVotiRounded=Math.round(mediaVoti);
        %>
        <%= (int)mediaVotiRounded %> / 5
    </h4>
    
    <%
        if (numeroRecensioni > 0) {
            for (Recensione recensione : recensioni) {
    %>
                <div class="recensione">
                    <h3><%= "Username : " +  recensione.getRecensioneUtente().getUsername() %></h3>
                    
                    <p><%= recensione.getTesto() %></p>
                    <p>Valutazione: 
                    <%
                        for (int i = 1; i <= 5; i++) {
                        	if (i <= recensione.getVoto()) {
                    %>
                                <i class="bi bi-star-fill" style="color: #f39c12;"></i>
                    <%
                            } else if(i>recensione.getVoto()) {
                    %>
                                <i class="bi bi-star" style="color: #ddd;"></i>
                    <%
                            }
                        }
                    %>
                    /5
                    </p>
                    
                    <% 
                        // Verifico se l'utente loggato ed sè l'autore della recensione , funzionaaaa lesgo
                        if(utenteLoggato!=null)
                        if (utenteLoggato.getId() == recensione.getRecensioneUtente().getId() || utenteLoggato.getRuolo().equals(Ruolo.ADMIN)) { 
                    %>
                        
                        <form action="<%= request.getContextPath() %>/RecensioneEliminaServlet" method="post">
                            <input type="hidden" name="idRecensione" value="<%= recensione.getId() %>">
                            <input type="hidden" name="idGioco" value="<%= gioco.getId() %>">
                            <button class="btn-rece" type="submit">Elimina</button>
                            
                        </form>
                     
				    
				    
    <button class="btn-recensione" onclick="toggleEditForm(<%= recensione.getId() %>)">Modifica</button>

    <!-- Form di modifica recensione -->
    <div id="editForm_<%= recensione.getId() %>" style="display:none;">
        <form action="<%= request.getContextPath() %>/RecensioneAggiornaServlet" method="post">
            <input type="hidden" name="idRecensione" value="<%= recensione.getId() %>">
            <input type="hidden" name="idGioco" value="<%= gioco.getId() %>">
            <label for="voto">Voto:</label>
            <input type="number" name="voto" value="<%= recensione.getVoto() %>" min="1" max="5">
            <label for="testo">Testo:</label>
            <textarea name="testo"><%= recensione.getTesto() %></textarea>
            <button class="btn btn-danger" type="submit">Modifica Recensione</button>
        </form>
    </div>

 </div>                   <% 
                        } 
                    %>
                </div>
    <%
            }
        } else {
    %>
            <p>Non ci sono recensioni per questo gioco.</p>
    <%
        }
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
  <script src="<%= request.getContextPath() %>/Js/DettagliGiocoScript.js"></script>

</body>
</html>
