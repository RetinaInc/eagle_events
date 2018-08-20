<%@page import="com.event.eagle.dto.Eventorganizer"%>
<%@page import="java.util.List"%>
<%@page import="com.event.eagle.dto.Event"%>

<%
	Event editdata = null;
	if (request.getAttribute("editdata") != null) {
		editdata = (Event) request.getAttribute("editdata");
	}

	List<Eventorganizer> eventorganizers = (List<Eventorganizer>) request.getAttribute("eventOrganizers");
%>



<form action="csvfileupload" method="post" role="form"
	class="form-horizontal form-label-left mr30" enctype="multipart/form-data">


	<input type="hidden" name="action" value="add">

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12"> Name
			<span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="text" name="name" required="required"
				class="form-control col-md-7 col-xs-12">
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
			Description <span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="text" name="description" required="required"
				class="form-control col-md-7 col-xs-12">
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">Organizer</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<select class="form-control eventOrganizer" name="organizerid"
				id="organizerid" required>


			</select>
		</div>
	</div>


	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12"> Date
			<span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="datetime-local" name="date" required="required"
				class="form-control col-md-7 col-xs-12">
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
			Chairs Per Table <span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="number" name="chairspertable" required="required"
				class="form-control col-md-7 col-xs-12">
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">
			Import Guests List <span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="file" name="companyfile" required="required"
				aria-describedby="fileHelp" class="form-control col-md-7 col-xs-12">
		</div>
	</div>

	<button class="btn btn-success btn-add mr30" type="submit">Submit</button>

</form>



