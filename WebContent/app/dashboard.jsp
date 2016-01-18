<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<%@include file="nav.jsp"%>
	<div class="container" style="padding-right: 10%;">
		<h2>Agile Team Maturity Dashboard</h2>
		<div class="row">
			<%-- 			<div class="col-sm-3">
				<div class="wall">
					<div class="panel panel-primary">
						<div class="panel-heading">Assesment List</div>
						<div class="panel-body">
						  <select>
						    <c:forEach var="item" items="${assesmentDetails}">
						      <option id="${item.assessementId}" class="assesment">${item.name}-${item.releaseDate}
						     <c:forEach var="squad" items="${item.squadList}">
								<input type="hidden" class="squadId" value="${squad}" />
							 </c:forEach>
							</option>
						    </c:forEach> 
						  </select>
						  <div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" id="menu1"
								type="button" data-toggle="dropdown">
								Select Assessment <span class="caret"></span>
							</button>
							<!-- <ul class="dropdown-menu" role="menu" aria-labelledby="menu1"> -->
							<ul class="dropdown-menu dropdown-menu-right">
								<c:forEach var="item" items="${assesmentDetails}">
									<li role="presentation"><a href="#"
										id="${item.assessementId}" role="menuitem" class="assesment">
											<h4 class="list-group-item-heading">${item.name}</h4>
											<p class="list-group-item-text">Released :
												${item.releaseDate}</p>
									</a> <c:forEach var="squad" items="${item.squadList}">
											<input type="hidden" class="squadId" value="${squad}" />
										</c:forEach></li>
								</c:forEach>
							</ul>
							</div>
							
						</div>
					</div>
					<div>
						<div class="panel panel-primary">
							<div class="panel-heading">Squad List</div>
							<div class="panel-body">
<!-- 							<div class="dropdown">
								<button class="btn btn-primary dropdown-toggle" id="menu2"
									type="button" data-toggle="dropdown">
									Select Squad <span class="caret"></span>
								</button>
								
                                <ul class="dropdown-menu squadlist">

								</ul>
							</div> -->
							
							</div>
						</div>
					</div>
				</div>
			</div> --%>
			<div class="col-sm-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div
							style="text-align: center; font-size: 1.2em; font-weight: bold;">
							<span>Agile Team Maturity Dashboard</span>
						</div>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-sm-6">
								<label>Select Assesment</label>
								<select id="assesmentList">
									<c:forEach var="item" items="${assesmentDetails}">
										<option id="${item.assessementId}" class="assesment" 
										data-child='${item.squadList}'>${item.name}-${item.releaseDate}										
										</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-6">
								<label>Select Squad</label> 
								<select class="squadlist">
                                  
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="panel panel-primary">
									<div class="panel-heading"></div>
									<div class="panel-body">
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
														<td class="success" id="teamName">XXXXX</td>
													</tr>
													<tr>
														<td><strong>Team size:</strong></td>
														<td class="success">XXXXX</td>
													</tr>
													<tr>
														<td><strong>Assessment Type:</strong></td>
														<td class="success">Regular</td>
													</tr>
												</tbody>
											</table>
											<table class="table table-bordered table-striped">
												<thead>
													<tr>
														<th>Practice</th>
														<th>Target</th>
														<th>Current</th>
														<th>Previous</th>
													</tr>
												</thead>
												<tbody id="dataBody" style="font-size: x-small;">

												</tbody>
											</table>

										</div>
										<div class="col-sm-7 text-center">
											<div class="form-group text-center">
												<label>Agile Leadership and Collaboration Assessment
													Summary</label>
											</div>
											<div id="container"></div>


										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- </div> -->




				</div>
			</div>

		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/exporting.js"></script>
	<script src="js/highcharts.js"></script>
	<script src="js/highcharts-more.js"></script>
	<script src="js/genchart.js"></script>
	<script>
		var ajaxChart = function(url, param, bodyId) {
			var urlFull = url + "?" + param;
			$.get(urlFull, function(data) {
				var jsonData = JSON.parse(data);
				var dataYTarget = [];
				var dataYCurrent = [];
				var dataYPrevious = [];
				var dataXAxis = [];
				var templateData = "";
				if(jsonData.length){
				for (i = 0; i < jsonData.length; i++) {
				 var currentVal=parseFloat(jsonData[i].currentScore).toFixed(1);
					templateData += "<tr><td>" + jsonData[i].practiceName
							+ "</td><td>4</td><td>" + currentVal
							+ "</td><td>2</td></tr>";
					dataXAxis[i] = jsonData[i].practiceName;
					dataYTarget[i] = 4;
					dataYCurrent[i] =parseFloat(jsonData[i].currentScore);
					dataYPrevious[i] = 2;
				}
				genchart(dataYTarget, dataYCurrent, dataYPrevious, dataXAxis);
				}
				else
				{
				 templateData="No data found";
				 $("#container").html("No data found - Not able to generate graph");
				}
				$(bodyId).html(templateData);
				
			});
		};

		var selectClick = function(id) {
		    var dataChild=$("#"+id).attr('data-child'); 
			var squadList = dataChild.substring(1,dataChild.length-1).split(',');
			var templateText="<option>Select squad</option>";
			for (i = 0; i < squadList.length; i++) {
				templateText += "<option class='squadItem' data-parent='"
						+ id + "' role='menuitem' id='"
						+ squadList[i] + "'>"+ squadList[i] + "</option>";
			}
			console.log(templateText);
			$(".squadlist").html(templateText);
			$("#dataBody").html("");
			var url = "getmaturityscore.wss";
			var paramString = "assesmentId=" + $("#"+id).attr('id');
			var templateData = "";
			ajaxChart(url, paramString, "#dataBody");

		};

		$(window)
				.load(
						function() {

                            selectClick($("#assesmentList .assesment:first-child").attr('id'));
							$(".assesment").click(function(){
							selectClick($(this).attr('id'));
							});

							$(".squadlist")
									.on('click','.squadItem',
											function() {
												console.log("testing started");
												var dataVal = $(this)
														.attr('id');
												$("#teamName").html(dataVal);		
												var dataParent = $(this).attr(
														'data-parent');
												console.log(dataVal + " "
														+ dataParent);
												var url = "getsquadscore.wss";
												var paramString = "assesmentId="
														+ dataParent
														+ "&squadId=" + dataVal;
												console.log(url + " "
														+ paramString);
												ajaxChart(url, paramString,
														"#dataBody");
											});

						});
	</script>
</body>
</html>