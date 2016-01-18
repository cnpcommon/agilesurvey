<%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
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
  	<div class="well">
  	<h1>Maturity Assessment ${assesment.name} dated ${assesment.releaseDate}  </h1>
  	<form class="form-horizontal" role="form" action="saveSurveyResults.wss" method="post" id="surveyDetailsFrm" >
  		<input type="hidden" name="assesmentId" value="${assesment.assessementId}" />
	   
		<div class="panel panel-primary">
	  		<div class="panel-heading panel-primary"><b>Survey Questions </b> <span class="label label-warning pull-right" id="totalCount">0 Selected</span></div>
		  	<div class="panel-body">
		  		 <!--  Adding the questions in the collapsible accordian -->
		  		 <div class="panel-group" id="accordion">
		  		 
		  		 	<c:forEach items="${assesment.indicatorMap}" var="indicatorMap" >
		  		 	<div class="panel panel-default">
		  		 	 <!--  Question panel -->
		  		 		<div class="panel-heading">
		  		 			<h4 class="panel-title">
						        <a data-toggle="collapse" data-parent="#accordion" href="#quid_${indicatorMap.principleId}_${indicatorMap.practiceId}"><c:out value="${principleMap[indicatorMap.principleId].description}"/> &nbsp; <c:out value="${practiceMap[indicatorMap.practiceId].shortName}" /></a>
					        	<span id="stat_${indicatorMap.itemId}" class="label label-default pull-right"></span>
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
														<span class="pull-right" >Select&nbsp;<input type="radio" name="${indicatorMap.itemId}" value="${level.level}" /></span>
													</h4>
													</div>
													<div id="${indicatorMap.levelIndicatorMap[levelValueStr].questionid}" class="panel-collapse collapse in">
														<div class="panel-body">
															<c:out value="${indicatorMap.levelIndicatorMap[levelValueStr].indicatorText }" escapeXml="false"/>
														</div>
													</div>
												</div>
											</div>
						      			</c:if>
						      		</c:forEach>
						      		<!--  Now adding do not know or NA -->
						      		 <div class="panel-group">
									  <div class="panel panel-warning">
									    <div class="panel-heading">
									      <h4 class="panel-title">
									        <label>Do not know</label>
									        	<span class="pull-right" >Select&nbsp;<input type="radio" name="${indicatorMap.itemId}" value="0" /></span>
									      </h4>
									    </div>
									  </div>
									 </div>
						      		<div class="panel-group">
									  <div class="panel panel-warning">
									    <div class="panel-heading">
									      <h4 class="panel-title">
									        <label>Not applicable</label> 
									        <span class="pull-right" >Select&nbsp;<input type="radio" name="${indicatorMap.itemId}" value="0" /></span>
									      </h4>
									    </div>
									  </div>
									 </div>
						      </div>
    					</div>
		  		 	</div>
		  		 	
		  		 	</c:forEach>
		  		 </div><!--  End of accordian -->
		  		 <div class="col-sm-10">&nbsp;</div>
				<div class="col-sm-2"><button type="button" class="btn btn-primary btn-lg  pull-right" id="submitSurvey"><span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;Submit</button></div>
		  	</div>
		 </div>
	</form>
	<form id="hidFrm" action="home.wss" method="post">
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
	          <button type="button" class="btn btn-info" data-dismiss="modal" id="modalCloseBtn">Close</button>
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
     	var error = false;
     	var questionCount = <c:out value="${fn:length(assesment.indicatorMap)}" /> ;
     	$(document).ready(function(){
     		//Add onclick handler to all the radio to show a batch
     		updateInitialStats();
     		
     		$("input[type=radio]").click(function(){
     			//Update the status of answered question count 
     			var n = $( "input[type=radio]:checked" ).length;
     			$("#totalCount").html(n + " of "+questionCount+ " questions are answered");
     			$( "input[type=radio]:checked" ).each(function(index,element){
     			
     				//console.log("Selected name "+ element.name);
     				$("#stat_"+element.name).html("Already answered");
     			});
     		});
     		$('#alertModal').on('hidden.bs.modal', function () {
    				// do something
    				if(!error)
    				{
    					//Go to home page
    					$("#hidFrm").submit();
    				}
			});
     		$("#submitSurvey").click(function(){
     			console.log("Save operation is on");
     			//First do the validation
     			if(validateForm())
     			{
     				error = false;
     				$.post("saveSurveyResults.wss", $( "#surveyDetailsFrm" ).serialize()).done(function(data){
     					var serverResponse = eval("("+ data+")");
     					if(serverResponse.status=="0")
     					{
     						console.log("Save is successful");
     						showMessage("Survey Resutls saved. Thank you.")
     					}
     					else
     					{
     						error = true;
     						console.log("Save is NOT successfull");
     						showMessage("Survey Resutls are not saved.")
     					}
     				});
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
     		error = true;
     		//TODO: Form validation 
     		var n = $( "input[type=radio]:checked" ).length;
     		if(n<questionCount)
     		{
     			showMessage("You have not answered all the questions.<br/>Please answer all the questions.");
     			return false;
     		}
     		error = false;
     		return true;
     	}
     	function updateInitialStats()
     	{
     		//$("#totalCount").html(" 0 of "+questionCount+ " questions are answered");
     		var n = $( "input[type=radio]:checked" ).length;
   			$("#totalCount").html(n + " of "+questionCount+ " questions are answered");
   			$( "input[type=radio]:checked" ).each(function(index,element){
    				$("#stat_"+element.name).html("Already answered");
    		});
     	}
     </script>
  </body>
</html>