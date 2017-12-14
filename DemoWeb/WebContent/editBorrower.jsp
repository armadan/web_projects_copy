
<%@page import="com.library.gcit.entity.Borrower"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%
	Administrator service =Administrator.getInstance();
	Integer BorrowerID = Integer.parseInt(request
			.getParameter("borrowerID"));
	Borrower borrower = service.getBorrowerByID(BorrowerID);
%>



<div class="modal-dialog">

	<div class="modal-content">

		<div class="modal-header">

			<button type="button" class="close" data-dismiss="modal">&times;</button>

			<h4 class="modal-title">Update Book</h4>

		</div>

		<div class="modal-body">

			<form action="updateBorrower" method="post">
				<input type="text" style="width: 400px;" name="Borrower"
					value='<%=borrower.getBorrowername()%>' class="form-control"
					placeholder="Enter Borrower's name to Edit" required autofocus /><br />

				<input type="hidden" name="cardno" value=<%=borrower.getCardno()%>>
				<input type="submit" class="btn btn-primary" />
			</form>

		</div>

		<div class="modal-footer">

			<button type="button" id="btnUpdateBook" data-dismiss="modal"
				class="btn btn-default">Update</button>

			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<br /> <span style="float: left; visibility: hidden"
				class="label label-success">Success Label</span>

		</div>

	</div>

</div>



<script>
	$(document).ready(function() {

		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();

		});
	});
</script>

