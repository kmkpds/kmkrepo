	function initialize() {
      			
		  var myLatlng = new google.maps.LatLng(47.904, 1.907);
				 
		  var mapOptions = {
		    zoom: 13,
		    center: myLatlng,
		    mapTypeId: google.maps.MapTypeId.ROADMAP
		  }
		  
		  var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
		 
		  <c:forEach items="${listeStation}" var="station">	 
		
			  var latitude = '<c:out value="${station.latitude}"/>';
			  var longitude = '<c:out value="${station.longitude}"/>'; 
			  var nom = '<c:out value="${station.nomStation}"/>';
			  
			  var gare = new google.maps.LatLng(latitude,longitude);
			  var marker = new google.maps.Marker({
			      position: gare,
			      map: map,
			      title: nom
		  });
    
		  </c:forEach>
			
      }