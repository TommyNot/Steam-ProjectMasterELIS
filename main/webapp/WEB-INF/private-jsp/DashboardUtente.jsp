<%@page import="org.elis.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dashboard Utente</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Css/DashboardUtenteCss.css">
</head>
	<body>
  		<div class="dashboard-river">

    		<div class="dashboard-container">
  
		      <div class="dashboard">
		  
		        <div class="ui-row-1">
		  
		          <div class="logo-comp">
		          <span class="logo"><a href="<%= request.getContextPath() %>/public-jsp/HomePagePrincipale.jsp">
                	<img alt="logo" src="<%= request.getContextPath() %>/risorse-media/img_homepage/logo.png" width="200" style="margin: 10px;">
                	</a>
                	</span>
		          </div>
		  
		          <div class="search">
		          	<form action="<%= request.getContextPath() %>/GiocoCercaServlet" method="get" class="search">
		            	<input type="search" id="search" name="barraRicerca" placeholder="Search for a game...">
		            </form>
		          </div>
		
		          <div class="btn-group">
		            <button type="button" class="btn btn-secondary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false" data-bs-reference="parent">
		              <div class="profile-picture me-2">
		            <%   	Utente utente = (Utente) session.getAttribute("utenteLoggato");
                    		long idUtente; 
                    		if (utente != null) {
                            	
                            	idUtente = utente.getId();
                    		%>
                    	<h6 class="username"> <%=utente.getUsername() %> </h6>
                    		<%
                        	} else {
		                %>
		                <h6 class="username">User not <br> logged</h6>
		                <%
                        }
                    	%>
		              	<img id="avatar" alt="avatar utente" class="img-icon">
		              </div>
		            </button>
		            <ul class="dropdown-menu colore">
		              <li><a class="dropdown-item" href="<%= request.getContextPath()%>/WEB-INF/private-jsp/UtenteAggiornaUsername.jsp">Modifica username</a></li>
		              <li><a class="dropdown-item" href="<%= request.getContextPath()%>/WEB-INF/private-jsp/UtenteAggiornaEmail.jsp">Modifica email</a></li>
		               <li><a class="dropdown-item" href="<%= request.getContextPath()%>/WEB-INF/private-jsp/UtenteEliminaByUsername.jsp">Elimina account</a></li>
		              <li><a class="dropdown-item" href="<%= request.getContextPath()%>/LogoutServlet">Logout</a></li>
		            </ul>
		          </div>
		        </div>
		  
		        <div class="ui-row-2">

		          <div class="main-content">
		  
		            <div class="header">
		  
		              <div class="page-display">
		                <h1>Dashboard</h1>
		                <%
                        	if (utente != null) {
                        	
                        	idUtente = utente.getId();
		                %>
		                
		                <h2>Welcome <%=utente.getUsername() %>!</h2>
		                <%
                        	} else {
		                %>
		                <p>User not logged</p>
		                <%
                        }
                    	%>
		              </div>
		  
		  
		            </div>
		  
		            <div class="large-banner">
		  
		              <h2>Explore a World of Games</h2>
		              <a href="<%= request.getContextPath()%>/public-jsp/HomePagePrincipale.jsp" title="Explore">
		                Homepage
		              </a>
		  
		            </div>
		  
		            <hr>
		  
		            <div class="featured-clay">
		  
		              <div>
		  
		                <div></div>
		  
		                <div>
		                  <h3>Negozio</h3>
		                  <p>Visualizza tutti i giochi disponibili da aggiungere alle tue librerie</p>
		                  <a href="<%= request.getContextPath()%>/public-jsp/PageGiochi.jsp" title="featured clay">Let's Go</a>
		                </div>
		  
		              </div>
		  
		              <div>
		  
		                <div></div>
		  
		                <div>
		                  <h3>Librerie</h3>
		                  <p>Visualizza tutte le tue librerie e giochi</p>
		                  <a href="<%= request.getContextPath()%>/LibreriaFindByIdUtenteServlet" title="featured clay">Let's Go</a>
		                </div>
		  
		              </div>
		  
		              <div>
		  
		                <div></div>
		  
		                <div>
		                  <h3>Recensioni</h3>
		                  <p>Qui sono raggrupate tutte le recensioni che hai rilasciato.</p>
		                  <a href="<%= request.getContextPath()%>/public-jsp/PaginaElencoRecensioniUtente.jsp" title="featured clay">Let's Go</a>
		                </div>
		  
		              </div>
		             
		            </div>
		  
		            <hr>
		  
		            <div class="featured-users">
		  
		              <div>
		  
		                <div>
		  
		                  <div></div>
		  
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
		  
		             
		  
		            
		  
		            </div>
		  
		          </div>
		  
		        </div>
		  
		      </div>
		  
		    </div>
		  
		  </div>
		<script src="<%= request.getContextPath() %>/Js/DashboardUtenteJs.js"></script>
	</body>
	
</html>