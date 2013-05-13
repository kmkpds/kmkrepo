<%@page import="beans.Incident"%>
<%@page import="java.util.List"%>
<%@page import="railsimulator.web.incident.base.UtilsProjet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INCIDENT À TRAITER</title>
</head>
<body>
<br>
<h1>Liste des Incidents de la base </h1>
<%
	List<Incident> incidents= UtilsProjet.getAllIncident();
%>
<br>
<table border="1">
<tr>
<th>Détail</th>
<th>Numero</th>
<th>description</th>
<th>date de début</th>
<th>date de fin</th>
<th>criticite</th>

</tr>
<%
for(int i=0;i<incidents.size();i++)
{
%>
<tr>
<td>
	<form action="/Incident/voirDetailIncident" method="post">
		<input type="hidden" value="<%=incidents.get(i).getIdIncident()%>"  id="idincident" name="idincident" >
		<button type="submit" value="Voir" > Voir</button> 
	</form>	
		</td>
<td><%=incidents.get(i).getIdIncident()%></td>
<td><%=incidents.get(i).getDescription()%></td>
<td><%=incidents.get(i).getDateDebut()%></td>
<td><%=incidents.get(i).getDateFin()%></td>
<td><%=incidents.get(i).getCriticite()%></td>
</tr>
<%
}
%>
</table>

</body>
</html>