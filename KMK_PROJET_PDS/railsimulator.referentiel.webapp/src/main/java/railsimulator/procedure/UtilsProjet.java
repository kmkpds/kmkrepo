package railsimulator.procedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Action;
import beans.BiLigne;
import beans.Incident;
import beans.Procedure;
import beans.TypeIncident;

public class UtilsProjet {

	public static List<Incident> getAllIncident() {
		List<Incident> listresultat = new ArrayList<Incident>();
		try {
			BDD bdd = BDD.getBdd();
			Statement unStat = bdd.getMaConnection().createStatement();
			String sql = "select * from incident ;";
			System.out.println(sql);
			ResultSet unRes = unStat.executeQuery(sql);

			while (unRes.next()) {
				Incident p = new Incident();
				// idincident description datedebut mois annee
				// datefin criticite procedureintervention_idprocedure
				// type_idtype biligne_idbiligne
				p.setIdIncident(unRes.getInt("idincident"));
				p.setDescription(unRes.getString("description"));
				p.setDateDebut(unRes.getDate("datedebut").toString());
				p.setMois(unRes.getInt("mois"));
				p.setAnnee(unRes.getInt("annee"));
				p.setDateFin(unRes.getDate("datefin").toString());
				p.setCriticite(unRes.getString("criticite"));
				Procedure procedure = getProcedureById(unRes
						.getInt("procedureintervention_idprocedure"));
				p.setProcedure(procedure);
				TypeIncident typeIncident = getTypeIncidentById(unRes
						.getInt("type_idtype"));
				p.setTypeIncident(typeIncident);
				BiLigne biLigne = getBilingneById(unRes
						.getInt("biligne_idbiligne"));
				p.setBiLigne(biLigne);

				listresultat.add(p);
			}
		} catch (Exception e) {

		}
		return listresultat;

	}

	public static BiLigne getBilingneById(int int1) {
		BDD bdd = BDD.getBdd();
		Statement unStat;
		BiLigne biLigne = new BiLigne();
		try {
			unStat = bdd.getMaConnection().createStatement();
		
		String sql = "select * from biligne where idbiligne="+int1+" ;";
		System.out.println(sql);

		ResultSet unRes = unStat.executeQuery(sql);

		while (unRes.next()) {
			biLigne.setIdBiLigne(unRes.getInt("idbiligne"));
			biLigne.setNomBiLigne(unRes.getString("nombiligne"));
			return biLigne;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return biLigne;
	}

	public static TypeIncident getTypeIncidentById(int int1) {
		BDD bdd = BDD.getBdd();
		Statement unStat;
		TypeIncident typeIncident = new TypeIncident();
		try {
			unStat = bdd.getMaConnection().createStatement();
		
		String sql = "select * from type_incident where idtype="+int1+" ;";
		System.out.println(sql);

		ResultSet unRes = unStat.executeQuery(sql);

		while (unRes.next()) {
			typeIncident.setIdType(unRes.getInt("idtype"));
			typeIncident.setLibelleType(unRes.getString("libelletype"));
			return typeIncident;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typeIncident;
	}

	public static Procedure getProcedureById(int int1) {
		BDD bdd = BDD.getBdd();
		Statement unStat;
		Procedure procedure = new Procedure();
		try {
			unStat = bdd.getMaConnection().createStatement();
		
		String sql = "select * from procedureintervention where idprocedure="+int1+" ;";
		System.out.println(sql);

		ResultSet unRes = unStat.executeQuery(sql);

		while (unRes.next()) {
			procedure.setIdProcedure(unRes.getInt("idprocedure"));
			procedure.setLibelleProcedure(unRes.getString("libelleprocedure"));
			return procedure;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return procedure;
	}
	
	public static Incident getIncidentById(int id) {
		List<Incident> incidents= UtilsProjet.getAllIncident();
		Incident incident=null;
		for(int i=0;i<incidents.size();i++)
		{
			if(id == incidents.get(i).getIdIncident())
			{
				incident = incidents.get(i);
			}
		}

		return incident;
	}
	
	public static List<String> getAllActionLibelle()
	{
		List<String> listresultat = new ArrayList<String>();
		try {
			BDD bdd = BDD.getBdd();
			Statement unStat = bdd.getMaConnection().createStatement();
			String sql = "select distinct libelleactionintervention from actionintervention ;";
			System.out.println(sql);
			ResultSet unRes = unStat.executeQuery(sql);

			while (unRes.next()) {
				listresultat.add(unRes.getString("libelleactionintervention"));
			}
		} catch (Exception e) {

		}
		return listresultat;

		
	
	}
	public static List<Action> getAllAction()
	{

		List<Action> listresultat = new ArrayList<Action>();
		try {
			BDD bdd = BDD.getBdd();
			Statement unStat = bdd.getMaConnection().createStatement();
			String sql = "select * from actionintervention ;";
			System.out.println(sql);
			ResultSet unRes = unStat.executeQuery(sql);

			while (unRes.next()) {
				Action p = new Action();
				p.setIdActionIntervention(unRes.getInt("idactionintervention"));
				p.setLibelleActionIntervention(unRes.getString("libelleactionintervention"));
				p.setDateDebut(unRes.getDate("datedebut").toString());
				p.setMois(unRes.getInt("mois"));
				p.setAnnee(unRes.getInt("annee"));
				p.setDateFin(unRes.getDate("datefin").toString());
				p.setStatut(unRes.getString("statut"));
				Procedure procedure = getProcedureById(unRes
						.getInt("procedureintervention_idprocedure"));
				p.setProcedureAction(procedure);

				listresultat.add(p);
			}
		} catch (Exception e) {

		}
		return listresultat;

		
	}
}
