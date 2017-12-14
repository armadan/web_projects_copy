<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Book"%>
<%@page import="java.util.List"%>
<%
	List<Book> books = (List<Book>) request.getAttribute("books");
%>
<html>
<h2>The Book in the Branches</h2>

<form action="borrowerBooks" method="post">
 Book:
	<select name="bookid" style="width:198px">
		<% for(Book a : books) { %>
			<option value="<%=a.getBookid()%>"><%=a.getTitle()%></option>
		<% } %>
	</select><br/>	
	
  <input type="hidden"  name="cardno" class="form-control" 
			value="<%= request.getAttribute("cardno")%>"  /><br/>
			
 <input type="hidden"  name="branchid" class="form-control" 
			value="<%= request.getAttribute("branchid")%>"  /><br/>
 
 		<button type="submit">Next</button>
</form>
</html>
