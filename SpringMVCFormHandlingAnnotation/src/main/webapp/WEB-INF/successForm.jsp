<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Spring MVC form submission</title>
</head>

<body>
Dear ${form.name}, 
<br>
Your mail is ${form.email}
<br>
You chose the courses below:
<br>
	<c:forEach var="course" items="${form.courses}">  
			<c:out value="${course}"/><br>
	</c:forEach>
<br>
${form.tutor} will be your tutor!
<br>
Your hidden nickname is ${form.hiddenMessage}
</body>
</html>
