$(document).ready(function() {
	loadChar();
});

function loadChar() {
	$('#patPerBookChartId').empty();
	$.get("getPatternsChart", {}, function(data) {
		var chartValues = [];
		for ( var i = 0; i < data.patternRepetitions.length; i++) {
			chartValues[i] = data.patternRepetitions[i];
		}
		var series = [];
		for ( var i = 0; i < data.patternNames.length; i++) {
			series[i] = {
				label : data.patternNames[i]
			};
		}
		var ticks = [];
		for ( var i = 0; i < data.bookTitles.length; i++) {
			ticks[i] = data.bookTitles[i];
		}
		plot3 = $.jqplot('patPerBookChartId', chartValues, {
			// Tell the plot to stack the bars.
			stackSeries : true,
			captureRightClick : true,
			series : series,
			seriesDefaults : {
				renderer : $.jqplot.BarRenderer,
				rendererOptions : {
					// Put a 30 pixel margin between bars.
					barMargin : 30,
					// Highlight bars when mouse button pressed.
					// Disables default highlighting on mouse over.
					highlightMouseDown : true
				},
				pointLabels : {
					show : true
				}
			},
			axes : {
				xaxis : {
					renderer : $.jqplot.CategoryAxisRenderer,
					ticks : ticks
				},
				yaxis : {
					// Don't pad out the bottom of the data range.
					// By
					// default,
					// axes scaled as if data extended 10% above and
					// below the
					// actual range to prevent data points right on
					// grid
					// boundaries.
					// Don't want to do that here.
					padMin : 0
				}
			},
			legend : {
				show : true,
				location : 'e',
				placement : 'outside'
			}
		});
		// Bind a listener to the "jqplotDataClick" event. Here,
		// simply
		// change
		// the text of the info3 element to show what series and
		// ponit
		// were
		// clicked along with the data for that point.
		$('#patPerBookChartId').bind(
				'jqplotDataClick',
				function(ev, seriesIndex, pointIndex, data) {
					$('#info3').html(
							'series: ' + seriesIndex + ', point: ' + pointIndex
									+ ', data: ' + data);
				});
	});
}
