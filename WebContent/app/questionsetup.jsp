<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
  	
  	<div class="well">
  	<h1>New  assessment </h1>
	    <div class="panel panel-primary">
	  	<div class="panel-heading panel-primary"><h5>Configure questions </h5></div>
	  	<div class="panel-body">
			<form class="form-horizontal" role="form" action="submitQuestionSetup.wss" id="questionDetailsFrm">
				  <div class="form-group">
				    <label class="control-label col-sm-2" >Survey name</label>
				    <label class="control-label col-sm-2 alert-info" style="text-align:left;">${assesmentDetails.name}</label>
				    <label class="control-label col-sm-2" >Survey release date</label>
			    	<label class="control-label col-sm-2 alert-info" style="text-align:left;" >${assesmentDetails.releaseDate}</label>
				    
				  </div>
				  <div class="form-group">
				    <label class="control-label  col-sm-2" for="sqadList">Applicable Squads</label>
				    <div class="checkbox  col-sm-10">
				    	<c:forEach items="${assesmentDetails.squadList}" var="squadInfo">
						  ${squadInfo}
						</c:forEach>
					</div>	
				  </div>
				  <!--  Principles and practices -->
				  <div class="form-group">
				  	 <label class="control-label  col-sm-2" for="principle">Select Principle</label>
				  	 <div class="col-sm-4">
				    	<select class="form-control" id="principle" name="principle">
				    		<option value="" selected>Select principle</option>
					        <c:forEach items="${principleList}" var="principle">
					        	<option value="${principle.principleId }">${ principle.description}</option>
					        </c:forEach>
					      </select>
				    </div>
				    <label class="control-label  col-sm-2" for="practice">Select Practice</label>
				  	 <div class="col-sm-4">
				    	<select class="form-control" id="practice" name="practice">
					    </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="control-label col-sm-2" >Practice Description</label>
				    <label class="control-label col-sm-8" id="desc">Practice Description</label>
				   </div> 
				   <div class="form-group">
				   	<label class="control-label col-sm-2" for="maturityLevel">Level Indicator </label>
				   	<div class="col-sm-10">
				    	<select class="form-control" id="maturityLevel" name="maturityLevel" >
				    		<c:forEach items="${levelList}" var="levelDetails">
				    			<c:if test="${levelDetails.level>0 }" >
				    				<option value="${levelDetails.level}">${levelDetails.name}</option>
				    			</c:if>
				    		</c:forEach>
					    </select>
				    </div>
				   </div>
				    <div class="form-group">
				   	<label class="control-label col-sm-2" for="maturityIndicator">Level Indicator Text </label>
				   	<div class="col-sm-10">
				    	<textarea class="form-control" rows="5" id="maturityIndicator" name="maturityIndicator"></textarea>
				    </div>
				   </div>
				  <div class="col-sm-2">&nbsp;</div>
				  <div class="col-sm-10">
				  	<button type="button" class="btn btn-default btn-lg" id="saveBtn">Save</button>
				  	<button type="submit" class="btn btn-primary btn-lg">Submit and create survey</button>
				  </div>
				  
			</form>
	  	</div>
		</div>
	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    var practiceMap = ${practiceMap};
    $(document).ready(function(){
    	console.log("On load");
    	$("#saveBtn").click(function(){
    		//Validate the inputs 
    		console.log("On save");
    		var principle = $("#principle option:selected").val();
    		var practice = $("#practice option:selected").val();
    		var level = $("#maturityLevel option:selected").val();
    		var leveIndicator = $("#maturityIndicator").val();
    		//Validate
    		//var dataToSave = {"princliple": principle , "practice":practice ,"maturityLevel": level ,"leveIndicator" : leveIndicator };
    		//POST BY AJAX
    		
    		$.post( "test.php", $( "#questionDetailsFrm" ).serialize() ).done(function(data){
    			console.log("Saved data "+data);
    		});
    		
    	});
    	$("#principle").change(function(){
    		var selected = $('#principle option:selected').val();
    		var listItem = "";
    		var practiceList  = practiceMap[""+selected];
    		console.log(practiceList);
    		for(var index=0;index<practiceList.length;index++)
    		{
    			var practiceDetails = practiceList[index];
    			listItem += "<option value=\" "+practiceDetails.practiceId+"\">"+practiceDetails.shortName+"</option>" ;
    		}
    		 $("#practice").html(listItem);
    	});
    	
    });
    </script>
  </body>
</html>