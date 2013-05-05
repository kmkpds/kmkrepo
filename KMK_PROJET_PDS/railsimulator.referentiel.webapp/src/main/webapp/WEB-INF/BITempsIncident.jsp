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
		<title>Accueil - Rail Simulator</title>
		<script type="text/javascript" src="js/main.js"></script>
	    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <script src="js/highcharts.js"></script>
        <script src="js/modules/exporting.js"></script>
		<script type="text/javascript">
		$(function () {
	        $('#container').highcharts({
	            chart: {
	                type: 'column'
	            },
	            title: {
	                text: 'Stacked column chart'
	            },
	            xAxis: {
	                categories: [
	                    'Mouvement social',
	                    'Paquet suspect',
	                    'Sensor inactif',
	                    'Porte bloqu�e',
	                    'Distance de s�curit�',
	                    'Echec �lectrique',
	                    'Appel en urgence',
	                    'Vitesse excessive',
	                    'Vitesse excessive',
	                    'Arr�t momentan�',
	                    'D�faut �quipement',
	                    'Risque de collision',
	                    'Collision',
	                    'Alerte � la bombe',
	                    'Station manqu�e',
	                    'Fuite de gaz',
	                    'Synchronization perdue',
	                    'Feu',
	                    'Temp�te de neige',
	                    'Inondation',
	                    'Accident voyageur',
	                    'Voyageur tomb�',
	                    'Obstacle sur le chemin',
	                    'Voyageur malade',
	                    'Temp�rature anormale'
	                ],
	                labels: {
	                    rotation: -45,
	                    align: 'right',
	                    style: {
	                        fontSize: '13px',
	                        fontFamily: 'Verdana, sans-serif'
	                    }
	                }
	            },
	            yAxis: {
	                min: 0,
	                title: {
	                    text: 'Total fruit consumption'
	                },
	                stackLabels: {
	                    enabled: true,
	                    style: {
	                        fontWeight: 'bold',
	                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                    }
	                }
	            },
	            legend: {
	                align: 'right',
	                x: -100,
	                verticalAlign: 'top',
	                y: 20,
	                floating: true,
	                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
	                borderColor: '#CCC',
	                borderWidth: 1,
	                shadow: false
	            },
	            tooltip: {
	                formatter: function() {
	                	var cpt=this.y;
	                    return '<b>'+ this.x +'</b><br/>'+
	                        this.series.name +': '+ cpt +'<br/>';
	                }
	            },
	            plotOptions: {
	                column: {
	                    stacking: 'normal',
	                    dataLabels: {
	                        enabled: false,
	                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
	                    }
	                }
	            },
	            series: [{
	                name: 'Temps Max',
	                data: [<c:forEach var="max" items="${listeTempsMaxIncident}" >
	                       <c:set var="max" value="${fn:split(max, ':')}"/>	
                           <c:out value="${max[0]}"/>,
			               </c:forEach>]
	            }, {
	                name: 'Temps Moyen',
	                data: [<c:forEach var="max" items="${listeTempsMaxIncident}" >
                    <c:set var="max" value="${fn:split(max, ':')}"/>	
                    <c:out value="${max[0]}"/>,
		               </c:forEach>]
	            }, {
	                name: 'Temps Min',
	                data: [<c:forEach var="max" items="${listeTempsMaxIncident}" >
                    <c:set var="max" value="${fn:split(max, ':')}"/>	
                    <c:out value="${max[0]}"/>,
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
									<a href="#">Demande d'�volution</a>
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
					
					<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
				
					</div>
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>