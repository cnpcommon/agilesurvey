<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Agile Maturity Assessment Survey Tool</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%@include file="nav.jsp" %>

		<div class="container">
		<div class="page-header">
			<h1>Agile Maturity Survey Tool ( Version : 0.1)</h1>
		</div>
		<div class="jumbotron">
			<span class="lead">Click on the following button to take assesment assigned to you</span>
			<p><a href="loadSurvey.wss" role="button" class="btn btn-lg btn-danger"><span class="glyphicon glyphicon-list">&nbsp;</span>Take assessment</a></p>
		</div>
	</div>

	<!-- <div class="well">
		
	</div> -->
	<%@include file="footer.jsp" %>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	
</body>
</html>