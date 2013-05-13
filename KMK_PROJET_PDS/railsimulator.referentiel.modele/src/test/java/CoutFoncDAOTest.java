package test;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.org.apache.bcel.internal.generic.NEW;

import beans.CoutFonctionnement;
import beans.Reseau;
import beans.Ligne;
import dao.HibernateUtils;
import dao.CoutFontionnementDAO;

public class CoutFoncDAOTest extends TestCase  {
	private Session se = null;


	private CoutFonctionnement cout = new CoutFonctionnement();
	
	private Ligne ligne = new Ligne();
	private Reseau reseau = new Reseau();
	private CoutFonctionnement coutFonctionnement = new CoutFonctionnement();
    private List<CoutFonctionnement> listeCoutFonctionnement;
	private CoutFontionnementDAO coutFonctionnement_dao=new CoutFontionnementDAO();

	boolean test;
	
	public void testCoutFonctionnementByID (){
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		
		//Purge de la table Cout Fonctionnement & ligne de la base de donnée de test
		Query delete=se.createQuery("delete from CoutFonctionnement");
		Query delete3=se.createQuery("delete from Ligne");
		Query delete4=se.createQuery("delete from Reseau");
		delete.executeUpdate();
		//delete2.executeUpdate();
		t.commit();
		
		
		//création d'un reseau test
		Transaction t1 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t1.commit();
		se.flush();
		
		
		//création d'une ligne test
		Transaction t2 = se.beginTransaction();
		ligne.setReseau(reseau);
		ligne.setNomLigne("ligne 14");
		ligne.setCommentaire("test");
		ligne.setLongueur(45);
		ligne.setNombredestation(15);
		ligne.setDureemoyennetrajet(30);
		ligne.setNombredetrain(15);
		ligne.setPrixdeplace(0.12);
		ligne.setNombrepassagers(50000);
		se.save(ligne);		
		t2.commit();
		se.flush();
		
		// Création d'un cout de fontionnement
		
		Transaction t3 = se.beginTransaction();
		coutFonctionnement.setConsommationenergie(2.03);
		coutFonctionnement.setEntretienmaterielroulant(570);
		coutFonctionnement.setTarificationdedeplacement(0.17);
		coutFonctionnement.setLigne(ligne);
		se.save(coutFonctionnement);
		t3.commit();
		
		//Récupération de l'identifiant nouvellement créer
		se.flush();
		int id=coutFonctionnement.getIdcoutfonct();
		
		//Récupération d'un objet Zone par la méthode getZoneByID
		coutFonctionnement = coutFonctionnement_dao.getCoutFonctionnementByID(id);
		
		
		//Vérification des données récupérées
		if(coutFonctionnement.getConsommationenergie()==2.03 && coutFonctionnement.getEntretienmaterielroulant()==570 && coutFonctionnement.getTarificationdedeplacement()==0.17 ){
			test=true; 
		}
		else{
			test=false;
		}

		assertTrue(test); 
		
		//nouvelle purge de la table zone & reseau
		Transaction t4 = se.beginTransaction();
		Query delete5=se.createQuery("delete from Zone");		
		Query delete6=se.createQuery("delete from Reseau");	
		Query delete7=se.createQuery("delete from Reseau");	
		delete5.executeUpdate();
		delete6.executeUpdate();
		delete7.executeUpdate();
		t4.commit();
		se.close();
		
		
	}
	
}
