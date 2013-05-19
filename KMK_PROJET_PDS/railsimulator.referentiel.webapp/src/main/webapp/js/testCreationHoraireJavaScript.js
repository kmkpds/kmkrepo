function test(){
	alert("D\351but du test");
	//initialisation des champs du formulaire
	initField();
	changeLB(document.parametresHoraire.vitesseMoyenne.value);
	if (document.getElementById("cadencementJOMin").options[document.getElementById("cadencementJOMin").selectedIndex].text==0 &&
			document.getElementById("cadencementSamediMin").options[document.getElementById("cadencementSamediMin").selectedIndex].text==0 &&
			document.getElementById("cadencementDimancheJFMin").options[document.getElementById("cadencementDimancheJFMin").selectedIndex].text==0 &&
			document.getElementById("cadencementJOSec").options[document.getElementById("cadencementJOSec").selectedIndex].text==45 &&
			document.getElementById("cadencementSamediSec").options[document.getElementById("cadencementSamediSec").selectedIndex].text==45 &&
			document.getElementById("cadencementDimancheJFSec").options[document.getElementById("cadencementDimancheJFSec").selectedIndex].text==45){
		alert("La fonction changeLB est bonne");
	}
	else{
		alert("La fonction changeLB n'est pas bonne");
	}
	document.parametresHoraire.cadencementJOMin.value="1";
	updateLB('cadencementJOMin');
	if (document.getElementById("cadencementJOSec").options[document.getElementById("cadencementJOSec").selectedIndex].text==0){
		alert("La fonction updateLB est bonne");
	}
	else{
		alert("La fonction updateLB n'est pas bonne");
	}
	testCheckSubmit(1);
	testCheckSubmit(2);
	testCheckSubmit(3);
	testCheckSubmit(4);
		
	alert("Fin du test");
}

function initField(){
	deselectionHeuresPointes();
	//initialisation des champs du formulaire
	document.parametresHoraire.vitesseMoyenne.value="20";
	document.parametresHoraire.heurePTJO.value="1";
	document.parametresHoraire.minutePTJO.value="1";
	document.parametresHoraire.heurePTSamedi.value="2";
	document.parametresHoraire.minutePTSamedi.value="2";
	document.parametresHoraire.heurePTDimancheJF.value="3";
	document.parametresHoraire.minutePTDimancheJF.value="3";
	document.parametresHoraire.heureDTJO.value="4";
	document.parametresHoraire.minuteDTJO.value="4";
	document.parametresHoraire.heureDTSamedi.value="5";
	document.parametresHoraire.minuteDTSamedi.value="5";
	document.parametresHoraire.heureDTDimancheJF.value="6";
	document.parametresHoraire.minuteDTDimancheJF.value="6";
	document.parametresHoraire.tempsStationnementJOMin.value="7";
	document.parametresHoraire.tempsStationnementJOSec.value="7";
	document.parametresHoraire.tempsStationnementSamediMin.value="8";
	document.parametresHoraire.tempsStationnementSamediSec.value="8";
	document.parametresHoraire.tempsStationnementDimancheJFMin.value="9";
	document.parametresHoraire.tempsStationnementDimancheJFSec.value="9";
	document.parametresHoraire.heuresPointeJO[0].checked=true;
	document.parametresHoraire.heuresPointeJO[1].checked=true;
	document.parametresHoraire.heuresPointeSamedi[0].checked=true;
	document.parametresHoraire.heuresPointeSamedi[1].checked=true;
	document.parametresHoraire.heuresPointeDimancheJF[0].checked=true;
	document.parametresHoraire.heuresPointeDimancheJF[1].checked=true;	
}

