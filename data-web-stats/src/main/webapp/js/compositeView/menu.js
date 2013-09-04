$(document).ready(function() {
    $(document).ready(function() {
        $("#jMenu").jMenu();
    });
	$('#dbInfoNavLink').click(function(event) {
		document.location.href = 'DbInfo';
	});
	$('#showExampleLink').click(function(event) {
		document.location.href = 'ShowExample';
	});	
	$('#patPerBookNavLink').click(function(event) {
		document.location.href = 'PatternsPerBook';
	});
	$('#patDistributionNavLink').click(function(event) {
		document.location.href = 'PatternDistribution';
	});
});
