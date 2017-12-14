<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@page import="com.library.gcit.servlet.AdministratorServlet"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%@page import="java.util.ArrayList"%>
<%String Result= (String)request.getAttribute("result");%>

<%
Administrator service = Administrator.getInstance();
String searchString=request.getParameter("searchString");
Integer totalCount = service.getLibraryBranchescountBySearch(searchString);

Integer pageSize = 10;
Integer pageCount = 0;
if (totalCount % pageSize > 0) {
	pageCount = totalCount / pageSize + 1;
} else {
	pageCount = totalCount / pageSize;
}
List<LibraryBranch>  branches= new ArrayList<LibraryBranch>();

if (request.getAttribute("branches") != null) {
	 branches = (List<LibraryBranch>) request.getAttribute(" branches");
} else {
	 branches= service.getLibraryBranches(1,null);
}

%>


<%  if(Result!=null){ %>
   
	   <div   id="success" class="alert alert-success alert-dismissible" role="alert">
	   <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span  aria-hidden="true">&times;</span></button>
	   <strong>Success!</strong> <%=Result%>
	 </div>
   
   <% }%>





<html>
<head>
<title>List of available LibararyBranches</title>
</head>
<body>

	<div class="input-group">
		<form action="searchLibraryBranches" method="post">
			<input type="text" class="form-control" placeholder="Branch Name" name="searchString" aria-describedby="basic-addon1"><br/>
			<br /><button type="submit">Search!</button>
		</form>
	</div>




	<nav aria-label="Page navigation" >
		<ul class="pagination" >
		<%
				for (int i = 1; i <= pageCount; i++) {
			%>

			<li><a href="pageLibraryBranches?pageNo=<%=i%>"><%=i%></a></li>
			<%
				}
			%>
		</ul>
	</nav>

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
<h2>LibraryBranch List</h2>


<div class="panel panel-default" class="well" class="form-group">
<div class="panel-heading">LibraryBranch List</div>
<div class="panel-body">
<table class="table">

 <tr>
    <th>index</th>
    <th>Branch Name</th> 
    <th>Branch Address</th> 
     <th>Edit</th>
    <th>Delete</th> 
 </tr>
<% for(LibraryBranch lb :  branches)  { %>
 
  <tr>
     <td><%=branches.indexOf(lb)%></td>
    <td><%=lb.getBranchname() %></td>
    <td><%=lb.getBranchaddress()%></td> 
    	<td><button class="btn btn-sm btn-success" data-toggle="modal"data-target="#myModal1"
					href='editLibraryBranch.jsp?branchid=<%=lb.getBranchid()%>'>Edit</button></td>
			<td><button class="btn btn-sm btn-danger" data-toggle="modal"data-target="#myModal1"
					href='deleteLibraryBranch.jsp?branchid=<%=lb.getBranchid()%>'>Delete</button></td>
			

  </tr>
    <% } %>
</table><br/>	
</div>
</div>

<div  id="myModal1"  role="dialog" tabindex="-1"  class="modal fade">
  
</div>


</body>
</html>



