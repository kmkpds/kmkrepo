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
<title>Visualisation Par Train - Rail Simulator</title>

<script type="text/javascript" src="js/visualiserTrainJavaScript.js"></script>

<script type="text/javascript">
setTimeout("testIntegration()",500);

//testIntegration2();	
</script>
</head>
<body>
	<form action="train" method=get class="RnoForm" name="formAfficher2">
		<h3 class="RnoSectionTitle">Informations du Train</h3>



		<div class="RnoSectionContent">
			<div class="RnoDataTable">
				<table>
					<tr class="RnoMainHeader">

						<th><a href="#">IdTrain</a></th>
						<th><a href="#">Vitesse</a></th>
						<th><a href="#">Sens</a></th>
						<th><a href="#">Nombre de wagon</a></th>
						<th><a href="#">Heure arrivée du train(Jour ouvré)</a></th>
						<th><a href="#">Heure arrivée du train(Samedi)</a></th>
						<th><a href="#">Heure arrivée du train(Dimanche/Jour Ferié)</a></th>

					</tr>


					<c:forEach var="train" items="${listeTrainByLigne}">
						<tr>
							<c:if test="${train.idTrain == idTrain}">
								<td><c:out value="${train.idTrain}" /></td>
								<td><c:out value="${train.vitesse}" /></td>
								<td><c:out value="${train.sens}" /></td>
								<td><c:out value="${train.nombredewagon}" /></td>
								
								
 							
								<td><select id="idtrainHoraire" name="idtrainHoraire">	
								<c:forEach items="${listeTrainHoraireStation}" var="trainHoraire">	
									<c:if test="${train.idTrain == trainHoraire.train.idTrain}"> 							
										<option value="${trainHoraire.heureJO}">${trainHoraire.heureJO} ${trainHoraire.station.nomStation}</option>  
									</c:if> 	
								</c:forEach>								
								</select></td>
								<td><select id="idtrainHoraire" name="idtrainHoraire">
								<c:forEach items="${listeTrainHoraireStation}" var="trainHoraire">
									<c:if test="${train.idTrain == trainHoraire.train.idTrain}"> 
											<option value="${trainHoraire.heureSamedi}">${trainHoraire.heureSamedi} ${trainHoraire.station.nomStation}</option> 
									</c:if> 
								</c:forEach>											 
								</select></td>
								<td><select id="idtrainHoraire" name="idtrainHoraire">
								<c:forEach items="${listeTrainHoraireStation}" var="trainHoraire">
									<c:if test="${train.idTrain == trainHoraire.train.idTrain}"> 
											<option value="${trainHoraire.heureDimancheJF}">${trainHoraire.heureDimancheJF} ${trainHoraire.station.nomStation}</option>
									</c:if> 	
								</c:forEach>									
								</select></td>
								</c:if> 
						</tr>
					</c:forEach>

					<tr class="RnoCenter">

						<th><a href="#">idwagon</a></th>
						<th><a href="#">temperatureWagon</a></th>
						<th><a href="#">frequentation</a></th>
					</tr>

					<c:forEach var="wagon" items="${listeWagon}">
						<tr>
							<td onclick="showhide(${wagon.idWagon})"><c:out
									value="${wagon.idWagon}" /></td>
							<td><c:out value="${wagon.temperatureWagon}" /></td>
							<td><c:out value="${wagon.frequentation}" /></td>

							<tr class="RnoRight" name=${wagon.idWagon } style="display: none;"><th><a href="#">idPorte</a></th>
							<th><a href="#">statut</a></th>
						</tr>

							<c:forEach var="porte" items="${listePortes}">
								<tr name=${wagon.idWagon } style="display: none;">
									<c:if test="${(porte.wagon.idWagon == wagon.idWagon)}">
									
									<td><c:out value="${porte.idPorte}" /></td>
									<c:if test="${porte.statut == 0}">
										<td >Porte fermée</td>
									</c:if>
									<c:if test="${porte.statut == 1}">
										<td >Porte ouverte</td>
									</c:if>
									</c:if>
								</tr>
							</c:forEach>


						</tr>
						<tr class="RnoRight" name=${wagon.idWagon } style="display: none;">

							<th><a href="#">idFrein</a></th>
							<th><a href="#">temperatureFrein doit etre < 90°</a></th>

							<c:forEach var="frein" items="${listeFreins}">
								<c:if test="${frein.wagon.idWagon == wagon.idWagon}">
									<tr name=${wagon.idWagon } style="display: none;">
									<td><c:out value="${frein.idFrein}" /></td>
									<c:if test="${frein.temperature> 90}">
									<td><c:out value="${frein.temperature}" /> temperature elevee !!</td>
									</c:if>
									<c:if test="${frein.temperature<= 90}">
									<td><c:out value="${frein.temperature}" /></td>
									</c:if>
									</c:if>
						</tr>
					</c:forEach>
					</tr>
					</c:forEach>
					<div id="errors">
						<c:forEach items="${errorsMessage}" var="list">
							<p style="color: red">${errorsMessage}</p>
						</c:forEach>
					</div>
				</table>
			</div>
		</div>
		<input type="hidden" name="idTrain" id="idTrain"
			value="<c:out value="${idTrain}"/>" />

		<button type="submit" value="deconnexionTrainTest" name="action"
			id="action" width="200px">Deconnexion</button>
	</form>



</body>
</html>