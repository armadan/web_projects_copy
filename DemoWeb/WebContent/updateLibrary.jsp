

<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%
	List<LibraryBranch> branches = (List<LibraryBranch>) request.getAttribute("branches");
%>
<html>
<h2>Update library</h2>

<form action="updateLibrary" method="post">
 Library:<br/>
	<select class="selectpicker show-menu-arrow" name="branchid"  >
		<% for(LibraryBranch a : branches) { %>
			<option data-icon="glyphicon glyphicon-book"  value="<%=a.getBranchid()%>"><%=a.getBranchname()%></option>
		<% } %>
	</select><br/><br/><br/>	
 <input type="text" style="width: 400px;" name="name"  class="form-control" 
                placeholder="Enter Library  name" required autofocus /><br/>
                
 <input type="text" style="width: 400px;" name="address"  class="form-control" 
                placeholder="Enter  library address" required autofocus /><br/><br/>
 	<input type="submit" />
</form>
</html>
