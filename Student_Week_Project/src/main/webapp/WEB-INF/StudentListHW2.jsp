<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"/>
<title>Student List</title>
</head>
<body>
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="HomepageHW2">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page">Student List</li>
  </ol>
</nav>

<a class="btn btn-primary" href="StudentAdd" role="button">New Student</a>

<table class="table table-bordered">
	<thead>
	    <tr>
	      <th scope="col">Student</th>
	      <th scope="col">Age</th>
	      <th scope="col">Parent</th>
	      <th scope="col">Email</th>
	      <th scope="col">Group</th>
	      <th scope="col"></th>
	    </tr>
 	</thead>
  	<tbody>
  	<c:forEach items="${entries_student}" var="entry" varStatus="loop">
  		<tr>
  			<th scope="row">${ entry.getName()}</th>
  			<td>${ 2022 -  entry.getBirthYear() }</td>
			<td>${ entry.getParentName() }</td>
			<td>${entry.getParentEmail() }</td>
			<td>${entry.getGroupName() }</td>
			<td><a href="StudentEdit?id=${entry.id}">Edit</a></td>				
  		</tr>
  	</c:forEach>
	</tbody>
</table>
</body>
</html>