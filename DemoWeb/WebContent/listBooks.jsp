<%@include file="includes.jsp"%>
<%@page import="com.library.gcit.servlet.AdministratorServlet"%>
<%@page import = "com.library.gcit.entity.Book"%>
<%@page import = "com.library.gcit.entity.Author"%>
<%@page import = "com.library.gcit.entity.Publisher"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%String Result= (String)request.getAttribute("result");%>
<%
Administrator service = Administrator.getInstance();
String searchString=request.getParameter("searchString");
Integer totalCount = service.getBookscountBySearch(searchString);
Integer pageSize = 10;
Integer pageCount = 0;
if (totalCount % pageSize > 0) {
	pageCount = totalCount / pageSize + 1;
} else {
	pageCount = totalCount / pageSize;
}

List<Book> books =new ArrayList<Book>();

if (request.getAttribute("books") != null) {
	   books = (List<Book>) request.getAttribute("books");
} else {
	   books = service.getBooks(1,null);
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

			url : "searchBooks",
			data : {
				searchString : $('#searchString').val(),
				pageNo : pageNo

			}
		}).done(function(data) {

			$('#booksTable').html(data);
		})

		$.ajax({

			url : "pageBooks",
			data : {
				searchString : $('#searchString').val(),
				pageNo : pageNo

			}
		}).done(function(data) {

			$('#pageBooks').html(data);
		})

	}
</script>


<html>
<head>
<title>List of available Authors</title>
</head>
<body>
   
       
	<div class="input-group">
		<form action="searchBooks">
			<input type="text" class="form-control" placeholder="Book Name"
				name="searchString" id="searchString"
				aria-describedby="basic-addon1"><br /> <br />
			<button type="button" onclick="search(1)">Search!</button>
		</form>
	</div>




	<nav aria-label="Page navigation" id="pageBooks">
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
<div class="panel-heading">Book List</div>
<div class="panel-body">
<table class="table" id="booksTable">
 <tr>
    <th>Index</th>
    <th>Book</th> 
    <th>Author</th>
    <th>Publisher</th>
    <th>Edit</th>
    <th>Delete</th>
 </tr>
<% for(Book a : books) { %>
 
  <tr>
    <td><%=books.indexOf(a)%></td>
    <td>
    <%	
           out.println(a.getTitle());
    %>
   </td>
    <td>
      <% 
      if (a.getAuthors() != null && a.getAuthors().size() > 0) {
    	   String space="";
		for (Author b : a.getAuthors()) { 
		   
			out.println(space);
		   out.println(b.getAuthorname());
		   space="," ;
 }	
      }
		  
      %>
  </td>
  <td>
        <%
            if(a.getPublisher()!=null){
            	 out.println(a.getPublisher().getPublishername()); 

            }
        %>
  </td>
 
    <td><button  class="btn btn-sm btn-success" data-toggle="modal"data-target="#myModal1"
						href='editBook.jsp?bookID=<%=a.getBookid()%>'>Edit</button> </td>
	<td><button  class="btn btn-sm btn-danger" data-toggle="modal"data-target="#myModal1"
						href='deleteBook1.jsp?bookID=<%=a.getBookid()%>'>Delete</button> </td>					
   
  </tr>
    <% } %>
</table><br/>
</div>	
</div>	


<div  id="myModal1"  role="dialog" tabindex="-1"  class="modal fade">
  
</div>

</body>
</html>

