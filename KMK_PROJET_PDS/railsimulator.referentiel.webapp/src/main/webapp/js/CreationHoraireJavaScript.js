var lbMin = 0;
var lbSec = 0;
// initialisation des listbox de cadencement
function changeLB(value) {
	var vitesse = value;
	// Calcul du cadencement minimum
	// x km --> 60
	// 0,2+0,105 --> y
	// y = (60*0,305)/x = (60*0,305)/vitesse
	var cadencementMin = (60 * 0.305) / vitesse;
	document.parametresHoraire.cadencementJOMin.options.length = 0;
	document.parametresHoraire.cadencementSamediMin.options.length = 0;
	document.parametresHoraire.cadencementDimancheJFMin.options.length = 0;
	document.parametresHoraire.cadencementJOSec.options.length = 0;
	document.parametresHoraire.cadencementSamediSec.options.length = 0;
	document.parametresHoraire.cadencementDimancheJFSec.options.length = 0;
	lbMin = 0;
	lbSec = 0;
	if (cadencementMin >= 1) {
		lbMin = 1;
	}
	if (cadencementMin >= 2) {
		lbMin = 2;
	}
	if (cadencementMin >= 3) {
		lbMin = 3;
	}
	if (cadencementMin >= 4) {
		lbMin = 4;
	}
	if (cadencementMin >= 5) {
		lbMin = 5;
	}
	if (cadencementMin >= 6) {
		lbMin = 6;
	}
	if (cadencementMin >= 7) {
		lbMin = 7;
	}
	if (cadencementMin >= 8) {
		lbMin = 8;
	}
	if (cadencementMin >= 9) {
		lbMin = 9;
	}
	var lbSecTmp = cadencementMin - lbMin;
	if (lbSecTmp >= 0.25) {
		// lbSec = 15;
		lbSec = 1;
	}
	if (lbSecTmp >= 0.50) {
		// lbSec = 30;
		lbSec = 2;
	}
	if (lbSecTmp >= 0.75) {
		// lbSec = 45;
		lbSec = 3;
	}
	var j = 0;
	for ( var i = lbMin; i <= 10; i++) {
		document.parametresHoraire.cadencementJOMin[j] = new Option(i, j);
		document.parametresHoraire.cadencementSamediMin[j] = new Option(i, j);
		document.parametresHoraire.cadencementDimancheJFMin[j] = new Option(i,
				j);

		j = j + 1;
	}
	var Seconde = [ "0", "15", "30", "45" ];
	j = 0;
	for ( var i = lbSec; i < 4; i++) {
		document.parametresHoraire.cadencementJOSec[j] = new Option(Seconde[i],
				j);
		document.parametresHoraire.cadencementSamediSec[j] = new Option(
				Seconde[i], j);
		document.parametresHoraire.cadencementDimancheJFSec[j] = new Option(
				Seconde[i], j);
		j = j + 1;
	}
	// document.parametresHoraire.action.style.display="block";
	// document.parametresHoraire.action.disabled = false;
	document.getElementById('msgSubmitButton').style.display = 'none';
}
// initialisation des listbox de cadencement
function updateLB(value) {
	var Seconde = [ "0", "15", "30", "45" ];
	if (value == "cadencementJOMin") {
		var selectElmt = document.getElementById("cadencementJOMin");
		var textselectionne = selectElmt.options[selectElmt.selectedIndex].text;
		document.parametresHoraire.cadencementJOSec.options.length = 0;
		// if (document.parametresHoraire.cadencementJOMin.value>lbMin){
		if (textselectionne > lbMin) {
			j = 0;
			for ( var i = 0; i < 4; i++) {
				document.parametresHoraire.cadencementJOSec[j] = new Option(
						Seconde[i]);
				j = j + 1;
			}
		} else {
			j = 0;
			for ( var i = lbSec; i < 4; i++) {
				document.parametresHoraire.cadencementJOSec[j] = new Option(
						Seconde[i], j);
				j = j + 1;
			}
		}
	}
	if (value == "cadencementSamediMin") {
		var selectElmt = document.getElementById("cadencementSamediMin");
		var textselectionne = selectElmt.options[selectElmt.selectedIndex].text;
		document.parametresHoraire.cadencementSamediSec.options.length = 0;
		if (textselectionne > lbMin) {
			j = 0;
			for ( var i = 0; i < 4; i++) {
				document.parametresHoraire.cadencementSamediSec[j] = new Option(
						Seconde[i]);
				j = j + 1;
			}
		} else {
			j = 0;
			for ( var i = lbSec; i < 4; i++) {
				document.parametresHoraire.cadencementSamediSec[j] = new Option(
						Seconde[i], j);
				j = j + 1;
			}
		}
	}
	if (value == "cadencementDimancheJFMin") {
		var selectElmt = document.getElementById("cadencementDimancheJFMin");
		var textselectionne = selectElmt.options[selectElmt.selectedIndex].text;
		document.parametresHoraire.cadencementDimancheJFSec.options.length = 0;
		if (textselectionne > lbMin) {
			j = 0;
			for ( var i = 0; i < 4; i++) {
				document.parametresHoraire.cadencementDimancheJFSec[j] = new Option(
						Seconde[i]);
				j = j + 1;
			}
		} else {
			j = 0;
			for ( var i = lbSec; i < 4; i++) {
				document.parametresHoraire.cadencementDimancheJFSec[j] = new Option(
						Seconde[i], j);
				j = j + 1;
			}
		}
	}
}
function checkSubmit() {
	compteurJO = 0;
	compteurSamedi = 0;
	compteurDimancheJF = 0;
	compteurTempsStationnement = 0;
	var check = new Boolean();
	check = true;
	msgAlert = "Attention !! \n";
	av = document.getElementsByName("heuresPointeJO");
	for (e = 0; e < av.length; e++) {
		if (av[e].checked == true) {
			compteurJO = compteurJO + 1;
		}
	}
	if (compteurJO == 0) {
		msgAlert = msgAlert
				+ "- heures de pointe jours ouvr\351s non renseign\351es \n";
	}

	av = document.getElementsByName("heuresPointeSamedi");
	for (e = 0; e < av.length; e++) {
		if (av[e].checked == true) {
			compteurSamedi = compteurSamedi + 1;
		}
	}
	if (compteurSamedi == 0) {
		msgAlert = msgAlert + "- heures de pointe samedi non renseign\351es \n";
	}

	av = document.getElementsByName("heuresPointeDimancheJF");
	for (e = 0; e < av.length; e++) {
		if (av[e].checked == true) {
			compteurDimancheJF = compteurDimancheJF + 1;
		}
	}
	if (compteurDimancheJF == 0) {
		msgAlert = msgAlert
				+ "- heures de pointe dimanche et jours f\351ri\351s non renseign\351es \n";
	}
	if (document.parametresHoraire.heurePTJO.value > document.parametresHoraire.heureDTJO.value) {
		msgAlert = msgAlert
				+ "- l'heure du premier train ne peut \352tre sup\351rieur \340 l'heure du dernier train (Jours ouvr\351s)\n";
		check = false;
	}
	if (document.parametresHoraire.heurePTSamedi.value > document.parametresHoraire.heureDTSamedi.value) {
		msgAlert = msgAlert
				+ "- l'heure du premier train ne peut \352tre sup\351rieur \340 l'heure du dernier train (Samedi)\n";
		check = false;
	}
	if (document.parametresHoraire.heurePTDimancheJF.value > document.parametresHoraire.heureDTDimancheJF.value) {
		msgAlert = msgAlert
				+ "- l'heure du premier train ne peut \352tre sup\351rieur \340 l'heure du dernier train (Dimanche et jours f\351ri\351s)\n";
		check = false;
	}
	if ((document.parametresHoraire.heurePTJO.value == document.parametresHoraire.heureDTJO.value)
			&& (document.parametresHoraire.minutePTJO.value >= document.parametresHoraire.minuteDTJO.value)) {
		msgAlert = msgAlert
				+ "- l'heure du premier train ne peut \352tre sup\351rieur \340 l'heure du dernier train (Jours ouvr\351s)\n";
		check = false;
	}
	if ((document.parametresHoraire.heurePTSamedi.value == document.parametresHoraire.heureDTSamedi.value)
			&& (document.parametresHoraire.minutePTSamedi.value >= document.parametresHoraire.minuteDTSamedi.value)) {
		msgAlert = msgAlert
				+ "- l'heure du premier train ne peut \352tre sup\351rieur \340 l'heure du dernier train (Samedi)\n";
		check = false;
	}
	if ((document.parametresHoraire.heurePTDimancheJF.value == document.parametresHoraire.heureDTDimancheJF.value)
			&& (document.parametresHoraire.minutePTDimancheJF.value >= document.parametresHoraire.minuteDTDimancheJF.value)) {
		msgAlert = msgAlert
				+ "- l'heure du premier train ne peut \352tre sup\351rieur \340 l'heure du dernier train (Dimanche et jours f\351ri\351s)\n";
		check = false;
	}

	if (document.parametresHoraire.vitesseMoyenne.value == 0) {
		msgAlert = msgAlert + "- la vitesse moyenne n'a pas \351t\351 saisie \n";
		check = false;
	}

	if (document.parametresHoraire.tempsStationnementJOMin.value == 0 && document.parametresHoraire.tempsStationnementJOSec.value == 0) {
		msgAlert = msgAlert + "- le temps de stationnement ne peut \352tre nul (Jours ouvr\351s) \n";
		check = false;
	}
	if (document.parametresHoraire.tempsStationnementSamediMin.value == 0 && document.parametresHoraire.tempsStationnementSamediSec.value == 0) {
		msgAlert = msgAlert + "- le temps de stationnement ne peut \352tre nul (Samedi) \n";
		check = false;
	}
	if (document.parametresHoraire.tempsStationnementDimancheJFMin.value == 0 && document.parametresHoraire.tempsStationnementDimancheJFSec.value == 0) {
		msgAlert = msgAlert + "- le temps de stationnement ne peut \352tre nul (Dimanche et jours f\351ri\351s) \n";
		check = false;
	}
	if (compteurDimancheJF == 0 | compteurSamedi == 0 | compteurJO == 0
			| compteurTempsStationnement != 0 | check == false) {
		alert(msgAlert);
	}
}