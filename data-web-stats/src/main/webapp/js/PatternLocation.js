$(document).ready(function() {
	loadChart();
});

function loadChart() {
	$('#patLocationChartId').empty();
	$.get("getPatternLocationChart", {}, function(data) {
		var chart = new Chart({
			chartId : 'patLocationChartId',
			chartValues : data.patternsLocationList,
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
