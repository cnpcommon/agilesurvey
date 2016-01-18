<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
		<div class="container">
		<div class="page-header" style="margin-top:-40px;">
			<h3>Agile Maturity Survey Tool ( Version : 0.1)</h3>
		</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div style="text-align:center;font-size:1.2em;font-weight: bold;"><span>Agile Team Maturity Dashboard</span></div>
						</div>
						<div class="panel-body">
						  <div class="row">
						  	<div class="col-sm-10">
						  		<div class="form-group">
						  		<label for="assessmentId">Select Assessment</label>
						  		<select name="assessmentId" id="assessmentId">
						  			<option value="" selected>Select assessment</option>
						  			<c:forEach  items="${assessmentList}"  var="assessmentDetails">
						  				<option value="${assessmentDetails.assessementId}">
						  					<c:out value="${assessmentDetails.name}"/>-<c:out value="${assessmentDetails.releaseDate}" />
						  				</option>
						  			</c:forEach>
						  		</select>
						  		</div>
						  	</div>
						  </div>
						  <div class="row">	
						  	<div class="col-sm-12">
						  		<div class="panel panel-primary">
						  			<div class="panel-heading" id="assesmentInfo"></div>
						  			<div class="panel-body">
						  				<div class="row">
						  					<div class="col-sm-5">
						  					<table class="table table-bordered small">
											    <tbody>
											      <tr>
											        <td><strong>Executive Leader:</strong></td>
											        <td>XXXXX</td>
											        
											      </tr>
											      <tr>
											        <td><strong>Domain:</strong></td>
											        <td class="success">XXXXX</td>
											      </tr>
											      <tr>
											        <td><strong>Subdomain Leader:</strong></td>
											        <td class="success">XXXXX</td>
											      </tr>
												  <tr>
											        <td><strong>Team name:</strong></td>
											        <td class="success">${squadDetails}</td>
											      </tr>
												  <tr>
											        <td><strong>Team size:</strong></td>
											        <td class="success">4</td>
											      </tr>
												  <tr>
											        <td><strong>Assessment Type:</strong></td>
											        <td class="success">Regular</td>
											      </tr>
											    </tbody>
											  </table>
											  <table class="table table-bordered table-striped small">
												<thead>
													<tr>
														<th>Practice</th>
														<th>Target</th>
														<th>Current</th>
														<th>Previous</th>
													</tr>
												</thead>
												<tbody id="dataBody" >

												</tbody>
											   </table>
						  					</div>
						  					<div class="col-sm-7 text-center">
						  						<div class="form-group text-center">
						  							<label >Assessment Date:&nbsp;</label>
						  							<label id="dtField"></label>
						  						</div>
						  						<div id="container"></div>
						  					</div>
						  				</div>
						  			</div>
						  		</div>
						  	</div>
						  </div>
						</div>
					</div>
				</div>
			</div>
				<div class="modal fade" id="alertModal" role="dialog">
				    <div class="modal-dialog modal-sm">
				      <div class="modal-content">
				        <div class="modal-header">
				          <h4 class="modal-title">Application Message</h4>
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
	<script src="js/highcharts.js"></script>
	
	<script src="js/highcharts-more.js"></script>
	<script src="js/genchart.js"></script>
	<script type="text/javascript">
		var _SQAD_NAME = "${squadDetails}";
		var _ASSESMENT_DT = {};
		<c:forEach  items="${assessmentList}"  var="assessmentDetails">
			_ASSESMENT_DT["${assessmentDetails.assessementId}"] = "${assessmentDetails.releaseDate}";
		</c:forEach>
		var _DEFAULT_ID = "";
		<c:if test="${fn:length(assessmentList) >0}" >
			_DEFAULT_ID = "${assessmentList[0].assessementId}" ;
		</c:if>
		$(document).ready(function(){
			
			$("#assessmentId").change(function(event){
			
				//Depending on selected value get the info
				var selectedValue = $(this).val();
				console.log(selectedValue);
				if(selectedValue != "")
				{
					loadResultsForAssessment(selectedValue);
				}
				else
				{
					showMessage("Please select an assesment from the dropdown");
				}
				
			});
			
			if(_DEFAULT_ID!= "")
			{
				loadResultsForAssessment(_DEFAULT_ID);
				$("#assessmentId").val(_DEFAULT_ID);
			}
		
		}); //End of document.ready
		
		function showMessage(text)
		{
			//Set the message 
     		$("#stausText").html(text);
     		//Show the modal window
     		$("#alertModal").modal("show");
		}
		function loadResultsForAssessment(assessmentId)
		{
		
			$.get("getSurveyResultsForSqud.wss?assessmentId="+assessmentId+"&squadId="+_SQAD_NAME).done(function(data){
					
						var serverResponse = JSON.parse(data);
						if(serverResponse.status=="0")
						{
							populateGraph(serverResponse.payload);
							$("#dtField").html(_ASSESMENT_DT[assessmentId]);
						}
						else
						{
							showMessage("No survey results found ");
						}
						
					}).error(function(err){
						
						showMessage("Unable to retrieve results "+ err);
					});
					
		}
		/*
		 * Method custsy RAJU PRARASHAR
		*/
		function populateGraph(data)
		{
			var jsonData = data;
			var dataYTarget = [];
			var dataYCurrent = [];
			var dataYPrevious = [];
			var dataXAxis = [];
			var templateData = "";
			
			for (i = 0; i < jsonData.length; i++) {
				templateData += "<tr><td>" + jsonData[i].practiceName
						+ "</td><td>4</td><td>" + jsonData[i].currentScore
						+ "</td><td>2</td></tr>";
				dataXAxis[i] = jsonData[i].practiceName;
				dataYTarget[i] = 4;
				dataYCurrent[i] = parseInt(jsonData[i].currentScore);
				dataYPrevious[i] = 2;
			}
			$("#dataBody").html(templateData);
			genchart(dataYTarget, dataYCurrent, dataYPrevious, dataXAxis);
				
		}
	
	</script>
</body>
</html>