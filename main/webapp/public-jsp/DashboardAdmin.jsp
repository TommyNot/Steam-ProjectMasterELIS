<%@page import="org.elis.businesslogic.BusinessLogic"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="org.elis.model.Utente" %> 
    <%@page import="org.elis.model.Offerta" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard Admin</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/Css/DashboardAdminCss.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" type="text/css">
</head>
    <body>
    <nav id="sidebar">
        <ul>
            <li>
                <span class="logo">Admin steam</span>
                <button onclick=toggleSidebar() id="toggle-btn">
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368">
                        <path d="M120-240v-80h720v80H120Zm0-200v-80h720v80H120Zm0-200v-80h720v80H120Z"/>
                    </svg>
                </button>
            </li>
            <li class="active">
                <a href="<%= request.getContextPath()%>/public-jsp/DashboardAdmin.jsp">
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368">
                        <path d="M240-200h120v-240h240v240h120v-360L480-740 240-560v360Zm-80 80v-480l320-240 320 240v480H520v-240h-80v240H160Zm320-350Z"/>
                    </svg>
                    <span>Home</span>
                </a>
            </li>
            <li class="">
                <button class="dropdown-btn">
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368">
                        <path d="M400-120q-17 0-28.5-11.5T360-160v-480H160q0-83 58.5-141.5T360-840h240v120l120-120h80v320h-80L600-640v480q0 17-11.5 28.5T560-120H400Zm40-80h80v-240h-80v240Zm0-320h80v-240H360q-26 0-49 10.5T271-720h169v200Zm40 40Z"/>
                    </svg>
                    <span>Ban</span>
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368">
                        <path d="M480-360 280-560h400L480-360Z"/>
                    </svg>
                </button>
                <ul class="sub-menu">
                    <div>
                        <li><a href="#Ban" onclick="showSection('Ban')">Ban utente</a></li>
                        <li><a href="#Elimina" onclick="showSection('Elimina')">Elimina gioco</a></li>
                    </div>
                </ul>
            </li>
            <li class="">
                <button class="dropdown-btn">
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368">
                        <path d="M856-390 570-104q-12 12-27 18t-30 6q-15 0-30-6t-27-18L103-457q-11-11-17-25.5T80-513v-287q0-33 23.5-56.5T160-880h287q16 0 31 6.5t26 17.5l352 353q12 12 17.5 27t5.5 30q0 15-5.5 29.5T856-390ZM513-160l286-286-353-354H160v286l353 354ZM260-640q25 0 42.5-17.5T320-700q0-25-17.5-42.5T260-760q-25 0-42.5 17.5T200-700q0 25 17.5 42.5T260-640Zm220 160Z"/>
                    </svg>
                    <span>Offerta</span>
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368">
                        <path d="M480-360 280-560h400L480-360Z"/>
                    </svg>
                </button>
                <ul class="sub-menu">
                    <div>
                        <li><a href="#Gestisci" onclick="showSection('Gestisci')">Gestisci offerte</a></li>
                        <li><a href="#Aggiorna" onclick="showSection('Aggiorna')">Aggiorna offerta</a></li>
                        <li><a href="#Crea" onclick="showSection('Crea')">Crea offerta</a></li>
                    </div>
                </ul>
            </li>
            <li class="">
                <button class="dropdown-btn">
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368">
                        <path d="M480-480q-66 0-113-47t-47-113q0-66 47-113t113-47q66 0 113 47t47 113q0 66-47 113t-113 47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm240-320q33 0 56.5-23.5T560-640q0-33-23.5-56.5T480-720q-33 0-56.5 23.5T400-640q0 33 23.5 56.5T480-560Zm0-80Zm0 400Z"/>
                    </svg>
                    <span>Utenti</span>
                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368">
                        <path d="M480-360 280-560h400L480-360Z"/>
                    </svg>
                </button>
                <ul class="sub-menu">
                    <div>
                        <li><a href="#Visualizza" onclick="showSection('Visualizza')">Visualizza utenti</a></li>
                        <li><a href="#Cerca" onclick="showSection('Cerca')">Cerca utente</a></li>
                    </div>
                </ul>
            </li>
            <li class="">
            <a href="<%= request.getContextPath()%>/LogoutServlet" >
	            	<svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368">
	            	<path d="M200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h280v80H200v560h280v80H200Zm440-160-55-58 102-102H360v-80h327L585-622l55-58 200 200-200 200Z"/></svg>
	            	<span>Logout</span>
            	</a>
            </li>            
        </ul>
    </nav>
    <main>
        <div class="container active" id="home">
            <%
                        Utente utente = (Utente) session.getAttribute("utenteLoggato");
                    	long idUtente;
                        if (utente != null) {
                        	
                        	idUtente = utente.getId();
                    %>
                    <div id = "user">
                        <h2>Benvenuto <%= utente.getUsername()%> Admin di Steam pezzotto family!</h2>
                    </div>
                    <%
                        } else {
                    %>
                    <p>Utente non loggato</p>
                    <%
                        }
                    %>
        </div>
        
        <div class="container " id="Ban">
            <h2>Ban utente</h2>
            <h2>Elimina Account</h2>
   		<form id="eliminaUtente">
        
        <label for="id">ID:</label><br>
        <input type="text" id="id" name="id" required><br><br>
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username" required><br><br>

        <input type="submit" value="Elimina Account">
    	</form>
    	<div id="utenteResult"></div>
        </div>
        
        <div class="container " id="Elimina">
            <h2>Elimina gioco</h2>
            
           <form id="cercaGioco">
	        	<label for="nomeGioco">Nome del Gioco:</label>
	        	<input type="text" id="nomeGioco" name="nomeGioco">
	        	<button type="submit" class="btn-conf">Cerca Gioco</button>
	    	</form>


		    <div id="resultGioco"></div>
		
		  
		    <div id="deleteButton"></div>        
		    
		    </div>

        <div class="container " id="Gestisci">
            <h2>Attiva offerte</h2>
        </div>
        <div class="container" id="Aggiorna">
        	<h2>Aggiorna offerta</h2><br><br>
        	<h3>Modifica data inizio offerta</h3>
        	<form id="aggiornaInizio">
        <label for="offerta1">Seleziona offerta</label>
        <select id="offerta1" name="id">
            <% List<Offerta> offerte = BusinessLogic.offertaVisualizzaTutto(); 
               Offerta offertaSelezionata = (Offerta) request.getAttribute("offertaSelezionata"); 
               for (Offerta offerta : offerte) { 
            %>
                <option value="<%= offerta.getId() %>" <%= (offertaSelezionata != null && offertaSelezionata.getId() == offerta.getId()) ? "selected" : "" %>>
                    <%= offerta.getNome() %>
                </option>
            <% } %>
        </select>
        
        <label for="data_inizio">Data Inizio:</label>
        <input type="date" id="data_inizio" name="data_inizio" required>

        <button type="submit">Aggiorna data</button>
    </form>
        	<div id="resultInizio"></div>         
        	<div >
        		<br><h3>Modifica data fine</h3>
        		<form id="aggiornaFine">
        		<label for="offerta2">Seleziona offerta</label>
        		<select id="offerta2" name="id">
        		 <% List<Offerta> offerte2 = BusinessLogic.offertaVisualizzaTutto(); 
               Offerta offertaSelezionata2 = (Offerta) request.getAttribute("offertaSelezionata"); 
               for (Offerta offerta : offerte) { 
            %>
                <option value="<%= offerta.getId() %>" <%= (offertaSelezionata2 != null && offertaSelezionata2.getId() == offerta.getId()) ? "selected" : "" %>>
                    <%= offerta.getNome() %>
                </option>
            <% } %>
        		</select>
        		<label for="data_inizio">Data Fine:</label>
		        <input type="date" id="data_fine" name="data_fine" required>
		
		        <button type="submit">Aggiorna data</button>
        		</form>
        		<div id="resultFine"></div>
        	</div> 
        	
        	<div>
        	<br><h3>modifica sconto</h3>
        	<form id="aggiornaSconto">
        	<label for=offerta3>Seleziona offerta</label>
        	<select id="offerta3" name="id">
        	 <% List<Offerta> offerte3 = BusinessLogic.offertaVisualizzaTutto(); 
               Offerta offertaSelezionata3 = (Offerta) request.getAttribute("offertaSelezionata"); 
               for (Offerta offerta : offerte) { 
            %>
                <option value="<%= offerta.getId() %>" <%= (offertaSelezionata3 != null && offertaSelezionata3.getId() == offerta.getId()) ? "selected" : "" %>>
                    <%= offerta.getNome() %>
                </option>
            <% } %>
        	</select>
        	 <label for="sconto">Sconto (%):</label>
       		 <input type="number" id="sconto" name="sconto" required>
       		 
       		 <button type="submit">Aggiorna sconto</button>
        	</form>
				<div id="resultSconto"></div>       	
        	</div>
        </div>

        
        
        
        
        <div class="container" id="Crea">
            <h2 id="creazioneOfferta">Crea offerta</h2>
            <form id="creaOfferta">
        <label for="nome">Nome Offerta:</label>
        <input type="text" id="nome" name="nome" required>

        <label for="sconto">Sconto (%):</label>
        <input type="number" id="sconto" name="sconto" required>

        <label for="data_inizio">Data Inizio:</label>
        <input type="date" id="data_inizio" name="data_inizio" required>

        <label for="data_fine">Data Fine:</label>
        <input type="date" id="data_fine" name="data_fine" required>

        <button type="submit">Crea Offerta</button>
    </form>
    <div id="resultCreaOfferta"></div>
        </div>
        
        <div class="container" id="Visualizza"></div>
        
        <div class="container" id="Cerca">
            <h2>Cerca utenti</h2><br>
            <form id="searchForm">
			    <label for="username">Nome utente da cercare</label><br>
			    <input type="text" id="username1" name="username" required placeholder="Nome utente da cercare"><br><br>
			    <button type="submit" class="btn-conf">Avvia ricerca</button>
			</form>	
			<div id="result"></div>
			<br><br>
				<form id="searchForm2">
					<label for="idUtente">Id dell'utente</label><br>
					<input type="text" id="idUtente" name="idUtente" required placeholder="Id utente"><br><br>
					<button type="submit" class="btn-conf"> Avvia ricerca Id</button>
				</form>
				<div id="resultId"></div>
        </div>
    </main>


    <script src="<%= request.getContextPath() %>/Js/DashboardAdminScript.js"></script>
</body>
</html>