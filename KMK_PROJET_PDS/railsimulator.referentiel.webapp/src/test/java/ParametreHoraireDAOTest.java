
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Ligne;
import beans.ParametreHoraire;
import beans.Zone;
import dao.HibernateUtils;
import dao.LigneDAO;
import dao.ParametreHoraireDAO;
import dao.ReseauDAO;
import beans.Reseau;
public class ParametreHoraireDAOTest extends TestCase {

	private Session se = null;
	  private ParametreHoraire parametreHoraire;
	  private List<ParametreHoraire> parametreHoraireList;
	  private Ligne ligne = new Ligne();
	  private LigneDAO ligneDAO = new LigneDAO();
	  private List<ParametreHoraire> listeParametreHoraire;
	  boolean test;
	  private Reseau reseau = new Reseau();
	  private ReseauDAO reseauDAO = new ReseauDAO();

		public  void testCreateParametreHoraire(){
			se = HibernateUtils.getSession();
			
			Transaction t1 = se.beginTransaction();
			Query create1=se.createSQLQuery("delete from parametrehoraire");
			create1.executeUpdate();
			t1.commit();
			
			Transaction t2 = se.beginTransaction();
			Query create2=se.createSQLQuery("delete from trainhorairestation");
			create2.executeUpdate();
			t2.commit();
			
			Transaction t3 = se.beginTransaction();
			Query create3=se.createSQLQuery("delete from train");
			create3.executeUpdate();
			t3.commit();
			
			Transaction t4 = se.beginTransaction(); 
			Query create4=se.createSQLQuery("delete from station_has_station");
			create4.executeUpdate();
			t4.commit();
			
			Transaction t5 = se.beginTransaction();
			Query create5=se.createSQLQuery("delete from station");
			create5.executeUpdate();
			t5.commit();
			
			Transaction t6 = se.beginTransaction();
			Query create6=se.createSQLQuery("delete from ligne");
			create6.executeUpdate();
			t6.commit();
			
			Transaction t7 = se.beginTransaction();
			Query create7=se.createSQLQuery("delete from lieu");
			create7.executeUpdate();
			t7.commit();
			
			Transaction t8 = se.beginTransaction();
			Query create8=se.createSQLQuery("delete from geolocalisation");
			create8.executeUpdate();
			t8.commit();
			
			Transaction t9 = se.beginTransaction();
			Query create9=se.createSQLQuery("delete from zone");
			create9.executeUpdate();
			t9.commit();
			
			Transaction t10 = se.beginTransaction();
			Query create10=se.createSQLQuery("delete from reseau");
			create10.executeUpdate();
			t10.commit();

			//création d'un reseau test
			Transaction t11 = se.beginTransaction();
			reseau.setNomReseau("reseau 1");
			se.save(reseau);
			//reseau =  reseauDAO.getReseauByID(idReseau);
			t11.commit();
			se.flush();
			//crÈation d'une ligne test
			Transaction t12 = se.beginTransaction();
			//ligne.setNomLigne("TestUnitaireLigne");
			//ligne.setReseau(reseau);
			//ligne.setCommentaire("test");
			//int idLigne = (Integer) se.save(ligne);	
			ligneDAO.createLigne("TestUnitaireLigne", "Test Com", reseau);
			int idLigne = ligneDAO.listerLigne().get(0).getIdLigne();
			ligne = ligneDAO.getLigneByID(idLigne);
			t12.commit();
			se.flush();
			ParametreHoraireDAO parametreHoraire_DAO = new ParametreHoraireDAO();
			parametreHoraire_DAO.createParametreHoraire(ligne, "05:05:00", "05:05:00", "05:05:00", "06:06:00", "06:06:00", "06:06:00", "07:07:00", "07:07:00", "07:07:00", "heuresPointeJO910", "heuresPointeJO910", "heuresPointeJO910", "08:08:00", "08:08:00", "08:08:00", 80);

			//Test visant a vérifier la bonne insertion
			Query requete = se.createQuery("select count(*) from ParametreHoraire where idLigne="+ idLigne +" and heurePremierTrainJO='05:05:00' and heurePremierTrainSamedi='05:05:00'  and heurePremierTrainDimancheJF='05:05:00' and heureDernierTrainJO='06:06:00' and heureDernierTrainSamedi='06:06:00'  and heureDernierTrainDimancheJF='06:06:00' and cadencementJO= '07:07:00' and cadencementSamedi= '07:07:00' and cadencementDimancheJF= '07:07:00' and heuresPointeJO ='heuresPointeJO910' and heuresPointeSamedi ='heuresPointeJO910' and heuresPointeDimancheJF ='heuresPointeJO910' and tempsStationnementJO='08:08:00' and tempsStationnementSamedi='08:08:00' and tempsStationnementDimancheJF='08:08:00' and vitesseMoyenne=80");
			//le count doit retourner 1
			assertEquals("1",requete.uniqueResult().toString()); 
			
			//nouvelle purge de la table Ligne & ParametreHoraire
			Transaction t13 = se.beginTransaction();
			Query delete13=se.createQuery("delete from ParametreHoraire");		
			delete13.executeUpdate();
			Query delete14=se.createQuery("delete from Ligne");		
			delete14.executeUpdate();	
			t13.commit();
			se.close();
		}

