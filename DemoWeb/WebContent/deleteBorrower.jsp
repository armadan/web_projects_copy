<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Borrower"%>
<%@page import="java.util.List"%>
<%
	List<Borrower> Borrowers = (List<Borrower>) request.getAttribute("BorrowList");
%>
<html>
<h2>Delete Borrower</h2>

<form action="deleteBorrower" method="post">
 Borrower:
	<select name="cardno" style="width:198px">
		<% for(Borrower a :  Borrowers) { %>
			<option value="<%=a.getCardno()%>"><%=a.getBorrowername() %></option>
		<% } %>
	</select><br/><br/>	
 	<input type="submit" />
</form>
</html>


