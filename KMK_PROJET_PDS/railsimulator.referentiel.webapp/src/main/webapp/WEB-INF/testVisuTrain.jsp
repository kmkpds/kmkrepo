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
	function loadStationTrain(ligneidentifiant) {
		document.formAfficher.idStation.options.length = 0;
		document.formAfficher.idTrain.options.length = 0;
		j = 0;
		<c:forEach items="${listeStation}" var="Station">
		stationLigneId = <c:out value="${Station.ligne.idLigne}"/>;
		stationId = <c:out value="${Station.idStation}"/>;
		//stationName =<c:out value="${Station.nomStation}"/>;
		if (stationLigneId == ligneidentifiant) {
			//alert(stationLigneId);
			document.formAfficher.idStation[j] = new Option(stationId);
			j = j + 1;
		}
		</c:forEach>
		j = 0;
		<c:forEach items="${listeTrain}" var="Train">
		trainLigneId = <c:out value="${Train.ligne.idLigne}"/>;
		trainId = <c:out value="${Train.idTrain}"/>;
		//stationName =<c:out value="${Station.nomStation}"/>;

		if (trainLigneId == ligneidentifiant) {
			//alert(trainLigneId);
			document.formAfficher.idTrain[j] = new Option(trainId);
			j = j + 1;
		}
		</c:forEach>
	}//fin fonction loadStationTrain

	function loadStationTrainTest() {
		//alert("dans loadStationtest");
		document.getElementById("idLigne").selectedIndex = 1;
		var ligneID = document.getElementById("idLigne").options[document
				.getElementById("idLigne").selectedIndex].value;
		loadStationTrain(ligneID); //document.formAfficher.idLigne.value
		var listNom = new Array();
		var i = 0;
		<c:forEach items="${listeStation}" var="Station">
		stationLigneId = <c:out value="${Station.ligne.idLigne}"/>;
		stationId = <c:out value="${Station.idStation}"/>;
		//stationName =<c:out value="${Station.nomStation}"/>;
		if (stationLigneId == ligneID) {
			listNom[i] = stationId;
			i = i + 1;
		}
		</c:forEach>
		<c:forEach items="${listeTrain}" var="Train">
		trainLigneId = <c:out value="${Train.ligne.idLigne}"/>;
		trainId = <c:out value="${Train.idTrain}"/>;
		//stationName =<c:out value="${Station.nomStation}"/>;
		if (trainLigneId == ligneID) {
			listNom[i] = trainId;
			i = i + 1;
		}
		</c:forEach>
		var lb1 = document.getElementById("idStation").options[0].value;
		var list1 = listNom[0];
		var lb2 = document.getElementById("idStation").options[1].value;
		var list2 = listNom[1];
		var lb3 = document.getElementById("idTrain").options[0].value;
		var list3 = listNom[2];
		var lb4 = document.getElementById("idTrain").options[1].value;
		var list4 = listNom[3];

		if (lb1 == list1 && lb2 == list2 && lb3 == list3 && lb4 == list4) {
			alert("La fonction loadStationTrain fonctionne correctement");
		} else {
			alert("La fonction loadStationTrain ne fonctionne pas correctement");
		}

	}//fin fonction loadStationTrain


	function testIntegration() {
		document.getElementById("idLigne").selectedIndex = 1;
		alert("dans testI1");
		var ligneID =document.getElementById("idLigne").options[document.getElementById("idLigne").selectedIndex].value;
		alert(ligneID);
		loadStationTrain(ligneID);
		
		
	}//fin fonction testIntegration

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
				</div> <!-- /Global Links Top --> <!-- Page Header --> <!-- Page Header -->
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
				</div> <!-- /Page Header --> <jsp:include page="menuTrain.jsp" /> <!-- Body Content -->
				<div id="RnoBody">

					<form action="train" method="get" class="RnoForm"
						name="formAfficher">
						<h3 class="RnoSectionTitle">Informations du Train</h3>
						<div class="RnoSectionContent RnoSpacers">
							<div class="sc">
								<div class="RnofLeft">
									<div id="errors">
										<c:forEach items="${errorsMessage}" var="list">
											<p style="color: red">${errorsMessage}</p>
										</c:forEach>
									</div>
									<table class="RnoFieldsValuesTable">

										<tr>

											<th><label> Nom de la Ligne <strong
													class="RnoMandatory"></strong> <span>:</span>
											</label></th>
											<td><select id="idLigne" name="idLigne"
												onchange="loadStationTrainTest(this.value)">
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


										</tr>
									</table>
								</div>
							</div>
						</div>
						<button type="submit" value="testIntegration" name="action"
							id="action" width="200px" onclick="testIntegration()">TestIntégration</button>
					</form>
					<br>
					<button type="submit" value="TestJavaScriptCode" name="action"
						id="action" width="200px" onclick="loadStationTrainTest()">TestJavaScriptCode</button>

				</div>
			</td>
		</tr>
	</table>

</body>
</html>