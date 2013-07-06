<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
  function add() {
    $(function() {
      $.post("addOperation", {
        inputNumber1 : $("#inputNumber1").val(),
        inputNumber2 : $("#inputNumber2").val()
      }, function(data) {
        $("#sum").replaceWith('<span id="sum">' + data + '</span>');
      });
    });
  }
</script>
<c:url var="toHomeUrl" value="redirectHome" />


<title>User</title>
</head>
<body>
  <h2>User Manager</h2>
  <table>
    <tr>
      <td>First Name</td>
    </tr>
    <tr>
      <td>Last Name</td>
    </tr>
    <tr>
      <td colspan="2">
        <div style="border: 1px solid #ccc; width: 250px;">
          Add Two Numbers: <br /> <input id="inputNumber1" type="text" size="5"> + <input id="inputNumber2" type="text" size="5"> <input
            type="submit" value="Add" onclick="add()" /> <br /> Sum: <span id="sum">(Result will be shown here)</span>
        </div>
      </td>
    </tr>
    <tr>
      <td>
        <form method="POST" action="${toHomeUrl}">
          <input type="submit" value="Redirect Home" />
        </form>
      </td>
    </tr>
  </table>
</body>
</html>
