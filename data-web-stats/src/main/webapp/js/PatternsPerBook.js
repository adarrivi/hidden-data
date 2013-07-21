$(document).ready(function() {
	loadCharHor();
});

function loadCharHor() {
	$('#patPerBookChartId').empty();
	$.get("getPatternsChart", {}, function(data) {
		var chartValues = getChartValues(data);
		var series = getSeries(data);

		$.jqplot('patPerBookChartId', chartValues, {
			series : series,
			legend : {
				show : true,
				location : 'e',
				placement : 'outside'
			},
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

function getSeries(data) {
	var series = [];
	for ( var i = 0; i < data.patternNames.length; i++) {
		series[i] = {
			label : data.patternNames[i]
		};
	}
	return series;
};

