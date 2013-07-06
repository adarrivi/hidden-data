<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Home title</title>
</head>
<body>
  <h2>Home body</h2>
  <table>
    <tr>
      <td><c:url var="addUrl" value="redirectToUser" />
        <form method="POST" action="${addUrl}">
          <input type="submit" value="Redirect to User Mng" />
        </form></td>
    </tr>
  </table>
</body>
</html>
