<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Author"%>
<%@page import="java.util.List"%>
<%
	List<Author> authors = (List<Author>) request.getAttribute("authorList");
%>
<html>
<h2>Update Author</h2>

<form action="updateAuthor" method="post">	
 <p> Select Author to Update</p>
 <select class="selectpicker show-tick show-menu-arrow" name="authorId"    >
	
		<% for(Author a : authors) { %>
			<option data-icon="glyphicon glyphicon-user" value="<%=a.getAuthorid()%>"><%=a.getAuthorname()%></option>
		<% } %>
	</select><br/><br/>			
 <input type="text" style="width: 400px;" name="Author"  class="form-control" 
                placeholder="Enter Author's name" required autofocus /><br/><br/>
 	<input type="submit" />
</form>
</html>



