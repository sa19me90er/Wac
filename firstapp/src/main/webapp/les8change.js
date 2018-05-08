$(document).ready(function (){
	function loadData(){
		var code = getParameterByName("id");
		$.ajax({
			  type: "get",
			  url: "/firstapp/restservices/countries/"+code,
/*			  beforeSend: function (xhr) {
			        var token = window.sessionStorage.getItem("sessionToken");
			        xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
			    },
*/
			  success: function(result){
				  	$("#code").val(result.code);
					$("#iso3").val(result.iso3);
					$("#name").val(result.name);
					$("#capital").val(result.capital);
					$("#continent").val(result.continent);
					$("#region").val(result.region);
					$("#goverment").val(result.goverment);
					$("#population").val(result.population);
					$("#lat").val(result.lat);
					$("#longitude").val(result.lng);
					$("#surface").val(result.surface);
			  },
			  error: function(){
				  $("#codeField").removeClass("hidden");
			  }
			});
		
	}
	
	loadData();
	
	
	function getParameterByName(name, url) {
	    if (!url) url = window.location.href;
	    name = name.replace(/[\[\]]/g, "\\$&");
	    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
	        results = regex.exec(url);
	    if (!results) return null;
	    if (!results[2]) return '';
	    return decodeURIComponent(results[2].replace(/\+/g, " "));
	}
	
	$("#save").on("click",function (event) {
		var url = "/firstapp/restservices/countries/create";
		var method = "POST";
		if(getParameterByName("id"))
		{
			url = "/firstapp/restservices/countries/save";
			method = "PUT";
		}
		
		event.preventDefault();
		
				var formData = JSON.stringify($("#countryForm").serializeArray());
				$.ajax({
					  type: method,
					  url: url +"?"+$("#countryForm").serialize(),
					  data: formData,
					  success: function(){
						  window.location = "les8prac1.html";
					  },
					});
			});
	
	$("#close").on("click",function (event)
			{
		window.location = "les8prac1.html";
		event.preventDefault();
			});
});