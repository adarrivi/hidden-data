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

$('#loadChartButton').click(
		function(event) {
			$.get("getPatternsChart", {}, function(data) {
				alert(data[0].patternRepetitions);
				var s1 = [ 7, 5, 3, 4 ];
				var s2 = [ 7, 5, 3, 4 ];
				var s3 = [ 14, 9, 3, 8 ];
				plot3 = $.jqplot('chart3', [ s1, s2, s3 ], {
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
							// Don't pad out the bottom of the data range. By
							// default,
							// axes scaled as if data extended 10% above and
							// below the
							// actual range to prevent data points right on grid
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
				// Bind a listener to the "jqplotDataClick" event. Here, simply
				// change
				// the text of the info3 element to show what series and ponit
				// were
				// clicked along with the data for that point.
				$('#chart3').bind(
						'jqplotDataClick',
						function(ev, seriesIndex, pointIndex, data) {
							$('#info3').html(
									'series: ' + seriesIndex + ', point: '
											+ pointIndex + ', data: ' + data);
						});
			});
		});
