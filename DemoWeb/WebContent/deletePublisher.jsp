<%@include file="includes.jsp"%>
<%@page import="com.library.gcit.entity.Publisher"%>
<%@page import="java.util.List"%>
<%
List<Publisher> publishers= (List<Publisher>) request.getAttribute("pubList");
%>
<html>
<h2>Delete Publisher</h2>

<form action="deletePublisher" method="post">
	Publisher:
	<select class="selectpicker show-menu-arrow"  name="pubId"  multiple data-actions-box="true" multiple title="choose one or more Publishers">
		<% for(Publisher p : publishers) { %>
			<option value="<%=p.getPublisherid()%>"><%=p.getPublishername() %></option>
		<% } %>
	</select><br/><br/>
 <input type="submit" />
</form>
</html>