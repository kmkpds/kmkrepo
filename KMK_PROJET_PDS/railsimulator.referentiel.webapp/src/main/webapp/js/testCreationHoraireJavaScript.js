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
