

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>Visualisation Reseau - Rail Simulator</title>
<script type="text/javascript" src="js/main.js"></script>




<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=drawing"></script>

<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>

<script type="text/javascript">

    var map = null;
    var marker = null;
	var decimals = 8;
	var arrayOfMarker = new Array();
	var arrayOfIdsZone = new Array();
	
	window.onload = function() {	
        //initialiser la google map
		var myLatlng = new google.maps.LatLng(47.904, 1.907);
		var geocoder = new google.maps.Geocoder();
		 map = new google.maps.Map(document.getElementById("map"), {
			zoom : 13,
			center : myLatlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		});
        //initialiser un marker
		 marker = new google.maps.Marker({
			draggable : true,
			map : map
		});
		 
		
       //initialiser et rajouter les outils de marquage et de dessin de la map
		var drawingManager = new google.maps.drawing.DrawingManager({
			drawingMode : google.maps.drawing.OverlayType.MARKER,
			drawingControl : true,
			drawingControlOptions : {
				position : google.maps.ControlPosition.TOP_CENTER,
				drawingModes : [ google.maps.drawing.OverlayType.MARKER,
						google.maps.drawing.OverlayType.RECTANGLE ]
			},

			circleOptions : {
				fillColor : '#ffff00',
				fillOpacity : 1,
				strokeWeight : 5,
				clickable : false,
				editable : true,
				zIndex : 1
			}
		});
		drawingManager.setMap(map);
       // recuperer les coordonées du rectangle
		google.maps.event
				.addListener(
						drawingManager,
						'overlaycomplete',
						function(event) {
							if (event.type == google.maps.drawing.OverlayType.RECTANGLE) {

								var latz = event.overlay.getBounds()
										.getNorthEast().lat();
								var lngz = event.overlay.getBounds()
										.getNorthEast().lng();
								var lattz = event.overlay.getBounds()
										.getSouthWest().lat();
								var lnggz = event.overlay.getBounds()
										.getSouthWest().lng();
								
								

								document.getElementById('latzone').value = latz;
								document.getElementById('lngzone').value = lngz;
								document.getElementById('latzone1').value = lattz;
								document.getElementById('lngzone1').value = lnggz;
								
								
								
								var latx = latz;
								var lngx = lnggz;
								var latxx = lattz;
								var lngxx = lngz;			
													
								var tableauLatLng = [ 
								                     new google.maps.LatLng(latx,lngx), 
								                     new google.maps.LatLng(lattz,lnggz), 
								                     new google.maps.LatLng(latxx,lngxx),
								                     new google.maps.LatLng(latz,lngz)                   
								                    
								                    
								                 ];
								
								 var surface = google.maps.geometry.spherical.computeArea(tableauLatLng);
							        document.getElementById('Surface').value = surface;
								

							}
							//récupere les coordonnées du marker
							if (event.type == google.maps.drawing.OverlayType.MARKER) {								
								var lat = event.overlay.getPosition().lat();
								var lon = event.overlay.getPosition().lng();
								document.getElementById('latLieu').value = lat;
								document.getElementById('lngLieu').value = lon;

							}
						});
		
		
		/*
		 * Modification de l'emplacement du marqueur
		 */
		google.maps.event
				.addListener(
						marker,
						"dragend",
						function(e) {
							refreshMap(e.latLng);
							geocoder
									.geocode(
											{
												"latLng" : e.latLng
											},
											function(data, status) {
												if (status == google.maps.GeocoderStatus.OK
														&& data[0]) {
													document
															.getElementById("suggest").style.display = "none";
													document
															.getElementById("addr").value = data = data[0].formatted_address;
												}
											});
						});

	

		
	};
	
	// Fonction qui permet de centrer la map et de geolocaliser la zone globale
	
	 function  geolocaliser() {
  
		 var geocoder = new google.maps.Geocoder();
		geocoder 
		
		         .geocode(
						{
							"address" : document.getElementById("addr").value
						},
						function(data, status) {

							if (status == google.maps.GeocoderStatus.OK) {

								document.getElementById("addr").value = data[0].formatted_address;
								refreshMap(data[0].geometry.location) ;

								
						}} );
	 }

	
	
		
		 // Actualise l'affichage de la map
		 
		function refreshMap(point) {
			var b = Math.pow(10, decimals);
			document.getElementById("lat").value = Math.round(point.lat() * b)
					/ b;
			document.getElementById("lng").value = Math.round(point.lng() * b)
					/ b;
			map.setCenter(point);
			marker.setPosition(point);
			marker.setTitle(point.lat() + ";" + point.lng());
		}

			// fonction qui verifie le nombre de station par zone

		function verifierNbrStation(){
				
				
				var valueSurface = document.getElementById("Surface").value;
				
				var valueNbrStation = document.getElementById("NbrMAxStation").value; 
				
				if( valueSurface <= 300000 && valueNbrStation > 14 ){
					alert(" le Nombre maximum de stations à saisir est 14");
				    $("#NbrMAxStation").val("");
				}else if( valueSurface <= 600000 && valueNbrStation > 18 ){
					alert(" le Nombre maximum de stations à saisir est 18");
				    $("#NbrMAxStation").val("");
				}else if( valueSurface <= 900000 && valueNbrStation > 22 ){
					alert(" le Nombre maximum de stations à saisir est 22");
				    $("#NbrMAxStation").val("");
				}			
			
								
			}
	

	
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
							<li><a href="#">Manuel</a></li>
							<li><a href="#">Demande d'évolution</a></li>
						</ul>
					</div>
				</div> <!-- /Global Links Top --> <!-- Page Header -->
				<div id="RnoBranding" class="sc">
					<a href="#"> <img src="images/logos/kmk-rail.jpg" id="RnoLogo"
						alt="Kamikaze" width="63" height="63" /> </a>
					<h1 id="RnoApplicationName">
						<img src="images/header/kmk-header.jpg"
							alt="KAMIKAZE PROJECT - Rail Simulator" />
					</h1>
					<span> <img src="images/branding-coin.gif" alt="" width="31"
						height="31" /> </span>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td>
							<!-- /Page Header --> <jsp:include page="menuCreationReseau.jsp" />
							<!-- Body Content -->
							<div id="RnoBody ">

								<left>
								<div id="global">
									<div id="map" style="float: left; width: 700px; height: 500px"></div>

								</div>

								</left>
								:
							</div>
						</td>
						<td>

							<div>
								<div id="errors">
									<c:forEach items="${errorsMessage}" var="list">
										<p style="color: red">${errorsMessage}</p>
									</c:forEach>
								</div>
								<form id="search_frm" action="#">
									<fieldset>
										<legend>Définir la Zone Globale </legend>
										<label >Zone Globale:</label> <input
											type="text" id="addr" name="addr" /> <input type="button"
											value="Geolocaliser" onclick="geolocaliser()" id="action" />
								</form>
								<div id="coords">
			
									<form id="reverse_frm" style="">
										<table>
											<tr>
												<td><label for="lat">latitude :</label></td>
												<td><input id="lat" type="text" /></td>
											</tr>
											<tr>
												<td><label for="lng">longitude :</label></td>
												<td><input id="lng" type="text" /></td>
											</tr>




										</table>
									</form>
								</div>
								</fieldset>
							</div>

							<form name="f1" action="reseau" method="post">
								<div>
									<fieldset style="width: 370px">
										<legend>Création Du Reseau </legend>
										<label for="reseau" width="200px">Nom Reseau:</label> <input
											id="reseau" type="text" name="reseau" />
										<button type="submit" value="CreerReseau" name="action"
											id="action" width="200px">Creer Reseau</button>

									</fieldset>
								</div>
							</form>


							<div>
								<form action="reseau" method="post">
									<fieldset style="width: 370px">
										<legend>Paramétre Zone</legend>
										<table>
											<tr>
												<td><label for="nomReseau" width="300px">Nom
														Reseau:</label></td>
												<td><select id="idReseau" name="idReseau">
														<c:forEach items="${listeReseau}" var="list">
															<option value="${list.idReseau}">${list.nomReseau}</option>

														</c:forEach>
												</select></td>
											</tr>

											<tr>
												<td><label for="latzone" width="300px">Latitude
														Zone:</label></td>
												<td><input id="latzone" type="text" name="latitudeZone" />
												</td>
											</tr>
											<tr>
												<td><label for="lngzone" width="200px">Longitude
														Zone:</label></td>
												<td><input id="lngzone" type="text"
													name="longitudeZone" /></td>
											</tr>
											<tr>
												<td><label for="latzone1" width="200px">Latitude
														ZoneB:</label>
												</td>
												<td><input id="latzone1" type="text"
													name="latitudeZoneB" />
												</td>
											</tr>

											<tr>
												<td><label for="lngzone1" width="200px">Longitude
														ZoneB:</label>
												</td>
												<td><input id="lngzone1" type="text"
													name="longitudeZoneB">
												</td>
											</tr>
											<tr>
												<td><label for="Surface" width="310px">Surface
														de la zone:</label>
												</td>
												<td><input id="Surface" type="text" name="Surface">
												</td>
											</tr>

											<tr>
												<td><label for="NbrMAxStation" width="350px">Nombre
														de stations de la zone:</label>
												</td>
												<td><input id="NbrMAxStation" type="text"
													name="NbrMaxStation" onblur="verifierNbrStation()">
												</td>
											</tr>
											<tr>
												<td><label for="NbrHabt" width="350px">Nombre
														d'habitants de la zone :</label>
												</td>
												<td><input id="NbrHabt" type="text" name="NbrHabt" />
												</td>
											</tr>


										</table>
										<center>
										<button type="submit" value="GenererReseau" name="action"
									        id="action" width="200px">Generer Reseau</button>
										<button type="submit" value="AjoutZone" name="action"
											id="action" width="200px">Ajouter zone</button>
										<button type="reset" value="ViderChampsZone"
											name="ViderChampsZone" width="200px">Vider les
											champs</button>
										</center>
									</fieldset>
								</form>
							</div>

							<div>
								<form action="reseau" method="post">
									<fieldset style="width: 300px">
										<legend>Paramétre Lieu</legend>
										<table>
											<tr>
												<td><label for="idZone" width="250px">Numéro de
														la Zone:</label></td>
												<td><select id="idZone" name="idZone">
														<c:forEach items="${listeZone}" var="list">
															<option value="${list.idZone}">${list.idZone}</option>
														</c:forEach>
												</select>
												</td>
											</tr>
											<tr>
												<td><label for="latLieu" width="250px">Latitude
														Lieu : </label>
												</td>
												<td><input id="latLieu" type="text" name="latitudeLieu">
												</td>
											</tr>
											<tr>
												<td><label for="lngLieu" width="250px">Longitude
														Lieu : </label>
												</td>
												<td><input id="lngLieu" type="text"
													name="longitudeLieu">
												</td>
											</tr>
											<tr>
												<td><label for="nomLieu" width="250px">Nom Lieu
														: </label>
												</td>
												<td><input id="nomLieu" type="text" name="nomLieu">
												</td>
											</tr>
											<tr>
												<td><label for="TypeLieu" width="250px">Type
														Lieu : </label>
												</td>
												<td><SELECT id="typeLieu" name="typeLieu"
													width="25.0px">
														<OPTION value="1">Centre ville</OPTION>
														<OPTION value="2">Lieux Touristiques</OPTION>
														<OPTION value="3">Lieux de Travail</OPTION>
														<OPTION value="4">Habitations</OPTION>
														<OPTION value="5">Lieux de commerce</OPTION>
												</SELECT>
												</td>
											</tr>

										</table>
										<center>
										<button type="submit" value="AjoutLieu" name="action"
											id="action">Ajouter Lieu</button>
										<button type="reset" value="ViderChampsLieu"
											name="ViderChampsLieu" width="200px">Vider les
											champs</button>
										</center>
									</fieldset>

								</form>
							</div> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<!-- /Page Layout -->


</body>
</html>