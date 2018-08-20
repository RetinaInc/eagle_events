<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%
	response.addHeader("Cache-Control",
			"no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
	response.addHeader("Pragma", "no-cache");
	response.addDateHeader("Expires", 0);

	String tab1class = "active";
	String tab2class = "";
	String tab3class = "";
	String tab4class = "";
	String tab5class = "";

	if (request.getAttribute("activetab") != null) {

		tab1class = "";

		if (request.getAttribute("activetab").equals("1")) {
			tab1class = "active";
		} else if (request.getAttribute("activetab").equals("2")) {
			tab2class = "active";
		} else if (request.getAttribute("activetab").equals("3")) {
			tab3class = "active";
		} else if (request.getAttribute("activetab").equals("4")) {
			tab4class = "active";
		} else if (request.getAttribute("activetab").equals("5")) {
			tab5class = "active";
		}
	}
%>
<!DOCTYPE html>
<html lang="en">


<jsp:include page="header.jsp" />
<script src="ajaxcalls/employee.js"></script>
<script src="ajaxcalls/deleteEmployee.js"></script>

<style>
.mr50 {
	margin-top: 50px;
}

.mr30 {
	margin-top: 30px;
}
</style>


<body class="nav-md">



	<div class="container body">


		<div class="main_container">


			<jsp:include page="menu.jsp" />



			<div class="right_col" role="main">



				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="dashboard_graph">

							<div class="fullwidth-block greet-section"
								style="padding-top: 30px;">
								<div class="container">

									<div class="row">

										<div class="row setup-content">



											<div class="col-xs-8 col-md-offset-2">
												<div class="">
													<%
														if (request.getAttribute("message") != null) {
													%>

													<div class="alert alert-info  fade in"
														style="color: #31708f; background-color: #d9edf7; border-color: #bce8f1;">
														<%
															out.print(request.getAttribute("message"));
														%>
													</div>

													<%
														request.removeAttribute("message");

														}
													%>

													<div class="container">
														<h2>Employee</h2>
													</div>

													<div id="exTab2" class="container">
														<ul class="nav nav-tabs">
															<li class="<%out.print(tab1class);%>"><a href="#1"
																data-toggle="tab"> <i class="fa fa-database"></i>
																	Employee List
															</a></li>
															<li class="<%out.print(tab2class);%>"><a href="#2"
																data-toggle="tab"> <i class="fa fa-plus"></i> 
																	Add Employee
															</a></li>
															<li class="<%out.print(tab3class);%>"><a href="#3"
																data-toggle="tab"> <i class="fa fa-pencil-square-o"></i>
																	Edit Employee
															</a></li>

														

														</ul>

														<div class="tab-content mr50">
															<div class="tab-pane <%out.print(tab1class);%>" id="1"
																align="center">

																<jsp:include page="listEmployee.jsp" />

															</div>

															<div class="tab-pane <%out.print(tab2class);%>" id="2"
																align="center">
																<jsp:include page="addEmployee.jsp" />


															</div>

															<div class="tab-pane <%out.print(tab3class);%>" id="3"
																align="center">

																<jsp:include page="editEmployee.jsp" />

															</div>

														



														</div>
													</div>

													<hr></hr>



												</div>
											</div>



										</div>


									</div>
									<!-- .row -->

								</div>
								<!-- .container -->
							</div>

							<div class="clearfix" style="height: 500px;"></div>
						</div>
					</div>

				</div>
				<br />

				<div class="row"></div>


				<div class="row">
					<div class="col-md-4 col-sm-4 col-xs-12"></div>
				</div>


				<div class="col-md-8 col-sm-8 col-xs-12">



					<div class="row"></div>
					<div class="row"></div>
				</div>
			</div>


		</div>
		<!-- /page content -->
	</div>







</body>

</html>
