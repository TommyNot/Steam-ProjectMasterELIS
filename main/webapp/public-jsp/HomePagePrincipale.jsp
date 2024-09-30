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
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="rgb(107, 113, 121)" class="bi bi-steam" viewBox="0 0 16 16">
  						<path d="M.329 10.333A8.01 8.01 0 0 0 7.99 16C12.414 16 16 12.418 16 8s-3.586-8-8.009-8A8.006 8.006 0 0 0 0 7.468l.003.006 4.304 1.769A2.2 2.2 0 0 1 5.62 8.88l1.96-2.844-.001-.04a3.046 3.046 0 0 1 3.042-3.043 3.046 3.046 0 0 1 3.042 3.043 3.047 3.047 0 0 1-3.111 3.044l-2.804 2a2.223 2.223 0 0 1-3.075 2.11 2.22 2.22 0 0 1-1.312-1.568L.33 10.333Z"/>
  						<path d="M4.868 12.683a1.715 1.715 0 0 0 1.318-3.165 1.7 1.7 0 0 0-1.263-.02l1.023.424a1.261 1.261 0 1 1-.97 2.33l-.99-.41a1.7 1.7 0 0 0 .882.84Zm3.726-6.687a2.03 2.03 0 0 0 2.027 2.029 2.03 2.03 0 0 0 2.027-2.029 2.03 2.03 0 0 0-2.027-2.027 2.03 2.03 0 0 0-2.027 2.027m2.03-1.527a1.524 1.524 0 1 1-.002 3.048 1.524 1.524 0 0 1 .002-3.048"/>
						</svg>
						<h6 class="testo-menu-alto colore-scritte" id="steam">Steam</h6>
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
						<input type="text" placeholder="cerca" name="cercaInput" autofocus id="input-ricerca" class="nascondi-input-ricerca">
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
			<div class="col-2">
				<div class="box-3">
					<img src="<%=request.getContextPath() %>/risorse-media/img_homepage/Steam_Deck.jpg" alt="Steam deck" class="img-aside-section">
					<strong class="testo-aside-section-1">STEAM DECK</strong>
					<h5 class="testo-aside-section-2">I tuoi giochi, ovunque</h5>
				</div>
			</div>
			<div class="col-2">
				<div class="box-3">
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