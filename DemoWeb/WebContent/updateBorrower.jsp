<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Borrower"%>
<%@page import="java.util.List"%>
<%
List<Borrower> Borrowers = (List<Borrower>) request.getAttribute("BorrowList");
%>
<html>
<h2>Update Borrower</h2>

<form action="updateBorrower" method="post">
 Borrower:
	<select name="cardno" style="width:198px">
		<% for(Borrower a :  Borrowers) { %>
			<option value="<%=a.getCardno()%>"><%=a.getBorrowername() %></option>
		<% } %>
	</select><br/>	
 NEW Borrower: <input type="text" style="width: 400px;" name="borrower" class="form-control" 
			placeholder="Enter borrower name" required autofocus /><br/>
 
 Address:
        <input type="text" style="width: 400px;" name="address" class="form-control" 
			placeholder="Enter address" required autofocus /><br/>
        
 phone:
        <input type="text" style="width: 400px;" name="phone"  class="form-control" 
			placeholder="Enter phone number" required autofocus/><br/><br/>
 	<input type="submit" />
</form>
</html>



