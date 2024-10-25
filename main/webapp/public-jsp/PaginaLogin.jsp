<%@page import="org.apache.tomcat.util.http.fileupload.RequestContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/PaginaLoginCss.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" type="text/css">
</head>
<body>

     <div id="logo">
         <span class="logo"><a href="<%= request.getContextPath() %>/public-jsp/HomePagePrincipale.jsp">
          <img alt="logo" src="<%= request.getContextPath() %>/risorse-media/img_homepage/logo.png" style="width: 200px; height: auto; margin: 10px;">
          </a>
         </span>
    </div>
<%
        String successMessage = (String) request.getAttribute("Success");
        if (successMessage != null) {
    %>
        <div class="success">
            <%= successMessage %>
        </div>
    <%
        }
    %>
  
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form action="<%=request.getContextPath()%>/LogicaRegistrazioneServlet" method="post">
			<h1>Crea Account</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="bi bi-facebook"></i></a>
				<a href="https://www.instagram.com/steampezzottofamily/profilecard/?igsh=dXNha3Q5NWZtOHV1" target="_blank" class="social-icon"><i class="bi bi-instagram"></i></a>
				<a href="#" class="social"><i class="bi bi-linkedin"></i></a>
			</div>
			<label for="usernameFromInput" style="font-weight: bold;">Username</label>
			<input type="text"  id="usernameFromInput" name="usernameFromInput" required />
			<label for="emailFromInput" style="font-weight: bold;">Email</label>
			<input type="email"  id="emailFromInput" name="emailFromInput" required />
			<label for="passwordFromInput" style="font-weight: bold;">Password</label>
			 		<div class="error-container">
		    <%
		        String failedRegister = (String) request.getAttribute("Error");
		        if (failedRegister  != null) {
		    %>
		        <div class="alert">
		            <%= failedRegister  %>
		        </div>
		    <%
		        }
		    %>
		</div>
			<input type="password"  id="passwordFromInput" name="passwordFromInput" required />
			<button type="submit">Registrati</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="<%=request.getContextPath()%>/LogicaLoginServlet" method="post">
			<h1>Accedi</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="bi bi-facebook"></i></a>
				<a href="https://www.instagram.com/steampezzottofamily/profilecard/?igsh=dXNha3Q5NWZtOHV1" target="_blank" class="social-icon"><i class="bi bi-instagram"></i></a>
				<a href="#" class="social"><i class="bi bi-linkedin"></i></a>
			</div>
			<label for="emailLogin" style="font-weight: bold;">Email</label>
			<input type="email" id="emailLogin" name="emailLogin" required />
			<label for="passwordLogin" style="font-weight: bold;">Password</label>
			<input type="password" id="passwordLogin" name="passwordLogin" required />
	 		<div class="error-container">
		    <%
		        String failedLogin = (String) request.getAttribute("Error");
		        if (failedLogin != null) {
		    %>
		        <div class="alert">
		            <%= failedLogin %>
		        </div>
		    <%
		        }
		    %>
		</div>

			<div class="checkbox-container">
				<input type="checkbox" value="ricordami" id="ricordami" name="ricordami">
				<label for="ricordami" id="rcd">Ricordami</label>
			</div>
			<a href="<%= request.getContextPath() %>/public-jsp/ResetPassword.jsp">Password dimenticata?</a>
			<button type="submit">Login</button>
		</form>
	</div>


	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Bentornato</h1>
				<p>Accedi con i tuoi dati dell'account Steam Pezzotto</p>
				<button class="ghost" id="signIn">Login!</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Non sei ancora registrato?</h1>
				<p>Entra a far parte della nostra famiglia , Steam Pezzotto Family</p>
				<button class="ghost" id="signUp">Registrati!</button>
			</div>
		</div>
	</div>
</div>

  




			
		<script src="<%=request.getContextPath()%>/Js/LoginPageScript.js"></script>
</body>
</html>