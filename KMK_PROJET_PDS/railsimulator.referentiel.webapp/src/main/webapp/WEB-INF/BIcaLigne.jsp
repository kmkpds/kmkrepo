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
		<title>Bi Ca Ligne</title>
		<script type="text/javascript" src="js/main.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="js/highstock.js"></script>
<script src="js/modules/exporting.js"></script>
	

				 						 
				
<script type="text/javascript">
$(function() {




	// create the chart when all data is loaded


		$('#container').highcharts('StockChart', {
		    chart: {
		    },

		    rangeSelector: {
		        selected: 4
		    },

		    yAxis: {

		    },
		    

		    tooltip: {
		    	 pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y} Euro</b><br/>',
		    	valueDecimals: 2
		    },
		    
		    series : [{
		    	name : 'CA',
		    	data: [				               
					    <c:forEach var="recette" items="${recetteListe}">	
						<c:set var="dateParts" value="${fn:split(recette.date, '-')}"/>	
						[Date.UTC(<c:out value="${dateParts[0]}"/>,<c:out value="${dateParts[1]-1}"/>,<c:out value="${dateParts[2]}"/>	,0,0,0,0), <c:out value="${recette.ca}"/>	],
						</c:forEach>
					  ]},
		    {
		    	name : 'Coût',
		    	data: [				               
						<c:forEach var="recette" items="${recetteListe}">	
						<c:set var="dateParts" value="${fn:split(recette.date, '-')}"/>	
						[Date.UTC(<c:out value="${dateParts[0]}"/>,<c:out value="${dateParts[1]-1}"/>,<c:out value="${dateParts[2]}"/>	,0,0,0,0), <c:out value="${recette.cout}"/>	],
						</c:forEach>
					  ]
		    	
		    }]

		});
	

});

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
					<!-- Navigation Top Level 1 --><jsp:include page="menuBi.jsp"/> <!-- /Navigation Top Level 1 -->
					<!-- Body Content -->
					<div id="RnoBody">
					
						<div id="container" style="height: 500px; min-width: 600px"></div>

					</div>
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>