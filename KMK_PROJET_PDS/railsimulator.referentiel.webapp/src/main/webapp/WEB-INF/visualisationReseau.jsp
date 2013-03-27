

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
		<title>Visualisation Réseau - Rail Simulator</title>
		<script type="text/javascript" src="js/main.js"></script>
		
		<style type="text/css">
		
        		#map_canvas { height: 100% }
        </style>
        
    	<script type="text/javascript"
      		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCoWz18DcPRiE7yGoTpQ7ix3CNhUiWXHes&sensor=true">
    	</script>
    	
    	<script type="text/javascript">
    	
    	//initialisation de la map		
    	  function initialize() {
      	//coordonnées de la ville Orléans		
          var myLatlng = new google.maps.LatLng(47.904, 1.907);

		  var mapOptions = {
		    zoom: 13,
		    center: myLatlng,
		    mapTypeId: google.maps.MapTypeId.ROADMAP
		  }
		  var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
		 
		  //Positions des stations dans la map
		  /*Récupération de la liste des stations*/
		  <c:forEach items="${listeStation}" var="station">	 
		  	var id = '<c:out value="${station.idStation}"/>';
		  	var commentaire = '<c:out value="${station.commentaireStation}"/>';
		  	var latitude = '<c:out value="${station.latitude}"/>';
		  	var longitude = '<c:out value="${station.longitude}"/>'; 
		  	var nom = '<c:out value="${station.nomStation}"/>';
		  
		  	var gare = new google.maps.LatLng(latitude,longitude);
		  	
		  	/* Personnalisation d'icone */
		 	var icone = new google.maps.MarkerImage("images/icones/icone_bleu.png");
		 					/*new google.maps.Size(14, 14),//Dimensions de l'image
		 					new google.maps.Point(0,0),//Origine de l'image 0,0
		 					new google.maps.Point(8,13));*/ //point d'accrochage sur la map, du picto (varie en fonction de ces dimensions)
		 	
		 	var marqueur = new google.maps.Marker({
		 	  position: gare,
		      map: map,
		      icon: icone,//Attribution de l'icône personnalisée
		      title: nom
		 	});
		 	marqueur.setMap(map);
		 	
		  </c:forEach>
      		
		//Création: Tracé d'une ligne entre les stations
		/*Récupération des données des stations*/
		  <c:forEach items="${listeStation}" var="station">	
			var latitudeStationDepart = '<c:out value="${station.latitude}"/>';
		  	var longitudeStationDepart = '<c:out value="${station.longitude}"/>'; 
		  	
		 	 <c:forEach items="${station.stationAller}" var="stationAller">
		    	var latitudeStationArrive = '<c:out value="${stationAller.latitude}"/>';
		  		var longitudeStationArrive = '<c:out value="${stationAller.longitude}"/>';
		  	
			 	//on crée un tableau de coordonnées pour la création d'une ligne
		 	 	var tabCoordonnees = [
		 	   		// new google.maps.LatLng(latitude,longitude)
		 			new google.maps.LatLng(latitudeStationDepart, longitudeStationDepart),
					new google.maps.LatLng(latitudeStationArrive, longitudeStationArrive)
		     	];
		     	var ligne = new google.maps.Polyline({
			        path: tabCoordonnees,
			        strokeColor: "#4169E1",//ligne de couleur rouge
			        strokeOpacity: 1.0,
			        strokeWeight: 5
		     	});
		     	
		     	ligne.setMap(map);
		      </c:forEach>
		      
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
						</div>
						</center>
					
					<div class="RnoSection" >
						<h3 class="RnoSectionTitle">
							<span></span>Liste des stations
						</h3>
								
						<div class="RnoSectionContent">
							<div class="RnoDataTable">
								<table>
									<tr class="RnoMainHeader">
							 			<th class="first RnoSmallCell"><a href="#">Id</a></th>
										<th><a href="#">Nom</a></th>
										<th><a href="#">Commentaire</a></th>
										<th><a href="#">Latitude</a></th>
										<th><a href="#">Longitude</a></th>
									</tr>
				
				 					<c:forEach items="${listeStation}" var="station">
										<tr><td>${station.idStation}</td>
											<td>${station.nomStation}</td>
											<td>${station.commentaireStation}</td>
											<td>${station.latitude}</td>
											<td>${station.longitude}</td>
										</tr>
									</c:forEach>
									
								</table>
							</div>
						</div>
					</div>
                           				
					</div>
					
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>