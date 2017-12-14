
<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Book"%>
<%@page import="java.util.List"%>
<%
	List<Book> books = (List<Book>) request.getAttribute("booklist");
%>
<html>
<h2>Delete Book</h2>

<form action="deleteBook" method="post" >
 <p>Select a Book to Delete</p>
 
	<select class="selectpicker show-menu-arrow" name="bookid" style="width:198px"  multiple data-actions-box="true" multiple title="choose one or more Books ">
		<% for(Book a : books) { %>
			<option data-icon="glyphicon glyphicon-book"  value="<%=a.getBookid()%>"><%=a.getTitle()%></option>
		<% } %>
	</select><br/><br/>	
 	<input type="submit" />
</form>
</html>



