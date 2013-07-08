$(document).ready(function() {
	$.get("getDatabaseInfo", {}, function(data) {
		$("#authorNumber").text("Authors: " + data.numberOfAuthors);
		$("#bookNumber").text("Books: " + data.numberOfBooks);
		$("#patternNumber").text("Patterns: " + data.numberOfPatterns);
	})
});
