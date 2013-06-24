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
<title>Visualisation Train - Rail Simulator</title>
<script type="text/javascript">
		function loadStationTrain(ligneidentifiant){
			document.formAfficher.idStation.options.length = 0;
			document.formAfficher.idTrain.options.length = 0;
			j=0;
			<c:forEach items="${listeStation}" var="Station">
				stationLigneId = <c:out value="${Station.ligne.idLigne}"/>;
				stationId =<c:out value="${Station.idStation}"/>;
				//stationName =<c:out value="${Station.nomStation}"/>;
				if(stationLigneId==ligneidentifiant){
					//alert(stationLigneId);
					document.formAfficher.idStation[j] = new Option(stationId);
					j=j+1;
				}	
			</c:forEach> 
			j=0;
			<c:forEach items="${listeTrain}" var="Train">
				trainLigneId = <c:out value="${Train.ligne.idLigne}"/>;
				trainId =<c:out value="${Train.idTrain}"/>;
				//stationName =<c:out value="${Station.nomStation}"/>;
					
				if(trainLigneId==ligneidentifiant){
					//alert(trainLigneId);
					document.formAfficher.idTrain[j] = new Option(trainId);
					j=j+1;
				}	
			</c:forEach> 
		}//fin fonction loadStationTrain

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
								<li>
									<a href="#">Manuel</a>
								</li>
								<li>
									<a href="#">Demande d'évolution</a>
								</li>
							</ul>
						</div>
					</div> <!-- /Global Links Top --> <!-- Page Header --> <!-- Page Header -->
					<div id="RnoBranding" class="sc">
						<a href="#">
							<img src="images/logos/kmk-rail.jpg" id="RnoLogo" alt="Kamikaze" width="63" height="63" />
						</a>
						<h1 id="RnoApplicationName">
							<img src="images/header/kmk-header.jpg" alt="KAMIKAZE PROJECT - Rail Simulator" />
						</h1>
						<span>
							<img src="images/branding-coin.gif" alt="" width="31" height="31" />
						</span>
					</div> <!-- /Page Header --> <jsp:include page="menuTrain.jsp" /> <!-- Body Content -->
					<div id="RnoBody">

	<form action="train" method="get" class="RnoForm" name="formAfficher">
		<h3 class="RnoSectionTitle">Informations du Train</h3>
		<div class="RnoSectionContent RnoSpacers">
			<div class="sc">
				<div class="RnofLeft">
					<div id="errors">
						<c:forEach items="${errorsMessage}" var="list">
							<p style="color: red">${errorsMessage}</p>
						</c:forEach>
					</div>
					<button type="submit" value="purger" onclick="checkSubmit()"
						name="action" id="action" width="200px">Purger</button>
					<button type="submit" value="mock" onclick="checkSubmit()"
						name="action" id="action" width="200px">Mock</button>
					<table class="RnoFieldsValuesTable">

						<tr>

							<th><label> Nom de la Ligne <strong
									class="RnoMandatory"></strong> <span>:</span>
							</label></th>
							<td><select id="idLigne" name="idLigne" onchange="loadStationTrain(this.value)">
								<option value=""></option>
								<c:forEach items="${listeLigne}" var="list">
									<option value="${list.idLigne}">${list.nomLigne}</option>
								</c:forEach>
							</select></td>
							<th><label> Nom de la station <strong
									class="RnoMandatory"></strong> <span>:</span>
							</label></th>
							<td><select id="idStation" name="idStation">
									<c:forEach items="${listeStation}" var="station">
										<!-- <option value="${station.idStation}">${station.nomStation}</option> -->
									</c:forEach>
							</select></td>
							<th><label> Id du train <strong
									class="RnoMandatory"></strong> <span>:</span>
							</label></th>
							<td><select id="idTrain" name="idTrain">
									<c:forEach items="${listeTrain}" var="train">
										<!--  <option value="${train.idTrain}">${train.idTrain}</option> -->
									</c:forEach>
									
							</select></td>
							<!--  <th><input type="submit" name="action" id="action"
								value="Visualisationparstation" /></th>
								<th><input type="submit" name="action" id="action"
								value="Visualisationpartrain" /></th>-->
								<th><button type="submit" value="VisualisationParStation"
										name="action" id="action"
										width="200px">Visualisation par station</button>
										<button type="submit" value="VisualisationParTrain"
										name="action" id="action"
										width="200px">Visualisation par train</button></th>

						</tr>
					</table>
				</div>
			</div>
		</div>

	</form>


</div>
</td>
</tr>
</table>

</body>
</html>