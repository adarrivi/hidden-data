$(document).ready(function() {
	loadChart();
});

function loadChart() {
	$('#patPerBookChartId').empty();
	$('#patPerBookChartId').mask("Loading...");
	$.get("getPatternsChart", {}, function(data) {
		$('#patPerBookChartId').unmask();
		var chart = new Chart({
			chartId : 'patPerBookChartId',
			chartValues : getChartValues(data),
			seriesDefaults : {
				renderer : $.jqplot.BarRenderer,
				pointLabels : {
					show : true,
					location : 'e',
					edgeTolerance : -15
				},
				shadowAngle : 135,
				rendererOptions : {
					barDirection : 'horizontal'
				}
			},
			axes : {
				yaxis : {
					renderer : $.jqplot.CategoryAxisRenderer
				}
			}
		});

		chart.setSerieNames(data.patternNames);
		chart.draw();
	});
};

function getChartValues(data) {
	var chartValues = [];
	for ( var patternIndex = 0; patternIndex < data.patternRepetitions.length; patternIndex++) {
		var chartRow = [];
		for ( var bookIndex = 0; bookIndex < data.bookTitles.length; bookIndex++) {
			chartRow[bookIndex] = [
					data.patternRepetitions[patternIndex][bookIndex],
					data.bookTitles[bookIndex] ];
		}
		chartValues[patternIndex] = chartRow;
	}
	return chartValues;
};