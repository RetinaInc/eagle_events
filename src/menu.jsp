<div class="col-md-3 left_col">
	<div class="left_col scroll-view">

		<div class="navbar nav_title" style="border: 0;">
			<a href="homepage.jsp" class="site_title"><i class="fa fa-paw"></i>
				<span>Eagle Event Company!</span></a>


		</div>
		<div class="clearfix"></div>



		<!-- /menu prile quick info -->

		<br />

		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu"
			style="display: block">

			<div class="menu_section">
				<ul class="nav side-menu">


					<li><a href="employ"><i class="fa fa-user"></i> Employee</a></li>


					<br />
					<li><a href="eventOrganizer"><i class="fa fa-square"></i>
							Event Organizer</a></li>
					<li><br />
					<li><a href="event"><i class="fa fa-calendar"></i> Event</a></li>

					<br />
					<li><a href="manageguests"><i class="fa fa-users"></i>
							Manage Guests</a></li>
					<br />

					<li><a href="generateseating"><i class="fa fa-server"></i>
							Generate Seating</a></li>
					<br />

					<li><a href="printcards"><i class="fa fa-sticky-note"></i>
							Print Cards</a></li>
					<br />



					<li><a href="logout"><i class="fa fa-sign-out"></i> Logout</a></li>


				</ul>
			</div>

			<div class="sidebar-footer hidden-small hide">
				<a data-toggle="tooltip" data-placement="top" title="Settings">
					<span class="glyphicon glyphicon-cog hide" aria-hidden="true"></span>
				</a> <a data-toggle="tooltip" data-placement="top" title=""> <span
					class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
				</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span
					class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
				</a> <a data-toggle="tooltip" data-placement="top" title="Logout"> <span
					class="glyphicon glyphicon-off" aria-hidden="true"></span>
				</a>



			</div>

		</div>

		<!-- /menu footer buttons -->
	</div>
</div>

<!-- top navigation -->
<div class="top_nav">

	<div class="nav_menu">
		<nav class="" role="navigation">
			<div class="nav toggle">
				<a id="menu_toggle"><i class="fa fa-bars"></i></a>
			</div>


			<ul class="nav navbar-nav navbar-right">



				<h3 style="padding-top: 7px;">


					Hi,
					<%
					out.print(session.getAttribute("name"));
				%>

				</h3>
			</ul>
		</nav>
	</div>

</div>
