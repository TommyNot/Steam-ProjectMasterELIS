<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Errore 403 - Accesso negato</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/ErrorPageCss.css">
</head>
<body>
 
    <div class="container">
    
    	<div class="logo">
            <span class="logo"><a href="<%= request.getContextPath() %>/public-jsp/HomePagePrincipale.jsp">
                	<img alt="logo" src="<%= request.getContextPath() %>/risorse-media/img_homepage/logo.png" width="250" style="margin: 10px;">
                	</a>
               </span>
                </div>
    
        <h1 class="error-title">403</h1>
        <h2 class="error-message">Accesso negato</h2>
        <p class="description">Non hai i permessi per accedere a questa pagina.</p>
        <a href="<%= request.getContextPath() %>/public-jsp/HomePagePrincipale.jsp" class="home-button">Torna alla Home</a>
        <div class="steam-style">
            <p class="steam-footer">Powered by FamilySteamPezzotto</p>
        </div>
    </div>
</body>
</html>