<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
		<meta http-equiv="Content-Type"	content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<link rel="stylesheet" type="text/css" href="css/corp.css"  />
		<link rel="alternate stylesheet" type="text/css" href="css/print.css" media="screen" title="Version imprimable" id="stylesheet-print" />
		<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
		<title>Confirmation d'envoi</title>
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
					<!-- /Page Header --> 
			
					<!-- Body Content -->
					<div id="RnoBody">	
					<h2 id="RnoPageTitle">
								Confirmation d'envoie du message
							</h2>										
															
								<div class="RnoDataTable">
										<table>
											<tr class="RnoMainHeader">
											
												<th><a href="#">Annonce</a></th>
												<th><a href="#">Criticité</a></th>
												
												<th><a href="#">Train</a></th>
												<th><a href="#">Ligne</a></th>
												<th><a href="#">Station</a></th>
												<th><a href="#">Wagon</a></th>											
											</tr>
										
												<tr>												
													<td>${libelleAnnonce}</td>
													<td>${criticite}</td>
													
													<td>${train} ; ${train2} ; ${train3} ; ${train4}</td>
													<td>${ligne} ; ${ligne2} ; ${ligne3}; ${ligne4}</td>
													<td>${station} ; ${station2} ; ${station3} ; ${station4}</td>
													<td>${wagon} ; ${wagon2}; ${wagon3}; ${wagon4}</td>	
												
												<tr class="RnoMainHeader">			
												<th><a href="#">Commentaire</a></th>	
												</tr>								
												</tr>
												<tr><td>${commentaire}</td>	
												</tr>
									
										</table>
									</div>								
					</div>
				</td>
			</tr>
		</table>
		
		<!-- /Page Layout -->
	</body>
</html>