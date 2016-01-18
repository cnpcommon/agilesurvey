/**
 * author Raju Parashar
 */

var genchart = function(dataYTarget, dataYActual, dataYPrevious, dataXAxis) {

	$('#container')
			.highcharts(
					{

						chart : {
							polar : true,
							type : 'line'
						},

						title : {
							text : 'Agile Leadership and Collaboration Assessment <br/>Summary',
							style: {
				                color: '#000000',
				                fontWeight: 'bold',
				                fontSize : '12px'
				            }
						},

						pane : {
							size : '80%'
						},

						xAxis : {
							categories : dataXAxis,
							tickmarkPlacement : 'on',
							lineWidth : 0
						},

						yAxis : {
							gridLineInterpolation : 'polygon',
							lineWidth : 0,
							min : 0
						},

						tooltip : {
							shared : true,
							pointFormat : '<span style="color:{series.color}">{series.name}: <b>{point.y:,.1f}</b><br/>'
						},

						legend : {
							align : 'center',
							layout : 'horizontal'
						},

						series : [ {
							name : 'Target',
							data : dataYTarget,
							pointPlacement : 'on'
						}, {
							name : 'Current',
							data : dataYActual,
							pointPlacement : 'on'
						}, {
							name : 'Previous',
							data : dataYPrevious,
							pointPlacement : 'on'
						} ]

					});

};