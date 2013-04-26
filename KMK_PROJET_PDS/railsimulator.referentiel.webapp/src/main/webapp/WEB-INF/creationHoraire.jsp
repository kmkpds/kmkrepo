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
		<title>Création Horaire - Rail Simulator</title>
		<script type="text/javascript" src="js/main.js"></script>	
		<script type="text/javascript">
			var lbMin = 0;
  			var lbSec = 0;
    		//initialisation des listbox de cadencement		
    	  	function changeLB(value) {
    	  		var vitesse = value;
    	  		// x km --> 60 
    	  		// 0,2+0,105 --> y
    	  		// y = (60*0,305)/vitesse
    	  		var cadencementMin = (60*0.305)/vitesse;
    	  		document.parametresHoraire.cadencementJOMin.options.length = 0; 
	  			document.parametresHoraire.cadencementSamediMin.options.length = 0;
	  			document.parametresHoraire.cadencementDimancheJFMin.options.length = 0;
	  			document.parametresHoraire.cadencementJOSec.options.length = 0; 
	  			document.parametresHoraire.cadencementSamediSec.options.length = 0;
	  			document.parametresHoraire.cadencementDimancheJFSec.options.length = 0;
	  			lbMin = 0;
	  			lbSec = 0;
    	  		if(cadencementMin>=1){
    	  			lbMin = 1;	
    	  		}
    	  		if(cadencementMin>=2){
    	  			lbMin = 2;	
    	  		}
    	  		if(cadencementMin>=3){
    	  			lbMin = 3;	
    	  		}
    	  		if(cadencementMin>=4){
    	  			lbMin = 4;	
    	  		}
    	  		if(cadencementMin>=5){
    	  			lbMin = 5;	
    	  		}
    	  		if(cadencementMin>=6){
    	  			lbMin = 6;	
    	  		}
    	  		if(cadencementMin>=7){
    	  			lbMin = 7;	
    	  		}
    	  		if(cadencementMin>=8){
    	  			lbMin = 8;	
    	  		}
    	  		if(cadencementMin>=9){
    	  			lbMin = 9;	
    	  		}
    	  		var lbSecTmp = cadencementMin - lbMin;
    	  		if (lbSecTmp>=0.25){
    	  			//lbSec = 15;
    	  			lbSec = 1;
    	  		}
    	  		if (lbSecTmp>=0.50){
    	  			//lbSec = 30;
    	  			lbSec = 2;
    	  		}
    	  		if (lbSecTmp>=0.75){
    	  			//lbSec = 45;
    	  			lbSec = 3;
    	  		}
    	  		var j = 0;
    	  		for( var i=lbMin; i<=10; i++){
    	  			document.parametresHoraire.cadencementJOMin[j] = new Option(i, j); 
    	  			document.parametresHoraire.cadencementSamediMin[j] = new Option(i, j); 
    	  			document.parametresHoraire.cadencementDimancheJFMin[j] = new Option(i, j);
    	  			
    	  			/*document.parametresHoraire.cadencementJOMin.options[i].text=i;
    	  			document.parametresHoraire.cadencementJOMin.options[i].value=i;
    	  			document.parametresHoraire.getElementById("cadencementSamediMin").options[i].text=i;
    	  			document.parametresHoraire.getElementById("cadencementSamediMin").options[i].value=i;
    	  			document.parametresHoraire.getElementById("cadencementDimancheJFMin").options[i].text=i;
    	  			document.parametresHoraire.getElementById("cadencementDimancheJFMin").options[i].value=i;*/	
    	  			j=j+1
    	  		}
    	  		var Seconde = ["0", "15", "30", "45"];
    	  		j = 0;
    	  		for( var i=lbSec; i<4; i++){
    	  			document.parametresHoraire.cadencementJOSec[j] = new Option(Seconde[i], j); 
    	  			document.parametresHoraire.cadencementSamediSec[j] = new Option(Seconde[i], j);  
    	  			document.parametresHoraire.cadencementDimancheJFSec[j] =new Option(Seconde[i], j);
    	  			j=j+1;
    	  			/*document.parametresHoraire.cadencementJOMin.options[i].text=i;
    	  			document.parametresHoraire.cadencementJOMin.options[i].value=i;
    	  			document.parametresHoraire.getElementById("cadencementSamediMin").options[i].text=i;
    	  			document.parametresHoraire.getElementById("cadencementSamediMin").options[i].value=i;
    	  			document.parametresHoraire.getElementById("cadencementDimancheJFMin").options[i].text=i;
    	  			document.parametresHoraire.getElementById("cadencementDimancheJFMin").options[i].value=i;*/	
    	  		}
    	  		//document.parametresHoraire.action.style.display="block";
    	  		//document.parametresHoraire.action.disabled = false;
    	  		document.getElementById('msgSubmitButton').style.display = 'none';
    		}	
    		
    	  //initialisation des listbox de cadencement		
    	  	function updateLB(value) {
    	  		var Seconde = ["0", "15", "30", "45"];
				if(value=="cadencementJOMin"){
					var selectElmt = document.getElementById("cadencementJOMin");
					var textselectionne = selectElmt.options[selectElmt.selectedIndex].text;
					document.parametresHoraire.cadencementJOSec.options.length = 0; 
					//if (document.parametresHoraire.cadencementJOMin.value>lbMin){
					if (textselectionne>lbMin){
						j = 0;
						for( var i=0; i<4; i++){
		    	  			document.parametresHoraire.cadencementJOSec[j] = new Option(Seconde[i]); 
		    	  			j=j+1;
		    	  		}		
					}else{
						j=0;
						for( var i=lbSec; i<4; i++){
		    	  			document.parametresHoraire.cadencementJOSec[j] = new Option(Seconde[i], j); 
		    	  			j=j+1;
		    	  		}		
					}
				}
				if(value=="cadencementSamediMin"){
					var selectElmt = document.getElementById("cadencementSamediMin");
					var textselectionne = selectElmt.options[selectElmt.selectedIndex].text;
					document.parametresHoraire.cadencementSamediSec.options.length = 0;
					if (textselectionne>lbMin){ 
						j = 0;
						for( var i=0; i<4; i++){
		    	  			document.parametresHoraire.cadencementSamediSec[j] = new Option(Seconde[i]); 
		    	  			j=j+1;
		    	  		}		
					}
					else{
						j=0;
						for( var i=lbSec; i<4; i++){
		    	  			document.parametresHoraire.cadencementSamediSec[j] = new Option(Seconde[i], j);  
		    	  			j=j+1;
		    	  		}
					}
				}
				if(value=="cadencementDimancheJFMin"){
					var selectElmt = document.getElementById("cadencementDimancheJFMin");
					var textselectionne = selectElmt.options[selectElmt.selectedIndex].text;
					document.parametresHoraire.cadencementDimancheJFSec.options.length = 0; 
					if (textselectionne>lbMin){
						j = 0;
						for( var i=0; i<4; i++){
		    	  			document.parametresHoraire.cadencementDimancheJFSec[j] = new Option(Seconde[i]); 
		    	  			j=j+1;
		    	  		}
					}
					else{
						j=0;
						for( var i=lbSec; i<4; i++){
		    	  			document.parametresHoraire.cadencementDimancheJFSec[j] =new Option(Seconde[i], j);
		    	  			j=j+1;
		    	  		}
					}
				}
	  		}
			function checkSubmit(){
				compteurJO = 0;
				compteurSamedi=0;
				compteurDimancheJF=0;
				compteurTempsStationnement = 0;
				var check = new Boolean();
				check=true;
				msgAlert = "Attention !! \n";
				av=document.getElementsByName("heuresPointeJO");
				for (e=0;e<av.length;e++) {
					if (av[e].checked==true) {
						compteurJO = compteurJO +1;
					}
				 }
				if (compteurJO==0){
					msgAlert = msgAlert + "- heures de pointe jours ouvrés non renseignées \n";
				}
				
				av=document.getElementsByName("heuresPointeSamedi");
				for (e=0;e<av.length;e++) {
					if (av[e].checked==true) {
						compteurSamedi = compteurSamedi +1;
					}
				 }
				if (compteurSamedi==0){
					msgAlert = msgAlert + "- heures de pointe samedi non renseignées \n";
				}
				
				av=document.getElementsByName("heuresPointeDimancheJF");
				for (e=0;e<av.length;e++) {
					if (av[e].checked==true) {
						compteurDimancheJF = compteurDimancheJF +1;
					}
				 }
				if (compteurDimancheJF==0){
					msgAlert = msgAlert + "- heures de pointe dimanche et jours fériés non renseignées \n";
				}
				if(document.parametresHoraire.heurePTJO.value>document.parametresHoraire.heureDTJO.value ){
					msgAlert = msgAlert + "- l'heure du premier train ne peut être supérieur à l'heure du dernier train (Jours ouvrés)\n";
					check= false;
				}
				if(document.parametresHoraire.heurePTSamedi.value>document.parametresHoraire.heureDTSamedi.value ){
					msgAlert = msgAlert + "- l'heure du premier train ne peut être supérieur à l'heure du dernier train (Samedi)\n";
					check= false;
				}
				if(document.parametresHoraire.heurePTDimancheJF.value>document.parametresHoraire.heureDTDimancheJF.value ){
					msgAlert = msgAlert + "- l'heure du premier train ne peut être supérieur à l'heure du dernier train (Dimanche et jours fériés)\n";
					check= false;
				}
				if((document.parametresHoraire.heurePTJO.value==document.parametresHoraire.heureDTJO.value) && (document.parametresHoraire.minutePTJO.value >=document.parametresHoraire.minuteDTJO.value)){
					msgAlert = msgAlert + "- l'heure du premier train ne peut être supérieur à l'heure du dernier train (Jours ouvrés)\n";
					check= false;
				}
				if((document.parametresHoraire.heurePTSamedi.value==document.parametresHoraire.heureDTSamedi.value) && (document.parametresHoraire.minutePTSamedi.value >=document.parametresHoraire.minuteDTSamedi.value)){
					msgAlert = msgAlert + "- l'heure du premier train ne peut être supérieur à l'heure du dernier train (Samedi)\n";
					check= false;
				}
				if((document.parametresHoraire.heurePTDimancheJF.value==document.parametresHoraire.heureDTDimancheJF.value) && (document.parametresHoraire.minutePTDimancheJF.value >=document.parametresHoraire.minuteDTDimancheJF.value)){
					msgAlert = msgAlert + "- l'heure du premier train ne peut être supérieur à l'heure du dernier train (Dimanche et jours fériés)\n";
					check= false;
				}
				
				if (document.parametresHoraire.vitesseMoyenne.value==0){
					msgAlert = msgAlert + "- la vitesse moyenne n'a pas été saisie \n";
				}
				if (compteurDimancheJF==0 | compteurSamedi==0 | compteurJO==0 | compteurTempsStationnement !=0 |check==false |document.parametresHoraire.vitesseMoyenne.value==0){
					alert(msgAlert);
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
					<br>
					<div id="RnoBody ">
						<center>
						<form name ="parametresHoraire" action="horaire" method="post">
									<fieldset style="width: 700px">
										<legend>Paramètres horaire</legend>
										<table>
											<tr style="height:20px">
												<td>
													<label for="idLigne" width="250px">
														Identifiant ligne:
													</label> 
												</td>
												<td align="center">
													<select id="idLigne" name="idLigne" style="width:130px">
														<c:forEach items="${listeLigne}" var="list">
															<option value="${list.idLigne}">${list.nomLigne}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<br>
											<tr style="height:20px">
												<td>
													<label for="vitesseMoyenne" width="250px">Vitesse moyenne:</label> 
												</td>
												<td align ="center">
													<select id="vitesseMoyenne" name="vitesseMoyenne" style="width:130px" onchange="changeLB(this.value)">
														<c:forTokens var="entry" items="0;10;20;30;40;50;60;70;80;90;100;110;120;130;140" delims=";">
															<option value="${entry}">  ${entry} km/h</option>
														</c:forTokens>
													</select>
												</td>
											</tr>	
											<br>	
											<tr style="height:20px">
												<td></td>
												<td align="center" width="15%">Jours ouvrés</td>
												<td align="center" width="15%">Samedi</td>
												<td align="center" width="25%">Dimanche et jours fériés</td>	
											</tr>
											<tr style="height:20px">
												<td>Heure du premier train (heures - minutes):</td>
												<td align="center">
													<select id="heurePTJO" name="heurePTJO" style="width:45px">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minutePTJO" name="minutePTJO" style="width:45px">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>			
												<td align="center">
													<select id="heurePTSamedi" name="heurePTSamedi" style="width:45px">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minutePTSamedi" name="minutePTSamedi" style="width:45px">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
												<td align="center">
													<select id="heurePTDimancheJF" name="heurePTDimancheJF" style="width:45px">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minutePTDimancheJF" name="minutePTDimancheJF" style="width:45px">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr style="height:20px">
												<td>
													Heure du dernier train (heures - minutes): 
												</td>
												<td align="center">
													<select id="heureDTJO" name="heureDTJO" style="width:45px">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minuteDTJO" name="minuteDTJO" style="width:45px">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>			
												<td align="center">
													<select id="heureDTSamedi" name="heureDTSamedi" style="width:45px">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minuteDTSamedi" name="minuteDTSamedi" style="width:45px">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
												<td align="center">
													<select id="heureDTDimancheJF" name="heureDTDimancheJF" style="width:45px">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="minuteDTDimancheJF" name="minuteDTDimancheJF" style="width:45px">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<td>
													Cadencement (minutes - secondes):</label> 
												</td>
												<td align="center">
													<select id="cadencementJOMin" name="cadencementJOMin" onchange="updateLB('cadencementJOMin')" style="width:45px">
														<!-- <c:forEach var="entry" begin="00" end="10">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>-->
													</select>
													<select id="cadencementJOSec" name="cadencementJOSec" style="width:45px">
														<!-- <option value="0">0</option>
														<option value="15">15</option>
														<option value="30">30</option>
														<option value="45">45</option>-->
													</select>
												</td>			
												<td align="center">
													<select id="cadencementSamediMin" name="cadencementSamediMin" onchange="updateLB('cadencementSamediMin')" style="width:45px">
														<!-- <c:forEach var="entry" begin="00" end="10">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>-->
													</select>
													<select id="cadencementSamediSec" name="cadencementSamediSec" style="width:45px">
														<!-- <option value="0">0</option>
														<option value="15">15</option>
														<option value="30">30</option>
														<option value="45">45</option>-->
													</select>
												</td>
												<td align="center">
													<select id="cadencementDimancheJFMin" name="cadencementDimancheJFMin" onchange="updateLB('cadencementDimancheJFMin')" style="width:45px">
														<!-- <c:forEach var="entry" begin="00" end="10">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>-->
													</select>
													<select id="cadencementDimancheJFSec" name="cadencementDimancheJFSec" style="width:45px">
														<!-- <option value="0">0</option>
														<option value="15">15</option>
														<option value="30">30</option>
														<option value="45">45</option>-->
													</select>
												</td>
											</tr>
											<tr style="height:20px">
												<td>
													Temps de stationnement (minutes - secondes) 
												</td>
												<td align="center">
													<select id="tempsStationnementJOMin" name="tempsStationnementJOMin" style="width:45px">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="tempsStationnementJOSec" name="tempsStationnementJOSec" style="width:45px">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>			
												<td align="center">
													<select id="tempsStationnementSamediMin" name="tempsStationnementSamediMin" style="width:45px">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="tempsStationnementSamediSec" name="tempsStationnementSamediSec" style="width:45px">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
												<td align="center">
													<select id="tempsStationnementDimancheJFMin" name="tempsStationnementDimancheJFMin" style="width:45px">
														<c:forEach var="entry" begin="00" end="23">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
													<select id="tempsStationnementDimancheJFSec" name="tempsStationnementDimancheJFSec" style="width:45px">
														<c:forEach var="entry" begin="00" end="59">
															<option value="${entry}">  ${entry}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr style="height:115px">
												<td>
													Heures de pointe: 
												</td>
												<td align="center">
													<div style="width: 125px; height: 100px; overflow-y: scroll;">
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO67"> 06:00 - 07:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO78"> 07:00 - 08:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO89"> 08:00 - 09:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO910"> 09:00 - 10:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO1011"> 10:00 - 11:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO1112"> 11:00 - 12:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO1213"> 12:00 - 13:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO1314"> 13:00 - 14:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO1415"> 14:00 - 15:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO1516"> 15:00 - 16:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO1617"> 16:00 - 17:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO1718"> 17:00 - 18:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO1819"> 18:00 - 19:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO1920"> 19:00 - 20:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO2021"> 20:00 - 21:00<br>
														<input type="checkbox" name="heuresPointeJO" value="heuresPointeJO2122"> 21:00 - 22:00<br>
													</div>
												</td>
												<td align="center">
													<div style="width: 125px; height: 100px; overflow-y: scroll;">
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi67"> 06:00 - 07:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi78"> 07:00 - 08:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi89"> 08:00 - 09:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi910"> 09:00 - 10:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi1011"> 10:00 - 11:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi1112"> 11:00 - 12:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi1213"> 12:00 - 13:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi1314"> 13:00 - 14:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi1415"> 14:00 - 15:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi1516"> 15:00 - 16:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi1617"> 16:00 - 17:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi1718"> 17:00 - 18:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi1819"> 18:00 - 19:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi1920"> 19:00 - 20:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi2021"> 20:00 - 21:00<br>
														<input type="checkbox" name="heuresPointeSamedi" value="heuresPointeSamedi2122"> 21:00 - 22:00<br>
													</div>
												</td>
												<td align="center">
													<div style="width: 125px; height: 100px; overflow-y: scroll;">
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF67"> 06:00 - 07:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF78"> 07:00 - 08:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF89"> 08:00 - 09:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF910"> 09:00 - 10:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF1011"> 10:00 - 11:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF1112"> 11:00 - 12:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF1213"> 12:00 - 13:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF1314"> 13:00 - 14:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF1415"> 14:00 - 15:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF1516"> 15:00 - 16:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF1617"> 16:00 - 17:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF1718"> 17:00 - 18:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF1819"> 18:00 - 19:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF1920"> 19:00 - 20:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF2021"> 20:00 - 21:00<br>
														<input type="checkbox" name="heuresPointeDimancheJF" value="heuresPointeDimancheJF2122"> 21:00 - 22:00<br>
													 </div>
												</td>
											</tr>
										</table>
										<br>
										<center>
											<button type="submit" value="genererGrilleHoraire" onclick="checkSubmit()" name="action" id="action"  width="200px">
												Créer la grille horaire
											</button>
											<div id="msgSubmitButton">Merci de sélectionner une vitesse avant de pouvoir générer la grille horaire.</div>
										</center>
									</fieldset>
								</form>
						</center>                           				
					</div>
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
	</body>
</html>