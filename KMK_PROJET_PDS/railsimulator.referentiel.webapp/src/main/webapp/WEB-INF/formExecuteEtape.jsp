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
		<title>Excécution d'étape</title>
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
					</div> 
					<!-- /Global Links Top -->
					<!-- Page Header --> 
					<!-- Page Header -->
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
					</div> <!-- /Page Header --> <jsp:include page="menuRef.jsp" /> <!-- Body Content -->
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
								Validation d'une Etape : Envoie de Message
							</h2>
							<!-- /Page Title -->
							<!-- Vertical Steps Layout -->
							<div id="RnoVertStepsLayout">
								<!-- Steps -->
								<ol id="RnoVertSteps">
									<li class="RnoStepsCurrent">
										<p class="RSI1">Lancer une étape</p>
									</li>
									<li>
										<p class="RSI2">Validation</p>
									</li>
								</ol>
								<!-- /Steps -->
								<!-- Vertical Steps Right -->
							<div id="RnoVertStepsRight" class="RSI1">
								<!-- Bordered Workspace -->
								<div id="RnoWorkspace" style="min-height: 350px;">
									
									<!-- /Vertical Steps Right Title -->
									<!-- Section -->
									<div class="RnoSectionFree">
									
										<form action="etape" method="post" class="RnoForm">
											<h3 class="RnoSectionTitle">Informations d'étape</h3>
											<div class="RnoSectionContent RnoSpacers">
												<div class="sc">
													<div class="RnofLeft">
														
														<table class="RnoFieldsValuesTable">
														<tr>
																<th>
																	<label>
																	
																		Type d'incident 
																		
																		<span>:</span>
																	</label>
																</th>
																<td>
																	${typeEvenement} 
															
																</td>
															</tr>
															<tr>
																<th>
																	<label>
																	
																		Annonce Voyageur
																		
																		<span>:</span>
																	</label>
																</th>
																<td>
																
									<TEXTAREA rows="4" cols ="60"  name="libelleAnnonce" /> ${libelleAnnonce} </TEXTAREA>															
																</td>
															</tr>
															
															<tr>
																<th>
																	<label>
																		Criticité 
																		
																		<span>:</span>
																	</label>
																</th>
																<td>
																<SELECT name="criticite" id="criticite" />
																		<OPTION VALUE="1">1</OPTION>
																		<OPTION VALUE="2">2</OPTION>
																		<OPTION VALUE="3">3</OPTION>
																		<OPTION VALUE="4">4</OPTION>
																		<OPTION VALUE="5">5</OPTION>
																</SELECT>				
																</td>
															</tr>
															<tr>
																<th>
																	<label>
																		Train 																	
																		<span>:</span>
																	</label>
																</th>
																<td>
														
									<input type="text" name="train" id="train" value="XFRZ33" />
									<input type="text" name="train2" id="train2" value="" />
									<input type="text" name="train3" id="train3" value="" />
									<input type="text" name="train4" id="train4" value="" />
																
																</td>
															</tr>
															<tr>
																<th>
																	<label>
																		ligne 
																	
																		<span>:</span>
																	</label>
																</th>
																<td>
																	<input type="text" name="ligne" id="ligne" value="Ligne 8" /> 
																	<input type="text" name="ligne2" id="ligne2" value="" /> 
																	<input type="text" name="ligne3" id="ligne3" value="" /> 
																	<input type="text" name="ligne4" id="ligne4" value="" /> 
																
																</td>
															</tr>
															<tr>
																<th>
																	<label>
																		Station 
																		
																		<span>:</span>
																	</label>
																</th>
																<td>
																	<input type="text" name="station" id="station" value="Créteil" /> 
																	<input type="text" name="station2" id="station2" value="" /> 
																	<input type="text" name="station3" id="station3" value="" /> 
																	<input type="text" name="station4" id="station4" value="" /> 
																
																</td>
															</tr>
															<tr>
																<th>
																	<label>
																		Wagon
																		
																		<span>:</span>
																	</label>
																</th>
																<td>
																	<input type="text" name="wagon" id="wagon" value="XMD5P9" /> 
																	<input type="text" name="wagon2" id="wagon2" value="" /> 
																	<input type="text" name="wagon3" id="wagon3" value="" /> 
																	<input type="text" name="wagon4" id="wagon4" value="" /> 
																
																</td>
															</tr>
															<tr>
																<th>
																	<label>
																		Descriprtion 																		 
																		<span>:</span>
																	</label>
																</th>
																<td>
													<TEXTAREA rows="4" cols ="60"  name="commentaire" > </TEXTAREA>																													
																</td>
															</tr>
														</table>
													</div>
												</div>
											</div>
											<!-- /Bordered Workspace -->
											<!-- Page Level Buttons Steps -->
											<div id="RnoPageLevelButtonsSteps"
												class="RnoPageLevelButtons sc">
												<ul class="RnoBtn RnoSep">
													<li>
														<input type="submit" name="action" id="action" value="Valider" />
													</li>
													<li>
														<input type="submit" name="action" id="action" value="Annuler" />
													</li>
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