		public  void testListerParametreHoraire(){
			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction(); 

			Query delete=se.createQuery("delete from ParametreHoraire");
			Query delete2=se.createQuery("delete from Ligne");		
			delete.executeUpdate();
			delete2.executeUpdate();
			t.commit();
			//création d'un reseau test
			Transaction t2 = se.beginTransaction();
			reseau.setNomReseau("reseau 1");
			se.save(reseau);
			//reseau =  reseauDAO.getReseauByID(idReseau);
			t2.commit();
			se.flush();
			//création d'une ligne test
			Transaction t3 = se.beginTransaction();
			ligneDAO.createLigne("TestUnitaireLigne", "Test Com", reseau);
			int idLigne = ligneDAO.listerLigne().get(0).getIdLigne();
			ligne = ligneDAO.getLigneByID(idLigne);
			t3.commit();
			se.flush();
			ParametreHoraireDAO parametreHoraire_DAO = new ParametreHoraireDAO();
			int idParametreHoraire1 = parametreHoraire_DAO.createParametreHoraireReturnId(ligne, "05:05:00", "05:05:00", "05:05:00", "06:06:00", "06:06:00", "06:06:00", "07:07:00", "07:07:00", "07:07:00", "heuresPointeJO910", "heuresPointeJO910", "heuresPointeJO910", "08:08:00", "08:08:00", "08:08:00", 80);
			int idParametreHoraire2 =parametreHoraire_DAO.createParametreHoraireReturnId(ligne, "06:06:00", "06:06:00", "06:06:00", "07:07:00", "07:07:00", "07:07:00", "08:08:00", "08:08:00", "08:08:00", "heuresPointeJO1011", "heuresPointeJO1011", "heuresPointeJO1011", "09:09:00", "09:09:00", "09:09:00", 90);
			//Récupération de la list
			listeParametreHoraire = parametreHoraire_DAO.listerParametreHoraire();
			//Vérification du nombre de résultat (2)
			if (listeParametreHoraire.size()==2){
				//VÈrification des donnÈes rÈcupÈrÈes
				if(listeParametreHoraire.get(0).getLigne().getIdLigne()==idLigne && listeParametreHoraire.get(0).getIdparametrehoraire()==idParametreHoraire1  && listeParametreHoraire.get(1).getIdparametrehoraire()==idParametreHoraire2 && listeParametreHoraire.get(1).getLigne().getIdLigne()==idLigne ){
					test=true; 
				}
				else{
					test=false;
				}
			}
			else{
				test= false;
			}
			assertTrue(test); 
			
			//nouvelle purge de la table Ligne & ParametreHoraire
			Transaction t4 = se.beginTransaction();
			Query delete4=se.createQuery("delete from ParametreHoraire");		
			delete4.executeUpdate();
			Query delete3=se.createQuery("delete from Ligne");		
			delete3.executeUpdate();
			t4.commit();
			se.close();
		}

		public  void testgetParametreHoraireByID(){
			se = HibernateUtils.getSession();
			Transaction t = se.beginTransaction(); 

			Query delete=se.createQuery("delete from ParametreHoraire");
			Query delete2=se.createQuery("delete from Ligne");		
			delete.executeUpdate();
			delete2.executeUpdate();
			t.commit();
			//création d'un reseau test
			Transaction t2 = se.beginTransaction();
			reseau.setNomReseau("reseau 1");
			se.save(reseau);
			//reseau =  reseauDAO.getReseauByID(idReseau);
			t2.commit();
			se.flush();			//création d'une ligne test
			Transaction t3 = se.beginTransaction();
			ligneDAO.createLigne("TestUnitaireLigne", "Test Com", reseau);
			int idLigne = ligneDAO.listerLigne().get(0).getIdLigne();
			ligne = ligneDAO.getLigneByID(idLigne);
			t3.commit();
			se.flush();
			ParametreHoraireDAO parametreHoraire_DAO = new ParametreHoraireDAO();
			int idParametreHoraire =parametreHoraire_DAO.createParametreHoraireReturnId(ligne, "05:05:00", "05:05:00", "05:05:00", "06:06:00", "06:06:00", "06:06:00", "07:07:00", "07:07:00", "07:07:00", "heuresPointeJO910", "heuresPointeJO910", "heuresPointeJO910", "08:08:00", "08:08:00", "08:08:00", 80);
			//Récupération de l'objet parametreHoraire
			parametreHoraire = parametreHoraire_DAO.getParametreHoraireByID(idParametreHoraire);
			//Vérification du nombre de résultat (2)
			if(parametreHoraire.getIdparametrehoraire()== idParametreHoraire && parametreHoraire.getLigne().getIdLigne()==idLigne){
				test=true; 
			}
			else{
				test=false;
			}
			assertTrue(test); 
			//nouvelle purge de la table Ligne & ParametreHoraire
			Transaction t4 = se.beginTransaction();
			Query delete4=se.createQuery("delete from ParametreHoraire");		
			delete4.executeUpdate();
			Query delete3=se.createQuery("delete from Ligne");		
			delete3.executeUpdate();	
			t4.commit();
			se.close();
		}
}
