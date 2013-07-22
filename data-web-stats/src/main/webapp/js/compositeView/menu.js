$(document).ready(function() {
    $(document).ready(function() {
        $("#jMenu").jMenu();
    });
	$('#dbInfoNavLink').click(function(event) {
		document.location.href = 'DbInfo';
	});
	$('#patPerBookNavLink').click(function(event) {
		document.location.href = 'PatternsPerBook';
	});
	$('#patLocationNavLink').click(function(event) {
		document.location.href = 'PatternLocation';
	});
});
