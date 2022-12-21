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
    <li class="breadcrumb-item"><a href="GroupListHW2">Group List</a></li>
    <li class="breadcrumb-item active" aria-current="page">Group Edit</li>
  </ol>
</nav>
<table class="table table-sm">
	<form method ="post">
		<div class="col-auto">	
			<tr>
				 	<td><label class="form-label">Group Name</label></td>
				 	<td><input type="text" class="form-control" name="values" value="${a.getGroupNumer()}""></td>	
			</tr>
		</div>	
		<div class="form-check">
		 <c:forEach items="${a.getStudentList()}" var="student">
			 <tr>
				 	<td><label class="form-label">${student.getName()}</label></td>
				 	<td> <input class="form-check-input" type="checkbox" name="values" value="${student.getId()}">
						  <label class="form-check-label" for="flexCheckDefault">
						    Remove
						  </label>
				  	</td>
				 	
			</tr>
		 </c:forEach>	
		</div>				
		  	<tr>
		  		<td><input class="btn btn-primary" type="submit" value="Submit"></td>
		  		<td><a class="btn btn-primary" href="GroupListHW2" role="button">Back</a></td>			
			</tr>	
	</form>
</table>
</body>
</html>