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
		<title>Fréquentation Type Usager</title>
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
					</div> <!-- /Page Header --> <jsp:include page="menuBi.jsp" /> <!-- Body Content -->
					<div id="RnoBody">
						<!-- Middle Content -->
						<div id="RnoMainContent" class="sc">
							<!-- Page Title -->
							<h2 id="RnoPageTitle">
								</a>Sélection Paramètre(s)
							</h2>
							<!-- /Page Title -->
							<!-- Vertical Steps Layout -->
							<div id="RnoVertStepsLayout">
								<!-- Steps -->
								<ol id="RnoVertSteps">
									<li class="RnoStepsCurrent">
										<p class="RSI1">Sélection Paramètre(s)</p>
									</li>
									<li>
										<p class="RSI2">Visualisation Répartition Usager</p>
									</li>
								</ol>
								<!-- /Steps -->
								<!-- Vertical Steps Right -->
								<div id="RnoVertStepsRight" class="RSI1">
									<!-- Bordered Workspace -->
									<div id="RnoWorkspace" style="min-height: 350px;">
										<!-- Vertical Steps Right Title -->
										<p class="RnoVertStepsTitle">Fréquentation Type Usager</p>
										<!-- /Vertical Steps Right Title -->
										<!-- Section -->
										<div class="RnoSectionFree">
											<form action="bi" method="post" class="RnoForm">
												<h3 class="RnoSectionTitle">Sélection Paramètre(s)</h3>
												<div class="RnoSectionContent RnoSpacers">
													<div class="sc">
														<div class="RnofLeft">
															<p>Sélection</p>
															<table class="RnoFieldsValuesTable">

																
																<tr>
																	<th>
																		<label>
																			Ligne
																			<strong class="RnoMandatory">*</strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<select name="idLigne" id="idLigne" > 
																			<c:forEach var="list" items="${listeLigne}" >
																				<option value="${list.idBiLigne}">${list.nomBiLigne}</option> 
																			</c:forEach> 
																		</select>
																	</td>
																</tr>
																
																<tr>
																	<th>
																		<label>
																			Date
																			<strong class="RnoMandatory">*</strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<select name="date" id="date" > 
																			<c:forEach var="list" items="${listeAnne}" >
																				<option value="${list}">${list}</option> 
																			</c:forEach> 
																		</select>
																	</td>
																	
																</tr>
																<tr>
																	<th>
																		<label>
																			Mois
																			<strong class="RnoMandatory">*</strong> 
																			<span>:</span>
																		</label>
																	</th>
																<td>
																		<select name="mois" id="mois" > 																			
																				<option value="1">Janvier</option>
																				<option value="2">Fevrier</option> 
																				<option value="3">Mars</option> 
																				<option value="4">Avril</option> 
																				<option value="5">Mai</option> 
																				<option value="6">Juin</option> 
																				<option value="7">Juillet</option> 
																				<option value="8">Aout</option> 
																				<option value="9">Septembre</option> 
																				<option value="10">Octobre</option> 
																				<option value="11">Novembre</option>  
																				<option value="12">Decembre</option> 
																			 
																		</select>
																	</td>
																	
																</tr>
															</table>
														</div>
														<!-- /Section -->														
													</div>
																							
													
													<!-- /Bordered Workspace -->
													<!-- Page Level Buttons Steps -->
												</div>
												<div id="RnoPageLevelButtonsSteps"
													class="RnoPageLevelButtons sc">
													<ul class="RnoBtn RnoSep">

														<li><input type="submit" name="action" id="action"
															value="Valider Ligne Usager 2" /></li>
													</ul>
												</div>
											</form>
											<!-- /Page Level Buttons Steps -->
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
