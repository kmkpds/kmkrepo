<%@page import="java.util.List"%>
<%@page import="railsimulator.web.incident.base.UtilsProjet"%>
<%@page import="beans.Incident"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detail Incident</title>
</head>
<body>
<h1>Incident :</h1>
<br>
<%
Incident incident =(Incident) session.getAttribute("incident");
%>
id : <%=incident.getIdIncident() %>
<br>
Description : <%=incident.getDescription() %>
<br>
Date debut : <%=incident.getDateDebut() %> , Mois : <%=incident.getMois() %> , Annee : <%=incident.getAnnee() %> 
<br>
Date de fin<%=incident.getDateFin() %>
<br>
Criticite : <%=incident.getCriticite() %>
<br>
Procedure : <%=incident.getProcedure()!=null?incident.getProcedure().getLibelleProcedure():"--" %>
<br>
Type : <%=incident.getTypeIncident()!=null?incident.getTypeIncident().getLibelleType():"--" %>
<br>
BiLigne : <%=incident.getBiLigne()!=null?incident.getBiLigne().getNomBiLigne():"--" %>
<br>
<br>
Les actions possibles :
<%
List<String> lists=UtilsProjet.getAllActionLibelle();
for(int i=0;i<lists.size();i++)
{
	%>
	<button><%=lists.get(i) %></button>
	<%
}

%>
</body>
</html>