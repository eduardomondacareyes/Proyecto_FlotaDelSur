function agenda_viajescontroller(opcion) {
	$("#info").hide();
	$("#info").removeClass("alert-success").addClass("alert-danger");
	var token = $("meta[name='_csrf']").attr("content");
	
	switch(opcion){
	case "list":
		$.ajax({
			type : "post",
		    headers: {"X-CSRF-TOKEN": token}, //send CSRF token in header
			url : "/agenda_viajes/list",
			success : function(res) {
				$('#agenda_ViajesTable').bootstrapTable('load', res);
				$('#agenda_ViajesTable tbody').on('click', 'tr',  function () {
					$("#id_agenda").val($(this).find("td:eq(0)").text());
					$("#origen").val($(this).find("td:eq(1)").text());
					$("#destino").val($(this).find("td:eq(2)").text());
					$("#costo").val($(this).find("td:eq(3)").text());
					$("#cant_pasajeros").val($(this).find("td:eq(4)").text());
					$("#fecha").val($(this).find("td:eq(5)").text());
					$("#correo").val($(this).find("td:eq(6)").text());
					$("#myModal .close").click();
				});
				
				
				$("#myModados").modal({show:true});
			},
			error : function() {
				$("#msg").show();
				$("#msg").html("Error en busqueda de agendamiento.")
			}
		});       			
		break;
	case "get":
		$.ajax({
			type : "post",
		    headers: {"X-CSRF-TOKEN": token}, //send CSRF token in header
			url : "/Agenda_Viajes/get",
			data : "id_agenda="+$("#id_agenda").val(),
			success : function(res) {
				if (res == null || res == "") {
					$("#msg").show();
					$("#msg").html("No se encontraron registros.");
				} else {
					
					$("#id_agenda").val(res.id_agenda);
					$("#origen").val(res.origen);
					$("#destino").val(res.destino);
					$("#costo").val(res.costo);
					$("#cant_pasajeros").val(res.cant_pasajeros);
					$("#fecha").val(res.fecha);
					$("#correo").val(res.correo);
					
				}
			},
			error : function() {
				$("#msg").show();
				$("#msg").html("Error en busqueda.");
			}
		});       			
		break;
	case "insert":
		var json = 
			{
				
				'origen': $("#origen").val(),
				'destino': $("#destino").val(),
				'costo': $("#costo").val(),
				'cant_pasajeros': $("#cant_pasajeros").val(),
				'fecha': ( $("#fecha").val() ? $("#fecha").val() : "0"),
				'correo': ( $("#correo").val() ? $("#correo").val() : "0"),
			};
	
	    var postData = JSON.stringify(json);

	    $.ajax({
			type : "post",
			url : "/agenda_viajes/insert",
			headers: {"X-CSRF-TOKEN": token}, //send CSRF token in header
			data : postData,
			contentType : "application/json; charset=utf-8",
	        dataType : "json",
			success : function(res) {
				if (res == 1) {
					$("#info").removeClass("alert-danger").addClass("alert-success");
					$("#info").show();
					$("#info").html("Su viaje ha sido agendado.");
				} else {
					$("#info").show();
					$("#info").html("No se pudo agendar su viaje.");
				}
			},
			error : function() {
				$("#info").show();
				$("#info").html("error al agendar el viaje.");
			}
		});       	
	    break;
	case "update":
		var json = 
			{
			'origen': $("#origen").val(),
			'destino': $("#destino").val(),
			'costo': $("#costo").val(),
			'cant_pasajeros': $("#cant_pasajeros").val(),
			'fecha': ( $("#fecha").val() ? $("#fecha").val() : "0"),
			'correo': $("#correo").val()
			};
		;

	    var postData = JSON.stringify(json);

		$.ajax({
			type : "post",
			headers: {"X-CSRF-TOKEN": token}, //send CSRF token in header
			url : "/Agenda_Viajes/update",
			data : postData,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(res) {
				if (res == 1) {
					$("#msg").removeClass("alert-danger").addClass("alert-success");
					$("#msg").show();
					$("#msg").html("Registro modificado correctamente.");
				} else {
					$("#msg").show();
					$("#msg").html("No se pudo modificar el registro.");
				}
			},
			error : function() {
				$("#msg").show();
				$("#msg").html("No se pudo modificar el registro.");
			}
		});       	
    break;
	case "delete":
		$.ajax({
			type : "post",
			headers: {"X-CSRF-TOKEN": token}, //send CSRF token in header
			url : "/Agenda_Viajes/delete",
			data : "id_agenda="+$("#id_agenda").val(),
			success : function(res) {
				if (res == 1) {
					$("#msg").removeClass("alert-danger").addClass("alert-success");
					$("#msg").show();
					$("#msg").html("Registro eliminado correctamente.");
				} else {
					$("#msg").show();
					$("#msg").html("No se pudo eliminar el registro.");
				}
			},
			error : function() {
				$("#msg").show();
				$("#msg").html("No se pudo eliminar el registro.");
			}
		});
		break;
	default:
		$("#msg").show();
		$("#msg").html("Opción incorrecta.");
	}
}