function testCheckSubmit(i){
	if (i==1){
		alert("test de la fonction checkSubmit 1 - Tous les champs \351tant remplis, elle ne doit pas vous retourner d'erreur, sinon cela signifie qu'elle n'est pas bonne");
		checkSubmit();	
	}
	if(i==2){
		alert("test de la fonction checkSubmit 2 - Tous les champs sont remplis, mais l'horaire du dernier train est inf\351rieur \340 l'horaire du premier train (JO et Dimanche)");
		document.parametresHoraire.heurePTJO.value="5";
		document.parametresHoraire.minutePTJO.value="0";
		document.parametresHoraire.heurePTDimancheJF.value="3";
		document.parametresHoraire.minutePTDimancheJF.value="3";
		document.parametresHoraire.heureDTJO.value="4";
		document.parametresHoraire.minuteDTJO.value="0";
		document.parametresHoraire.heureDTDimancheJF.value="1";
		document.parametresHoraire.minuteDTDimancheJF.value="1";
		checkSubmit();	
	}
	if(i==3){
		alert("test de la fonction checkSubmit 3 - Tous les champs sont remplis mais aucune heure de pointe n'est saisie pour le samedi");
		document.parametresHoraire.heuresPointeSamedi[0].checked=false;
		document.parametresHoraire.heuresPointeSamedi[1].checked=false;
		checkSubmit();	
	}
	if(i==4){
		alert("test de la fonction checkSubmit 4 - Aucun champs n'est valide.");
		document.parametresHoraire.heuresPointeJO[0].checked=false;
		document.parametresHoraire.heuresPointeJO[1].checked=false;
		document.parametresHoraire.heuresPointeSamedi[0].checked=false;
		document.parametresHoraire.heuresPointeSamedi[1].checked=false;
		document.parametresHoraire.heuresPointeDimancheJF[0].checked=false;
		document.parametresHoraire.heuresPointeDimancheJF[1].checked=false;
		document.parametresHoraire.vitesseMoyenne.value="20";
		document.parametresHoraire.heurePTJO.value="6";
		document.parametresHoraire.minutePTJO.value="6";
		document.parametresHoraire.heurePTSamedi.value="7";
		document.parametresHoraire.minutePTSamedi.value="7";
		document.parametresHoraire.heurePTDimancheJF.value="8";
		document.parametresHoraire.minutePTDimancheJF.value="8";
		document.parametresHoraire.heureDTJO.value="4";
		document.parametresHoraire.minuteDTJO.value="4";
		document.parametresHoraire.heureDTSamedi.value="5";
		document.parametresHoraire.minuteDTSamedi.value="5";
		document.parametresHoraire.heureDTDimancheJF.value="6";
		document.parametresHoraire.minuteDTDimancheJF.value="6";
		document.parametresHoraire.tempsStationnementJOMin.value="0";
		document.parametresHoraire.tempsStationnementJOSec.value="0";
		document.parametresHoraire.tempsStationnementSamediMin.value="0";
		document.parametresHoraire.tempsStationnementSamediSec.value="0";
		document.parametresHoraire.tempsStationnementDimancheJFMin.value="0";
		document.parametresHoraire.tempsStationnementDimancheJFSec.value="0";
		checkSubmit();	
	}
	initField();
}
function deselectionHeuresPointes(){
	document.parametresHoraire.heuresPointeJO[0].checked=false;
	document.parametresHoraire.heuresPointeJO[1].checked=false;
	document.parametresHoraire.heuresPointeJO[2].checked=false;
	document.parametresHoraire.heuresPointeJO[3].checked=false;
	document.parametresHoraire.heuresPointeJO[4].checked=false;
	document.parametresHoraire.heuresPointeJO[5].checked=false;
	document.parametresHoraire.heuresPointeJO[6].checked=false;
	document.parametresHoraire.heuresPointeJO[7].checked=false;
	document.parametresHoraire.heuresPointeJO[8].checked=false;
	document.parametresHoraire.heuresPointeJO[9].checked=false;
	document.parametresHoraire.heuresPointeJO[10].checked=false;
	document.parametresHoraire.heuresPointeJO[11].checked=false;
	document.parametresHoraire.heuresPointeJO[12].checked=false;
	document.parametresHoraire.heuresPointeJO[13].checked=false;
	document.parametresHoraire.heuresPointeJO[14].checked=false;
	document.parametresHoraire.heuresPointeJO[15].checked=false;
	document.parametresHoraire.heuresPointeSamedi[0].checked=false;
	document.parametresHoraire.heuresPointeSamedi[1].checked=false;
	document.parametresHoraire.heuresPointeSamedi[2].checked=false;
	document.parametresHoraire.heuresPointeSamedi[3].checked=false;
	document.parametresHoraire.heuresPointeSamedi[4].checked=false;
	document.parametresHoraire.heuresPointeSamedi[5].checked=false;
	document.parametresHoraire.heuresPointeSamedi[6].checked=false;
	document.parametresHoraire.heuresPointeSamedi[7].checked=false;
	document.parametresHoraire.heuresPointeSamedi[8].checked=false;
	document.parametresHoraire.heuresPointeSamedi[9].checked=false;
	document.parametresHoraire.heuresPointeSamedi[10].checked=false;
	document.parametresHoraire.heuresPointeSamedi[11].checked=false;
	document.parametresHoraire.heuresPointeSamedi[12].checked=false;
	document.parametresHoraire.heuresPointeSamedi[13].checked=false;
	document.parametresHoraire.heuresPointeSamedi[14].checked=false;
	document.parametresHoraire.heuresPointeSamedi[15].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[0].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[1].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[2].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[3].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[4].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[5].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[6].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[7].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[8].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[9].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[10].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[11].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[12].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[13].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[14].checked=false;
	document.parametresHoraire.heuresPointeDimancheJF[15].checked=false;
}
function testIntegration(choix){
	if(choix==1){
		deselectionHeuresPointes();
		initField();
		alert("Vous allez \352tre redirig\351 vers une nouvelle page. Celle-ci vous indiquera si le test d'int\351gration c'est bien d\351roul\351 ou non.");

	}
	else{
		//VŽrification tableau jours ouvrŽs
		tableauJO = document.getElementById("tableauHoraireJOTab");
		checkJO = true;
//		tableauJOtd = tableauJO.getElementsByTagName('td');
//		alert("1==> "+tableauJO.rows.length);
//		alert("2 ==>" +tableauJO.rows[0].cells.length);
//		for(i=1; i<tableauJO.rows.length;i++){
//			for(j=1; j<tableauJO.rows[0].cells.length;j++){
//				alert(tableauJO.rows[i].cells[j].innerHTML);
//			}
//		}
		if(tableauJO.rows[1].cells[1].innerHTML!="01:01:00" |
				tableauJO.rows[1].cells[2].innerHTML!="01:01:00" |
				tableauJO.rows[1].cells[3].innerHTML!="01:01:00"|
				tableauJO.rows[2].cells[1].innerHTML!="01:10:34" |
				tableauJO.rows[2].cells[2].innerHTML!="01:10:34" |
				tableauJO.rows[2].cells[3].innerHTML!="01:10:34"|
				tableauJO.rows[3].cells[1].innerHTML!="01:22:31" |
				tableauJO.rows[3].cells[2].innerHTML!="01:22:31" |
				tableauJO.rows[3].cells[3].innerHTML!="01:22:31"|
				tableauJO.rows[4].cells[1].innerHTML!="01:40:27" |
				tableauJO.rows[4].cells[2].innerHTML!="01:40:27" |
				tableauJO.rows[4].cells[3].innerHTML!="01:40:27"|
				tableauJO.rows[5].cells[1].innerHTML!="02:00:20" |
				tableauJO.rows[5].cells[2].innerHTML!="02:00:20" |
				tableauJO.rows[5].cells[3].innerHTML!="02:00:20"){
			checkJO=false;
		}
		if(checkJO){
			alert("Les valeurs r\351cup\351r\351es pour les jours ouvr\351s sont correctes");
		}
		else{
			alert("Les valeurs r\351cup\351r\351es pour les jours ouvr\351s sont incorrectes");
		}
		//VŽrification tableau samedi
		tableauSamedi = document.getElementById("tableauHoraireSamediTab");
		checkSamedi = true;
		if(tableauSamedi.rows[1].cells[1].innerHTML!="02:02:00" |
				tableauSamedi.rows[1].cells[2].innerHTML!="02:02:00" |
				tableauSamedi.rows[1].cells[3].innerHTML!="02:02:00"|
				tableauSamedi.rows[2].cells[1].innerHTML!="02:11:34" |
				tableauSamedi.rows[2].cells[2].innerHTML!="02:11:34" |
				tableauSamedi.rows[2].cells[3].innerHTML!="02:11:34"|
				tableauSamedi.rows[3].cells[1].innerHTML!="02:23:31" |
				tableauSamedi.rows[3].cells[2].innerHTML!="02:23:31" |
				tableauSamedi.rows[3].cells[3].innerHTML!="02:23:31"|
				tableauSamedi.rows[4].cells[1].innerHTML!="02:41:27" |
				tableauSamedi.rows[4].cells[2].innerHTML!="02:41:27" |
				tableauSamedi.rows[4].cells[3].innerHTML!="02:41:27"|
				tableauSamedi.rows[5].cells[1].innerHTML!="03:01:20" |
				tableauSamedi.rows[5].cells[2].innerHTML!="03:01:20" |
				tableauSamedi.rows[5].cells[3].innerHTML!="03:01:20"){
			checkSamedi=false;
		}
		if(checkSamedi){
			alert("Les valeurs r\351cup\351r\351es pour le samedi sont correctes");
		}
		else{
			alert("Les valeurs r\351cup\351r\351es pour le samedi sont incorrectes");
		}
		
		//VŽrification tableau dimanche et jours fŽriŽs
		tableauDimanche = document.getElementById("tableauHoraireDimancheJFTab");
		checkDimancheJF = true;
		if(tableauDimanche.rows[1].cells[1].innerHTML!="03:03:00" |
				tableauDimanche.rows[1].cells[2].innerHTML!="03:03:00" |
				tableauDimanche.rows[1].cells[3].innerHTML!="03:03:00"|
				tableauDimanche.rows[2].cells[1].innerHTML!="03:12:34" |
				tableauDimanche.rows[2].cells[2].innerHTML!="03:12:34" |
				tableauDimanche.rows[2].cells[3].innerHTML!="03:12:34"|
				tableauDimanche.rows[3].cells[1].innerHTML!="03:24:31" |
				tableauDimanche.rows[3].cells[2].innerHTML!="03:24:31" |
				tableauDimanche.rows[3].cells[3].innerHTML!="03:24:31"|
				tableauDimanche.rows[4].cells[1].innerHTML!="03:42:27" |
				tableauDimanche.rows[4].cells[2].innerHTML!="03:42:27" |
				tableauDimanche.rows[4].cells[3].innerHTML!="03:42:27"|
				tableauDimanche.rows[5].cells[1].innerHTML!="04:02:20" |
				tableauDimanche.rows[5].cells[2].innerHTML!="04:02:20" |
				tableauDimanche.rows[5].cells[3].innerHTML!="04:02:20"){
			checkDimancheJF=false;
		}
		if(checkDimancheJF){
			alert("Les valeurs r\351cup\351r\351es pour le dimanche et les jours f\351ri\351s sont correctes");
		}
		else{
			alert("Les valeurs r\351cup\351r\351es pour le dimanche et les jours f\351ri\351s sont incorrectes");
		}
		
		if(checkJO && checkSamedi && checkDimancheJF){
			alert("Le test d'int\351gration s'est bien d\351roul\351")
		}
		else{
			alert("Le test d'int\351gration ne s'est pas bien d\351roul\351")
		}
	}
}


