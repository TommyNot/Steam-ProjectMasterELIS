<%@page import="org.elis.businesslogic.BusinessLogic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.elis.model.Gioco" %>
<%@ page import="org.elis.model.Offerta" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Games Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/GiochiPublisherCss.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" type="text/css">
</head>
<body>
<section class="section-products">
    <div class="container">
        <div class="row justify-content-center text-center">
            <div class="col-md-8 col-lg-6">
                <div class="header">
                    <h3>Prodotti Pubblici</h3>
                    <h2>Giochi</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <% 
               
                Gioco gioco = new Gioco();
            	Offerta offerta = new Offerta();
                List<Gioco> giochi = BusinessLogic.VisualizzaTuttiGiochi();
                
                for (Gioco game : giochi) { 
            %>
                
                <div class="col-md-6 col-lg-4 col-xl-3">
                    <div id="product-<%= gioco.getId() %>" class="single-product">
                        <div class="part-1">
                  
                            <% if (gioco.getOfferta() != null) { %>
                                <span class="discount"><%= offerta.getSconto() + "% off" %></span>
                            <% } %>
                            <ul>
                                <li><a href="#"><i class="fas fa-shopping-cart"></i></a></li>
                                <li><a href="#"><i class="fas fa-heart"></i></a></li>
                                <li><a href="#"><i class="fas fa-plus"></i></a></li>
                                <li><a href="#"><i class="fas fa-expand"></i></a></li>
                            </ul>
                        </div>
                        <div class="part-2">
                            <h3 class="product-title"><%= gioco.getNome() %></h3>
                            <% if (game.getOldPrice() != null) { %>
                                <h4 class="product-old-price">$<%= game.getOldPrice() %></h4>
                            <% } %>
                            <h4 class="product-price">$<%= game.getPrice() %></h4>
                        </div>
                    </div>
                </div>
            <% 
                } 
            %>
        </div>
    </div>
</section>

</body>
</html>
