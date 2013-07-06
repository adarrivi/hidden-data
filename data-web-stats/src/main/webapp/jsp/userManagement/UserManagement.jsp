<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(document).ready(function() {
	    $.getScript('/js/UserManagement.js');
	})
</script>

<title>User Management</title>

</head>
<body>
	<div id="result">Result</div>
	<div class="userMng">
		<button id="createUserSubmit">Create User</button>
		<button id="listUsersSubmit">List Users</button>
	</div>

</body>
</html>