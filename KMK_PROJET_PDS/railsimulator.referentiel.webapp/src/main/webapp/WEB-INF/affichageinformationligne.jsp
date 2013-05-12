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
									<li class="RnoStepsCurrent">
										<p class="RSI1">Information d'une ligne</p>
									</li>
									<li>
										<p class="RSI2">Modification de la frequence</p>
									</li>
								</ol>
								<!-- /Steps -->
								<!-- Vertical Steps Right -->
								<div id="RnoVertStepsRight" class="RSI1">
									<!-- Bordered Workspace -->
									<div id="RnoWorkspace" style="min-height: 350px;">
										<!-- Vertical Steps Right Title -->
										<!--<p class="RnoVertStepsTitle">Ligne</p> -->
										<!-- /Vertical Steps Right Title -->
										<!-- Section -->
										<div class="RnoSectionFree">
										
										
										<form action="optimisation" method="get" class="RnoForm" name="formAfficher" >
										<h3 class="RnoSectionTitle">Informations de la Ligne</h3>
												<div class="RnoSectionContent RnoSpacers">
													<div class="sc">
														<div class="RnofLeft">
							                          
															<table class="RnoFieldsValuesTable">
																
																<tr>
                                                                  
																	<th>
																	
																		<label>
																			Nom de la Ligne
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																		 
																	</th>
																	<td>
																	
																	<select id="idLigne" name="idLigne">
														            <c:forEach items="${listeLigne}" var="list">
															        <option value="${list.idLigne}">${list.nomLigne}</option>
														            </c:forEach>
												                    </select>
																	
							
																	</td>
																	<th>
																		<label>
																			Frequence
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																		
																	</th>
																	<th>
																	<input type="radio" name="frequence" value="frequenceJO"/>JO
																	</th>
																	<th>
																	<input type="radio" name="frequence" value="frequenceSamedi"/>Samedi
																	</th>
																	<th>
																	<input type="radio" name="frequence" value="frequenceDimancheetJF"/>Dimanche et JF
																	</th>																	
				                                                    <th>
				                                                    <input type="submit" name="action" id="action" value="Afficher" />
				                                                    </th>
				                                                  
																</tr>
																</table>
																</div>
																</div>
																</div>
																
										</form>
									
											<form action="optimisation" method="get" class="RnoForm" name="form1">
												<h3 class="RnoSectionTitle">Informations de la Ligne</h3>
												<div class="RnoSectionContent RnoSpacers">
													<div class="sc">
														<div class="RnofLeft">
							                          
															<table class="RnoFieldsValuesTable">
																
								
																<tr>
																	<th>
																		<label>
																			Frequence De La Ligne
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																	
																		<input type="text" name="frequenceLigne" id="frequenceLigne" value="<c:out value="${freq}"/>"/>
																	
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			ID Ligne
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" readonly name="idLigne" id="idLigne"  value="<c:out value="${ligne.idLigne}"/>"/> 
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			 longueur de la Ligne
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" name="longueurLigne" id="longueurLigne" value="<c:out value="${ligne.longueur}"/>" /> 
																		
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			 Nombre de station de la ligne 
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" name="nombreLigne" id="nombreLigne" value="<c:out value="${ligne.nombredestation}"/>" /> 
																		
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			Durée moyenne d'un trajet
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" name="duréeTrajet" id="duréeTrajet" value="<c:out value="${ligne.dureemoyennetrajet}"/>" /> 
																		
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			 Nombre de train circulant sur la ligne
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" name="nombreTrain" id="nombreTrain" value="<c:out value="${ligne.nombredetrain}"/>"/> 
																		
																	</td>
																</tr>
								                                <tr>
																	<th>
																		<label>
																			 Nombre de passagers:
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" name="nombrePassagers" id="nombrePassagers" value="<c:out value="${ligne.nombrepassagers}"/>"/> 
																		
																	</td>
																</tr>
					
															</table>
															
															<h3 class="RnoSectionTitle">Informations du train :</h3>
															<table  class="RnoFieldsValuesTable">
															
														     
																<tr>
																	<th>
																		<label>
																			Nombre de wagons
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" name="nombreWagon" id="nombreWagon" value="<c:out value="${train.nombredewagon}"/>"/> 
																		
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			Vitesse moyenne du train :
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" readonly name="vitesseTrain" id="vitesseTrain" value="<c:out value="${train.vitesse}"/>" /> 
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			 Nombre de places assises :
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" name="nombrePlacesa" id="nombrePlacesa" value="<c:out value="${train.nombredeplacea}"/>"/> 
																		
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			 Nombre de places debouts
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" name="nombrePlacesd" id="nombrePlacesd" value="<c:out value="${train.nombredeplaced}"/>"/> 
																		
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			Total des nombres de places
																			<strong class="RnoMandatory"></strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" name="nombrePlaces" id="nombrePlaces" value="<c:out value="${total}"/>"/> 
																		
																	</td>
																</tr>
																
													
															
															
															</table>
														</div>
													</div>
												</div>
												<!-- /Bordered Workspace -->
												<!-- Page Level Buttons Steps -->
										
												<div id="RnoPageLevelButtonsSteps" class="RnoPageLevelButtons sc">
													<ul class="RnoBtn RnoSep">
														<li>
															<input type="submit" name="action" id="action" value="ModifierLaFrequence" />
														</li>
														<li>
															<input type="submit" name="action" id="action" value="Annuler" />
														</li>
													</ul>
												</div>
												
											
											
											
										    </form>
		                                </div>
															
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
