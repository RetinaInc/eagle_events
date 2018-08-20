<%@page import="java.util.List"%>
<%@page import="com.event.eagle.dto.Event"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%
	response.addHeader("Cache-Control",
			"no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
	response.addHeader("Pragma", "no-cache");
	response.addDateHeader("Expires", 0);
	
	
	List<Event> eventData = null;
	if (request.getAttribute("eventData") != null) {
		eventData = (List<Event>) request.getAttribute("eventData");
	}

%>
<!DOCTYPE html>
<html lang="en">


<jsp:include page="header.jsp" />

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

										<div class="row setup-content ">



											<div class="col-xs-8 col-md-offset-2 themeborder">
												<div class="tab-pane active" id="1" align="center">
													<form action="csvfileupload" method="post" role="form"
														enctype="multipart/form-data">

														<div class="form-group">
															<h3>Choose CSV file</h3>
															<input type="file" class="form-control-file mr30"
																name="companyfile" aria-describedby="fileHelp">

														</div>

														<div class="form-group">
															<label class="control-label col-md-3 col-sm-3 col-xs-12">Event</label>
															<div class="col-md-6 col-sm-6 col-xs-12">
																<select class="form-control" name="eventid"
																	id="eventid" required>
																	<option value="" class=""></option>

																	<%
																		for (Event entry : eventData) {
																	%>
																	<option value="<%out.print(entry.getId());%>"
																		class="citycode citycodeoption<%out.print(entry.getName());%>">
																		<%
																			out.print(entry.getName());
																		%>
																	</option>
																	<%
																		}
																	%>

																</select>
															</div>
														</div>

														<button class="btn btn-success btn-add mr30" type="submit">Submit</button>

													</form>

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





	<script src="js/input-mask/jquery.inputmask.js" type="text/javascript"></script>
	<script src="js/input-mask/jquery.inputmask.date.extensions.js"
		type="text/javascript"></script>
	<script src="js/input-mask/jquery.inputmask.extensions.js"
		type="text/javascript"></script>


</body>

</html>
