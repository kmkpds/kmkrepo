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
						<form action="horaire" method="post">
									<fieldset style="width: 700px">
										<legend>Paramètre horaire</legend>
										<table>
											<tr style="height:20px">
												<td>
													<label for="idLigne" width="250px">
														Identifiant ligne:
													</label> 
												</td>
												<td align="center">
													<select id="idLigne" name="idLigne" style="width:100px">
														<c:forEach items="${listeLigne}" var="list">
															<option value="${list.idLigne}">${list.nomLigne}</option>
													
														</c:forEach>
													</select>
												</td>
											</tr>
											<br>
											<tr style="height:20px">
												<td>
													<label for="vitesseMoyenne" width="250px">Vitesse moyenne:</label> 
												</td>
												<td align ="center">
													<select id="vitesseMoyenne" name="vitesseMoyenne" style="width:100px">
														<c:forTokens var="entry" items="20;30;40;50;60;70;80;90;100;110;120;130;140" delims=";">
															<option value="${entry}">  ${entry} km/h</option>
														</c:forTokens>
													</select>
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
												<td>Heure du premier train (heures - minutes):</td>
												<td align="center">
													<select id="heurePTJO" name="heurePTJO">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minutePTJO" name="minutePTJO">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>			
												<td align="center">
													<select id="heurePTSamedi" name="heurePTSamedi">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minutePTSamedi" name="minutePTSamedi">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
												<td align="center">
													<select id="heurePTDimancheJF" name="heurePTDimancheJF">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minutePTDimancheJF" name="minutePTDimancheJF">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr style="height:20px">
												<td>
													Heure du dernier train (heures - minutes): 
												</td>
												<td align="center">
													<select id="heureDTJO" name="heureDTJO">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minuteDTJO" name="minuteDTJO">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>			
												<td align="center">
													<select id="heureDTSamedi" name="heureDTSamedi">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minuteDTSamedi" name="minuteDTSamedi">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
												<td align="center">
													<select id="heureDTDimancheJF" name="heureDTDimancheJF">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minuteDTDimancheJF" name="minuteDTDimancheJF">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<td>
													Cadencement (minutes - secondes):</label> 
												</td>
												<td align="center">
													<select id="CadencementJOMin" name="CadencementJOMin">
														<c:forEach var="entry" begin="00" end="10">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="CadencementJOSec" name="CadencementJOSec">
														<option value="0">0</option>
														<option value="15">15</option>
														<option value="30">30</option>
														<option value="45">45</option>
													</select>
												</td>			
												<td align="center">
													<select id="CadencementSamediMin" name="CadencementSamediMin">
														<c:forEach var="entry" begin="00" end="10">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="CadencementSamediSec" name="CadencementSamediSec">
														<option value="0">0</option>
														<option value="15">15</option>
														<option value="30">30</option>
														<option value="45">45</option>
													</select>
												</td>
												<td align="center">
													<select id="CadencementDimancheJFMin" name="CadencementDimancheJFMin">
														<c:forEach var="entry" begin="00" end="10">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="CadencementDimancheJFSec" name="CadencementDimancheJFSec">
														<option value="0">0</option>
														<option value="15">15</option>
														<option value="30">30</option>
														<option value="45">45</option>
													</select>
												</td>
											</tr>
											<tr style="height:20px">
												<td>
													Temps de stationnement (minutes - secondes) 
												</td>
												<td align="center">
													<select id="tempsStationnementJOMin" name="tempsStationnementJOMin">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="tempsStationnementJOSec" name="tempsStationnementJOSec">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>			
												<td align="center">
													<select id="tempsStationnementSamediMin" name="tempsStationnementSamediMin">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="tempsStationnementSamediSec" name="tempsStationnementSamediSec">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
												<td align="center">
													<select id="tempsStationnementDimancheJFMin" name="tempsStationnementDimancheJFMin">
														<c:forEach var="entry" begin="00" end="23">
															<option value="  ${entry}, ">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="tempsStationnementDimancheJFSec" name="tempsStationnementDimancheJFSec">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr style="height:115px">
												<td>
													Heures de pointe: 
												</td>
												<td align="center">
													<div style="width: 125px; height: 100px; overflow-y: scroll;">
														<input type="checkbox" name="heuresPointeJO67" value="heuresPointeJO67"> 06:00 - 07:00<br>
														<input type="checkbox" name="heuresPointeJO78" value="heuresPointeJO78"> 07:00 - 08:00<br>
														<input type="checkbox" name="heuresPointeJO89" value="heuresPointeJO89"> 08:00 - 09:00<br>
														<input type="checkbox" name="heuresPointeJO910" value="heuresPointeJO910"> 09:00 - 10:00<br>
														<input type="checkbox" name="heuresPointeJO1011" value="heuresPointeJO1011"> 10:00 - 11:00<br>
														<input type="checkbox" name="heuresPointeJO1112" value="heuresPointeJO1112"> 11:00 - 12:00<br>
														<input type="checkbox" name="heuresPointeJO1213" value="heuresPointeJO1213"> 12:00 - 13:00<br>
														<input type="checkbox" name="heuresPointeJO1314" value="heuresPointeJO1314"> 13:00 - 14:00<br>
														<input type="checkbox" name="heuresPointeJO1415" value="heuresPointeJO1415"> 14:00 - 15:00<br>
														<input type="checkbox" name="heuresPointeJO1516" value="heuresPointeJO1516"> 15:00 - 16:00<br>
														<input type="checkbox" name="heuresPointeJO1617" value="heuresPointeJO1617"> 16:00 - 17:00<br>
														<input type="checkbox" name="heuresPointeJO1718" value="heuresPointeJO1718"> 17:00 - 18:00<br>
														<input type="checkbox" name="heuresPointeJO1819" value="heuresPointeJO1819"> 18:00 - 19:00<br>
														<input type="checkbox" name="heuresPointeJO1920" value="heuresPointeJO1920"> 19:00 - 20:00<br>
														<input type="checkbox" name="heuresPointeJO2021" value="heuresPointeJO2021"> 20:00 - 21:00<br>
														<input type="checkbox" name="heuresPointeJO2122" value="heuresPointeJO2122"> 21:00 - 22:00<br>
													</div>
												</td>
												<td align="center">
													<div style="width: 125px; height: 100px; overflow-y: scroll;">
														<input type="checkbox" name="heuresPointeSamedi67" value="heuresPointeSamedi67"> 06:00 - 07:00<br>
														<input type="checkbox" name="heuresPointeSamedi78" value="heuresPointeSamedi78"> 07:00 - 08:00<br>
														<input type="checkbox" name="heuresPointeSamedi89" value="heuresPointeSamedi89"> 08:00 - 09:00<br>
														<input type="checkbox" name="heuresPointeSamedi910" value="heuresPointeSamedi910"> 09:00 - 10:00<br>
														<input type="checkbox" name="heuresPointeSamedi1011" value="heuresPointeSamedi1011"> 10:00 - 11:00<br>
														<input type="checkbox" name="heuresPointeSamedi1112" value="heuresPointeSamedi1112"> 11:00 - 12:00<br>
														<input type="checkbox" name="heuresPointeSamedi1213" value="heuresPointeSamedi1213"> 12:00 - 13:00<br>
														<input type="checkbox" name="heuresPointeSamedi1314" value="heuresPointeSamedi1314"> 13:00 - 14:00<br>
														<input type="checkbox" name="heuresPointeSamedi1415" value="heuresPointeSamedi1415"> 14:00 - 15:00<br>
														<input type="checkbox" name="heuresPointeSamedi1516" value="heuresPointeSamedi1516"> 15:00 - 16:00<br>
														<input type="checkbox" name="heuresPointeSamedi1617" value="heuresPointeSamedi1617"> 16:00 - 17:00<br>
														<input type="checkbox" name="heuresPointeSamedi1718" value="heuresPointeSamedi1718"> 17:00 - 18:00<br>
														<input type="checkbox" name="heuresPointeSamedi1819" value="heuresPointeSamedi1819"> 18:00 - 19:00<br>
														<input type="checkbox" name="heuresPointeSamedi1920" value="heuresPointeSamedi1920"> 19:00 - 20:00<br>
														<input type="checkbox" name="heuresPointeSamedi2021" value="heuresPointeSamedi2021"> 20:00 - 21:00<br>
														<input type="checkbox" name="heuresPointeSamedi2122" value="heuresPointeJO2122"> 21:00 - 22:00<br>
													</div>
												</td>
												<td align="center">
													 <div style="width: 125px; height: 100px; overflow-y: scroll;">
														<input type="checkbox" name="heuresPointeDimancheJF67" value="heuresPointeDimancheJF67"> 06:00 - 07:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF78" value="heuresPointeDimancheJF78"> 07:00 - 08:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF89" value="heuresPointeDimancheJF89"> 08:00 - 09:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF910" value="heuresPointeDimancheJF910"> 09:00 - 10:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF1011" value="heuresPointeDimancheJF1011"> 10:00 - 11:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF1112" value="heuresPointeDimancheJF1112"> 11:00 - 12:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF1213" value="heuresPointeDimancheJF1213"> 12:00 - 13:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF1314" value="heuresPointeDimancheJF1314"> 13:00 - 14:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF1415" value="heuresPointeDimancheJF1415"> 14:00 - 15:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF1516" value="heuresPointeDimancheJF1516"> 15:00 - 16:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF1617" value="heuresPointeDimancheJF1617"> 16:00 - 17:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF1718" value="heuresPointeDimancheJF1718"> 17:00 - 18:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF1819" value="heuresPointeDimancheJF1819"> 18:00 - 19:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF1920" value="heuresPointeDimancheJF1920"> 19:00 - 20:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF2021" value="heuresPointeDimancheJF2021"> 20:00 - 21:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF2122" value="heuresPointeDimancheJF2122"> 21:00 - 22:00<br>
													 </div>
												</td>
											</tr>
										</table>
										<br>
										<center>
											<button type="submit" value="genererGrilleHoraire" name="action" id="action" width="200px">
												Créer la grille horaire
											</button>
										</center>
									</fieldset>
								</form>
						</center>                           				
					</div>
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>