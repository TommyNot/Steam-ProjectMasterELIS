<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="org.elis.model.Gioco" %>
<%@ page import="org.elis.model.Genere" %>
<%@ page import="org.elis.model.Offerta" %> 
<%@ page import="org.elis.model.Utente" %> 
<%@ page import="org.elis.model.Recensione" %>
<%@page import="org.elis.businesslogic.BusinessLogic"%>
<%@ page import="java.util.Base64" %>

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
            <div id="logo">
         <span class="logo"><a href="<%= request.getContextPath() %>/public-jsp/HomePagePrincipale.jsp">
          <img alt="logo" src="<%= request.getContextPath() %>/risorse-media/img_homepage/logo.png" style="width: 200px; height: auto; margin: 10px;">
          </a>
         </span>
    </div>
                
               <div class="result">
			        	                     <% 
					String success = (String) request.getAttribute("successo");
					if (success != null) { 
					%>
					    <div class="success-delete-gioco">
					        <%= success %>
					    </div>
					<% 
					} 
					%>
					
					                    <% 
					String successGiocoAdd = (String) request.getAttribute("addGioco");
					if (successGiocoAdd != null) { 
					%>
					    <div class="successGiocoAdd">
					        <%= successGiocoAdd %>
					    </div>
					<% 
					} 
					%>
					
								                    <% 
					String successGiocoModificato = (String) request.getAttribute("giocoModifica");
					if (successGiocoModificato != null) { 
					%>
					    <div class="successGiocoAdd">
					        <%= successGiocoModificato %>
					    </div>
					<% 
					} 
					%>
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
                    
                    <li><a href="<%= request.getContextPath()%>/LogoutServlet" >Logout</a></li>
                </ul>
            </nav>
        </header>
        
        <main>
        
     
 
            <section id="home" class="active">
                <h2>Benvenuto nella Dashboard</h2>
                <p>Qui puoi gestire i tuoi giochi, visualizzare le vendite, le recensioni e controllare lo stock.</p>
      				
  				<canvas id="myChart" width="400" height="200">
  				</canvas>
  				
  				<div>
  				<% 
  				List<Gioco> giochiPub = BusinessLogic.VisualizzaTuttiGiochi(utente.getId());
  				List<Gioco> tuttiGiochi = BusinessLogic.VisualizzaTuttiGiochi();
  				
  				int sommaRec = 0;
  				for(Gioco giochi: tuttiGiochi){
  					
  					List<Recensione> recGiochi = BusinessLogic.TrovaRecensioneByIdGioco(giochi.getId());
  					sommaRec += recGiochi.size();
  				}
  				
  				
  				%>
  				</div>
  				
  				
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
    					<input type="file" name="immagine" accept="image/jpeg,image/png" required><br>
				
				        <label for="prezzo">Prezzo:</label>
				        <input type="number" id="prezzo" name="prezzo"  required step="0.01" min="0">
				        
				  
				        
							    <label for="genere">Seleziona genere/i:</label>
								<% 
								    List<Genere> generi1 = BusinessLogic.VisalizzaTuttiGeneri();
								    for (Genere genere : generi1) {
								%>
								    <input type="checkbox" name="genere" value="<%= genere.getId() %>" id="genere_<%= genere.getId() %>">
								    <label for="genere_<%= genere.getId() %>"><%= genere.getNome() %></label><br>
								<% 
								    }
								%>




				
				         
				
				        <button type="submit" class="btn btn-success">Aggiungi Prodotto</button>
				        <button type="button" id="cancel-add-product" class="btn btn-secondary">Annulla</button>
				    </form>
				</div>
				
				
			<div id="edit-product-form" class="edit-form-container"  style="display: none;">
   
					    <form action="<%= request.getContextPath() %>/GiocoAggiornaServlet" method="post" enctype="multipart/form-data">
					    	<label for="giocoId">ID Gioco da Modificare:</label>
				       		 <input type="number" id="giocoId" name="giocoId"  placeholder="Inserisci l'ID del gioco da eliminare" required>
					        <label for="nome">Nome Gioco:</label>
					        <input type="text" id="nome" name="nome" >
					
					        <label for="dataRilascio">Data Rilascio:</label>
					        <input type="date" id="dataRilascio" name="dataRilascio" >
					
					        <label for="descrizione">Descrizione:</label>
					        <input type="text" id="descrizione" name="descrizione"  >
					
					        <label for="immagine">Carica Immagine (opzionale):</label>
					        <input type="file"  id="immagine" name="immagine" accept="image/jpeg,image/png"><br>
					        <p>Lascia vuoto per mantenere l'immagine attuale.</p>
					
					        <label for="prezzo">Prezzo:</label>
					        <input type="number" id="prezzo" name="prezzo" step="0.01" min="0" >
					
					        
					    <label for="genere">Seleziona genere/i:</label>
								<% 
								    List<Genere> AggiornaGenere = BusinessLogic.VisalizzaTuttiGeneri();
								    for (Genere genere : AggiornaGenere) {
								%>
								    <input type="checkbox" name="genere" value="<%= genere.getId() %>" id="genere_<%= genere.getId() %>">
								    <label for="genere_<%= genere.getId() %>"><%= genere.getNome() %></label><br>
								<% 
								    }
								%>

					        
					      <label for="offerta">Seleziona offerta:</label>
					        <select id="offerta" name="offerta">
					        	<option value="null">Nessuna Offerta</option> 
					            <%
					                List<Offerta> offerte = BusinessLogic.offertaVisualizzaTutto();
					                
					                for (Offerta offerta : offerte) {
					            %>
					            	
					                <option value="<%= offerta.getId() %>"  ><%= offerta.getNome() %></option>
					            <%
					                }
					            %>
					        </select>
					
					        <button type="submit" class="btn btn-success">Modifica Gioco</button>
					        <button type="button" id="cancel-edit-product" class="btn btn-secondary">Annulla</button>
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
    				<button class="button" type="button" id="btn-modifica-gioco"  > Modifica Gioco </button>
														
						
					<div id="result">
					</div>

				    <p>Elenco dei tuoi giochi pubblicati e delle loro informazioni.</p>
				    <div id="products-container">
				     <% 
				     Utente utenteGioco = (Utente) session.getAttribute("utenteLoggato");
                 		long idUtenteGioco = 0;
			            
			            if (utenteGioco != null) {
			            	idUtenteGioco = (Long) utenteGioco.getId();
			            	System.out.println(utenteGioco.getId());//visualizza id utente controllo da levare dopo 
			                List<Gioco> giochi = BusinessLogic.VisualizzaTuttiGiochi(idUtenteGioco);
			                if (giochi.isEmpty()) {
			        %>
                    <p>Nessun gioco disponibile.</p>
        			<% 
                			} else {
                   					 for (Gioco gioco1 : giochi) { 
                        				long id = gioco1.getId(); 
                        				System.out.println(gioco1.getId());// controllo id gioco da levare dopo
                       					 Offerta offerta = gioco1.getOffertaGioco(); 
       							 %>
				                <div id="product-<%= gioco1.getId() %>" class="single-product">
				                    <div class="part-1">
				                        <% if (offerta != null) { %>
				                            <span class="discount"><%= offerta.getSconto() + "% off" %></span>
				                        <% } %>
				                        	
				                        	<div class="product__image">
				                        	 <% 
													    String immagineBase64 = gioco1.getImmagine(); 
				                        	 System.out.println("Immagine Base64: " + immagineBase64);

													    if (immagineBase64 != null && !immagineBase64.isEmpty()) { 
													%>
													   <img src="data:image/jpeg;base64,<%= immagineBase64 %>" style="width: 220px; height: 300px;" />
													<% 
													    } else { 
													%>
													    <p>Immagine non disponibile</p>
													<% 
													    } 
													%>
				                        	</div>

				                        
				                    </div>
				  <div class="part-2">
            
            
            <h3 class="product-title">Nome Gioco : <%= gioco1.getNome() %></h3>
            
            <div class="discount-container">
                <% if (offerta != null) { %>
                    
                    <h4 class="product-old-price">Prezzo : €<%= gioco1.getPrezzo() %></h4>
                    <h4 class="product-price">Prezzo Scontato :€<%= Math.round((gioco1.getPrezzo() - (gioco1.getPrezzo() * offerta.getSconto() / 100)) * 100.0) / 100.0 %></h4>
                <% } else { %>
                    <h4 class="product-price">Prezzo : €<%= gioco1.getPrezzo() %></h4>
                <% } %>
            </div>

            
            <h6 class="product-id">ID GIOCO: <%= gioco1.getId() %></h6>
            <form action="<%= request.getContextPath() %>/GiocoVediDettagli" method="get"  >  
            	<input type="hidden" value="<%= gioco1.getId() %>" name="barraRicerca" id="barraRicerca">
            	<button class="button" type="submit" >Visualizza dettagli</button>
            </form>
           
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
                        <h3>Giochi che possiedi</h3>
                        <p>Visualizza il numero totale di giochi che possiedi.</p>
                        <%
                        
			        	List<Gioco> g=BusinessLogic.VisualizzaTuttiGiochi(idUtenteGioco);
			        	%>
			        
			        <h4>Numero di giochi attualmente nel sito: <%=g.size() %></h4>
                    </div>
                    <div class="stat">
                        <h3>Recensioni</h3>
                        <p>Visualizza le recensioni ricevute dai tuoi giochi.</p>
                    	
                    </div>
                    <div class="stat">
                        <h3>Vendite</h3>
                        <p>Visualizza vendite dei tuoi giochi.</p>
                        <!-- Grafico o elenco dello stock -->
                    </div>
                </div>
            </section>

            <section id="impostazioni" class="hidden">
                <h2>Impostazioni</h2>
                <p>Configura le tue preferenze e le impostazioni del profilo.</p>
            </section>
        </main>
        
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
	
    </div>


<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
   <script src="<%= request.getContextPath() %>/Js/DashboardPublisherScript.js"></script>
	

<script>
const giochiCount = <%= giochiPub.size() %>
const recCount = <%= sommaRec %>


    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar', 
        data: {
            labels: ['Giochi', 'Recensioni Totali'],
            datasets: [{
                label: 'Statistiche Attuali',
                data: [giochiCount,recCount], 
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
</body>
</html>
