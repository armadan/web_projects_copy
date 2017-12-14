<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.LibraryBranch"%>
<%@page import="java.util.List"%>


<html>
<div class="form-group">
	<form action="addBranch" method="post" >
		<input type="text" style="width: 400px;" name="branchName" class="form-control"
			placeholder="Enter Library Branch name" required autofocus/><br/>
			
			
			<input type="text" style="width: 400px;" name="branchAddress" class="form-control"
			placeholder="Enter Library Branch Address" required autofocus/><br/>
			
	
		<input type="submit" class="btn btn-primary"/> 
	</form>
</div>
</html>