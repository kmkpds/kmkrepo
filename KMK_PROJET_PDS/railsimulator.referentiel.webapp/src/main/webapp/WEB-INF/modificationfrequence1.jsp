<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<link rel="stylesheet" type="text/css" href="css/corp.css" />
		<link rel="alternate stylesheet" type="text/css" href="css/print.css" media="screen" title="Version imprimable" id="stylesheet-print" />
		<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
		<title>Modifier Ligne - Rail Simulator</title>
		<script type="text/javascript" src="js/main.js"></script>
		
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.gvChart-1.1.min.js"></script>

		
		<script type="text/javascript">
		gvChartInit();
		jQuery(document).ready(function(){
	
			jQuery('#myTableStats').gvChart({
				chartType: 'BarChart',
				gvSettings: {
					vAxis: {title: 'Evolution des couts'},
					hAxis: {title: 'Frequence'},
					width: 720,
					height: 300
					}
			});
		});
</script>
		
		<style>
			body{
				text-align: center;
				font-family: Arial, sans-serif;
				font-size: 12px;
			}
			
			a{
				text-decoration: none;
				font-weight: bold;
				color: #555;
			}
			
			a:hover{
				color: #000;
			}
			
			div.main{
				margin: auto;
				text-align: left;
				width: 720px;
			}
		
			div.clean{
				border: 1px solid #850000;
			}
			
			.clean td, .clean th{
				border: 2px solid #bbb;
				background: #ddd;
				padding: 5px 10px;
				text-align: center;
				border-radius: 2px;
			}
			
			.clean table{
				margin: auto;
				margin-top: 15px;
				margin-bottom: 15px;
			}
			
			.clean caption{
				font-weight: bold;
				
			}
			
			.gvChart,.clean{
				border: 2px solid #850000;
				border-radius: 5px;
				-moz-border-radius: 10px;
				width: 720px;
				
				
				margin-top: 20px;
			}
			
			pre{
				background: #eee;
				padding: 10px;
				border-radius: 10px;
				-moz-border-radius: 10px;
			}
		</style>
		
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
					</div> <!-- /Page Header --> <jsp:include page="menuOptimisation.jsp" /> <!-- Body Content -->
					<div id="RnoBody">
						<c:if test="${erreurGénérale}">
							<table id="RnoMsg" class="RnoMsgError">
								<tr>
									<td>
										<h2>Des informations obligatoires sont manquantes.</h2>
										<p>Veuillez les compléter</p>
									</td>
								</tr>
							</table>
						</c:if>
						<!-- Middle Content -->
						<div id="RnoMainContent" class="sc">
							<!-- Page Title -->
							<h2 id="RnoPageTitle">
								<a></a>Informations d'une ligne : 
							</h2>
							<!-- /Page Title -->
							<!-- Vertical Steps Layout -->
							<div id="RnoVertStepsLayout">
								<!-- Steps -->
								<ol id="RnoVertSteps">
									<li >
										<p class="RSI1">Information d'une ligne</p>
									</li>
									<li class="RnoStepsCurrent">
										<p class="RSI2">Modification de la frequence</p>
									</li>
								</ol>
								<!-- /Steps -->
								<!-- Vertical Steps Right -->
								<div id="RnoVertStepsRight" class="RSI2">
									<!-- Bordered Workspace -->
									<div id="RnoWorkspace" style="min-height: 350px;">
										<!-- Vertical Steps Right Title -->
										<!--<p class="RnoVertStepsTitle">Ligne</p> -->
										<!-- /Vertical Steps Right Title -->
										<!-- Section -->
										<div class="RnoSectionFree">
											<form action="optimisation" method="get" class="RnoForm">
												<h3 class="RnoSectionTitle">Modification de la fréquence</h3>
												<div class="RnoSectionContent RnoSpacers">
													<div class="sc">
														<div class="RnofLeft">
															
															<table class="RnoFieldsValuesTable">

															<tr>
																	<th>
																		<label>
																			Nouvelle Frequence
																			<strong class="RnoMandatory">*</strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text"  name="NouvelleFrequence" value="<c:out value="${frequence}"/>"/> 
																	</td>
																	<th>
																	<input type="submit" name="action" id="action" value="OK" />
																	</th>
																</tr>
																
																<tr>
																	<th>
																		<label>
																			Nombre de train à rajouter 
																			 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text"  name="NombreDeTrainàRajouter" value="<c:out value="${nbTrainArajouter}"/>"/> 
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			Investissment Train : 
																			 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text"  name="InvestissmentTrain" value="<c:out value="${InvestissmentTrain}"/>"/> 
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			Retour Sur investissment : 
																			 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text"  name="rsi" value="<c:out value="${rsi}"/>"/> 
																	</td>
																</tr>
																
																<tr>
																	<th>
																		<label>
																			Nombre de places proposées :
																			 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text"  name="NombreDeTrainàRajouter" value="<c:out value="${nbPlaceProposee}"/>"/> 
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			Taux de satisfaction :
																			 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text"  name="Tauxsatisfaction" value="<c:out value="${TauxSatisfaction}"/>"/> 
																	</td>
																</tr>
																
															</table>
															
															</div>
															</div>
															</div>
															</form>
																				<div class="clean">
				<table id='myTableClean'>
					<caption>Tableau des coûts </caption>
					<thead>
						<tr>
							<th></th>
							<th>Coût unitaire</th>
							<th>Coût Fréquence normal</th>
							<th>Coût Prévisionel de la nouvelle fréquence</th>

						</tr>
					</thead>
						<tbody>
						
						<tr>
							<th>Coût Materiel Roulant   euro/jour </th>
							<td><c:out value="${prixtrain}"/></td>
							<td><c:out value="${coutfreqmateriel}"/></td>
							<td><c:out value="${coutMaterielRoulantPrev}"/></td>
							
						</tr>
						<tr>
							<th>Coût de la Tarification De Déplacement   euro/voy*km </th>
							<td><c:out value="${tarificationdedeplacement}"/></td>
							<td><c:out value="${coutTarifNormal}"/></td>
							<td><c:out value="${coutTarifPrev}"/></td>
					  
					  </tr>
					  <tr>
					  <th>Coût de la maintenance   euro/km/jour </th>
					<td><c:out value="${coutMaintenance}"/></td>
							<td><c:out value="${coutMaintNormal}"/></td>
							<td><c:out value="${coutMaintPrev}"/></td>
					  </tr>
					  <tr>
					  <th>Coût de la consommation énérgétique  euro/KWh/jour </th>
					   <td><c:out value="${coutEnerg}"/></td>
							<td><c:out value="${consEnergNormal}"/></td>
							<td><c:out value="${consEnergPrev}"/></td>
					  </tr>
					  <tr>
					  <th>Coût Incident par jour   euro/jour</th>
					   <td><c:out value="10510"/></td>
							<td><c:out value="${coutIncidentnormal}"/></td>
							<td><c:out value="${coutIncidentPrev}"/></td>
					  </tr>
					 <tr>
					  	  
					<th>Total</th>
					<td></td>
					<td><c:out value="${totalcout}"/></td>
					<td><c:out value="${totalcoutPrev}"/></td>
					  </tr>
					  
					  
					  <tr>
					  <th>Recette Tarifaire </th>
					   <td><c:out value="${prixplace}"/></td>
							<td><c:out value="${revenuvoynormal}"/></td>
							<td><c:out value="${revenuvoyPrev}"/></td>
					  </tr> 
					  
					   <tr>
					  <th>Resultat</th>
					   <td></td>
							<td><c:out value="${resultatNormal}"/></td>
							<td><c:out value="${resultatPrev}"/></td>
					  </tr> 
					  
					</tbody>
				</table>
				</div>
					
				
				</div>
				
				
				<table id='myTableStats'>
					<caption></caption>
					<thead>
						<tr>
							<th></th>
						<th>Coût Fréquence normal</th>
							<th>Coût Prévisionel de la nouvelle fréquence</th>

						</tr>
					</thead>
						<tbody>
						
						<tr>
							<th>Coût Materiel Roulant</th>
							<td><c:out value="${coutfreqmateriel}"/></td>
							<td><c:out value="${coutMaterielRoulantPrev}"/></td>
							
						</tr>
						<tr>
							<th>Coût de la Tarification De Déplacement</th>
						<td><c:out value="${coutTarifNormal}"/></td>
							<td><c:out value="${coutTarifPrev}"/></td>
					  
					  </tr>
					  <tr>
					  <th>Coût de la maintenance</th>
							<td><c:out value="${coutMaintNormal}"/></td>
							<td><c:out value="${coutMaintPrev}"/></td>
					  </tr>
					  <tr>
					  <th>Coût de la consommation énérgétique</th>
					 		<td><c:out value="${consEnergNormal}"/></td>
							<td><c:out value="${consEnergPrev}"/></td>
					  </tr>
					  
					    <tr>
					  <th>Recette Tarifaire  euro/voy/jour</th>
					   
							<td><c:out value="${revenuvoynormal}"/></td>
							<td><c:out value="${revenuvoyPrev}"/></td>
					  </tr> 
					
					  
					</tbody>
				</table>
				
															
											<!-- /Vertical Steps Right -->
									</div>
									<!-- /Vertical Steps Layout -->
								</div>
			
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>
