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
		<title>Cr�er Tache - Rail Simulator</title>
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
									<a href="#">Demande d'�volution</a>
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
					</div> <!-- /Page Header --> <jsp:include page="menuRefEmploye.jsp" /> <!-- Body Content -->
					<div id="RnoBody">
						<c:if test="${erreurG�n�rale}">
							<table id="RnoMsg" class="RnoMsgError">
								<tr>
									<td>
										<h2>Des informations obligatoires sont manquantes.</h2>
										<p>Veuillez les compl�ter</p>
									</td>
								</tr>
							</table>
						</c:if>
						<!-- Middle Content -->
						<div id="RnoMainContent" class="sc">
							<!-- Page Title -->
							<h2 id="RnoPageTitle">
								<a></a>Cr�ation d'une tache
							</h2>
							<!-- /Page Title -->
							<!-- Vertical Steps Layout -->
							<div id="RnoVertStepsLayout">
								<!-- Steps -->
								<ol id="RnoVertSteps">
									<li class="RnoStepsCurrent">
										<p class="RSI1">Cr�er une tache </p>
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
									<!-- Vertical Steps Right Title -->
									<p class="RnoVertStepsTitle">Tache</p>
									<!-- /Vertical Steps Right Title -->
									<!-- Section -->
									<div class="RnoSectionFree">
										<form action="tache?action=valider" method="post" class="RnoForm">
											<h3 class="RnoSectionTitle">Informations de la tache </h3>
											<div class="RnoSectionContent RnoSpacers">
												<div class="sc">
													<div class="RnofLeft">
														<p>Renseignements de la tache</p>
														<table class="RnoFieldsValuesTable">
															
															
															<tr>
																<th>
																	<label>
																		Libelle tache 
																		<strong class="RnoMandatory">*</strong> 
																		<span>:</span>
																	</label>
																</th>
																<td>
																	<input type="text" name="libelletache" id="libelletache" value="" /> 
																	<c:if test="${erreur[2]==true}">
																		<div class="RnoFrmErrorMsg">
																			<strong>
																					Saisissez le libelle de la tache 
																			</strong>
																		</div>
																	</c:if>
																</td>
															</tr>
															<tr>
																
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
