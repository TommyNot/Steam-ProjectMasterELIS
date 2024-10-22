<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
     <meta charset="UTF-8" meta name="viewport" content="width=device-width, initial-scale=1">
     <title>Reset Password Page </title>
     <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/ResetPasswordCss.css">
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" type="text/css">
</head>
<body>
    <div id="logo">
         <span class="logo"><a href="<%= request.getContextPath() %>/public-jsp/PaginaLogin.jsp">
          <img alt="logo" src="<%= request.getContextPath() %>/risorse-media/img_homepage/logo.png" style="width: 200px; height: auto; margin: 10px;">
          </a>
         </span>
    </div>
 
    <div class="container" id="container">
		<form action="<%=request.getContextPath()%>/UtenteRipristinaPasswordServlet" method="post">
			<h1>Reset password</h1>
			<div class="social-container">
				<a href="#" class="social"><i class="bi bi-facebook"></i></a>
				<a href="#" class="social"><i class="bi bi-google"></i></a>
				<a href="#" class="social"><i class="bi bi-linkedin"></i></a>
			</div>
			<label for="username" style="font-weight: bold;">Username</label>
			<input type="text"  id="username" name="username" required />
			<label for="email" style="font-weight: bold;">Email</label>
			<input type="email"  id="email" name="email" required />
			<label for="passwordNuova" style="font-weight: bold;">Nuova Password</label>
			<input type="password"  id="passwordNuova" name="passwordNuova" required />
			<label for="passwordConferma" style="font-weight: bold;">Conferma la tua nuova Password</label>
			<input type="password"  id="passwordConferma" name="passwordConferma" required />
			<button type="submit" id="Reset">Reset</button>
		</form>
	</div>

	<footer>
	<p>
		Created by
		<a target="_blank" href="#"> Steam Pezzotto Family</a>
		
	</p>
</footer>
</body>
</html>