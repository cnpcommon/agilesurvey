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
  	<%@include file="nav.html" %>
  	<div class="well">
  	<h1>New  Maturity Assessment  </h1>
  	<form class="form-horizontal" role="form" action="saveAssesmentDetails.wss" method="post" id="surveyDetailsFrm" >
  		<input type="hidden" name="assesmentId" value="" />
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
					 
		  	</div><!--  End of panel body -->
		</div>
		<div class="panel panel-primary">
	  		<div class="panel-heading panel-primary"><b>Questions setup </b> <span class="label label-warning">0 Selected</span></div>
		  	<div class="panel-body">
		  		 <!--  Adding the questions in the collapsible accordian -->
		  		 <div class="panel-group" id="accordion">
		  		 
		  		 	<c:forEach items="${indicatorList}" var="indicatorMap" >
		  		 	<div class="panel panel-default">
		  		 	 <!--  Question panel -->
		  		 		<div class="panel-heading">
		  		 			<h4 class="panel-title">
						        <input type="checkbox" name="questionId" value="${indicatorMap.principleId}_${indicatorMap.practiceId}"/>
						        <a data-toggle="collapse" data-parent="#accordion" href="#quid_${indicatorMap.principleId}_${indicatorMap.practiceId}"><c:out value="${principleMap[indicatorMap.principleId].description}"/> &nbsp; <c:out value="${practiceMap[indicatorMap.practiceId].shortName}" /></a>
					        </h4>
		  		 		</div>
		  		 		<div id="quid_${indicatorMap.principleId}_${indicatorMap.practiceId}" class="panel-collapse collapse">
						      <div class="panel-body">
						      	
						      		<c:forEach items="${levels}" var="level" >
						      			<c:set var="levelValueStr">${level.level}</c:set>
						      			<c:if test="${ (level.level > 0)  && ( indicatorMap.levelIndicatorMap[levelValueStr] != null ) }" >
						      				<div class="panel-group">
												<div class="panel panel-info">
													<div class="panel-heading">
													<h4 class="panel-title">
														<a data-toggle="collapse" href="#${indicatorMap.levelIndicatorMap[levelValueStr].questionid}"><c:out value="${level.name}" /></a>
													</h4>
													</div>
													<div id="${indicatorMap.levelIndicatorMap[levelValueStr].questionid}" class="panel-collapse collapse">
														<div class="panel-body">
															<c:out value="${indicatorMap.levelIndicatorMap[levelValueStr].indicatorText }" />
														</div>
													</div>
												</div>
											</div>
						      			</c:if>
						      		</c:forEach>
						      		
						      	
						      </div>
    					</div>
		  		 	</div>
		  		 	
		  		 	</c:forEach>
		  		 </div><!--  End of accordian -->
		  		 <div class="col-sm-10">&nbsp;</div>
				<div class="col-sm-2"><button type="button" class="btn btn-primary btn-lg" id="saveAndReleaseBtn">Save & Release</button></div>
		  	</div>
		 </div>
	</form>
	<div class="modal fade" id="alertModal" role="dialog">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	        <div class="modal-header">
	          <h4 class="modal-title">Save status</h4>
	        </div>
	        <div class="modal-body">
	          <p id="stausText"></p>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
	        </div>
	      </div>
	    </div>
	</div>
	</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
     <script type="text/javascript">
     
     	$(document).ready(function(){
     	
     		$("#saveAndReleaseBtn").click(function(){
     			console.log("Save operation is on");
     			//First do the validation
     			if(validateForm())
     			{
     				$.post("saveAssesmentDetails.wss", $( "#surveyDetailsFrm" ).serialize()).done(function(data){
     					var serverResponse = eval("("+ data+")");
     					if(serverResponse.status=="0")
     					{
     						console.log("Save is successful");
     					}
     					else
     					{
     						console.log("Save is NOT successfull");
     					}
     				});
     			}
     			else
     			{
     				alert("Validation error");
     			}
     		});
     	
     	});
     	function showMessage(message)
     	{
     		//Set the message 
     		$("#stausText").html(message);
     		//Show the modal window
     		$("#alertModal").modal("show");
     	}
     	function validateForm()
     	{
     		//TODO: Form validation 
     		return true;
     	}
     </script>
  </body>
</html>