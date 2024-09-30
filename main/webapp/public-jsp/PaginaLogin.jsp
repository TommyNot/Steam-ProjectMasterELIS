<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login page</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/Css/PaginaLoginCss.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" type="text/css">
</head>
<body>
	<h1>Login to your account</h1>
	
	 <form action="<%=request.getContextPath()%>/LogicaLogin" method="post" class="form">
	 	<div class="cont">
	   		<label for="emailAccesso">Email:</label>
	   		<input type="email" id="emailAccesso" placeholder="mario.rossi@gmail.com" name="emailLogin" required>
	   	</div>
        <div class="cont">
	   		<label for="passwordAccesso">Password:</label>
	   		<input type="password" id="passwordAccesso" name="passwordLogin" required>
	   	</div>
	   	<div class="cont-2">
	   		<label for="RestaCollegato">Ricordami</label>
	   		<input type="checkbox" id="RestaCollegato" name="checkboxFormInput">
	   	</div>
        <input type="submit" value="Login">
        </form>
        <div id="link-registrazione">
        	<a href="PaginaRegistrazione.jsp" id="ancora-registrazione">Non sei ancora registrato? Crea un account!</a>
        </div>
</body>
</html>