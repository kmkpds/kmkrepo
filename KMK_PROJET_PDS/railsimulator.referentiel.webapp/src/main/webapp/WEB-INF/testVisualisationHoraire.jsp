<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> --%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>Creation Horaire - Rail Simulator</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/testCreationHoraireJavaScript.js"></script>
<script type="text/javascript">
setTimeout("testIntegration(2)",500);
</script>
</head>
<body>
	<!-- Page Layout -->
	<table id="RnoPage" class="RnoLayout-1col">
		<tr>
			<td id="RnoPageWidthRange">
				<!-- Global Links Top -->
				<div id="RnoGlobalLinksTop" class="sc">
					<div>
						<ul>
							<li><a href="#">Manuel</a></li>
							<li><a href="#">Demande d'évolution</a></li>
						</ul>
					</div>
				</div> <!-- /Global Links Top --> <!-- Page Header -->
				<div id="RnoBranding" class="sc">
					<a href="#"> <img src="images/logos/kmk-rail.jpg" id="RnoLogo"
						alt="Kamikaze" width="63" height="63" />
					</a>
					<h1 id="RnoApplicationName">
						<img src="images/header/kmk-header.jpg"
							alt="KAMIKAZE PROJECT - Rail Simulator" />
					</h1>
					<span> <img src="images/branding-coin.gif" alt="" width="31"
						height="31" />
					</span>
				</div> <!-- /Page Header --> <jsp:include page="menuCreationHoraire.jsp" />
				<!-- Body Content --> <br>
				<div id="RnoBody ">
					<center>
						<fieldset style="width: 700px">
							<legend>Visualisation horaire - <c:out value="${NomLigne}"/> </legend>
							<form name="parametresHoraire" action="horaire" method="post">
								<table>
									<tr style="height: 20px">
										<td><label for="idLigne" width="250px">
												Identifiant ligne: </label></td>
										<td align="center">
											<select id="idLigne" name="idLigne" style="width: 130px">
												<c:forEach items="${listeLigne}" var="list">
													<option value="${list.idLigne}">${list.nomLigne}</option>
												</c:forEach>
											</select>
										</td>
<!-- 										<td> -->
<!-- 											<button type="submit" value="visualiserHoraire" name="action" id="action" -->
<!-- 											width="200px">Rechercher</button></td> -->
										<td>
											<a href="#" onclick="javascript:showHide('JO');">Jours ouvrés</a>  
											<a href="#" onclick="javascript:showHide('Samedi');">Samedi</a>	
											<a href="#" onclick="javascript:showHide('DimancheJF');">Dimanche et jours fériés</a> 
										</td>	
									</tr>
								</table>
							</form>
							<div id="tableauHoraireJO" name="tableauHoraireJO"/>
								<br>
								<center>Horaires jours ouvrés</center>
								<table id="tableauHoraireJOTab" border="1">
									<c:forEach var="Ligne"
										items="${fn:split(ListHoraireJOStr, '//')}">
										<tr>
											<c:forEach var="Colonne" items="${fn:split(Ligne, '|')}">
												<td align="center">${Colonne}</td>
											</c:forEach>
										</tr>
									</c:forEach>
								</table>
								<br>
							</div>
							<div id="tableauHoraireSamedi" name="tableauHoraireSamedi" />
								<center>Horaires samedi</center>
								<table id="tableauHoraireSamediTab" border="1">
									<c:forEach var="Ligne"
										items="${fn:split(ListHoraireSamediStr, '//')}">
										<tr>
											<c:forEach var="Colonne" items="${fn:split(Ligne, '|')}">
												<td align="center">${Colonne}</td>
											</c:forEach>
										</tr>
									</c:forEach>
								</table>
								<br>
							</div>
							<div id="tableauHoraireDimancheJF" name="tableauHoraireDimancheJF" />
								<center>Horaires dimanche et jours fériés</center>
								<table  id="tableauHoraireDimancheJFTab" border="1">
									<c:forEach var="Ligne"
										items="${fn:split(ListHoraireDimancheJFStr, '//')}">
										<tr>
											<c:forEach var="Colonne" items="${fn:split(Ligne, '|')}">
												<td align="center">${Colonne}</td>
											</c:forEach>
										</tr>
									</c:forEach>
								</table>
								<br>
							</div>
						</fieldset>
					</center>
				</div>
			</td>
		</tr>
	</table>
	<!-- /Page Layout -->
</body>
</html>