<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
			<meta http-equiv="Content-Type"	content="text/html; charset=iso-8859-1" />
			<meta http-equiv="Content-Script-Type" content="text/javascript" />
			<meta http-equiv="Content-Style-Type" content="text/css" />
			<link rel="stylesheet" type="text/css" href="css/corp.css"  />
			<link rel="alternate stylesheet" type="text/css" href="css/print.css" media="screen" title="Version imprimable" id="stylesheet-print" />
			<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
			<title>Creation horaire - Rail Simulator</title>
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
							<img src="images/logos/kmk-rail.jpg" id="RnoLogo" alt="Kamikaze" width="63" height="63"/>
						</a>
						<h1 id="RnoApplicationName">
							<img src="images/header/kmk-header.jpg" alt="KAMIKAZE PROJECT - Rail Simulator"/>
						</h1>
						<span>
							<img src="images/branding-coin.gif" alt="" width="31" height="31"/>
						</span>
					</div>
					<!-- /Page Header --> 
					<jsp:include page="menuCreationHoraire.jsp"/> 
					<!-- Body Content -->
					<div id="RnoBody">
					
					
					</div>
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>