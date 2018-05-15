function initPage(result){


	fetch("https://ipapi.co/json/")
	.then(function (response){return response.json();})
	.then(function(myJson){
		
		$("#countrycode").append(myJson.country);
		$("#land").append(myJson.country_name);
		$("#regio").append(myJson.region);
		$("#stad").append(myJson.city);
		$("#postcode").append(myJson.postal);
		$("#latitude").append(myJson.latitude);
		$("#longitude").append(myJson.longitude);
		$("#ip").append(myJson.ip);
		
		localStorage.setItem('lat', myJson.latitude);
		localStorage.setItem('long', myJson.longitude);
		localStorage.setItem('stad', myJson.city);

		

	
	    var lat = localStorage.getItem('lat');
	    var long = localStorage.getItem('long');
	    var weerStad = localStorage.getItem('stad');
	    
        $("#h3-1").empty().append("Het weer in: "+ myJson.city);
        



// --------------------------------
					
			



// ------------------------------------------------------
	

		
	function showWeather(latitude, longitude, city){
		city=weerStad;
		var refresh = true;
		


// http://api.openweathermap.org/data/2.5/weather?lat=52.0916&lon=5.1228&appid=7aba276c0999327bea88ac89dc97703a
	fetch("http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&appid=7aba276c0999327bea88ac89dc97703a")
// fetch("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=cf6cdf79ac4838b91245e459f333b967")

		.then(function (response){return response.json();})	
	.then(function(myJson){

		
		// Tot change miliseconds to hours :
		function msToTime(duration) {
	        var milliseconds = parseInt((duration%1000)/100)
	            , seconds = parseInt((duration/1000)%60)
	            , minutes = parseInt((duration/(1000*60))%60)
	            , hours = parseInt((duration/(1000*60*60))%24);

	        hours = (hours < 10) ? "0" + hours : hours;
	        minutes = (minutes < 10) ? "0" + minutes : minutes;
	        seconds = (seconds < 10) ? "0" + seconds : seconds;

	        return hours + ":" + minutes + ":" + seconds + "." + milliseconds;
	    }

		zonsopgang=msToTime(myJson.sys.sunrise);
		zonsondergang=msToTime(myJson.sys.sunset);
		
		var temp = Math.round((myJson.main.temp - 273.15)* 100) / 100;
		$("#temptatuur").empty().append(temp + " °C");
		$("#luchtvochtigheid").empty().prepend(myJson.main.humidity);
		$("#windsnelheid").empty().prepend(myJson.wind.speed);
		$("#windrichting").empty().prepend(myJson.wind.deg);
		$("#zonsopgang").empty().prepend(zonsopgang);
		$("#zonsondergang").empty().prepend(zonsondergang);
		


		
		
	}
		
	)};
	showWeather(lat, long, weerStad);

// document.getElementById("stad").click(function() {
	
	$("#stad").click(function(){
	    $("#h3-1").empty().append("Het weer in: "+weerStad);                
	    showWeather(lat, long, weerStad);

	    });	


function loadCountries(){


	fetch("/firstapp/restservices/countries/")
	.then(function (response){return response.json();})
	.then(function(myJson){
		for (const m of myJson) {
			
			 var tr = $("<tr></tr>");
             
			 tr.click(function() {
            	 
                 $("#h3-1").empty().append("Het weer in: "+ m.capital);                
                 showWeather(m.lat, m.lng, m.capital);
                 console.log(m.lat, m.lng, m.capital);
                 });
            
			$("#destination-items-body").append(tr);
			tr.append("<td>"+m.name+"</td>")
			tr.append("<td>"+m.capital+"</td>")
			tr.append("<td>"+m.region+"</td>")
			tr.append("<td>"+m.surface+"</td>")
			tr.append("<td>"+m.population+"</td>")

		}

	

	});
	

	
	
}	
loadCountries();




	});

}


initPage();






