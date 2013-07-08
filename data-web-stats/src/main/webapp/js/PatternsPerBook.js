$(document).ready(function() {
	var setResultMessage = function(message) {
		$("#patternResult").text(message);
	};
	$('#addPatternButton').click(function(event) {
		$.post("addPattern", {}, function(data) {
			setResultMessage('Pattern added successfully');
		});
	});
	$('#numberOfPatternsButton').click(function(event) {
		$.get("list", {}, function(data) {
			setResultMessage('Number of patterns: ' + data.length);
		});
	});
	loadCharButton();
});

function loadCharButton() {
	$('#loadChartButton').click(
			function(event) {
				$('#chart3').empty();
				$.get("getPatternsChart", {}, function(data) {
					var chartValues = [];
					for ( var i = 0; i < data.length; i++) {
						chartValues[i] = data[i].patternRepetitions;
					}
					plot3 = $.jqplot('chart3', chartValues, {
						// Tell the plot to stack the bars.
						stackSeries : true,
						captureRightClick : true,
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
								renderer : $.jqplot.CategoryAxisRenderer
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
					$('#chart3').bind(
							'jqplotDataClick',
							function(ev, seriesIndex, pointIndex, data) {
								$('#info3').html(
										'series: ' + seriesIndex + ', point: '
												+ pointIndex + ', data: '
												+ data);
							});
				});
			});
}
