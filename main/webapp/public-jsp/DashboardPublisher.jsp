<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/DashboardPublisherCss.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" type="text/css">
    
    <title>Dashboard Publisher - Casa Editrice Giochi</title>
</head>
<body>
    <div class="container">
        <header>
            <div class="logo">
            <a href="<%= request.getContextPath() %>/public-jsp/DashboardPublisher.jsp">
                <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                      width="150px" height="100px" viewBox="0 0 355.666 89.333" enable-background="new 0 0 355.666 89.333"
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
                </div>

                <div class="user-info">
                    <img src="<%=request.getContextPath() %>/risorse-media/img_giochi/profilo.jpeg" alt="User Profile Picture" id="imgUtente"> 
                    <%
                        Utente utente = (Utente) session.getAttribute("utenteLoggato");
                    	long idUtente;
                        if (utente != null) {
                        	
                        	idUtente = utente.getId();
                    %>
                    <div id = "user">
                        <p>Benvenuto <%= utente.getUsername() %></p>
                    </div>
                    <%
                        } else {
                    %>
                    <p>Utente non loggato</p>
                    <%
                        }
                    %>
                </div>
           

            
            <nav>
                
                <ul>
                    <li><a href="#" onclick="showSection('home')">Home</a></li>
                    <li><a href="#" onclick="showSection('prodotti')">Prodotti</a></li>
                    <li><a href="#" onclick="showSection('statistiche')">Statistiche</a></li>
                    <li><a href="#" onclick="showSection('impostazioni')">Impostazioni</a></li>
                    <li><a href="<%= request.getContextPath()%>/LogoutServlet" >Logout</a></li>
                </ul>
            </nav>
        </header>
        
        <main>
            <section id="home" class="active">
                <h2>Benvenuto nella Dashboard</h2>
                <p>Qui puoi gestire i tuoi giochi, visualizzare le vendite, le recensioni e controllare lo stock.</p>
            </section>
                <div id="add-product-form" class="edit-form-container" style="display: none;">
				    <form action="<%= request.getContextPath()%>/GiocoAggiungiServlet" method="post" enctype="multipart/form-data">
				        <label for="nome">Nome Gioco:</label>
				        <input type="text" id="nome" name="nome" required>
				
				        <label for="dataRilascio">Data Rilascio:</label>
				        <input type="date" id="dataRilascio" name="dataRilascio" required>
				
				        <label for="descrizione">Descrizione:</label>
				        <input type="text" id="descrizione" name="descrizione" required>
				
				        <label for="immagine">Carica Immagine:</label>
    					<input type="file" name="immagine" accept="image/*" size=35 required><br>
				
				        <label for="prezzo">Prezzo:</label>
				        <input type="number" id="prezzo" name="prezzo" required>
				        
				  
				        
						<label for="genere">Seleziona genere:</label>	
							<select id="genere" name="genere">
							    <%
							        List<Genere> generi = BusinessLogic.VisalizzaTuttiGeneri();
							        Genere genereSelezionato = (Genere) request.getAttribute("genereSelezionato"); 
							        for (Genere genere : generi) {
							    %>
							        <option id="genere" value="<%= genere.getId() %>" <%= (genereSelezionato != null && genereSelezionato.getId() == genere.getId()) ? "selected" : "" %>><%= genere.getNome() %></option>
							    <%
							        }
							    %>
							</select>
							
							<label for="offerta">Seleziona offerta:</label>	
							<select id="offerta" name="offerta">
							    <%
							        List<Offerta> offerte = BusinessLogic.offertaVisualizzaTutto();
							        Offerta offertaSelezionata = (Offerta) request.getAttribute("offertaSelezionata"); 
							        for (Offerta offerta : offerte) {
							    %>
							        <option value="<%= offerta.getId() %>" <%= (offertaSelezionata != null && offertaSelezionata.getId() == offerta.getId()) ? "selected" : "" %>><%= offerta.getNome() %></option>
							    <%
							        }
							    %>
							</select>



				
				         
				
				        <button type="submit" class="btn btn-success">Aggiungi Prodotto</button>
				        <button type="button" id="cancel-add-product" class="btn btn-secondary">Annulla</button>
				    </form>
				</div>
				
				<div id="remove-product-form" class="edit-form-container" style="display: none;">
				    <form action="<%= request.getContextPath()%>/GiocoEliminaServlet" method="post">
				        <label for="productId">ID Gioco da Eliminare:</label>
				        <input type="number" id="productId" name="productId" required placeholder="Inserisci l'ID del gioco da eliminare">
				        
				        <label for="confirmation">Sei sicuro di voler eliminare questo gioco?</label>
				        <input type="hidden" name="confirm" value="false">
				        <input type="checkbox" id="confirmation" name="confirm" value="true" required> Conferma eliminazione
				        
				        <button type="submit" class="btn btn-danger">Elimina Prodotto</button>
				        <button type="button" id="cancel-remove-product" class="btn btn-secondary">Annulla</button>
				    </form>
	
				    
				</div>

				

			<section id="prodotti" class="hidden">
    				<h2>Prodotti</h2>
    				<button class="button" type="button" id="add-product-btn">Aggiungi Prodotto</button>
    				<button class="button" type="button" id="remove-product-btn">Elimina Prodotto</button>
						<label for="genere">Filtri:</label>
					<form action="<%= request.getContextPath() %>/GiocoGenereRicercaServlet" method="get" id="filter-form">
					    <select name="genere" id="genere" style="margin-right: 10px;">
					        <option value="">Seleziona un genere</option>
					        <%
					            for (Genere genere : generi) {
					        %>
					            <option id="opt" value="<%= genere.getId() %>"><%= genere.getNome() %></option>
					        <%
					            }
					        %>
					    </select>
					    <input type="submit" id="filtra" value="Filtra" style="margin-top: 10px; cursor: pointer;">
					</form>

					<div id="result">
					</div>

				    <p>Elenco dei tuoi giochi pubblicati e delle loro informazioni.</p>
				    <div id="products-container">
				     <% 
				     Utente utenteGioco = (Utente) session.getAttribute("utenteLoggato");
                 		long idUtenteGioco;
			            
			            if (utenteGioco != null) {
			            	idUtenteGioco = utenteGioco.getId();
			            	System.out.println(utenteGioco.getId());//visualizza id utente controllo da levare dopo 
			                List<Gioco> giochi = BusinessLogic.VisualizzaTuttiGiochi(idUtenteGioco);
			                if (giochi.isEmpty()) {
			        %>
                    <p>Nessun gioco disponibile.</p>
        			<% 
                			} else {
                   					 for (Gioco gioco : giochi) { 
                        				long id = gioco.getId(); 
                        				System.out.println(gioco.getId());// controllo id gioco da levare dopo
                       					 Offerta offerta = gioco.getOfferta(); 
       							 %>
				                <div id="product-<%= gioco.getId() %>" class="single-product">
				                    <div class="part-1">
				                        <% if (offerta != null) { %>
				                            <span class="discount"><%= offerta.getSconto() + "% off" %></span>
				                        <% } %>
				                        <img style='display:block; width:100px;height:100px;' id='base64image'
    											src='data:image/jpeg;base64,<%= gioco.getByteImmagine() %>' />
				                        <ul>
				                       		 <li>
				                        		<a id="btn-modifica" href="#" style="text-decoration: none;"><i class="bi bi-pencil-square"></i>Modifica</a>
				                       		 </li>
				                        
				                        </ul>
				                            
				                        
				                    </div>
				                    <div class="part-2">
				                        <h3 class="product-title"><%= gioco.getNome() %></h3>
				                        <h4 class="product-price">€<%= gioco.getPrezzo() %></h4>
				                        <h6 class="product-id">ID GIOCO: <%= gioco.getId() %></h6>
				                        <% if (offerta != null) { %>
				                            <h4 class="product-old-price" style="text-decoration: line-through;">€<%= gioco.getPrezzo() + (gioco.getPrezzo() * offerta.getSconto() / 100) %></h4>
				                        <% } %>
				                    </div>
				                </div>  
				        <% 
				            }
				        } 
			            }
				        %>
				    </div>
			</section>


            <section id="statistiche" class="hidden">
                <h2>Statistiche</h2>
                <div class="stats">
                    <div class="stat">
                        <h3>Vendite</h3>
                        <p>Visualizza le vendite dei tuoi giochi.</p>
                        <!-- Grafico o elenco delle vendite -->
                    </div>
                    <div class="stat">
                        <h3>Recensioni</h3>
                        <p>Visualizza le recensioni ricevute dai tuoi giochi.</p>
                        <!-- Grafico o elenco delle recensioni -->
                    </div>
                    <div class="stat">
                        <h3>Stock</h3>
                        <p>Controlla lo stock disponibile dei tuoi giochi.</p>
                        <!-- Grafico o elenco dello stock -->
                    </div>
                </div>
            </section>

            <section id="impostazioni" class="hidden">
                <h2>Impostazioni</h2>
                <p>Configura le tue preferenze e le impostazioni del profilo.</p>
            </section>
        </main>
        
        <footer>
            <p> @2024 Copywrite SteamFamilyPezzotto</p>
        </footer>
    </div>



   <script src="<%= request.getContextPath() %>/Js/DashboardPublisherScript.js"></script>

</body>
</html>
