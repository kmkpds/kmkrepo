<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<link rel="stylesheet" type="text/css" href="css/corp.css" />
		<link rel="alternate stylesheet" type="text/css" href="css/print.css"
			media="screen" title="Version imprimable" id="stylesheet-print" />
		<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
		<title>Log Tourniquets</title>
		<script type="text/javascript" src="js/main.js"></script>	
		<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$("#exam").dataTable();
			} );
		</script>
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
					<!-- Body Content -->
					<br>
					<div id="RnoBody ">
						<center>
								<table id="exam">
								<thead>	<tr>
							 			<th width="100 px">idTransaction</th>
										<th width="100 px">idClient</th>
										<th width="100 px">idTourniqet</th>
										<th width="150 px">date passage</th>
										<th width="150 px">statut transaction</th>
									</tr></thead>
				 				<tbody>
				 					<c:forEach items="${listClientPass}" var="clientP">
										<tr align="center" >
											<td width="auto" >${clientP[4]}</td>
											<td width="auto">${clientP[0]}</td>
											<td width="auto">${clientP[1]}</td>
											<td width="auto">${clientP[2]}</td>
											<td width="auto">${clientP[3]}</td>	
										</tr>
									</c:forEach></tbody>
									<tfoot></tfoot>
								</table>
								
							
						</center>                           				
					</div>
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>