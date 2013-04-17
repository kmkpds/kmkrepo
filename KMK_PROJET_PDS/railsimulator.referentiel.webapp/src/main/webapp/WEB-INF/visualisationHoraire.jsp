<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<script type="text/javascript" src="js/main.js"></script>	
		<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
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
								<li>
									<a href="#">Manuel</a>
								</li>
								<li>
									<a href="#">Demande d'évolution</a>
								</li>
							</ul>
						</div>
					</div>
					<!-- /Global Links Top -->
					<!-- Page Header -->
					<div id="RnoBranding" class="sc"> 
						<a href="#">
							<img src="images/logos/kmk-rail.jpg" id="RnoLogo" alt="Kamikaze" width="63" height="63"/>
						</a>
						<h1 id="RnoApplicationName">
							<img src="images/header/kmk-header.jpg" alt="KAMIKAZE PROJECT - Rail Simulator"/>
						</h1>
						<span>
							<img src="images/branding-coin.gif" alt="" width="31" height="31"/>
						</span>
					</div>
					<!-- /Page Header --> 
					<jsp:include page="menuCreationHoraire.jsp"/> 
					<!-- Body Content -->
					<br>
					<div id="RnoBody ">
						<center>
							<fieldset style="width: 700px">
								<legend>Paramètres horaire</legend>
								<table>
									<tr style="height:20px">
										<td>
											<label for="idLigne" width="250px">
												Identifiant ligne:
											</label> 
										</td>
										<td align="center">
											<input type="text" readonly name="idLigne" id="idLigne" value="<c:out value="${Parametres.idLigne}"/>" />
										</td>
									</tr>
									<br>
									<tr style="height:20px">
										<td>
											<label for="vitesseMoyenne" width="250px">Vitesse moyenne:</label> 
										</td>
										<td align ="center">
											<input type="text" readonly name="vitesseMoyenne" id="vitesseMoyenne" value="<c:out value="${Parametres.vitesseMoyenne}"/>" />
										</td>
									</tr>	
									<br>	
									<tr style="height:20px">
										<td></td>
										<td align="center" width="15%">Jours ouvrés</td>
										<td align="center" width="15%">Samedi</td>
										<td align="center" width="25%">Dimanche et jours fériés</td>	
									</tr>
									<tr style="height:20px">
										<td>Heure du premier train:</td>
										<td align="center">
											<input type="text" readonly name="heurePTJO" id="heurePTJO" value="<c:out value="${Parametres.heurePremierTrainJO}"/>" />
										</td>			
										<td align="center">
											<input type="text" readonly name="heurePTSamedi" id="heurePTSamedi" value="<c:out value="${Parametres.heurePremierTrainSamedi}"/>" />
										</td>
										<td align="center">
											<input type="text" readonly name="heurePTDimancheJF" id="heurePTDimancheJF" value="<c:out value="${Parametres.heurePremierTrainDimancheJF}"/>" />
										</td>
									</tr>
									<tr style="height:20px">
										<td>
											Heure du dernier train: 
										</td>
										<td align="center">
											<input type="text" readonly name="heureDTJO" id="heureDTJO" value="<c:out value="${Parametres.heureDernierTrainJO}"/>" />
										</td>			
										<td align="center">
											<input type="text" readonly name="heureDTSamedi" id="heureDTSamedi" value="<c:out value="${Parametres.heureDernierTrainSamedi}"/>" />
										</td>
										<td align="center">
											<input type="text" readonly name="heureDTDimancheJF" id="heureDTDimancheJF" value="<c:out value="${Parametres.heureDernierTrainDimancheJF}"/>" />
										</td>
									</tr>
									<tr>
										<td>
											Cadencement :</label> 
										</td>
										<td align="center">
											<input type="text" readonly name="cadencementJO" id="cadencementJO" value="<c:out value="${Parametres.cadencementJO}"/>" />
										</td>			
										<td align="center">
											<input type="text" readonly name="cadencementSamedi" id="cadencementSamedi" value="<c:out value="${Parametres.cadencementSamedi}"/>" />
										</td>
										<td align="center">
											<input type="text" readonly name="cadencementDimancheJF" id="cadencementDimancheJF" value="<c:out value="${Parametres.cadencementDimancheJF}"/>" />
										</td>
									</tr>
									<tr style="height:20px">
										<td>
											Temps de stationnement (minutes - secondes) 
										</td>
										<td align="center">
											<input type="text" readonly name="tempsStationnementJO" id="tempsStationnementJO" value="<c:out value="${Parametres.tempsStationnementJO}"/>" />
										</td>			
										<td align="center">
											<input type="text" readonly name="tempsStationnementSamedi" id="tempsStationnementSamedi" value="<c:out value="${Parametres.tempsStationnementSamedi}"/>" />
										</td>
										<td align="center">
											<input type="text" readonly name="tempsStationnementDimancheJF" id="tempsStationnementDimancheJF" value="<c:out value="${Parametres.tempsStationnementDimancheJF}"/>" />
										</td>
									</tr>
										<td>
											Heures de pointe: 
										</td>
										<td align="center">
											<input type="text" readonly name="heuresPointeJO" id="heuresPointeJO" value="<c:out value="${Parametres.heuresPointeJO}"/>" />
										</td>			
										<td align="center">
											<input type="text" readonly name="heuresPointeSamedi" id="heuresPointeSamedi" value="<c:out value="${Parametres.heuresPointeSamedi}"/>" />
										</td>
										<td align="center">
											<input type="text" readonly name="heuresPointeDimancheJF" id="heuresPointeDimancheJF" value="<c:out value="${Parametres.heuresPointeDimancheJF}"/>" />
										</td>
									</tr>
								</table>
							</fieldset>
						</center>                           				
					</div>
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>