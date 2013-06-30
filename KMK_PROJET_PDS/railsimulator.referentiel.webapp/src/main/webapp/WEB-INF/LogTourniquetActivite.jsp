<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$("#exam").dataTable();
			} );
		</script>
<title>Activité des tourniquets</title>
</head>
<body>
<table id="exam">
								<thead>	<tr>
							 			<th width="100 px">idTransaction</th>
										<th width="100 px">idClient</th>
										<th width="100 px">idTourniqet</th>
										<th width="150 px">date passage</th>
										<th width="150 px">statut transaction</th>
									</tr></thead>
				 				<tbody>
				 					<c:forEach items="${listClientPass}" var="clientP">
										<tr align="center" >
											<td width="auto" >${clientP[4]}</td>
											<td width="auto">${clientP[0]}</td>
											<td width="auto">${clientP[1]}</td>
											<td width="auto">${clientP[2]}</td>
											<td width="auto">${clientP[3]}</td>	
										</tr>
									</c:forEach></tbody>
									<tfoot></tfoot>
								</table>
								
</body>
</html>