<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.List, org.elis.model.Utente" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Casa Editrice</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/DashboardPublisherCss.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" type="text/css">
</head>
<body>
    <!-- Barra laterale -->
    <div class="barralato">
        <h2>Dashboard Casa Editrice</h2>
        <ul>
            <li><a href="DashboardPublisher.jsp">
                <i class="bi bi-house"></i> 
                <span>Home</span></a>
            </li>
            <li><a href="<%= request.getContextPath() %>/public-jsp/GiochiPublisher.jsp">
                <i class="bi bi-bag"></i>
                <span>Prodotti</span></a>
            </li>
            <li><a href="#settings">
                <i class="bi bi-gear"></i>
                <span>Impostazioni</span>
            </a></li>
            <li>
                <a href="<%= request.getContextPath() %>/LogoutServlet">
                    <i class="bi bi-box-arrow-left"></i>
                    <span>Logout</span>
                </a>
            </li>
        </ul>
        
        <div class="user-info">
            <img src="<%=request.getContextPath() %>/risorse-media/img_giochi/profilo.jpeg" alt="User Profile Picture"> 
            <%
                Utente utente = (Utente) session.getAttribute("utenteLoggato");
                if (utente != null) {
            %>
            <div>
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
    </div>

    <div class="content">
        <div id="home">
            <h1>Benvenuto <%= utente != null ? utente.getUsername() : "Utente" %></h1>
            
            <div class="statistiche">
                
                    <div class="container">
                        
                        <div class="card">
                            <img src="<%=request.getContextPath() %>/risorse-media/img_giochi/Elden_Ring.jpg" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">Prodotti</h5>
                                <p class="card-text">Visualizza i tuoi prodotti .</p>
                                <a href="#" class="btn">Vai ai tuoi prodotti</a>
                            </div>
                        </div>
                    </div>
             
            </div>

            <div class="grafico">
                <h2>Andamento delle vendite</h2>
                         <!-- Carta per le Vendite -->
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="icon-basket success font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3>156</h3>
                                        <span>Vendite</span> <!-- aggiungi numeri di giochi aggiungi a libreria -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="attivita">
    <h2>Attività Recenti Account</h2>
    <div class="grey-bg container-fluid">
        <section id="minimal-statistics">
            <div class="row">
                <div class="col-12 mt-3 mb-1">
                    
                </div>
            </div>
            <div class="row">
                <!-- Carta per le Recensioni -->
                <div class="col-xl-3 col-sm-6 col-12"> 
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="icon-pencil primary font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3>278</h3>
                                        <span>Recensioni</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Carta per le Vendite -->
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="align-self-center">
                                        <i class="icon-basket success font-large-2 float-left"></i>
                                    </div>
                                    <div class="media-body text-right">
                                        <h3>156</h3>
                                        <span>Vendite</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Carta per le Conversioni -->
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="media-body text-left">
                                        <h3 class="warning">64.89 %</h3>
                                        <span>Conversioni</span>
                                    </div>
                                    <div class="align-self-center">
                                        <i class="icon-pie-chart warning font-large-2 float-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <div class="col-xl-3 col-sm-6 col-12">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <div class="media d-flex">
                                    <div class="media-body text-left">
                                        <h3 class="primary">423</h3>
                                        <span>Support Tickets</span> 
                                    </div>
                                    <div class="align-self-center">
                                        <i class="icon-support primary font-large-2 float-right"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

              </div>  
            </div>
          
        
   
</body>
</html>
