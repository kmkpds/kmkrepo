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
		<link rel="stylesheet" type="text/css" media="all" href="css/jsDatePick_ltr.min.css" />
		<title>Affecter tâche  - Rail Simulator</title>
		<script type="text/javascript" src="js/main.js"></script>
		<script type="text/javascript" src="js/jsDatePick.min.1.3.js"></script>
	
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
					</div> <!-- /Page Header --> <jsp:include page="menuRefEmploye.jsp" /> <!-- Body Content -->
					
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
								<a></a>Affectation d'une tâche
							
							</h2>
							<!-- /Page Title -->
							<!-- Vertical Steps Layout -->
							<div id="RnoVertStepsLayout">
								<!-- Steps -->
								<ol id="RnoVertSteps">
									<li class="RnoStepsCurrent">
										<p class="RSI1">Affectation d'une tâche</p>
									</li>
									<li>
										<p class="RSI2">Mise à jour</p>
									</li>
								</ol>
								<!-- /Steps -->
								<!-- Vertical Steps Right -->
								<div id="RnoVertStepsRight" class="RSI1">
									<!-- Bordered Workspace -->
									<div id="RnoWorkspace" style="min-height: 350px;">
										<!-- Vertical Steps Right Title -->
										<p class="RnoVertStepsTitle">Employe</p>
										<!-- /Vertical Steps Right Title -->
										<!-- Section -->
										<div class="RnoSectionFree">
										<form action="afficherEmploye?action=valider" method="post" class="RnoForm">
											<input type="hidden" name="selectedate" id="selectedate" />
												<h3 class="RnoSectionTitle">Informations de l'employé</h3>
												<div class="RnoSectionContent RnoSpacers">
													<div class="sc">
														<div class="RnofLeft">
															<p>Renseignements de l'employé</p>
															<table class="RnoFieldsValuesTable">
																<tr>
																	<th>
																		<label>
																			ID employé
																			<strong class="RnoMandatory">*</strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" readonly name="idemp" id="idemp" value="<c:out value="${employe.idemp}"/>" /> 
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			Nom de l'employé
																			<strong class="RnoMandatory">*</strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" readonly name="nom" id="nom" value="<c:out value="${employe.nom}"/>" /> 
																	</td>
																</tr>
																<tr>
																	<th>
																		<label>
																			Prenom de l'employé
																			<strong class="RnoMandatory">*</strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" readonly name="prenom" id="prenom" value="<c:out value="${employe.prenom}"/>" /> 
																	</td>
																</tr>
																
																<tr>
																	<th>
																		<label>
																			Fonction de l'employé
																			<strong class="RnoMandatory">*</strong> 
																			<span>:</span>
																		</label>
																	</th>
																	<td>
																		<input type="text" readonly name="fonction" id="fonction" value="<c:out value="${employe.fonction}"/>" /> 
																	</td>
																</tr>
																
																<tr>
																	<th>
																		<label>
																			Régime horaire de l'employé
																			<strong class="RnoMandatory">*</strong> 
																			<span>:</span>
																		</label>
																	</th>
																		<td>
																			<input type="text" readonly name="horairep_id" id="horairep_id" value="<c:out value="${employe.horairep.heured}"/>" />
																			
																		</td>
																</tr>
																	<td>
																		<input type="text" readonly name="horairep_id" id="horairep_id" value="<c:out value="${employe.horairep.heuref}"/>" />
																		
																	</td>										
																	
																	<tr>
																		<th>
																			<label>
																				Liste des taches
																				<span>:</span>
																			</label>
																		</th>
																	<td>
																		<SELECT  name="idtache" id="idtache"/>
																		<c:forEach items="${listeTache}" var="list">
																 
																		<OPTION VALUE= "${list.idtache}"> ${list.idtache} </OPTION>
																		</c:forEach>
																		</SELECT>	
																	
																	</td>
																	
																	</tr>
																	
																	
																	
																	<td><div id="div3_example" style="margin:10px 0 30px 0; border:dashed 1px red; width:205px; height:230px;"></div></td>
																	<td><span id="div3_example_result" style="height:20px; line-height:20px; margin:10px 0 0 0; border:dashed 1px #666;"></span></td>
 																	
 																 <tr>
 																 <td>	
																		<label>
																			Heure de début 
																			<i> (HH:MM:SS)</i>
																			<span>:</span>
																		</label>
																		
																			<input type="text" name="heuredtache" id="heuredtache" value="" />
																			
																</td>
																</tr>
																	
																	
																<tr>	
																<td>	
																		<label>
																			Durée 
																			<i> (HH:MM:SS)</i>
																			<span>:</span>
																		</label>
																		
																		<input type="text" name="duree" id="duree" value="" />
																			
																</td>
																</tr>
																	
																<tr>
																<th>
																	<label>
																		Commentaire 																		 
																		<span>:</span>
																	</label>
																</th>
																	<td>
																		<TEXTAREA rows="4" cols ="60"  name="commentaire" id="commentaire" > </TEXTAREA>																													
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
															<input type="submit" name="valider" id="valider" value="valider" />
														</li>
														<li>
															<input type="reset" name="annuler" id="annuler" value="annuler" />
														</li>
													</ul>
												</div>
											</form>
											<!-- /Page Level Buttons Steps -->
										</div>
										<br></br>		
										
													<table style="display:inline" border =1>
													<th>
														<a href="#">id tache  </a>
													</th>
													<th>
														<a href="#">libelle tache  </a>
													</th>
													
													
													
													<c:forEach items="${listeTache}" var="list">
													<tr>
														<td>${list.idtache}</td>
														<td>${list.libelletache}</td>
														
														
													</tr>
																									
													</c:forEach>
											
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
		
		
	<script type="text/javascript">
	window.onload = function(){
		
		
		g_globalObject = new JsDatePick({
			useMode:1,
			isStripped:true,
			target:"div3_example"

		});		
		
		g_globalObject.setOnSelectedDelegate(function(){
			var obj = g_globalObject.getSelectedDay();
		
			document.getElementById("div3_example_result").innerHTML = obj.year + "-" + obj.month + "-" +  obj.day;
			document.getElementById("selectedate").value = obj.year + "-" + obj.month + "-" +  obj.day;
				
		});
		
		
		
		
	};
</script>
		
	</body>
</html>
