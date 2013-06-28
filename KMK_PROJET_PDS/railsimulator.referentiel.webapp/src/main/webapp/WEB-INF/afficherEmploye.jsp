<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="beans.HoraireP"%>
<%@page import="beans.FactHoraire"%>
<%@page import="beans.Employe"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% Map< Integer,FactHoraire >listStatus=( Map< Integer,FactHoraire >)request.getAttribute("listStatus");
List <Employe > listeEmploye=( List<Employe>)request.getAttribute("listeEmploye");

Map <Integer,HoraireP > mapHoraireP=(Map <Integer,HoraireP>)request.getAttribute("mapHoraireP");
int currentEmpCounter=0;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<link rel="stylesheet" type="text/css" href="css/corp.css" />
		<link rel="alternate stylesheet" type="text/css" href="css/print.css"media="screen" title="Version imprimable" id="stylesheet-print" />
		<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
		<title>Référentiel Employe - Rail Simulator</title>
		<script type="text/javascript" src="js/main.js"></script>
	</head>
	<body>
		<!-- Page Layout -->
		<table id="RnoPage" class="RnoLayout-1col">
			<tr>
				<td id="RnoPageWidthRange">
					<!-- Global Links Top -->
					<div id="RnoGlobalLinksTop" class="sc">
						<div>
							<ul>
								<li>
									<a href="#">Manuel</a>
								</li>
								<li>
									<a href="#">Demande d'évolution</a>
								</li>
							</ul>
						</div>
					</div> <!-- /Global Links Top --> <!-- Page Header -->
					<div id="RnoBranding" class="sc">
						<a href="#">
							<img src="images/logos/kmk-rail.jpg" id="RnoLogo" alt="Kamikaze" width="63" height="63" />
						</a>
						<h1 id="RnoApplicationName">
							<img src="images/header/kmk-header.jpg" alt="KAMIKAZE PROJECT - Rail Simulator" />
						</h1>
						<span>
							<img src="images/branding-coin.gif" alt="" width="31" height="31" />
						</span>
					</div> 
					<!-- /Page Header --> <jsp:include page="menuRefEmploye.jsp" /> <!-- Body Content -->
					<div id="RnoBody">
						<!-- Middle Content -->
						<div id="RnoMainContent" class="sc">
							<!-- Page Title -->
							<h2 id="RnoPageTitle">
								<a></a>Gestion du personnel
							</h2>
							<!-- /Page Title -->
							<!-- Bordered Workspace -->
							<div id="RnoWorkspace">
								<!-- Section -->
								<div class="RnoSection">
									<h3 class="RnoSectionTitle">
										<span></span>Liste du personnel par site
										
									</h3>					
									<div class="RnoSectionContent">
										<div class="RnoDataTable">
											<table>
																
												<tr class="RnoMainHeader">
													
													<th>
														<a href="#">Nom</a>
													</th>
													<th>
														<a href="#">Prenom</a>
													</th>
													<th>
														<a href="#">Fonction</a>
													</th>
													
													<th>
														<a href="#">Site</a>
													</th>
													<th>
														<a href="#">Heure debut previsionnelle </a>
													</th>
													<th>
														<a href="#">Heure fin previsionnelle</a>
													</th>
													
													<th>
														<a href="#">Detail</a>
													</th>
													
													<th>
														<a href="#">Status</a>
													</th>
																																			
													
													
													
													
												</tr>
												<c:forEach items="${listeEmploye}" var="list">
													<tr>
														<td>${list.nom}</td>
														<td>${list.prenom}</td>
														<td>${list.fonction}</td>
														
														<td>${list.getSite().getLibellesite()}</td>
														<td>${list.getHorairep().getHeured()}</td>
														<td>${list.getHorairep().getHeuref()}</td>
														
														<td>
														       
														<center> 
																<a href="/kmk_ref_webapp/afficherEmploye?action=tache&id=${list.idemp}">
																	<img alt="update" src="images/updateicon.gif" width="20" height="20">
																</a>
														
														</center>
														</td>
												
													
												
													
													 <td>
													 <% 
													FactHoraire f=listStatus.get(listeEmploye.get(currentEmpCounter).getIdemp());
													Employe emp=listeEmploye.get(currentEmpCounter);
													
													System.out.println("emp "+emp.getIdemp());
													HoraireP hp=mapHoraireP.get(emp.getHorairep_id());
													if(f!=null)
														System.out.println(" f "+hp.getHeuref());
													 if(f==null)
														out.print("Absent");
													else
													{
														String heuref=f.getHeuref();
														
														if(heuref.equals("00:00:00"))
														{
															String horaired=f.getHeured();
															String horairedPrev= hp.getHeured();
															
															
															Timestamp timestampd=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd")
															.format(new Date()).
															concat(" "+ horaired));
															
															Timestamp timestampdPrev=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd")
															.format(new Date ()).
															concat(" "+ horairedPrev));
															if(timestampdPrev.equals(timestampd))
																out.print("En poste, arrivée à l'heure");
															else if(timestampd.after(timestampdPrev))
																out.print("En poste, arrivée en retard");
															else if(timestampd.before(timestampdPrev))
																out.print("En poste, arrivée en avance");
															
														}
														else
														{
															System.out.println("hana");
															String horairef=f.getHeuref();
															String horairefPrev= hp.getHeuref();
															System.out.println("hf" +horairef+"   horairefff"+horairef);
															Timestamp timestampf=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).concat(" "+ horairef));
															
															Timestamp timestampfPrev=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd")
															.format(new Date ()).
															concat(" "+ horairefPrev)); 
															
															if(timestampfPrev.equals(timestampf))
																out.print("Absent , sortie à l'heure"); 
															
															else if(timestampf.before(timestampfPrev))
																out.print("Absent , sortie avant l'heure");
														}
													}
													%>
													 	 
														
													    	
													   	</td>

													
													
													<% currentEmpCounter++; 
													
													%>
														</tr>
												</c:forEach>
											</table>
										</div>
									</div>
								</div>
							</div>
							<!-- /Page Level Buttons Steps -->
						</div>
						<!-- /Vertical Steps Right -->
					</div> <!-- /Vertical Steps Layout -->
				</td>
			</tr>
		</table>
		<!-- /Page Layout -->
		
		<script type="text/javascript">

        var auto_refresh = setInterval(
		function ()
		{
  		 $('#RnoBody').load('ajax');
		}, 2000); 

		</script>
	</body>
</html>


