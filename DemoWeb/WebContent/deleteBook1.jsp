
<%@page import = "com.library.gcit.entity.Book"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%
   Administrator service= Administrator.getInstance() ;
   Integer bookID = Integer.parseInt(request.getParameter("bookID"));
   Book book = service.getBookByID(bookID);
%>


<div class="modal-dialog">

	<div class="modal-content">

      <div class="modal-header">

			<button type="button" class="close" data-dismiss="modal">&times;</button>

			<h4 class="modal-title">Delete Book</h4>
   
		</div>

			<form action="deleteBook" method="post" >
			<div class="modal-body">
          <p> Confirm to Delete the Book</p>
        <input type="hidden" name="booktitle"  value='<%= book.getTitle()%>'class="form-control" /><br/>
			
			<input type="hidden" name="bookid" value=<%=book.getBookid()%>>
			
      	</div>

			<div class="modal-footer">

				<button type="submit" class="btn btn-primary">Delete</button>

				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				

			</div>

		</form>
	</div>

</div>

<script>
$(document).ready(function() {
	
	$('.modal').on('hidden.bs.modal',function(e){
		$(this).removeData();
		
	});
});
</script>




