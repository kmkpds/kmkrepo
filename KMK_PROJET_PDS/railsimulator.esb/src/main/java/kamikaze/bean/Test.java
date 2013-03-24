package kamikaze.esb.bean;

import java.util.ArrayList; 
import java.util.List;

import kamikaze.esb.jms.GenerateXml;
import kamikaze.esb.jms.JMSReaderM2P;
import kamikaze.esb.jms.JMSWriterP2M;
import kamikaze.esb.model.Capteur;
import kamikaze.esb.model.EnvoyerEventToServiceMix;
import kamikaze.esb.model.Event;
import kamikaze.esb.model.EventType;
import kamikaze.esb.model.Wagon;

public class Test {

	/**
	 * @param args
	 */

	
	
	@SuppressWarnings("null")
	public static void main(String[] args) {
		//JMSReaderM2P reader=new JMSReaderM2P();
		JMSWriterP2M writer=new JMSWriterP2M();
		//GenerateXml xml;
		//Object obj = reader;
		
		EnvoyerEventToServiceMix envoy=new EnvoyerEventToServiceMix();
		Event event=new Event();
		event.setIdEvent(1);
		event.setCriticite(1);
		event.setLibelle("eventlibelle");
			EventType et=new EventType();
			et.setIdEventType(2);
			et.setLibelle("eventtypelibelle");
		event.setType(et);
			Capteur cap=new Capteur();
			cap.setIdCapteur(1);
			cap.setLatitude(43.93);
			cap.setLongitude(1.90);
				List<Object> ligneOrWagonOrTrain = null;
			cap.setLigneOrWagonOrTrain(ligneOrWagonOrTrain);
				Wagon w= new Wagon();
				w.setIdWagon(1);
					kamikaze.esb.model.Train t=new kamikaze.esb.model.Train();
						 List<Capteur> listec=new ArrayList<Capteur>();
						 Capteur cap3= new Capteur();
						 	cap3.setIdCapteur(3);
							cap3.setLatitude(9.77);
							cap3.setLongitude(9.67);
						 Capteur cap2= new Capteur();
						 	cap2.setIdCapteur(2);
							cap2.setLatitude(3.67);
							cap2.setLongitude(3.67);
						 listec.add(cap3);
						 listec.add(cap2);
					t.setIdTrain(1);
					t.setNomTrain("train1");
					t.setCapteur(listec);
					kamikaze.esb.model.TypeWagon tw =new kamikaze.esb.model.TypeWagon();
					tw.setIdTypeWagon(22);
					tw.setMoteur(true);
				w.setTrain(t);
				w.setTypewagon(tw);
				w.setCapteur(listec);
			cap.getLigneOrWagonOrTrain().add(w);
		event.setCapteur(cap);
		
		envoy.setMess(event);
		
		writer.SendFile(envoy);
		//xml.genererFile(event);	
	}

}
