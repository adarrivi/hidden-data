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