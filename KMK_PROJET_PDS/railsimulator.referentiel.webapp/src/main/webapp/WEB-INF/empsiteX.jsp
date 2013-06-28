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
String mContextPath = request.getContextPath();
String idsite=(String) request.getAttribute("idsite");
%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<link rel="stylesheet" type="text/css" href="css/corp.css" />
		<link rel="alternate stylesheet" type="text/css" href="css/print.css"media="screen" title="Version imprimable" id="stylesheet-print" />
		<link rel="author" title="Auteur" href="http://www.sqliagency.com/" />
		<link rel="stylesheet" type="text/css" href="fullcalendar.css" />
	
		
		
		
		<title>Référentiel Employe - Rail Simulator</title>
		<script type="text/javascript" src="js/main.js"></script>
	</head>
	<body>
		<!-- Page Layout -->
		
		<table id="RnoPage" class="RnoLayout-1col" >
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
								<a></a>Référentiel Personnel
							</h2>
							<!-- /Page Title -->
							<!-- Bordered Workspace -->
							<div id="RnoWorkspace">
								<!-- Section -->
								<div class="RnoSection">
							
									<h3 class="RnoSectionTitle">
										<span></span>Personnels Sélectionnés 
										<input type="button" id="startMockBtn" value="Activer mock" />
										<input type="button" id="stopTimerBtn" value="Arreter Javascript Timer"/>
									</h3>	
									
									
												
									<div class="RnoSectionContent">
										<div class="RnoDataTable">
											<table>
												<tr class="RnoMainHeader">
													<th style="display:none;" class="first RnoSmallCell">
														<a style="display:none;" href="#">Identifiant</a>
													</th>
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
														<a href="#">Heure début</a>
													</th>
													<th>
														<a href="#">Heure fin</a>
													</th>
													
													<th>
														<a href="#">Status</a>
													</th>
													
												</tr>
												<c:forEach items="${listeEmploye}" var="list">
													<tr id="${list.idemp}">
														<td style="display:none;">${list.idemp}</td>
														<td>${list.nom}</td>
														<td>${list.prenom}</td>
														<td>${list.fonction}</td>
														<td>${list.getHorairep().getHeured()}</td>
														<td>${list.getHorairep().getHeuref()}</td>
													
															
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
																out.print("Absent  sortie à l'heure"); 
															
															else if(timestampf.before(timestampfPrev))
																out.print("Absent  sortie avant l'heure");
														}
													}
													%>
													 	 
														
													    	
													   	</td>

													
													
													<% currentEmpCounter++;%>
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

	</body>
	<script type="text/javascript" src="<%=mContextPath%>/js/jquery-1.9.1.min.js" > </script>
	<script type="text/javascript">
	var timerGetStatus;
		$("#startMockBtn").click(function(){
			
			$.ajax({url:'<%=mContextPath+"/demo?action=lancer"%>',
					type:"POST",
					success:function(){
						demarreGetStatusTimer();
					}
					});
		});
		function stopGetStatusTimer()
		{
			clearInterval(timerGetStatus);
		}
		function demarreGetStatusTimer(){
			 timerGetStatus=setInterval(function(){
				 console.log("voila");
					$.ajax({url:'<%=mContextPath+"/demo?action=facthoraireAjax&id="+idsite%>',
						error:function(){
							console.log("errroe");
							
						},
						success:function(data,textStatus,jqXHR){
							var arrayEmp=new Array();
							arrayEmp=data.split("&");
							for(var key in arrayEmp){
								//var empArray=new Array();
								var emp_item=arrayEmp[key]; // chaine sous forme id_emp=status
								//.log("emp "+emp);
								var empArray=emp_item.split("=") ;
								
									var id_emp=empArray[0];
									
									
									//console.log("tr#"+id_emp);
								var status_emp=empArray[1];
								if(id_emp!="")
								$("tr#"+id_emp+" td:nth-child(7)").html(status_emp);
														}
							
						},
						type:"GET"
						});
				},3000);
		}
		$(document).ready(function(){
		$("#stopTimerBtn").click(function(){
			stopGetStatusTimer();
		});
		});
		
	</script>
</html>


