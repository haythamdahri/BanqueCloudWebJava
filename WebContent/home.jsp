<%@page import="com.banque.dao.*"%>
<%@ page import="com.banque.entities.*" %>
<%@ page import="com.banque.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Acceuil : Client</title>
	<link rel="icon" href="static/images/logo.jpg" />
	<link rel="stylesheet" type="text/css"  href="static/css/materialize.css"/>
	<link rel="stylesheet" type="text/css"  href="static/css/style.css" />
	<link rel="stylesheet" type="text/css"  href="static/css/all.min.css" />
	<link rel="stylesheet" type="text/css" href="static/css/material_icons.css" />

</head>
<body>


<main>
<%@ include file="parts/navbar.jsp" %>

	
	<div class="container">	
	
	<% if( session.getAttribute("added") != null || session.getAttribute("updated") != null || session.getAttribute("deleted") != null ){ %>
	<div class="card-panel white-text blue lighten-2"><i class="left material-icons"><%= session.getAttribute("icon") %></i><%= session.getAttribute("message") %></div>
	<%
		session.removeAttribute("updated");
		session.removeAttribute("deleted");
		session.removeAttribute("added");
		session.removeAttribute("icon");
		session.removeAttribute("message");
	} %>
	
	<%@ include file="parts/search_form.jsp" %>
	
	
	<table class="centered striped responsive-table " style="margin-bottom: 100px;">
        <tbody>
		<tr class="z-depth-3">
			<th style="font-weight: bold" class="blue-text">#</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Ville</th>
			<th colspan="2">Actions</th>
		</tr>
		<% if( request.getAttribute("one_client") == null ){%>
		<% for(Client client : (List<Client>)request.getAttribute("clients")){ %>
		<tr class="hoverable <% if( (session.getAttribute("client_added") != null && ((Client)session.getAttribute("client_added")).getCode() == client.getCode()) || (session.getAttribute("client_updated") != null && ((Client)session.getAttribute("client_updated")).getCode() == client.getCode()) ){ out.println("lime"); session.removeAttribute("client_updated");  session.removeAttribute("client_added"); } %> z-depth-1">
			<td class="blue-text" style="font-weight: bold"><blockquote style="border-left-color: #2196F3 !important"><%= client.getCode() %></blockquote></td>
			<td><%= client.getNom() %></td>
			<td><%= client.getPrenom() %></td>
			<td><%= client.getVille() %></td>
			<td>
				<a href="<%= request.getContextPath() %>/edit?code=<%= client.getCode() %>" class="btn-floating pulse waves-effect waves-light #ffab00 amber accent-4"><i class="material-icons right lighten-2 tooltipped" data-position="bottom" data-tooltip="Modifier Client">edit</i></a>
			</td>
			<td>
				<form id="removeClient<%= client.getCode() %>" action="<%= request.getContextPath() %>/delete" method="POST">
				<input type="hidden" name="code" value="<%= client.getCode() %>" />
				</form>
				<a onclick="document.getElementById('removeClient<%=client.getCode()%>').submit();" class="btn-floating pulse waves-effect waves-light orange darken-4 tooltipped" data-position="bottom" data-tooltip="Supprimer Client"><i class="material-icons right">remove_circle</i></a>
			</td>
		</tr>
		<% }
		}else{ 
			Client client = (Client)request.getAttribute("client");
			if( client != null){
		%>
			<tr class="z-depth-1">
			<td class="blue-text" style="font-weight: bold"><blockquote style="border-left-color: #2196F3 !important"><%= client.getCode() %></blockquote></td>
			<td><%= client.getNom() %></td>
			<td><%= client.getPrenom() %></td>
			<td><%= client.getVille() %></td>
			<td>
				<a href="<%= request.getContextPath() %>/edit?code=<%= client.getCode() %>" class="btn-floating pulse waves-effect waves-light #ffab00 amber accent-4"><i class="material-icons right lighten-2 tooltipped" data-position="bottom" data-tooltip="Modifier Client">edit</i></a>
			</td>
			<td>
				<form id="removeClient<%= client.getCode() %>" action="<%= request.getContextPath() %>/delete" method="POST">
				<input type="hidden" name="code" value="<%= client.getCode() %>" />
				</form>
				<a onclick="document.getElementById('removeClient<%= client.getCode() %>').submit();" class="btn-floating pulse waves-effect waves-light orange darken-4 tooltipped" data-position="bottom" data-tooltip="Supprimer Client"><i class="material-icons right">remove_circle</i></a>
			</td>
		</tr>
		<% }else{%>
				<div class="card-panel white-text red darken-1"><i class="left material-icons">do_not_disturb</i>Aucun client trouvé avec ID <%= request.getParameter("code") %></div>
		
		<% }} %>
		</tbody>
	</table>
	</div>
	
	
</main>


	<%@ include file="parts/footer.jsp" %>
	
	
	<script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="static/js/materialize.min.js"></script>
	<script type="text/javascript" src="static/js/custom.js"></script>
	
</body>
</html>