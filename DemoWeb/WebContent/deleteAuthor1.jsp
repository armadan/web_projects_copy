<%@page import = "com.library.gcit.entity.Author"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%
   Administrator service= Administrator.getInstance();
   Integer authorID = Integer.parseInt(request.getParameter("authorId"));
   Author author = service.getAuthorByID(authorID);
%>

<div class="modal-dialog">

	<div class="modal-content">

      <div class="modal-header">

			<button type="button" class="close" data-dismiss="modal">&times;</button>

			<h4 class="modal-title">Delete Author</h4>

		</div>

			<form action="deleteAuthor" method="post" >
			<div class="modal-body">
    
           <p>Confirm to Delete the Author</p>
	      <input type="hidden"  name="Author"  value='<%=author.getAuthorname()%>'class="form-control" /><br/>
			
			<input type="hidden" name="authorId" value=<%=author.getAuthorid() %>>
			
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
