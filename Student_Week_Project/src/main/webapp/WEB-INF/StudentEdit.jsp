<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"/>
<title>Student Edit</title>
</head>
<body>
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="HomepageHW2">Home</a></li>
    <li class="breadcrumb-item"><a href="StudentListHW2">Student List</a></li>
    <li class="breadcrumb-item active" aria-current="page">Student Edit</li>
  </ol>
</nav>
<table class="table table-bordered">
	<form method='post'>
		<div class="mb-3">
			<tr>
			 	<td><label class="form-label">Name</label></td>
			 	<td><input type="text" class="form-control" name="b" value="${a.name}"></td>
			</tr>
			<tr>
			 	<td><label class="form-label">Birth Year</label></td>
			 	<td><input type="text" class="form-control" name="c" value="${a.getBirthYear()}"></td>
			</tr>
			<tr>
			 	<td><label class="form-label">Parent Name</label></td>
			 	<td><input type="text" class="form-control" name="d" value="${a.getParentName()}"></td>
			</tr>
			<tr>
			 	<td><label class="form-label">Parent Email</label></td>
			 	<td><input type="text" class="form-control" name="e" value="${a.getParentEmail()}"></td>
			</tr>
			<tr>
			<td><label class="form-label">Group</label></td>
			<td><select class="form-select" name="f">			
				  <option selected value="">${a.getGroupName()}</option>
				  <c:forEach items="${entries_group}" var="entry">
				  		<c:if test = "${a.getGroupName() != entry.getGroupNumer()}">
				  			 <option value="${ entry.getGroupNumer()}">${ entry.getGroupNumer()}</option>
				  		</c:if>
				  </c:forEach>
				  </select>
		  	</td>
		  	</tr>
		  	<tr>
		  		<td><input class="btn btn-primary" type="submit" value="Submit"></td>				
			</tr>	
		</div>
	</form>
</table>
</body>
</html>