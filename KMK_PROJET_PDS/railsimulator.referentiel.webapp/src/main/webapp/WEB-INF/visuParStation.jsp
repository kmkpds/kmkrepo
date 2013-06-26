<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" type="text/css" href="css/corp.css" />
<link rel="alternate stylesheet" type="text/css" href="css/print.css"
	media="screen" title="Version imprimable" id="stylesheet-print" />
<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
<title>Visualisation Par Station - Rail Simulator</title>

</head>
<body>
	<form action="train" method="get" class="RnoForm" name="formAfficher">
		<h3 class="RnoSectionTitle">Informations de la station</h3>



		<div class="RnoSectionContent">
			<div class="RnoDataTable">
				<table>
					<tr class="RnoMainHeader">

						<th><a href="#">IdStation</a></th>
						<th><a href="#">NomStation</a></th>
						<th><a href="#">Nombre de passagers</a></th>


					</tr>


					<c:forEach var="station" items="${listeStationByLigne}">
						<tr>
							<c:if test="${station.idStation == idStation}">
								<td><c:out value="${station.idStation}" /></td>
						
							<td><c:out value="${station.nomStation}" /></td>
							<td><c:out value="${station.nombrepassagers}" /></td>
							</c:if>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
		<input type="hidden" name="idStation" id="idStation"
			value="<c:out value="${idStation}"/>" />

		<button type="submit" value="deconnexionStation" name="action" id="action"
			width="200px">Deconnexion</button>
	</form>




</body>
</html>