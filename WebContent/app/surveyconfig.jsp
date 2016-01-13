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
  	
  	<div class="well">
  	<h1>New  Maturity Assessment  </h1>
  	<form class="form-horizontal" role="form" action="saveSuryvey.wss" method="post" id="surveyDetailsFrm" >
	    <div class="panel panel-primary">
	  		<div class="panel-heading panel-primary"><b>Assessment details </b></div>
		  	<div class="panel-body">
					  <div class="form-group">
					    <label class="control-label  col-sm-2" for="surveyName">Survey name</label>
					    <div class="col-sm-10">
					    <input type="text" class="form-control" id="surveyName" name="surveyName">
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="control-label col-sm-2" for="releaseDate">Survey release date</label>
					    <div class="col-sm-10">
					    	<input type="text" class="form-control" id="releaseDate" name="releaseDate">
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="control-label col-sm-2" for="comment">Comment</label>
					    <div class="col-sm-10">
					    	<input type="text" class="form-control" id="comment" name="comment">
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="control-label  col-sm-2" for="sqadList">Select squad</label>
						    <div class="checkbox  col-sm-10">
							  <label><input type="checkbox" value="squad1" name="sqadList">Squad 1</label>
							  <label><input type="checkbox" value="squad2" name="sqadList">Squad 2</label>
							</div>
					  </div>
					  <div class="col-sm-2">&nbsp;</div>
					  <div class="col-sm-10"><button type="submit" class="btn btn-primary btn-lg">Continue</button></div>
		  	</div><!--  End of panel body -->
		</div>
		<div class="panel panel-primary">
	  		<div class="panel-heading panel-primary"><b>Questions setup </b> <span class="label label-warning">0 Selected</span></div>
		  	<div class="panel-body">
		  		 <!--  Adding the questions in the collapsible accordian -->
		  		 <div class="panel-group" id="accordion">
		  		 	
		  		 	<div class="panel panel-info"> <!--  Question panel -->
		  		 		<div class="panel-heading">
		  		 			
		  		 			<h4 class="panel-title">
						        <input type="checkbox" name="aa" value="questionId"/> <a data-toggle="collapse" data-parent="#accordion" href="#questionId">Agile principle and practice name</a>
					        </h4>
		  		 		</div>
		  		 		<div id="questionId" class="panel-collapse collapse in">
						      <div class="panel-body">
						      	Question content
						      </div>
    					</div>
		  		 	</div>
		  		 </div><!--  End of accordian -->
		  		 	
		  	</div>
		 </div>
	</form>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>