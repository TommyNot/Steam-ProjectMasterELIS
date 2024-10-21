<%@page import="org.elis.model.Gioco"%>
<%@page import="org.elis.model.Libreria"%>
<%@page import="java.util.List"%>
<%@page import="org.elis.businesslogic.BusinessLogic"%>
<%@page import="org.elis.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
	 <title>Libreria Page</title>
  	 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  	 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
  	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
     <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/LibreriaGiochiCss.css">

</head>
<body>
	 <nav class="navbar navbar-expand-lg colore" data-bs-theme="dark">
            <div class="container-fluid">
              <a class="navbar-brand" href="<%= request.getContextPath() %>/public-jsp/HomePagePrincipale.jsp">
                <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
		              width="90px" height="22px" viewBox="0 0 355.666 89.333" enable-background="new 0 0 355.666 89.333"
		              xml:space="preserve">
		             <g>
		             <path fill="#C5C3C0" d="M44.238,0.601C21,0.601,1.963,18.519,0.154,41.29l23.71,9.803c2.009-1.374,4.436-2.179,7.047-2.179
		                 c0.234,0,0.467,0.008,0.698,0.021l10.544-15.283c0-0.073-0.001-0.144-0.001-0.216c0-9.199,7.483-16.683,16.683-16.683
		                 c9.199,0,16.682,7.484,16.682,16.683c0,9.199-7.483,16.684-16.682,16.684c-0.127,0-0.253-0.003-0.379-0.006l-15.038,10.73
		                 c0.008,0.195,0.015,0.394,0.015,0.592c0,6.906-5.617,12.522-12.522,12.522c-6.061,0-11.129-4.326-12.277-10.055L1.678,56.893
		                 c5.25,18.568,22.309,32.181,42.56,32.181c24.432,0,44.237-19.806,44.237-44.235C88.475,20.406,68.669,0.601,44.238,0.601"/>
		             <path fill="#C5C3C0" d="M27.875,67.723l-5.434-2.245c0.963,2.005,2.629,3.684,4.841,4.606c4.782,1.992,10.295-0.277,12.288-5.063
		                 c0.965-2.314,0.971-4.869,0.014-7.189c-0.955-2.321-2.757-4.131-5.074-5.097c-2.299-0.957-4.762-0.922-6.926-0.105l5.613,2.321
		                 c3.527,1.47,5.195,5.52,3.725,9.047C35.455,67.526,31.402,69.194,27.875,67.723"/>
		             <path fill="#C5C3C0" d="M69.95,33.436c0-6.129-4.986-11.116-11.116-11.116c-6.129,0-11.116,4.987-11.116,11.116
		                 c0,6.13,4.987,11.115,11.116,11.115C64.964,44.55,69.95,39.565,69.95,33.436 M50.502,33.417c0-4.612,3.739-8.35,8.351-8.35
		                 c4.612,0,8.351,3.738,8.351,8.35s-3.739,8.35-8.351,8.35C54.241,41.767,50.502,38.028,50.502,33.417"/>
		             <path fill="#C5C3C0" d="M135.718,30.868l-2.964,5.21c-2.283-1.595-5.377-2.555-8.078-2.555c-3.087,0-4.997,1.278-4.997,3.567
		                 c0,2.781,3.393,3.428,8.436,5.238c5.421,1.917,8.537,4.17,8.537,9.135c0,6.793-5.342,10.608-13.02,10.608
		                 c-3.742,0-8.256-0.966-11.726-3.077l2.162-5.776c2.819,1.489,6.191,2.372,9.197,2.372c4.052,0,5.978-1.495,5.978-3.705
		                 c0-2.529-2.937-3.289-7.678-4.859c-5.403-1.804-9.147-4.171-9.147-9.666c0-6.197,4.963-9.756,12.104-9.756
		                 C129.499,27.604,133.499,29.181,135.718,30.868"/>
		             <polygon fill="#C5C3C0" points="158.888,34.161 158.888,61.5 151.909,61.5 151.909,34.161 141.779,34.161 141.779,28.175 
		                 168.988,28.175 168.988,34.161 	"/>
		             <polygon fill="#C5C3C0" points="183.7,34.143 183.7,41.652 197.056,41.652 197.056,47.638 183.7,47.638 183.7,55.459 
		                 199.196,55.459 199.196,61.5 176.723,61.5 176.723,28.175 199.196,28.175 199.196,34.143 	"/>
		             <path fill="#C5C3C0" d="M214.773,55.03l-2.206,6.471h-7.316l12.495-33.325h7.025L237.619,61.5h-7.563l-2.254-6.471H214.773z
		                  M221.219,36.125l-4.551,13.343h9.196L221.219,36.125z"/>
		             <polygon fill="#C5C3C0" points="273.436,41.056 264.316,60.529 260.378,60.529 251.406,41.23 251.406,61.5 244.723,61.5 
		                 244.723,28.175 251.391,28.175 262.591,52.231 273.393,28.175 280.119,28.175 280.119,61.5 273.437,61.5 	"/>
		             <path fill="#C5C3C0" d="M293.611,32.379c0,2.864-2.146,4.649-4.609,4.649c-2.472,0-4.623-1.785-4.623-4.649
		                 c0-2.863,2.151-4.636,4.623-4.636C291.466,27.743,293.611,29.516,293.611,32.379 M285.154,32.379c0,2.396,1.726,3.901,3.848,3.901
		                 c2.114,0,3.833-1.505,3.833-3.901c0-2.403-1.719-3.885-3.833-3.885C286.886,28.494,285.154,29.994,285.154,32.379 M289.066,30.01
		                 c1.195,0,1.597,0.632,1.597,1.315c0,0.626-0.371,1.046-0.823,1.26l1.071,2.007h-0.877l-0.903-1.779H288.2v1.779h-0.73V30.01
		                 H289.066z M288.207,32.142h0.814c0.527,0,0.838-0.331,0.838-0.747c0-0.42-0.223-0.69-0.84-0.69h-0.813V32.142z"/>
		             </g>
		             </svg>
              </a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <%
                        Utente utente = (Utente) session.getAttribute("utenteLoggato");
                    	long idUtente;
                        if (utente != null) {
                        	
                        	idUtente = utente.getId();
                    %>
                        <%= utente.getUsername() %>
                         <%
                        } else {
                    %>
                   	 Utente non loggato
                    <%
                        }
                    %>
                        <img id="avatar" alt="avatar utente" class="img-icon">
                    </a>
                    <ul class="dropdown-menu">
                      <li><a class="dropdown-item" href="<%= request.getContextPath()%>/public-jsp/PageGiochi.jsp">Negozio</a></li>
                      <li><a class="dropdown-item" href="<%= request.getContextPath()%>/LogoutServlet">Logout</a></li>
                      <li><hr class="dropdown-divider"></li>
                      <li><a class="dropdown-item" href="<%= request.getContextPath()%>/public-jsp/DashboardUtente.jsp">Torna alla tua Dashboard</a></li>
                    </ul>
                  </li>
                </ul>
                <form action="<%= request.getContextPath() %>/GiocoCercaServlet" class="d-flex" role="search">
                  <input class="form-control me-2" type="search" placeholder="Cerca un gioco" name="barraRicerca" aria-label="Search">
                  <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
              </div>
            </div>
        </nav>
        
       <div class="container">
        	<div class="container-2">
        		<h2 class="testo-lista-librerie">Le tue librerie</h2>
        		<button id="toggle-form-aggiungi-btn" class="testo-lista-librerie ancore">Aggiungi libreria</button>
        		<button id="toggle-form-modifica-btn" class="testo-lista-librerie ancore">Modifica nome libreria</button>
        		<button id="toggle-form-elimina-btn" class="testo-lista-librerie ancore">Elimina libreria</button>
        	</div>
        	
        	<div class="container-3" id="form-container-aggiungi" style="display: none;">
			    <form action="<%= request.getContextPath()%>/LibreriaAggiungiServlet" method="post" class="form">
			        <label for="nome" class="etichetta">Nome nuova libreria:</label>
			        <input type="text" id="nome" name="nomeLibreriaInput" required>
			        
			        <div class="button-group">
			            <button type="submit">Aggiungi libreria</button>
			            <button type="reset">Reset</button>
			        </div>
			    </form>
			</div>
			
					<%
				        String messaggioSuccesso = (String) request.getAttribute("messaggioSuccesso");
				        if (messaggioSuccesso != null) {
				    %>
				        <div class="success">
				            <%= messaggioSuccesso %>
				        </div>
				    <%
				        }
				    %>
			<div class="container-3" id="form-container-modifica" style="display: none;">
			    <form action="<%= request.getContextPath()%>/LibreriaAggiornaNomeServlet" method="post" class="form">
			    	<label for="nome" class="etichetta">Inserisci l'ID della libreria da modificare:</label>
			        <input type="text" id="nome" name="idLibreria" required>
			        <label for="nome" class="etichetta">Nuovo nome libreria:</label>
			        <input type="text" id="nome" name="nomeNuovoInput" required>
			        
			        <div class="button-group">
			            <button type="submit">Modifica nome libreria</button>
			            <button type="reset">Reset</button>
			        </div>

			    </form>
			</div>
			
			
			<div class="container-3" id="form-container-rimuovi" style="display: none;">
			    <form action="<%= request.getContextPath()%>/LibreriaEliminaServlet" method="post" class="form">
			        <label for="nome" class="etichetta">Inserisci l'ID della libreria da rimuovere:</label>
			        <input type="text" id="nome" name="libreriaId" required>
			        
			        <div class="button-group">
			            <button type="submit">Elimina libreria</button>
			            <button type="reset">Reset</button>
			        </div>
			    </form>
			</div>
		<h3 class="testo-lista-librerie">I tuoi giochi</h3>
      <div class="container-2">
    	<div class="list-group">
        <%
            // Ottieni la lista delle librerie associate all'utente
            List<Libreria> librerie = (List<Libreria>) request.getAttribute("librerieUtente");
            Utente utenteId = (Utente) session.getAttribute("utenteLoggato");
            idUtente = utenteId.getId();

            // Verifica se ci sono librerie disponibili
            if (librerie == null || librerie.isEmpty()) { 
        %>
            <p class="testo-lista-librerie">Nessuna libreria disponibile</p>
        <%
            } else {
                // Crea i link per ogni libreria
                for (Libreria l : librerie) { 
        %>
            <a href="LibreriaFindByIdUtenteServlet?id_libreria=<%= l.getId() %>" 
               class="list-group-item list-group-item-action list-group-item-dark">
               ID: <%= l.getId() %> <br> Nome: <%= l.getNome() %>
            </a>
        <%
                }
            } 
        %>
    </div>
		
    <!-- Visualizza i giochi associati alla libreria selezionata -->
    
        <%
            // Recupera i giochi dalla libreria selezionata
            List<Gioco> giochiUtente = (List<Gioco>) request.getAttribute("giochi"); %>
	<div class="games-cont">
        <%   if (giochiUtente != null && !giochiUtente.isEmpty()) {
                for (Gioco gioco : giochiUtente) {
        %>
        <div class="card mx-2 game">
        	<img src="data:image/jpeg;base64,<%= gioco.getImmagine() %>" class="card-img-top" alt="immagine gioco">
        	<div class="card-body carta">
    			<h5 class="card-title"><%= gioco.getNome() %></h5>
    			<p class="card-text">ID gioco: <%= gioco.getId() %>
  			</div>
        </div>
        <% }
         }
         %>
    </div>
	</div>
