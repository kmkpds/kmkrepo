<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<link rel="stylesheet" type="text/css" href="css/corp.css" />
		<link rel="alternate stylesheet" type="text/css" href="css/print.css"
			media="screen" title="Version imprimable" id="stylesheet-print" />
		<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
		<title>Mock Auto ETL</title>
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
								<!-- Body Content -->
							</td>
							<td>
	
								<div>
									
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