function Chart(settings) {
	this.settings = settings;
}

Chart.prototype.draw = function() {
	this.settings.legend = {
		show : true,
		location : 'e',
		placement : 'outside'
	};

	$.jqplot(this.settings.chartId, this.settings.chartValues, this.settings);
};

Chart.prototype.setSerieNames = function(serieNames) {
	var series = [];
	for ( var i = 0; i < serieNames.length; i++) {
		series[i] = {
			label : serieNames[i]
		};
	}
	this.settings.series = series;
};