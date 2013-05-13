<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
		<meta http-equiv="Content-Type"	content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<link rel="stylesheet" type="text/css" href="css/corp.css"  />
		<link rel="alternate stylesheet" type="text/css" href="css/print.css" media="screen" title="Version imprimable" id="stylesheet-print" />
		<title>Gestion Incidents</title>
		<script type="text/javascript" src="js/main.js"></script>
	    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <script src="js/highcharts.js"></script>
        <script src="js/modules/exporting.js"></script>
		<script type="text/javascript">
		$(function () {
	        $('#container').highcharts({
	            chart: {
	                type: 'bar'
	            },
	            title: {
	                text: 'Gestion des Incidents'
	            },
	            subtitle: {
	                text: ' '
	            },
	            xAxis: {
	            	 categories: [
	      	                    'Mouvement social',
	      	                    'Paquet suspect',
	      	                    'Sensor inactif',
	      	                    'Porte bloquée',
	      	                    'Distance de sécurité',
	      	                    'Echec électrique',
	      	                    'Appel en urgence',
	      	                    'Vitesse excessive',
	      	                    'Vitesse excessive',
	      	                    'Arrêt momentané',
	      	                    'Défaut équipement',
	      	                    'Risque de collision',
	      	                    'Collision',
	      	                    'Alerte à la bombe',
	      	                    'Station manquée',
	      	                    'Fuite de gaz',
	      	                    'Synchronization perdue',
	      	                    'Feu',
	      	                    'Tempête de neige',
	      	                    'Inondation',
	      	                    'Accident voyageur',
	      	                    'Voyageur tombé',
	      	                    'Obstacle sur le chemin',
	      	                    'Voyageur malade',
	      	                    'Température anormale'
	      	                ],
	                title: {
	                    text: null
	                }
	            },
	            yAxis: {
	                min: 0,
	                title: {
	                    text: 'Temps (Seconde)',
	                    align: 'high'
	                },
	                labels: {
	                    overflow: 'justify'
	                }
	            },
	            tooltip: {
	                valueSuffix: ' Minute '
	            },
	            plotOptions: {
	                bar: {
	                    dataLabels: {
	                        enabled: true
	                    }
	                }
	            },
	            legend: {
	                layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'top',
	                x: -100,
	                y: 100,
	                floating: true,
	                borderWidth: 1,
	                backgroundColor: '#FFFFFF',
	                shadow: true
	            },
	            credits: {
	                enabled: false
	            },
	            series: [{
	                name: 'Temps Max',
	                data: [<c:forEach var="max" items="${listeTempsMaxIncident}" >
	                <c:out value="${max}/60"/>,
			           </c:forEach>]
	            }, {
	                name: 'Temps Moyen',
	                data: [<c:forEach var="moyen" items="${listeTempsMoyenIncident}" >
	                <c:out value="${moyen}/60"/>,
			           </c:forEach>]
	            },  {
	                name: 'Temps Min',
	                data: [<c:forEach var="min" items="${listeTempsMinIncident}" >
	                <c:out value="${min}/60"/>,
			           </c:forEach>]
	            }]
	        });
	    });
	    
		</script>
		
	</head>
	<body>
		<script src="js/highcharts.js"></script>
        <script src="js/modules/exporting.js"></script>
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
					<!-- Navigation Top Level 1 --><jsp:include page="menuBi.jsp"/> <!-- /Navigation Top Level 1 -->
					<!-- Body Content -->
					<div id="RnoBody">
					
					<div id="container" style="min-width: 400px; height: 1100px; margin: 0 auto"></div>
				
					</div>
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>