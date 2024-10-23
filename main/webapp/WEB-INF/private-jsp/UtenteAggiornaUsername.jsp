<%@page import="org.elis.businesslogic.BusinessLogic"%>
<%@page import="org.elis.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update username Page</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/ResetPasswordCss.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" type="text/css">
</head>
<body>
    <div id="logo">
        <nav class="riga-alta">

        <span class="logo"><a href="<%= request.getContextPath() %>/public-jsp/HomePagePrincipale.jsp">
                	<img alt="logo" src="<%= request.getContextPath() %>/risorse-media/img_homepage/logo.png" width="250" style="margin: 10px;">
                	</a>
               </span>
        </nav>
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
    <%Utente utente = (Utente) session.getAttribute("utenteLoggato");
	long idUtente; 
	if (utente != null) {
    	
    	idUtente = utente.getId();
    	Utente u = BusinessLogic.UtenteFindById(idUtente);
    	if(u.getId() == utente.getId()){
    		%>
    	
 
    <div class="container" id="container">
		<form action="<%=request.getContextPath()%>/UtenteAggiornaUsernameServlet" method="post">
			<h1>Modifica username</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="bi bi-facebook"></i></a>
				<a href="#" class="social"><i class="bi bi-google"></i></a>
				<a href="#" class="social"><i class="bi bi-linkedin"></i></a>
			</div>
			
			<label for="username" style="font-weight: bold;">Nuovo username</label>
			<input type="text"  id="username" name="nuovoUsername" required />
			<button type="submit" id="Reset">Invia</button>
			 <a id="Reset" href="<%= request.getContextPath() %>/public-jsp/DashboardUtente.jsp">Torna alla dashboard</a>
		</form>
	</div>

<% 	}else{
		response.sendRedirect("public-jsp/ErrorAccessoNegatoPage.jsp");
		return;
	}
}
	%>

	<footer>
	<p>
		Created by
		<a target="_blank" href="#">Family Steam Pezzotto</a>
		
	</p>
</footer>
</body>
</html>