</div>

        
        <footer>
		   <div class="footer-container">
		   		<section class="footer-about">
				      <h2>Gioca. Scopri. Connettiti.</h2>
				      <p>Steam è la tua piattaforma per il gioco definitivo. Trova il tuo prossimo grande titolo, entra a far parte di una community globale di giocatori e crea le tue avventure digitali.</p>
				</section>
		        <section class="footer-social">
		        	<h3 style="display: flex; justify-content: center; margin-right: 3%;">Seguici</h3>
		        	<div class="social-icons">
		                 <a href="https://www.facebook.com/?locale=it_IT" target="_blank">
		                 	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="black" class="bi bi-facebook" viewBox="0 0 16 16">
		                      <path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951"/>
		                  	</svg>
		                  </a>
		                  <a href="https://x.com/?lang=it" target="_blank" style="margin: 0 1em;">
		                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="black" class="bi bi-twitter-x" viewBox="0 0 16 16">
		                          <path d="M12.6.75h2.454l-5.36 6.142L16 15.25h-4.937l-3.867-5.07-4.425 5.07H.316l5.733-6.57L0 .75h5.063l3.495 4.633L12.601.75Zm-.86 13.028h1.36L4.323 2.145H2.865z"/>
		                      </svg>
		                   </a>
		                   <a href="https://www.instagram.com/" target="_blank">
		                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="black" class="bi bi-instagram" viewBox="0 0 16 16">
		                        <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.9 3.9 0 0 0-1.417.923A3.9 3.9 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.9 3.9 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.9 3.9 0 0 0-.923-1.417A3.9 3.9 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599s.453.546.598.92c.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.5 2.5 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.5 2.5 0 0 1-.92-.598 2.5 2.5 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233s.008-2.388.046-3.231c.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92s.546-.453.92-.598c.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92m-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217m0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334"/>
		                        </svg>
		                    </a>
		               </div>
		               </section>
		               <section class="footer-copyright">
		               		<p>© 2024 Steam Pezzotto Family. Progetto Steam e il logo di Steam sono usati a scopo puramente didattico.</p>
		                </section>
		          </div>
		 </footer>                 
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>                 
	<script src="<%= request.getContextPath() %>/Js/LibreriaGiochiScript.js"></script>                  
</body>
</html>