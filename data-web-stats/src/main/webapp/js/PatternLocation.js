$(document).ready(function() {
	loadChart();
});

function loadChart() {
	$('#patLocationChartId').empty();
	$.get("getPatternLocationChart", {}, function(data) {
		var chart = new Chart({
			chartId : 'patLocationChartId',
			chartValues : data.patternsLocationList
		});

		chart.setSerieNames(data.bookTitles);
		chart.draw();
	});
};
