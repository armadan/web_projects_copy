<%@include file="includes.jsp"%>
<%@page import="com.library.gcit.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.library.gcit.servlet.AdministratorServlet"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%@page import="java.util.ArrayList"%>
<%String Result= (String)request.getAttribute("result");%>
<%


Administrator service = Administrator.getInstance();
String searchString=request.getParameter("searchString");
Integer totalCount = service.getPublisherscountBySearch(searchString);

Integer pageSize = 10;
Integer pageCount = 0;
if (totalCount % pageSize > 0) {
	pageCount = totalCount / pageSize + 1;
} else {
	pageCount = totalCount / pageSize;
}
List<Publisher> Publishers= new ArrayList<Publisher>();

if (request.getAttribute("Publishers") != null) {
	Publishers = (List<Publisher>) request.getAttribute("Publishers");
} else {
	Publishers = service.getPublishers(1,null);
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
<title>List of available publishers</title>
</head>
<body>

	<div class="input-group">
		<form action="searchPublishers" method="post">
			<input type="text" class="form-control" placeholder="Author Name" name="searchString" aria-describedby="basic-addon1"><br/>
			<br /><button type="submit">Search!</button>
		</form>
	</div>




	<nav aria-label="Page navigation" >
		<ul class="pagination" >
		<%
				for (int i = 1; i <= pageCount; i++) {
			%>

			<li><a href="pagePublishers?pageNo=<%=i%>"><%=i%></a></li>
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
<h2>Publishers List</h2>


<div class="panel panel-default" class="well" class="form-group">
<div class="panel-heading">Borrower List</div>
<div class="panel-body">
<table class="table">

 <tr>
    <th>index</th>
    <th>publisher</th> 
     <th>Edit</th> 
    <th>Delete</th> 
 </tr>
<% for(Publisher p : Publishers)  { %>
 
  <tr>
   <td><%=Publishers.indexOf(p)%></td>
    <td><%=p.getPublishername()%></td> 
    	<td><button class="btn btn-sm btn-success" data-toggle="modal"data-target="#myModal1"
					href='editPublisher.jsp?Publisherid=<%=p.getPublisherid()%>'>Edit</button></td>
			<td><button class="btn btn-sm btn-danger" data-toggle="modal"data-target="#myModal1"
					href='deletePublisher1.jsp?Publisherid=<%=p.getPublisherid()%>'>Delete</button></td>
			

  </tr>
    <% } %>
</table><br/>	
</div>
</div>

<div  id="myModal1"  role="dialog" tabindex="-1"  class="modal fade">
  
</div>


</body>
</html>









