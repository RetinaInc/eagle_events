<%@page import="com.event.eagle.dto.User"%>

<%
	User editdata = null;
	if (request.getAttribute("editdata") != null) {
		editdata = (User) request.getAttribute("editdata");
	}
%>



<form action="employ" method="post" role="form"
	class="form-horizontal form-label-left mr30">


	<input type="hidden" name="action" value="add"> <input
		type="hidden" name="id" >

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">First
			Name <span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="text" name="firstname" required="required"
				
				class="form-control col-md-7 col-xs-12">
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">Last
			Name <span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="text" name="lastname" required="required"
				class="form-control col-md-7 col-xs-12">
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12"> Email<span
			class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="text" name="email" required="required"
				class="form-control col-md-7 col-xs-12">
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12">Password
			<span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<input type="password" name="password" required="required"
				class="form-control col-md-7 col-xs-12">
		</div>
	</div>


	<div class="form-group">
		<label class="control-label col-md-3 col-sm-3 col-xs-12"> Role
			<span class="required">*</span>
		</label>
		<div class="col-md-6 col-sm-6 col-xs-12">
			<div class="radio">
				<label><input type="radio" name="role" checked="checked"
					value="0">Admin</label>
			</div>
			<div class="radio">
				<label><input type="radio" name="role" value="1">User</label>
			</div>

		</div>
	</div>

	<button class="btn btn-success btn-add mr30" type="submit">Submit</button>
</form>
