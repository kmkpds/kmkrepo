<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
		<meta http-equiv="Content-Type"	content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<link rel="stylesheet" type="text/css" href="css/corp.css"  />
		<link rel="alternate stylesheet" type="text/css" href="css/print.css" media="screen" title="Version imprimable" id="stylesheet-print" />
		<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
		<title>Visualisation Reseau - Rail Simulator</title>
		<script type="text/javascript" src="js/main.js"></script>
		
		<style type="text/css">


			
        		#map_canvas { height: 100% }
        </style>
        
    	<script type="text/javascript"
      		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZbdvS0wWf1Vxbr1xZd0NBSPs0VyEBVms&sensor=true">
    	</script>
    	
    	<script type="text/javascript">
      		function initialize() {
      			
      			
      		
      		  var myLatlng = new google.maps.LatLng(47.904, 1.907);


		 
		  var mapOptions = {
		    zoom: 13,
		    center: myLatlng,
		    mapTypeId: google.maps.MapTypeId.ROADMAP
		  }
		  var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
		  
		 
		  <c:forEach items="${listeStation}" var="station">	 
	

		  var latitude = '<c:out value="${station.latitude}"/>';
		  var longitude = '<c:out value="${station.longitude}"/>'; 
		  var nom = '<c:out value="${station.nomStation}"/>';
		  
		  var gare = new google.maps.LatLng(latitude,longitude);
		  var marker = new google.maps.Marker({
		      position: gare,
		      map: map,
		      title: nom
		  });
		    
			</c:forEach>
      			
        	
        	
        	
      		}
    	</script>
		
	</head>
	<body onload="initialize()">
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
					<jsp:include page="menuCreationReseau.jsp"/> 
					<!-- Body Content -->
					<div id="RnoBody ">
					
						<center><div id="map_canvas" style="width:800px; height:600px">
						</div></center>
				
					</div>
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>