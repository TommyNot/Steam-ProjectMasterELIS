<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home Page</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Css/HomePagePrincipaleCss.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" type="text/css">
</head>
<body class="container-fluid">
	<nav>
		<div class="row">
			<div class="col-12 box-1">
				<div class="riga-intestazione-1">
					<div class="div-icona-scritta-steam">
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
					</div>
				
					<div class="colore-scritte">
						<h6 class="testo-menu-alto">Visualizza</h6>
					</div>
					<div class="colore-scritte">
						<h6 class="testo-menu-alto">Amici</h6>
					</div>
					<div class="colore-scritte">
						<h6 class="testo-menu-alto">Giochi</h6>
					</div>
					<div class="colore-scritte">
						<h6 class="testo-menu-alto">Aiuto</h6>
					</div>
				</div>
			
			
				<div class="riga-intestazione-2">
					<div id="megafono">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="white" class="bi bi-megaphone-fill" viewBox="0 0 16 16">
  						<path d="M13 2.5a1.5 1.5 0 0 1 3 0v11a1.5 1.5 0 0 1-3 0zm-1 .724c-2.067.95-4.539 1.481-7 1.656v6.237a25 25 0 0 1 1.088.085c2.053.204 4.038.668 5.912 1.56zm-8 7.841V4.934c-.68.027-1.399.043-2.008.053A2.02 2.02 0 0 0 0 7v2c0 1.106.896 1.996 1.994 2.009l.496.008a64 64 0 0 1 1.51.048m1.39 1.081q.428.032.85.078l.253 1.69a1 1 0 0 1-.983 1.187h-.548a1 1 0 0 1-.916-.599l-1.314-2.48a66 66 0 0 1 1.692.064q.491.026.966.06"/>
						</svg>
					</div>
					<div id="bell">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="white" class="bi bi-bell" viewBox="0 0 16 16">
  						<path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2M8 1.918l-.797.161A4 4 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4 4 0 0 0-3.203-3.92zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5 5 0 0 1 13 6c0 .88.32 4.2 1.22 6"/>
						</svg>
					</div>
					<div class="link-utente">
						<label for="lista-per-utente" class="colore-scritte">Account</label>
						<select id="lista-per utente">
							<option><a href="public-jsp/PaginaLogin.jsp">Login</a></option>
							<option><a href="public-jsp/PaginaRegistrazione.jsp">Registrati</a></option>
						</select>
					</div>
				</div>	
			</div>
		</div>
	</nav>
	
	<nav>
		<div class="row">
			<div class="col-12 box-2">
				<div class="testo-scritte-2">
					<strong>NEGOZIO</strong>
				</div>
				<div class="testo-scritte-2">
					<strong>LIBRERIA</strong>
				</div>
				<div class="testo-scritte-2">
					<strong>COMUNITA'</strong>
				</div>
				<div class="testo-scritte-2">
					<strong>ACCOUNT</strong>
				</div>
			</div>
		</div>
	</nav>
	<section "introduzione">
		<div class="row">
			<div class="col-12">
				<div class="image-container">
					<img src="<%=request.getContextPath() %>/risorse-media/img_homepage/Homepage_background.jpg" alt="Immagine di sfondo background" id="img-background">
					
					<a href="ListaDesideriLogicaServlet" id="lista-desideri">Lista dei desideri</a>	
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmark-heart" viewBox="0 0 16 16" id="icona-lista-desideri">
  					<path fill-rule="evenodd" d="M8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>
  					<path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1z"/>
					</svg>
				
					<a href="public-jsp/CarrelloGiochi.jsp" id="carrello">Carrello</a>
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart2" viewBox="0 0 16 16" id="icona-carrello">
  					<path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5M3.14 5l1.25 5h8.22l1.25-5zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0m9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0"/>
					</svg>
					
					<h5 id="barra-ricerca"></h5>
					
					<h5 id="news-trends">Novità e tendenze</h5>
					<h5 id="categorie">Categorie</h5>
					<h5 id="notizie">Notizie</h5>
					<form action="ListaGiochi" method="post">
						<input type="text" placeholder="cerca" name="searchBarInput" autofocus id="input-ricerca" class="nascondi-input-ricerca">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16" id="lente-ingrandimento">
  						<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
						</svg>
					</form>
					
					<strong id="testo-saldi">SALDI DELL'EDITORE</strong>
				</div>
			</div>
		</div>
	</section>
	<aside>	
		<div class="row">
			<div class="col-4">
				<div class="box-3">
					<img src="<%=request.getContextPath() %>/risorse-media/img_homepage/Steam_Deck.jpg" alt="Steam deck" class="img-aside-section">
					<strong class="testo-aside-section-1">STEAM DECK</strong>
					<h5 class="testo-aside-section-2">I tuoi giochi, ovunque</h5>
					<img src="<%=request.getContextPath() %>/risorse-media/img_homepage/BuoniRegaloSteam.jpg" alt="Buoni regalo Steam" class="img-aside-section">
					<strong class="testo-aside-section-1">BUONI REGALO DI STEAM</strong>
					<h5 class="testo-aside-section-2">Regala il piacere del gioco</h5>
				</div>
			</div>
			<div class="col-8">
				<div class="cont-slider-immagini">
					<h5 id="evidenza-consigliati">IN EVIDENZA E CONSIGLIATI</h5>
					
					<button type="previous" class="button" onclick="immaginePrima(event)" id="button-previous"> < </button>
            		<img src="<%=request.getContextPath() %>/risorse-media/img_giochi/Cyberpunk_2077.jpg" alt="Cover gioco" id="immagine">
            		<button type="next" class="button" onclick="immagineDopo(event)" id="button-next"> > </button>
				</div>
			</div>
		</div>
	</aside>



	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/Js/HomePagePrincipaleScript.js"></script>
</body>
</html>