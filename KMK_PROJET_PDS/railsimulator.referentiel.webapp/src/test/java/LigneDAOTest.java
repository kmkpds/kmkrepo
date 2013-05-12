

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtils;
import dao.LigneDAO;
import beans.Ligne;
import beans.Reseau;
import beans.Station;


import junit.framework.TestCase;



public class LigneDAOTest extends TestCase {

	private Session se = null;
	
	
	private Ligne ligne = new Ligne();
	private Ligne ligne2 = new Ligne();
	private Reseau reseau = new Reseau();
	private Station station=new Station();
	private List<Ligne> listeLigne;
	
	private LigneDAO ligne_dao = new LigneDAO();
	
	boolean test;
	
	

	
	

	public void testCreateLigne(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction(); 
		
		//Purge de la table ligne & reseau base de donn�e de test
		Query delete=se.createQuery("delete from Ligne");
		Query delete2=se.createQuery("delete from Reseau");		
		delete.executeUpdate();
		delete2.executeUpdate();
		t.commit();
		
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
	    t2.commit();
	    se.flush();

		//Cr�ation d'une ligne par la methode createLigne
		ligne_dao.createLigne("Ligne Alpha", "Ligne Alpha",reseau);
 
		//Test visant a v�rifier la bonne insertion
		Query requete = se.createQuery("select count(*) from Ligne where nomLigne='Ligne Alpha' and commentaire='Ligne Alpha'");
 
		//le count doit retourner 1
		assertEquals("1",requete.uniqueResult().toString()); 

		//nouvelle purge de la table Ligne & reseau
		Transaction t3 = se.beginTransaction();
		Query delete3=se.createQuery("delete from Ligne");		
		delete3.executeUpdate();
		Query delete4=se.createQuery("delete from Reseau");		
		delete4.executeUpdate();
		t3.commit();
		se.close();

	}

	

	public void testSupprimerLigne(){


		
		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		
		//Purge de la table ligne & reseau base de donn�e de test
		Query delete=se.createQuery("delete from Ligne");
		Query delete2=se.createQuery("delete from Reseau");		
		delete.executeUpdate();
		delete2.executeUpdate();
		t.commit();
				
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t2.commit();
		se.flush();
		
		//Cr�ation d'une ligne
		Transaction t3 = se.beginTransaction();
		ligne.setNomLigne("Ligne Alpha");
		ligne.setCommentaire("Ligne Alpha");
		ligne.setReseau(reseau);
		se.save(ligne);		
		t3.commit();
		
		//R�cup�ration de l'identifiant nouvellement cr�er
		se.flush();		
		int id=ligne.getIdLigne();

		//Suppression de la ligne cr�er par la methode supprimer Ligne
		ligne_dao.supprimerLigne(id);

		//V�rification de la suppression
		Query requete = se.createQuery("select count(*) from Ligne where idLigne="+id);
        
		//Le count doit retourner 0
		assertEquals("0",requete.uniqueResult().toString());
		
		
		
		//nouvelle purge de la table Ligne & reseau
		Transaction t4 = se.beginTransaction();
		Query delete3=se.createQuery("delete from Reseau");		
		delete3.executeUpdate();
		t4.commit();
		se.close();
		


	}

	
	

