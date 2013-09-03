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
			axes : {
				xaxis : {
					label : 'Location',
					pad : 0
				},
				yaxis : {
					label : 'Amount of patterns',
					pad : 0,
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
