package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import beans.Reseau;
import dao.HibernateUtils;
import dao.ReseauDAO;
import junit.framework.TestCase;

public class ReseauDAOTest extends TestCase {
	
	private Session se = null;
	private List<Reseau> listeReseau;
	private Reseau reseau = new Reseau() ;
	private Reseau reseau2 = new Reseau() ;
	
	private ReseauDAO reseau_dao = new ReseauDAO();
	
	boolean test;

	
	
	public void testCreateLigne(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		//Purge de la table  reseau base de donnée de test
		Query delete=se.createQuery("delete from Reseau");		
		delete.executeUpdate();		
		t.commit();
		


		//Création d'une reseau par la methode createReeau
		reseau_dao.createReseau("Reseau Alpha");
 
		//Test visant a vérifier la bonne insertion
		Query requete = se.createQuery("select count(*) from Reseau where nomReseau='Reseau Alpha' ");
 
		//le count doit retourner 1
		assertEquals("1",requete.uniqueResult().toString()); 

		//nouvelle purge de la table reseau
		Transaction t2 = se.beginTransaction();
		Query delete2=se.createQuery("delete from Reseau");		
		delete2.executeUpdate();
		t2.commit();
		se.close();

	}
	
	public void testGetReseauByID(){
		
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		//Purge de la table  reseau base de donnée de test
		Query delete=se.createQuery("delete from Reseau");		
		delete.executeUpdate();		
		t.commit();
		
		
		//Création d'un Reseau
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("Reseau Alpha");			
		se.save(reseau);		
		t2.commit();
		
		se.flush();
		int id=reseau.getIdReseau();

		//Récupération d'un objet Ligne par la méthode getLigneByID
		reseau = reseau_dao.getReseauByID(id); 
		
		//Vérification des données récupérées
		if(reseau.getNomReseau().equals("Reseau Alpha") ){
			test=true; 
		}
		else{
			test=false;
		}

		assertTrue(test); 
		
		
		//nouvelle purge de la table reseau
		Transaction t3 = se.beginTransaction();
		Query delete2=se.createQuery("delete from Reseau");		
		delete2.executeUpdate();
		t3.commit();
		se.close();
		
	}
	
	public void testListerReseau(){
		
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		//Purge de la table  reseau base de donnée de test
		Query delete=se.createQuery("delete from Reseau");		
		delete.executeUpdate();		
		t.commit();
		
		
		//Création de deux Reseau
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("Reseau Alpha");			
		se.save(reseau);
		reseau2.setNomReseau("Reseau Beta");			
		se.save(reseau2);
		t2.commit();
		
	
		//Récupération de la liste de ligne par la méthode listerLigne
		listeReseau= reseau_dao.listerReseau();
		
		if (listeReseau.size()==2){
			//Vérification des données récupérées
			if(listeReseau.get(0).getNomReseau().equals("Reseau Alpha") && listeReseau.get(1).getNomReseau().equals("Reseau Beta")){
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

		
		
		
		
		//nouvelle purge de la table reseau
		Transaction t3 = se.beginTransaction();
		Query delete2=se.createQuery("delete from Reseau");		
		delete2.executeUpdate();
		t3.commit();
		se.close();
	}
	
	
	
}
