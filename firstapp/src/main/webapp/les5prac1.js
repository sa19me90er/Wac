$(document).ready(function() {
	
	function getDestinations(){
		$.getJSON("/restservices/countries",function (result){
			var table = $("#destination-items");
			var body = table.find("tbody");
			body.empty();
			for(var item in result){
				var value = result[item];
				body.append("<tr class='country' id='"+value.code+"' data-lat='"+value.lat+"' data-long='"+value.lng+"'><td>"+value.name+"</td><td>"+value.capital+"</td><td>"+value.surface+"</td><td>"+value.population+"</td></tr>")
			}
			
			addListeners();
		});
		
	}
	
	function addListeners(){
		$(".country").on("click",function (){
			var item = $(this);
			var lat = item.data("lat");
			var long = item.data("long");
			showWeather(lat,long);
		});
	}
	
	function loadLocation() {
		$.getJSON("http://ip-api.com/json", function(result){
			var location = $("#location-items");
			location.empty();
			
			location.append("<li>Landcode: "+result.countryCode+"</li>");
			location.append("<li>Land: "+result.country+"</li>");
			location.append("<li>Regio: "+result.region+"</li>");
			location.append("<li>Stad: "+result.city+"</li>");
			location.append("<li>Postcode: "+result.zip+"</li>");
			location.append("<li>Latitude: "+result.lat+"</li>");
			location.append("<li>Longtitude: "+result.lon+"</li>");
			location.append("<li>Ip: "+result.query+"</li>");
			
			showWeather(result.lat,result.lon);
			
			location.on("click",function (){
				showWeather(result.lat,result.lon);
			})
	    });
	}
	
	function showWeather(lat,long){
		var time = localStorage.getItem(lat+long+"time");
		var refresh = true;
		if(time != null)
		{
			var date = new Date(time);
			if(date.setMinutes(date.getMinutes() + 10)  >= new Date())
			{
				var data = localStorage.getItem(lat+long+"data");
				refreshLocation(JSON.parse(data));
				refresh = false;
			}
		}
		
		if(!refresh)
			return;
		
		$.getJSON("http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+long+"&appid=7aba276c0999327bea88ac89dc97703a",function (result){
			refreshLocation(result);
			localStorage.setItem(lat+long+"time", new Date());
			localStorage.setItem(lat+long+"data", JSON.stringify(result));
		});
	}
	
	function refreshLocation(result){
		var location = $("#weather-items");
		location.empty();
		var temp = Math.round((result.main.temp - 273.15)* 100) / 100;
		location.append("<li>Temperatuur: "+temp+"°C</li>");
		location.append("<li>Luchtvochtigheid: "+result.main.humidity+"%</li>");
		location.append("<li>windsnelheid: "+result.wind.speed+" km/u</li>");
		location.append("<li>Windrichting: "+degToCard(result.wind.deg)+"</li>");
		location.append("<li>Zonsopgang: "+result.sys.sunrise+"</li>");
		location.append("<li>Zonsondergang: "+result.sys.sunset+"</li>");
	}
	
	var degToCard = function(deg){
		  if (deg>11.25 && deg<33.75){
		    return "NNE";
		  }else if (deg>33.75 && deg<56.25){
		    return "ENE";
		  }else if (deg>56.25 && deg<78.75){
		    return "E";
		  }else if (deg>78.75 && deg<101.25){
		    return "ESE";
		  }else if (deg>101.25 && deg<123.75){
		    return "ESE";
		  }else if (deg>123.75 && deg<146.25){
		    return "SE";
		  }else if (deg>146.25 && deg<168.75){
		    return "SSE";
		  }else if (deg>168.75 && deg<191.25){
		    return "S";
		  }else if (deg>191.25 && deg<213.75){
		    return "SSW";
		  }else if (deg>213.75 && deg<236.25){
		    return "SW";
		  }else if (deg>236.25 && deg<258.75){
		    return "WSW";
		  }else if (deg>258.75 && deg<281.25){
		    return "W";
		  }else if (deg>281.25 && deg<303.75){
		    return "WNW";
		  }else if (deg>303.75 && deg<326.25){
		    return "NW";
		  }else if (deg>326.25 && deg<348.75){
		    return "NNW";
		  }else{
		    return "N"; 
		  }
		}
	
	loadLocation();
	getDestinations();
});