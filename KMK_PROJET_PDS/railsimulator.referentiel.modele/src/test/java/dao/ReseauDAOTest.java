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
		
		//Purge de la table  reseau base de donn�e de test
		Query delete=se.createQuery("delete from Reseau");		
		delete.executeUpdate();		
		t.commit();
		


		//Cr�ation d'une reseau par la methode createReeau
		reseau_dao.createReseau("Reseau Alpha");
 
		//Test visant a v�rifier la bonne insertion
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
		
		//Purge de la table  reseau base de donn�e de test
		Query delete=se.createQuery("delete from Reseau");		
		delete.executeUpdate();		
		t.commit();
		
		
		//Cr�ation d'un Reseau
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("Reseau Alpha");			
		se.save(reseau);		
		t2.commit();
		
		se.flush();
		int id=reseau.getIdReseau();

		//R�cup�ration d'un objet Ligne par la m�thode getLigneByID
		reseau = reseau_dao.getReseauByID(id); 
		
		//V�rification des donn�es r�cup�r�es
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
		
		//Purge de la table  reseau base de donn�e de test
		Query delete=se.createQuery("delete from Reseau");		
		delete.executeUpdate();		
		t.commit();
		
		
		//Cr�ation de deux Reseau
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("Reseau Alpha");			
		se.save(reseau);
		reseau2.setNomReseau("Reseau Beta");			
		se.save(reseau2);
		t2.commit();
		
	
		//R�cup�ration de la liste de ligne par la m�thode listerLigne
		listeReseau= reseau_dao.listerReseau();
		
		if (listeReseau.size()==2){
			//V�rification des donn�es r�cup�r�es
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
