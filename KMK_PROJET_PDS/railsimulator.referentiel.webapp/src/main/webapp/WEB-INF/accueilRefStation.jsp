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
<title>Référentiel Station - Rail Simulator</title>
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
					<a href="#"><img src="images/logos/kmk-rail.jpg"
						id="RnoLogo" alt="Kamikaze" width="63" height="63" /></a>
					<h1 id="RnoApplicationName">
						<img src="images/header/kmk-header.jpg"
							alt="KAMIKAZE PROJECT - Rail Simulator" />
					</h1>
					<span><img src="images/branding-coin.gif" alt="" width="31"
						height="31" /></span>
				</div> <!-- /Page Header --> <jsp:include page="menuRef.jsp" /> <!-- Body Content -->
				<div id="RnoBody">


					<!-- Middle Content -->
					<div id="RnoMainContent" class="sc">
						<!-- Page Title -->
						<h2 id="RnoPageTitle">
							</a>Référentiel Station
						</h2>
						
						<!-- /Page Title -->

						<!-- Bordered Workspace -->
						<div id="RnoWorkspace">




							<!-- Section -->
							<c:forEach items="${listeLigne}" var="list">
							<div class="RnoSection">
								<h3 class="RnoSectionTitle">
									<span></span>${list.nomLigne} <a href="/kmk_ref_webapp/station?action=formAjouter">Ajouter une station</a>
								</h3>
								
								<div class="RnoSectionContent">

									<div class="RnoDataTable">
										<table>
											<tr class="RnoMainHeader">
												<th class="first RnoSmallCell"><a href="#">Identifiant</a></th>
												<th><a href="#">Nom de la station</a></th>
												<th><a href="#">Commentaire</a></th>
												<th><a href="#">Modification de la station</a></th>
												<th><a href="#">Suppression de la station</a></th>

											</tr>
                                           
											<c:forEach items="${list.cantonlist}" var="canton">
												<tr>
													<td>${canton.station.idStation}</td>
													<td>${canton.station.nomStation}</td>
													<td>${canton.station.commentaireStation}</td>
													<td><center> <a
															href="/kmk_ref_webapp/station?action=modif&id=${canton.station.idStation}"><img
															alt="update" src="images/updateicon.gif" width="20"
															height="20"></a></center></td>
													<td><center> <a
															onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette station?')"
															href="/kmk_ref_webapp/station?action=supp&id=${canton.station.idStation}"><img
															alt="delete" src="images/croix.png"></a></center></td>

												</tr>
											</c:forEach>


										</table>
									</div>
								</div>
							</div>
                           </c:forEach>

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


