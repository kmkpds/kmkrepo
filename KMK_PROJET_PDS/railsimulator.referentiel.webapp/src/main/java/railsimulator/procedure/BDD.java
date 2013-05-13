package railsimulator.procedure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDD {
	private String serveur, user, mdp, bdd;
	Connection maConnection;
	public Connection getMaConnection() {
		return maConnection;
	}
	static BDD mabase;
	
	private BDD(String serveur, String user, String mdp, String bdd)
	{
		this.serveur = serveur;
		this.user = user;
		this.mdp = mdp;
		this.bdd = bdd;
	}
	private BDD()
	{
		this.serveur = "178.33.40.163:3307";
		this.user = "admin";
		this.mdp = "admin";
		this.bdd = "railsimulator";
		seConnecter();
	}
	private void chargerPilote () 
	{
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	 void seConnecter ()
	{
		//ouverture d'une connexion au serveur Mysql 
		this.chargerPilote();
		String url = "jdbc:mysql://"+this.serveur +"/"+this.bdd;
		try{
		this.maConnection = DriverManager.getConnection(url,this.user, this.mdp);
		}
		catch (SQLException exp) {
			System.err.println("Erreur de connexion au serveur " + exp);
		}
	}
	public void seDeconnecter()
	{
		if (this.maConnection !=null)
		{
			try{
				this.maConnection.close();
			}
			catch (SQLException exp){
			//	System.out.println("Erreur lors de la fermeture de la connexion");
			}
		}
	}
	public static  BDD getBdd()
	{
		if(mabase==null)
		{
			mabase = new BDD();
		}
		return mabase;
	}
	

}
