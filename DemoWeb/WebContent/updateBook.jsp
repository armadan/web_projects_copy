
<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Book"%>
<%@page import="java.util.List"%>
<%
	List<Book> books = (List<Book>) request.getAttribute("booklist");
%>
<html>
<h2>Update Book</h2>

<form action="updateBook" method="post">
 Book:
	<select  class="selectpicker show-menu-arrow"  name="bookid" >
		<% for(Book a : books) { %>
			<option data-icon="glyphicon glyphicon-book"  value="<%=a.getBookid()%>"><%=a.getTitle()%></option>
		<% } %>
	</select><br/><br/><br/>
	
 <input type="text" style="width: 400px;" name="booktitle"  class="form-control" 
			placeholder="Enter title name" required autofocus /><br/><br/>
 	<input type="submit" />
 	
</form>
</html>



