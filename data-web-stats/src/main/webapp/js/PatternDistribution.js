$(document).ready(function() {
	loadChart();
});

function loadChart() {
	$('#patDistributionChartId').empty();
	$('#patDistributionChartId').mask("Loading...");
	$.get("getPatternDistributionChart", {}, function(data) {
		$('#patDistributionChartId').unmask();
		var chart = new Chart({
			chartId : 'patDistributionChartId',
			chartValues : data.patternsDistributionList,
			title : "Pattern Distribution",
			axesDefaults  :{
				pad : 0
			},		
			axes : {
				xaxis : {
					label : 'Distribution in Book',
					tickOptions : {
						formatter : function(format, value) {							
							return (value*10) + "%";
						}
					}
				},
				yaxis : {
					label : 'Amount of patterns',
					labelRenderer : $.jqplot.CanvasAxisLabelRenderer,
					tickOptions : {
						formatter : function(format, value) {
							return value + "%";
						}
					}
				}
			}

		});

		chart.setSerieNames(data.bookTitles);
		chart.draw();
	});
};
