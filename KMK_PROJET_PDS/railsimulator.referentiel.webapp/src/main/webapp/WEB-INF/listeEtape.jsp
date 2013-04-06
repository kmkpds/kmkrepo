<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" type="text/css" href="css/corp.css" />
<link rel="alternate stylesheet" type="text/css" href="css/print.css"
	media="screen" title="Version imprimable" id="stylesheet-print" />
<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
<title>Etape - Rail Simulator</title>
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
							<li><a href="#">Manuel</a></li>
							<li><a href="#">Demande d'évolution</a></li>
						</ul>
					</div>
				</div> <!-- /Global Links Top --> <!-- Page Header -->
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
				</div> <!-- /Page Header --> <jsp:include page="menuRef.jsp" /> <!-- Body Content -->
				<div id="RnoBody">
							<c:if test="${confirmation}">
							<table id="RnoMsg" class="RnoMsgError">
								<tr>
									<td>
										<h2>Le message envoyé est le suivant :</h2>
										<p>annonce Voyageur : </p>
									</td>
								</tr>
							</table>
						</c:if>
					<!-- Middle Content -->
					<div id="RnoMainContent" class="sc">
						<!-- Page Title -->
						<h2 id="RnoPageTitle">
							<a></a>Procédure A
						</h2>
						<!-- /Page Title -->
						<!-- Bordered Workspace -->
						<div id="RnoWorkspace">
							<!-- Section -->
							<div class="RnoSection">
								<h3 class="RnoSectionTitle">
									<span></span>Voici la liste des étapes  
									<a
										href="/TransmissionTerrain/ligne?action=formAjouter">Ajouter
										une etape</a>
								</h3>
								<div class="RnoSectionContent">
									<div class="RnoDataTable">
										<table>
											<tr class="RnoMainHeader">
												<th class="first RnoSmallCell"><a href="#">Identifiant</a>
												</th>
												<th><a href="#">Nom de l'étape</a></th>
												<th><a href="#">description</a></th>
												<th><a href="#">Lancement Etape</a></th>											
											</tr>
											<c:forEach items="${listeEtape}" var = "list">
												<tr>
													<td>${list.idEtape}</td>
													<td>${list.nomEtape}</td>
													<td>${list.descriptionEtape}</td>
													<td> 
													<a	href="/TransmissionTerrain/etape?action=excecute&id=${list.idEtape}">
															<img alt="update" src="images/fournisseur.jpg" width="20"
															height="20" />
														</a></td>							
												</tr>
											</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!-- /Page Level Buttons Steps -->
					</div>
					<!-- /Vertical Steps Right -->
				</div> <!-- /Vertical Steps Layout -->
			</td>
		</tr>
	</table>
	<!-- /Page Layout -->
</body>
</html>


