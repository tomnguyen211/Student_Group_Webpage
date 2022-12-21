<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"/>
<title>Insert title here</title>
</head>
<body>
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="HomepageHW2">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page">Group List</li>
  </ol>
</nav>
<a class="btn btn-primary" href="GroupAdd" role="button">Add Group</a>
<table class="table table-bordered">
	<thead>
	    <tr>
	      <th scope="col">Group</th>
	      <th scope="col">Members</th>
	      <th scope="col"></th>
	    </tr>
 	</thead>
  	<tbody>
  	<c:forEach items="${entries_group}" var="entry" varStatus="loop">
  		<tr>
  			<th scope="row">${entry.getGroupNumer()}</th>
  			<td>
					<c:forEach items="${entry.getStudentList()}" var="student">
						${student.getName()}
					</c:forEach>
			</td>
			<td><a href="GroupEdit?id=${entry.id}">Edit</a></td>				
  		</tr>
  	</c:forEach>
	</tbody>
</table>
</body>
</html>