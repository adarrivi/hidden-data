<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/PatternsPerBook.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		onReady()
	});
</script>

</head>
<body>
	<div id="patternResult" class="patterns_result_div">Result</div>
	<div class="patterns_button_div">
		<button id="addPatternButton">Add Pattern</button>
		<button id="numberOfPatternsButton">Get Number Of Patterns</button>
		<button id="loadChartButton">Load Chart</button>
	</div>
	<div>
		<p class="text">
			You Clicked: <span id="info3">Nothing yet.</span>
		</p>
		<div id="chart3" class="patPerBookBarChart"></div>
	</div>
</body>
</html>