<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Author"%>
<%@page import="java.util.List"%>
<%
	List<Author> authors = (List<Author>) request.getAttribute("authorList");
%>
<html>
<h2>Delete Author</h2>




<form action="deleteAuthor" method="post" >

<select  class="selectpicker show-menu-arrow"  name="authorId" multiple data-actions-box="true" multiple title="choose one or more Authors ">
		<% for(Author a : authors) { %>
			<option data-icon="glyphicon glyphicon-user" value="<%=a.getAuthorid()%>"><%=a.getAuthorname()%></option>
		<% } %>
	</select><br/><br/>	
 	<input type="submit" />
</form>
</html>



