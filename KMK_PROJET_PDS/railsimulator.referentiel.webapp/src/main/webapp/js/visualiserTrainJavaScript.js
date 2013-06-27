function testIntegration(){
	
	idTrain = document.getElementById("idTrain").value;
	alert("IDtrain=>" +idTrain);
	
	
	if(document.formAfficher2.idTrain.value=idTrain){
			alert("Test integration SUCCESS");
	}
	else{
		alert("Test integration NOT SUCCESS");
	}

	
}//fin fonction testIntegration





function loadStationTrain(ligneidentifiant, listeLigne, listeTrain){
//			var ligne=ligneidentifiant;
			
			//var listeStation=listeStation;
			//var listeTrain=listeTrain;
			
//			alert("ligne" +ligne);
			//var listeStation=<%=listeStation%>;
			//alert("listeStation" +listeStation);
//			alert("listeTrain" +listeTrain);
			
//			var listBalises = document.getElementsByTagName('script').getAttribute('listeStation');
//		    alert(listBalises);
			 // //var div1 = document.getElementByID("listeStation").value;
			 // // var align = div1.getAttribute("listeStation");
			  
			//alert("idtrain" +document.getElementByID.getAttribute('listeStation'));
			document.formAfficher.idStation.options.length = 0;
			document.formAfficher.idTrain.options.length = 0;
			
			alert("dans load");
			
			j=0;
//			
//			for (var Station in listeStation) {
//				alert("dans for1");
//				stationLigneId = Station.ligne.idLigne;
//				stationId =Station.idStation;
//				//stationName =<c:out value="${Station.nomStation}"/>;
//				if(stationLigneId==ligneidentifiant){
//				//alert(stationLigneId);
//				document.formAfficher.idStation[j] = new Option(stationId);
//				j=j+1;
//				}
//			}
//			
//			j=0;
//			for ( var Train in listeTrain) {
//				trainLigneId = Train.ligne.idLigne;
//				trainId =Train.idTrain;
//				//stationName =<c:out value="${Station.nomStation}"/>;
//
//				if(trainLigneId==ligneidentifiant){
//				//alert(trainLigneId);
//				document.formAfficher.idTrain[j] = new Option(trainId);
//				j=j+1;
//				}
//			}
		}//fin fonction loadStationTrain
