var setResultMessage = function(message) {
	$("#result").text(message);
};
$('#createUserSubmit').click(function(event) {
	$.post("createUser", {}, function(data) {
		setResultMessage('User created successfully');
	});
});
$('#listUsersSubmit').click(function(event) {
	$.get("list", {}, function(data) {
		setResultMessage('Number of users: ' + data.length);
	});
});