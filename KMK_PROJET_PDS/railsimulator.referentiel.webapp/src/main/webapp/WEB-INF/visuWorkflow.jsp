<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="RnoMainContent" class="sc">
							<!-- Page Title -->
							<h2 id="RnoPageTitle">
								<a></a>Statut des incidents déclarés
							</h2>
							<!-- /Page Title -->
							<!-- Bordered Workspace -->
							<div id="RnoWorkspace">
								<!-- Section -->
								<c:forEach var="incident" items="${listeIncident}">	
								<div class="RnoSection">
									<h3 class="RnoSectionTitle">
										<span></span><c:out value="${incident.typeIncident.libelleType}"/>
										<c:out value=" déclaré le "/>
										<c:out value="${incident.dateDebut}"/>
										<c:out value=": Traitement par "/>
										<c:out value="${incident.procedure.libelleProcedure}"/>
										
										<c:set var="nbreTotal" value="0" scope="page"/>
										<c:set var="nbreFini" value="0" scope="page"/>
										<c:forEach var="action" items="${incident.procedure.listeAction}" varStatus="touteaction">
											<c:set var="nbreTotal" value="${touteaction.count}"/>
											
											<c:if test="${not empty action.dateDebut and not empty action.dateFin}">
												<c:set var="nbreFini" value="${nbreFini +1}"/>
											</c:if>
										</c:forEach>
										
										<c:if test="${empty incident.dateFin}"> EN COURS à ${(nbreFini/nbreTotal)*100}%</c:if>
										<c:if test="${not empty incident.dateFin}"> TERMINÉ à ${(nbreFini/nbreTotal)*100}% le <c:out value="${incident.dateFin}"/> </c:if>
										
									</h3>	
													
									<div class="RnoSectionContent">
										<div class="RnoDataTable">
											<table>
												<tr class="RnoMainHeader">
												
													<th>
														<a href="#">Action incident</a>
													</th>
													<th>
														<a href="#">Date d'enclenchement</a>
													</th>
													<th>
														<a href="#">Date de fin</a>
													</th>
													<th>
														<a href="#">Statut</a>
													</th>
												</tr>
												    <c:forEach var="action" items="${incident.procedure.listeAction}">	
													<tr>
														<td><c:out value="${action.libelleActionIntervention}"/></td>
													    <td><c:out value="${action.dateDebut}"/></td>
													    <td><c:out value="${action.dateFin}"/></td>
													
													    <td><c:if test="${not empty action.dateDebut and not empty action.dateFin}">
													    		<img src="images/icones/iconeTermine.png" width="20" height="20"> TERMINÉ
													    	</c:if>
													   	
													   		<c:if test="${empty action.dateDebut}">
													    		<img src="images/icones/iconeEnAttente.png" width="20" height="20"> EN ATTENTE
													    	</c:if>			   	
													   	
													   		<c:if test="${not empty action.dateDebut and empty action.dateFin}">
													    		<img src="images/icones/iconeEnCours.png" width="20" height="20"></> EN COURS
													    	</c:if>
													   	</td>
													   	
													</tr>
			                                        </c:forEach>
												
											</table>
										</div>
									</div>
								</div>
								</c:forEach>
							</div>
							<!-- /Page Level Buttons Steps -->
						</div>