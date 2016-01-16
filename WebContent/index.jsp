<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title>Agile Maturity Assessment Survey Tool</title>
		<meta name="generator" content="Bootply" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		
	</head>
	
	<body>
<!--login modal-->
<form class="form col-md-12 center-block" action="login.wss" method="post">
<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <h1 class="text-center">Agile Maturity Assessment Tool</h1>
          <h3 class="text-center">Login</h3>
      </div>
      
      <div class="modal-body">
	       <c:if test="${not empty loginError}" >       
	            <div class="alert alert-danger fade in">
                     <a href="#" class="close" data-dismiss="alert">&times;</a>
                     <strong>Error!</strong> ${loginError}
                </div>
	       </c:if>     
            <div class="form-group">
              <input type="text" class="form-control input-lg" name="Email" placeholder="Email">
            </div>
            <div class="form-group">
              <input type="password" class="form-control input-lg" name="Password" placeholder="Password">
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-lg btn-block">Sign In</button>
              <span class="pull-right"><a href="#">Register</a></span><span><a href="#">Need help?</a></span>
            </div>
          
      </div>
      <div class="modal-footer">
          <div class="col-md-12">
		  </div>	
      </div>
  </div>
  </div>
</div>
</form>
	<!-- script references -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>