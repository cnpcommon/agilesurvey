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
	<%@include file="nav.html"%>
	<div class="container" style="padding-right: 10%;">
		<h2>Agile Team Maturity Dashboard</h2>
		<div class="row">
			<div class="col-sm-3">
				<div class="wall">
					<div class="panel panel-primary">
						<div class="panel-heading">Assesment List</div>
						<div class="panel-body">
						  <%-- <select>
						    <c:forEach var="item" items="${assesmentDetails}">
						      <option id="${item.assessementId}" class="assesment">${item.name}
						      <p>(${item.releaseDate})</p>
						     <c:forEach var="squad" items="${item.squadList}">
								<input type="hidden" class="squadId" value="${squad}" />
							 </c:forEach>
							</option>
						    </c:forEach> 
						  </select>--%>
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
							<%-- <div class="list-group">
								<c:forEach var="item" items="${assesmentDetails}">
									<a href="#" id="${item.assessementId}"
										class="list-group-item assesment">
										<h4 class="list-group-item-heading">${item.name}</h4>
										<p class="list-group-item-text">Released :
											${item.releaseDate}</p>
									</a>
									<c:forEach var="squad" items="${item.squadList}">
										<input type="hidden" name="squadId" value="${squad}" />
									</c:forEach>
								</c:forEach>
							      </div> --%>
						</div>
					</div>
					<div>
						<div class="panel panel-primary">
							<div class="panel-heading">Squad List</div>
							<div class="panel-body">
							<div class="dropdown">
								<button class="btn btn-primary dropdown-toggle" id="menu2"
									type="button" data-toggle="dropdown">
									Select Squad <span class="caret"></span>
								</button>
								<!-- <ul class="dropdown-menu squadlist" role="menu"
									aria-labelledby="menu2"> -->
                                <ul class="dropdown-menu squadlist">

								</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="wall" style="width: 120%;">
					<div class="panel panel-primary">
						<div class="panel-heading">Maturity Metrics</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-sm-5">
									<div class="panel panel-primary">
										<div class="panel-heading">Assesment Results</div>
										<div class="panel-body">
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
									</div>
								</div>
								<div class="col-sm-7">
									<div class="panel panel-primary">
										<div class="panel-heading">Agile Leadership and
											Collaboration Assessment Summary</div>
										<div class="panel-body">
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

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/exporting.js"></script>
	<script src="js/highcharts.js"></script>
	<script src="js/highcharts-more.js"></script>
	<script src="js/genchart.js"></script>
	<script>
	var ajaxChart = function(url, param,bodyId) {
	var urlFull=url+"?"+param;
	$.get(urlFull, function(data) {
		var jsonData = JSON.parse(data);
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
		$(bodyId).html(templateData);
		genchart(dataYTarget, dataYCurrent, dataYPrevious, dataXAxis);
	});
};
$(window).load(	function() {
	$(".assesment").click(function() {
		var squadList = $(this).siblings(".squadId");
		var templateText = "";
		$(".squadlist").html("");
		for (i = 0; i < squadList.length; i++) {
				templateText += "<li role='presentation'>";
				templateText += "<a href='#' class='squadItem' data-parent='"+ $(this).attr('id')
						     + "' role='menuitem' id='"+ $(squadList[i]).val()+ "'>"
		   					 +"<h4 class='list-group-item-heading'>"+ $(squadList[i]).val()+"</h4>";
				templateText += "</a></li>";
				}
		console.log(templateText);
		$(".squadlist").html(templateText);
		$("#dataBody").html("");
		var url="getmaturityscore.wss";
		var paramString="assesmentId="+ $(this).attr('id');
		var templateData = "";
		ajaxChart(url,paramString,"#dataBody");
	});
	
	$(".squadlist").on('click','.squadItem',function() {
	    console.log("testing started");
		var dataVal = $(this).attr('id');
		var dataParent = $(this).attr('data-parent');
		console.log(dataVal + " "+ dataParent);
		var url = "getsquadscore.wss";
		var paramString="assesmentId="+ dataParent + "&squadId=" + dataVal;
		console.log(url+" "+paramString);
		ajaxChart(url,paramString,"#dataBody");
		});

	});
	</script>
</body>
</html>