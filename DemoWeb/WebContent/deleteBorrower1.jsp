
<%@page import="com.library.gcit.entity.Borrower"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%
	Administrator service =  Administrator.getInstance();
	Integer BorrowerID = Integer.parseInt(request
			.getParameter("borrowerID"));
	Borrower borrower = service.getBorrowerByID(BorrowerID);
%>


<div class="modal-dialog">

	<div class="modal-content">

		<div class="modal-header">

			<button type="button" class="close" data-dismiss="modal">&times;</button>

			<h4 class="modal-title">Delete Borrower</h4>

		</div>

		<form action="deleteBorrower" method="post">

			<div class="modal-body">
				<p>Confirm to delete the Author</p>

				<input type="hidden" name="Borrower"
					value='<%=borrower.getBorrowername()%>' class="form-control" /><br />

				<input type="hidden" name="cardno" value=<%=borrower.getCardno()%>>

			</div>

			<div class="modal-footer">

				<button type="submit" id="btnUpdateBook" class="btn btn-primary">Delete</button>

				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>


			</div>

		</form>
	</div>

</div>


<script>
	$(document).ready(function() {

		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();

		});
	});
</script>




