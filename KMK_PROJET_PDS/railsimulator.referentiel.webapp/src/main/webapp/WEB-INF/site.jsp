<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="RnoMainContent" class="sc">
							<!-- Page Title -->
							<h2 id="RnoPageTitle">
								<a></a>Employé
							</h2>
							<!-- /Page Title -->
							<!-- Bordered Workspace -->
							<div id="RnoWorkspace">
								<!-- Section -->
								<c:forEach var="employe" items="${listeEmploye}">	
								<div class="RnoSection">
								
									<h3 class="RnoSectionTitle">
										<span></span><c:out value="${employe.nom}"/>
										<span></span><c:out value="${employe.prenom}"/>
										<c:out value=" horaire prévu "/>
										<c:out value="${employe.horairep.heured}"/>
									
									</h3>	
									<div class="RnoSectionContent">
										<div class="RnoDataTable">
										
											<table border="1">
												<tr class="RnoMainHeader">

													<th>
														<a href="#">Date</a>
													</th>
													<th>
														<a href="#">Arrivée</a>
													</th>
													<th>
														<a href="#">Départ</a>
													</th>
													
																								
												</tr>
												
												
												
												    <c:forEach var="facthoraire" items="${employe.listeFactHoraire}">	
													<tr>
														<td><c:out value="${facthoraire.date}"/></td>
													    <td><c:out value="${facthoraire.heured}"/></td>
													    <td><c:out value="${facthoraire.heuref}"/></td>
													    
													    
													  </tr>
													    

													    <td>
														<c:if test="${empty facthoraire.heured and facthoraire.heured> employe.horairep.heuref and facthoraire.idemp == horairep.idemp}">
													    		<img src="images/icones/iconeEnAttente.png" width="20" height="20"> Absent 
													    </c:if>
													    
													    <c:if test="${empty facthoraire.heured}">
													    		<img src="images/icones/iconeEnAttente.png" width="20" height="20"> Absent 
													    </c:if>
													    
													    <c:if test="${not empty facthoraire.heuref and facthoraire.heuref> employe.horairep.heuref and facthoraire.idemp == employe.idemp }">
													    		<img src="images/icones/iconeEnAttente.png" width="20" height="20"> Absent 
													    </c:if>		
												
												
													    <c:if test="${not empty employe.horairep.heured and facthoraire.idemp == employe.idemp and facthoraire.heured == employe.horairep.heured }">
													    		<img src="images/icones/iconeTermine.png" width="20" height="20">Arrivée à l'heure
													    </c:if>


													   	<c:if test="${not empty facthoraire.heured and facthoraire.heured > employe.horairep.heured and facthoraire.idemp == employe.idemp}">
													    		<img src="images/icones/iconeEnCours.png" width="20" height="20"></> En poste avec retard 
													    </c:if>
													
													    	
													   	</td>

													
			                                        </c:forEach>
											</table>
										</div>
									</div>
								</div>
								</c:forEach>
							</div>
							<!-- /Page Level Buttons Steps -->
						</div>