<%@page import="com.event.eagle.dto.Eventorganizer"%>

<%
Eventorganizer editdata = null;
	if (request.getAttribute("editdata") != null) {
		editdata = (Eventorganizer) request.getAttribute("editdata");
	}
%>



<form action="eventOrganizer" method="post" role="form"
	class="form-horizontal form-label-left mr30">


	<input type="hidden" name="action" value="add"> <input
		type="hidden" name="id">

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">Host
			Name <span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="text" name="hostname" required="required"
				class="form-control col-md-7 col-xs-12">
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">Host
			Phone <span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="text" name="hostphone" required="required"
				class="form-control col-md-7 col-xs-12">
		</div>
	</div>





	<button class="btn btn-success btn-add mr30" type="submit">Submit</button>
</form>
