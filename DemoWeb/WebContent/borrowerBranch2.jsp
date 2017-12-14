<%@include file="includes.jsp"%>
<%@page import ="com.library.gcit.service.Librarian" %> 
<%@page import = "com.library.gcit.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%
Librarian  libService = Librarian.getInstance();
List<LibraryBranch> branches = libService.getAllBranches();
%>
<html>
<h2>Select a Library Branch you want to return a Book</h2>

<form action="borrowerBranch2" method="post">
 Library:
	<select name="branchid" style="width:198px">
		<% for(LibraryBranch a : branches) { %>
			<option value="<%=a.getBranchid()%>"><%=a.getBranchname()%></option>
		<% } %>
	</select><br/>
	
  <input type="hidden"  name="cardno" class="form-control" 
			value="<%= request.getAttribute("cardno")%>"  /><br/>
	
	<button type="submit">NEXT</button>	
	
</form>
</html>
