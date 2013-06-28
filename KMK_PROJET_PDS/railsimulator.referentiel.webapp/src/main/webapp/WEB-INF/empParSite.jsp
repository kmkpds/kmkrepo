<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<link rel="stylesheet" type="text/css" href="css/corp.css" />
		<link rel="alternate stylesheet" type="text/css" href="css/print.css"media="screen" title="Version imprimable" id="stylesheet-print" />
		<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
		<title>Référentiel Employe - Rail Simulator</title>
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
					</div> <!-- /Global Links Top --> <!-- Page Header -->
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
					</div> 
					<!-- /Page Header --> <jsp:include page="menuRefEmploye.jsp" /> <!-- Body Content -->
					<div id="RnoBody">
						<!-- Middle Content -->
						<div id="RnoMainContent" class="sc">
							<!-- Page Title -->
							<h2 id="RnoPageTitle">
								<a></a> Gestion de site 
							</h2>
							<!-- /Page Title -->
							<!-- Bordered Workspace -->
							<div id="RnoWorkspace">
								<!-- Section -->
								<div class="RnoSection">
								<form action="empParSite" method="get" class="RnoForm">
								
									<h3 class="RnoSectionTitle">
										<span></span> Site standard
																				
									</h3>					
									<div class="RnoSectionContent">
										<div class="RnoDataTable">							
							
											<table>																
												<tr class="RnoMainHeader">								
													
													<th>
														<a href="#">Type poste</a>
													</th>
													
													<th>
														<a href="#">Nombre min de personnel par poste</a>
													</th>
													
												<c:forEach items="${listePosteParSite}" var="list">		
													<tr>
														<td>${list.typeposte}</td>													
														<td>${list.nbminemp}</td>	
													</tr>	
																								
												</c:forEach>
											
											</table>
					
										</div>
										
										<div class="RnoDataTable">	
								<br></br>
									<table   >
									<tr>
													<th style="display:none;">
														<a href="#">Id site </a>
													</th>
													<th>
														<a href="#">Libelle du site </a>
													</th>
													<th>
														<a href="#">Adresse </a>
													</th>
													<th>
														<a href="#">Secteur </a>
													</th>
													<th>
														<a href="#">Nombre de personnel </a>
													</th>
													<th>
														<a href="#">Detail </a>
													</th>
													
													<c:forEach items="${listeSite}" var="list">
										<tr>
														<td style="display:none;">${list.idsite}</td>
														<td>${list.libellesite}</td>
														<td>${list.adresse}</td>
														<td>${list.secteur}</td>
														<td>${list.nbemp}</td>
														
														<td>
														       
														<center> 
																<a href="/kmk_ref_webapp/site?action=tache&id=${list.idsite}">
																	<img alt="update" src="images/updateicon.gif" width="20" height="20">
																</a>
														
														</center>
														</td>
										</tr>
																									
													</c:forEach>
													
										</tr>
										
											
									</table>
									<br></br>		
									
										
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


