<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.event.eagle.dto.User"%>

<%
	ArrayList<User> clientData = (ArrayList<User>) request.getAttribute("userData");
%>

<div class="box-body table-responsive no-padding">

	<table class="table table-bordered table-striped" id="example2">
		<thead>
			<tr>
				<th>Id</th>

				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Role</th>

				<th>Options</th>

			</tr>
		</thead>
		<tbody>



		</tbody>
	</table>
</div>