	public void testListerLigne(){


		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		
		//Purge de la table ligne & reseau base de donn�e de test
		Query delete=se.createQuery("delete from Ligne");
		Query delete2=se.createQuery("delete from Reseau");		
		delete.executeUpdate();
		delete2.executeUpdate();
		t.commit();
		
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t2.commit();
		se.flush();


        //Insertion de 2 nouvelles ligne
		Transaction t3 = se.beginTransaction();
		ligne.setNomLigne("Ligne Alpha");
		ligne.setCommentaire("Ligne Alpha");
		ligne.setReseau(reseau);
		se.save(ligne);	

		ligne2.setNomLigne("Ligne Beta");
		ligne2.setCommentaire("Ligne Beta");
		ligne2.setReseau(reseau);
		se.save(ligne2);	
		t3.commit();
        
		
		//R�cup�ration de la liste de ligne par la m�thode listerLigne
		listeLigne= ligne_dao.listerLigne();

		//V�rification du nombre de r�sultat (2)
		if (listeLigne.size()==2){
			//V�rification des donn�es r�cup�r�es
			if(listeLigne.get(0).getNomLigne().equals("Ligne Alpha") && listeLigne.get(0).getCommentaire().equals("Ligne Alpha") && listeLigne.get(1).getNomLigne().equals("Ligne Beta") && listeLigne.get(1).getCommentaire().equals("Ligne Beta")){
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

		
		//nouvelle purge de la table Ligne & reseau
		Transaction t4 = se.beginTransaction();
		Query delete3=se.createQuery("delete from Ligne");		
		Query delete4=se.createQuery("delete from Reseau");		
		delete3.executeUpdate();
		delete4.executeUpdate();		
		t4.commit();
		se.close();

	}

	
	

	public void testGetLigneByID(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		
		//Purge de la table ligne & reseau base de donn�e de test
		Query delete=se.createQuery("delete from Ligne");
		Query delete2=se.createQuery("delete from Reseau");		
		delete.executeUpdate();
		delete2.executeUpdate();
		t.commit();
		
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t2.commit();
		se.flush();
		
		//Cr�ation d'une ligne
		Transaction t3 = se.beginTransaction();
		ligne.setNomLigne("Ligne Alpha");
		ligne.setCommentaire("Ligne Alpha");
		ligne.setReseau(reseau);
		se.save(ligne);		
		t3.commit();
		
		//R�cup�ration de l'identifiant nouvellement cr�er
		se.flush();
		int id=ligne.getIdLigne();

		//R�cup�ration d'un objet Ligne par la m�thode getLigneByID
		ligne = ligne_dao.getLigneByID(id); 
		
		//V�rification des donn�es r�cup�r�es
		if(ligne.getNomLigne().equals("Ligne Alpha") && ligne.getCommentaire().equals("Ligne Alpha")){
			test=true; 
		}
		else{
			test=false;
		}

		assertTrue(test); 

		
		//nouvelle purge de la table Ligne & reseau
		Transaction t4 = se.beginTransaction();
		Query delete3=se.createQuery("delete from Ligne");		
		Query delete4=se.createQuery("delete from Reseau");		
		delete3.executeUpdate();
		delete4.executeUpdate();		
		t4.commit();
		se.close();
	}
	
	

	public void testModifierLigne(){

		se = HibernateUtils.getSession();
		Transaction t = se.beginTransaction();
		
		//Purge de la table ligne & reseau base de donn�e de test
		Query delete=se.createQuery("delete from Ligne");
		Query delete2=se.createQuery("delete from Reseau");		
		delete.executeUpdate();
		delete2.executeUpdate();
		t.commit();
		
		//cr�ation d'un reseau test
		Transaction t2 = se.beginTransaction();
		reseau.setNomReseau("reseau 1");
		se.save(reseau);		
		t2.commit();
		se.flush();
		
		//Cr�ation d'une ligne
		Transaction t3 = se.beginTransaction();
		ligne.setNomLigne("Ligne Alpha");
		ligne.setCommentaire("Ligne Alpha");
		ligne.setReseau(reseau);
		se.save(ligne);		
		t3.commit();
        
		//R�cup�ration de l'identifiant nouvellement cr�er
		se.flush();
		int id=ligne.getIdLigne();

		//Modification de l'objet Ligne par la m�thode modifierLigne
		ligne.setIdLigne(id);
		ligne.setNomLigne("Ligne Beta");
		ligne.setCommentaire("Ligne Beta");
		ligne_dao.modifierLigne(ligne);



		//R�cuperation de la ligne modifi�e
		ligne = (Ligne) se.createQuery("from Ligne where idLigne="+id).uniqueResult();
		
		

		//V�rification des modifications
		if(ligne.getNomLigne().equals("Ligne Beta") && ligne.getCommentaire().equals("Ligne Beta")){
			test=true; 
		}
		else{
			test=false;
		}
		
		assertTrue(test);
		
		//nouvelle purge de la table Ligne & reseau
		Transaction t4 = se.beginTransaction();
		Query delete3=se.createQuery("delete from Ligne");		
		Query delete4=se.createQuery("delete from Reseau");		
		delete3.executeUpdate();
		delete4.executeUpdate();		
		t4.commit();
		se.close();

	}
	
	
   
   
 
}
