<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	$(document).ready(
			function() {
				var setResultMessage = function(message) {
					$("#createResult").replaceWith(
							'<span id="createResult">' + message + '</span>');
				};
				$('#createUserSubmit').click(
						function(event) {
							$.post("createUser", {}, function(data) {
								setResultMessage('User created successfully');
							});
						});
				$('#listUsersSubmit').click(
						function(event) {
							$.get("list", {}, function(data) {
								setResultMessage('Number of users: ' + data.length);
							});
						});

			})
</script>

<title>User Management</title>

</head>
<body>
	<div style="border: 1px solid #ccc; width: 250px;">
		<input id="createUserSubmit" type="submit" value="Create" /> <br />Create<span
			id="createResult">(Result will be shown here)</span> <input
			id="listUsersSubmit" type="submit" value="List" /> <br />List<span
			id="listResult">(Result will be shown here)</span>
	</div>
</body>
</html>