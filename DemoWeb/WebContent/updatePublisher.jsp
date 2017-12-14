<%@include file="includes.jsp"%>
<%@page import="com.library.gcit.entity.Publisher"%>
<%@page import="java.util.List"%>
<%
List<Publisher> publishers= (List<Publisher>) request.getAttribute("pubList");
%>
<html>
<h2>Update Publisher</h2>

<form action="updatePublisher" method="post">

	<select   class="selectpicker show-menu-arrow" name="pubId" >
		<% for(Publisher p : publishers) { %>
			<option value="<%=p.getPublisherid()%>"><%=p.getPublishername() %></option>
		<% } %>
	</select><br/><br/>

		 <input type="text" name="publisher" class="form-control" 
			placeholder="Enter new publisher name" required autofocus /><br/>
	 
          <input type="text" name="address"class="form-control" 
			placeholder="Enter  borrower address " required autofocus  /><br/>
	 
          <input type="text" name="phone"  class="form-control" 
			placeholder="Enter phone number" required autofocus /><br/>
	<input type="submit" />
</form>
</html>