<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Borrower"%>
<%@page import="java.util.List"%>
<%@page import="com.library.gcit.servlet.AdministratorServlet"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%@page import="java.util.ArrayList"%>

<%String Result= (String)request.getAttribute("result");%>
<%
Administrator service = Administrator.getInstance();
String searchString=request.getParameter("searchString");
Integer totalCount = service.getBorrowerscountBySearch(searchString);
Integer pageSize = 10;
Integer pageCount = 0;
if (totalCount % pageSize > 0) {
	pageCount = totalCount / pageSize + 1;
} else {
	pageCount = totalCount / pageSize;
}

List<Borrower> Borrowers =new ArrayList<Borrower>();

if (request.getAttribute("borrowers") != null) {
	Borrowers = (List<Borrower>) request.getAttribute("borrowers");
} else {
	Borrowers = service.getBorrowers(1,null);
}
%>




<%  if(Result!=null){ %>
   
	   <div   id="success" class="alert alert-success alert-dismissible" role="alert">
	   <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span  aria-hidden="true">&times;</span></button>
	   <strong>Success!</strong> <%=Result%>
	 </div>
   
   <% }%>

<script>
	function search(pageNo) {
		$.ajax({

			url : "searchBorrowers",
			data : {
				searchString : $('#searchString').val(),
				pageNo : pageNo

			}
		}).done(function(data) {

			$('#BorrowersTable').html(data);
		})

		$.ajax({

			url : "pageBorrowers",
			data : {
				searchString : $('#searchString').val(),
				pageNo : pageNo

			}
		}).done(function(data) {

			$('#pageBorrowers').html(data);
		})

	}
</script>


<html>
<head>
<title>List of available Authors</title>
</head>
<body>

	<div class="input-group">
		<form action="searchBorrowers">
			<input type="text" class="form-control" placeholder="Borrower Name"
				name="searchString" id="searchString"
				aria-describedby="basic-addon1"><br/>
			<button type="button" onclick="search(1)">Search</span></button>
			
		</form>
	</div>


	<nav aria-label="Page navigation" id="pageBorrowers">
		<ul class="pagination">
			<%
				for (int i = 1; i <= pageCount; i++) {
			%>

			<li><a href="javascript:search(<%=i%>)"><%=i%></a></li>
			<%
				}
			%>
		</ul>
	</nav>
</body>	
</html>	
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


<div class="panel panel-default" class="well" class="form-group">
<div class="panel-heading">Borrower List</div>
<div class="panel-body">
<table class="table" id="BorrowersTable">
 <tr>
    <th>Index</th>
    <th>Borrower</th> 
     <th>Edit</th> 
      <th>Delete</th> 
    

 </tr>
<% for(Borrower a :  Borrowers)  { %>
 
  <tr>
    <td><%=Borrowers.indexOf(a)%></td>
    <td><%=a.getBorrowername()%></td> 
    <td><button  class="btn btn-sm btn-success" data-toggle="modal"data-target="#myModal1"
						href='editBorrower.jsp?borrowerID=<%=a.getCardno()%>'>Edit</button></td>
	<td><button  class="btn btn-sm btn-danger" data-toggle="modal"data-target="#myModal1"
						href='deleteBorrower1.jsp?borrowerID=<%=a.getCardno()%>'>Delete</button></td>					
   
  </tr>
    <% } %>
</table><br/>
</div>	
</div>


<div  id="myModal1"  role="dialog" tabindex="-1"  class="modal fade">
  
</div>


</body>
</html>


