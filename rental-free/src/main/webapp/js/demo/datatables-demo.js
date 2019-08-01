$(document).ready(function() {
    $('#dataTable').DataTable( {
		"paging" : true,
		"ordering" : true,
		"info" : true,
		"filter" : true,
		"lengthChange" : true,
		"order" : [[0,"desc"]]
    } );
} );