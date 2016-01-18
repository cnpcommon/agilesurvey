<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Hello
				${sessionScope.LOGGED_IN_USER.displayName}</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">

				<li class="active" id="menuitem1"><a href="home.wss"><span
						class="glyphicon glyphicon-home"></span> Home</a></li>
				<c:if test="${sessionScope.LOGGED_IN_USER.role == 'tribe_manager' }" >		
				<li class="dropdown" id="menuitem2"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Survey Administration<span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="loadAssesmentConfig.wss">Create Assessment</a></li>
						<li><a href="loadExistingQuestionConfig.wss">Configure
								Practices</a></li>
						<li><a href="gendashboard.wss">View Dashboard</a></li>
					</ul></li>
				</c:if>	
				<c:if test="${sessionScope.LOGGED_IN_USER.role != 'tribe_manager' }" >		
					<li id="menuitem3"><a href="loadSurvey.wss">Take Assessment</a></li>
				</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout.wss"><span class="glyphicon glyphicon-off"></span>
						Logout</a></li>
			</ul>
		</div>
	</div>
</nav>