<%@include file="includes.jsp"%>
<%@page import="com.library.gcit.entity.Publisher"%>
<%@page import = "com.library.gcit.entity.Author"%>
<%@page import="java.util.List"%>
<%
	List<Author> authors = (List<Author>) request.getAttribute("authorList");
	List<Publisher> publishers= (List<Publisher>) request.getAttribute("pubList");
%>
<html>
<h2>Add Book</h2>

<form action="addBook" method="post">

	Title: <input type="text" style="width: 400px;" name="bookTitle"  class="form-control" 
			placeholder="Enter title name" required autofocus/><br/>
	
	<p>Select Authors</p>
	<select  class="selectpicker show-menu-arrow"  name="authorId"  multiple data-actions-box="true" multiple title="choose one or more Authors ">
		<% for(Author a : authors) { %>
			<option data-icon="glyphicon glyphicon-user" value="<%=a.getAuthorid()%>"><%=a.getAuthorname()%></option>
		<% } %>
	</select><br/><br/>

<p> Select Publisher</p>
	<select class="selectpicker show-menu-arrow" name="pubId" >
		<% for(Publisher p : publishers) { %>
			<option data-icon="glyphicon glyphicon-home"  value="<%=p.getPublisherid()%>"><%=p.getPublishername() %></option>
		<% } %>
	</select><br/><br/>
	<input type="submit" />
</form>
</html>


