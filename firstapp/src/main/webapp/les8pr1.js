$(document).ready(function() {
	var dataTable = $('#countryTable').DataTable();
	function loadTable(){
		$.ajax({
			method:"GET",
			dataType:"json",
			url : "restservices/countries",
/*			beforeSend: function (xhr) {
		        var token = window.sessionStorage.getItem("sessionToken");
		        xhr.setRequestHeader( 'Authorization', 'Bearer ' + token);
		    },
*/
			success : function (result){
				for(var item in result){
					var value = result[item];
					
					dataTable.row.add([
						"<a class='remove glyphicon glyphicon-remove' style='color:red' id='"+value.code+"'></a>",
						value.code,
						value.name,
						value.population,
					]).draw( false);
					
				}
				
				addListeners();
			}
		});
				
	}
    
    
    function addListeners(){
    	$(".remove").on("click",function (){
    		var id = $(this).attr("id");
    		$.ajax({
    			  url: "/restservices/countries/delete/"+id,
    			  method: "DELETE",
    			}).done(function() {
    				dataTable.clear();
    				loadTable();
    		});
    	});
    	
    	dataTable.on('dblclick', 'tr', function () {
    		var id = $(this).find(".remove").attr("id");
    		window.location = "les8change.html?id="+id;
    		} );
    }
    
    loadTable();
} );