<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Hello  ${sessionScope.LOGGED_IN_USER.displayName}</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home Page</a></li>
    </ul>
  </div>
</nav>
    
  <body>
    
    <div class="well">
	    <div class="panel panel-primary">
	  		<div class="panel-heading panel-primary">Survey administrations for Iteration Manager</div>
	  		<div class="panel-body">
	  			<a href="loadAssesmentConfig.wss" class="btn btn-warning" role="button">Create a new assessment</a>
	  			<a href="loadExistingQuestionConfig.wss" class="btn btn-warning" role="button">Configure questions</a>
	  			
	  		</div>
	  	</div>
  	</div>
  	<%@ include file="footer.jsp" %>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>