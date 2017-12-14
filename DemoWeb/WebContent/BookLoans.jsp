<%@include file="includes.jsp"%>
<%@page import="com.library.gcit.servlet.AdministratorServlet"%>
<%@page import="com.library.gcit.entity.BookLoans"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%String Result= (String)request.getAttribute("result");%>
<%
	Administrator service = Administrator.getInstance();
	List<BookLoans> bookLoans = new ArrayList<BookLoans>();
	bookLoans = service.getAllLoans();
%>


   <%  if(Result!=null){ %>
   
	   <div  class="alert alert-success alert-dismissible" role="alert">
	   <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span  aria-hidden="true">&times;</span></button>
	   <strong>Success!</strong> <%=Result%>
	 </div>
   
   <% }%>
    
 
     
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	
}
</style>
</head>
<body>

	<h2>Book Loans</h2>


	<table>
		<tr>
			<th>BookID</th>
			<th>cardNO</th>
			<th>BranchID</th>
			<th>DateOut</th>
			<th>DueDATE</th>
			<th>DATEIN</th>
			<th>Edit</th>

		</tr>
		<%
			for (BookLoans a : bookLoans) {
		%>

		<tr>
			<td><%=a.getBook().getBookid()%></td>
			<td><%=a.getBorrower().getCardno()%></td>
			<td><%=a.getBranch().getBranchid()%></td>
			<td><%=a.getDateout()%></td>
			<td><%=a.getDuedate()%></td>
			<td><%=a.getDatein()%></td>



			<td><button class="btn btn-sm btn-danger" data-toggle="modal"
					data-target="#myModal1"
					href='dueDate.jsp?cardNo=<%=a.getBorrower().getCardno()%>&branchid=<%=a.getBranch().getBranchid()%>&bookid=<%=a.getBook().getBookid()%>'>Override</button>
		</tr>
		<%
			}
		%>
	</table>
	<br />


	<div id="myModal1" role="dialog" tabindex="-1" class="modal fade">

	</div>

</body>
</html>

