<%@include file="includes.jsp"%>
<%@page import="com.library.gcit.servlet.AdministratorServlet"%>
<%@page import="com.library.gcit.entity.Author"%>
<%@page import="com.library.gcit.entity.Book"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%
	String Result= (String)request.getAttribute("result");
%>



<%
	Administrator service = Administrator.getInstance();
	String searchString=request.getParameter("searchString");
	Integer totalCount = service.getAuthorscountBySearch(searchString);
	
	Integer pageSize = 10;
	Integer pageCount = 0;
	if (totalCount % pageSize > 0) {
		pageCount = totalCount / pageSize + 1;
	} else {
		pageCount = totalCount / pageSize;
	}
	List<Author> authors = new ArrayList<Author>();

	if (request.getAttribute("authors") != null) {
		 authors = (List<Author>) request.getAttribute("authors");
	} else {
		authors = service.getAuthors(1,null);
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

			url : "searchAuthors",
			data : {
				searchString : $('#searchString').val(),
				pageNo : pageNo

			}
		}).done(function(data) {

			$('#authorTable').html(data);
		})

		$.ajax({

			url : "pageAuthors",
			data : {
				searchString : $('#searchString').val(),
				pageNo : pageNo

			}
		}).done(function(data) {

			$('#pageAuthors').html(data);
		})

	}
</script>


<html>
<head>
<title>List of available Authors</title>
</head>
<body>



	<div class="input-group">
		<form action="searchAuthors">
			<input type="text" class="form-control" placeholder="Author Name"
				name="searchString" id="searchString"
				aria-describedby="basic-addon1"><br /> <br />
			<button type="button" onclick="search(1)">Search!</button>
		</form>
	</div>




	<nav aria-label="Page navigation" id="pageAuthors">
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
</style>
</head>
<body>
<html>

<div class="panel panel-default" class="well" class="form-group">
	<div class="panel-heading">Author List</div>
	<div class="panel-body">
		<table class="table" id="authorTable">
			<tr>
				<th>Index</th>
				<th>Author</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<%
				for (Author a : authors) {
			%>

			<tr>
				<td><%=authors.indexOf(a)%></td>
				<td><%=a.getAuthorname()%></td>


				<td><button class="btn btn-sm btn-success" data-toggle="modal"
						data-target="#myModal1"
						href='editAuthor.jsp?authorId=<%=a.getAuthorid()%>'>Edit</button>
				</td>
				<td><button class="btn btn-sm btn-danger" data-toggle="modal"
						data-target="#myModal1"
						href='deleteAuthor1.jsp?authorId=<%=a.getAuthorid()%>'>Delete</button>
				</td>



			</tr>
			<%
				}
			%>
		</table>
		<br />
	</div>
</div>

<div id="myModal1" role="dialog" tabindex="-1" class="modal fade">

</div>
</body>
</html>

