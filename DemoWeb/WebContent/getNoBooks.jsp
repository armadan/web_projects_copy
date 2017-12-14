<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Book"%>
<%@page import = "com.library.gcit.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%
List<Book> books = (List<Book>) request.getAttribute("booklist");
List<LibraryBranch> branches = (List<LibraryBranch>) request.getAttribute("branches");
%>
<html>
<h2> Update Copies</h2>

<form action="getNoBooks" method="post">
 <p>Library Branch</p>
	<select  class="selectpicker show-menu-arrow" name="branchid" >
		<% for(LibraryBranch a : branches) { %>
			<option  data-icon="glyphicon glyphicon-list"  value="<%=a.getBranchid()%>"><%=a.getBranchname()%></option>
		<% } %>
	</select><br/>

 <p>Select Book </p>
	<select  class="selectpicker show-menu-arrow" name="bookid" >
		<% for(Book a : books) { %>
			<option  data-icon="glyphicon glyphicon-book"  value="<%=a.getBookid()%>"><%=a.getTitle()%></option>
		<% } %>
	</select><br/><br/>	

	
	<button type="submit">Next</button>
</form>
</html>



