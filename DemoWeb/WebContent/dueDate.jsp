<%@page import="com.library.gcit.servlet.AdministratorServlet"%>
<%@page import = "com.library.gcit.entity.BookLoans"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%
   Administrator service = Administrator.getInstance();
   Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
   Integer bookid = Integer.parseInt(request.getParameter("bookid"));
   Integer branchid = Integer.parseInt(request.getParameter("branchid"));
   
    BookLoans loan = service.getDueDateByID(cardNo,bookid,branchid);
%>


<div class="modal-dialog">

	<div class="modal-content">

		<div class="modal-header">

			<button type="button" class="close" data-dismiss="modal">&times;</button>

			<h4 class="modal-title">Due Date</h4>

		</div>

		<form action="updatedate" method="post" >
			<div class="modal-body">

     <p>Change Due Date</p>

			<input type="date" style="width: 400px;" name="dueDate"  value='<%=loan.getDuedate()%>'class="form-control" 
			placeholder="Enter new Due Date" required autofocus/><br/>
			
			
			<input type="hidden" name="branchid" value=<%=loan.getBranch().getBranchid() %>>
			
			<input type="hidden" name="bookid" value=<%=loan.getBook().getBookid()%>>
			
			<input type="hidden" name="cardNo" value=<%=loan.getBorrower().getCardno()%>>


			</div>

			<div class="modal-footer">

				<button type="submit" id="btnUpdateBook" 
					class="btn btn-primary">Update</button>

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












