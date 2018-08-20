/**
 * 
 */

$(document).ready(function() {


		var eventid = $("#eventid").val();
		$("div.newelement").remove();
		$('#example2').children('tr').remove();

		$.ajax({
			url : 'reademployee',
			type : 'POST',
			dataType : 'html',
			data : 'eventid=' + eventid,
			success : function(data) {

				$.each(JSON.parse(data), function(index, item) {
					var role = "Admin";
					if(item.role==1){
						role="User";
					}
					$('#example2 tbody').append('<tr><td>'+item.id+'</td><td>'+item.firstname+'</td><td>'+item.lastname+'</td><td>'+item.email+'</td><td>'+role+'</td><td><button type="button" class="btn btn-danger btn-xs deleteButton" name="'+item.id+'">Delete</button></td></tr>');

				});

			},
			error : function(e) {

			}
	});
});
/**
 * 
 */
