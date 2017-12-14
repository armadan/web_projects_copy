<%@page import = "com.library.gcit.entity.Author"%>
<%@page import = "com.library.gcit.entity.Book"%>
<%@page import = "com.library.gcit.entity.Publisher"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%@page import="java.util.List"%>
<%
   Administrator service=Administrator.getInstance();
   Integer bookID = Integer.parseInt(request.getParameter("bookID"));
   Book book = service.getBookByID(bookID);
   List<Book> books=service.getAllBooks();
   List<Author> authors =service.getAllAuthors();
   List<Publisher> publishers =service.getAllPublishers();
%>

<div class="modal-dialog">

	<div class="modal-content">

		<div class="modal-header">

			<button type="button" class="close" data-dismiss="modal">&times;</button>

			<h4 class="modal-title">Update Book</h4>

		</div>

		<form action="editBookAuthPublisher" method="post" >
			<div class="modal-body">


	<input type="text" style="width: 400px;" name="booktitle"  value='<%= book.getTitle()%>'class="form-control" 
			placeholder="Enter book's  title to Edit" required autofocus/><br/><br/><br/><br/>	
			
			<select id="authorId"  name="authorId" style="width:198px" multiple >
	
	 <p> Select Authors</p>
	 
		<% for(Author a : authors) { %>
			<option value="<%=a.getAuthorid()%>"><%=a.getAuthorname()%></option>
		<% } %>
	</select><br/><br/>	<br/><br/>		
	
	 <p>Select Publisher</p>
	    <select  id="pubId"  name="pubId" style="width:198px" >
		<% for(Publisher p : publishers) { %>
			<option value="<%=p.getPublisherid()%>"><%=p.getPublishername() %></option>
		<% } %>
	</select><br/><br/>	<br/>	<br/>	
		
			<input type="hidden" name="bookid" value=<%=book.getBookid()%>>
	
			</div>

			<div class="modal-footer">

				<button type="submit" id="btnUpdateBook" 
					class="btn btn-primary">Update</button>

				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				

			</div>

		</form>
	</div>

</div>


<script type="text/javascript">

function populateDefaultAuthor(value){
	$('#authorId option[value=' + value + ']').prop('selected',true);
}

function populateDefaultPublisher(value){
	$('#pubId').val(value);
}


$(document).ready(function(){
	

	
	$('.modal').on('hidden.bs.modal',function(e){
		$(this).removeData();
	})
	
	
	<% if(book.getPublisher()!=null) {%>
		populateDefaultPublisher(<%=book.getPublisher().getPublisherid()%>)
	<%}%>
	
	<% if(book.getAuthors().size()>0) {%>
	<% for(Author auth : book.getAuthors()) { %>
			populateDefaultAuthor(<%=auth.getAuthorid()%>);
		<% } %>
	<%}%>
});
</script>


