$(document).ready(function() {
	$("#showExampleNextButton").button({
		text : false,
		icons : {
			primary : "ui-icon-triangle-1-e"
		}
	}).click(function() {
		getNewRandomExample();
	});
	getNewRandomExample();
});

function getNewRandomExample() {
	$("showRandomExampleContainer").mask("Loading...");
	$.get("getRandomExample", {}, function(data) {
		$("showRandomExampleContainer").unmask();
		$("#showExampleBookTitle").text(data.bookTitle);
		$("#showExampleAuthor").text(data.author);
		$("#showExamplePattern").text(data.patternName);

		var lines = "";
		for ( var i = 0; i < data.lines.length; i++) {
			lines += data.lines[i] + "<br>";
		}
		var replaced = lines.replace(/ /g,
				"<span class='highlightedSpace'>&nbsp</span>");
		$("#showExampleContent").html(replaced);
	});
}