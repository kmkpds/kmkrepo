package mock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import beans.FactHoraire;
import dao.FactHoraireDAO;

public class Mock2Emp18SortieAvantHeure  extends TimerTask {


	private static FactHoraire facthoraire ;
	private static FactHoraireDAO fact_dao = new FactHoraireDAO();
	
	
	public void run(){
		Integer idemp = null ;
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String dateh=dateFormat.format(date).toString();
		String  heured = null, heuref=null;
				
				idemp= 18;
			//	dateh ="2013-07-01";
				heured="04:45:00";
				heuref="11:40:00";
			
			
				System.out.println("avant object ");
			
			   facthoraire =new FactHoraire(idemp,dateh,heured,heuref);
			   fact_dao.createFactHoraire(facthoraire);
			
			
	}
		
}
