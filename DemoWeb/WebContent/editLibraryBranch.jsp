<%@page import="com.library.gcit.entity.LibraryBranch"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%
	Administrator service = Administrator.getInstance();
	Integer branchid = Integer.parseInt(request
			.getParameter("branchid"));
	LibraryBranch branch = service.getLibraryBranchByID(branchid);
%>

	<div class="modal-dialog">

		<div class="modal-content">

			<div class="modal-header">

				<button type="button" class="close" data-dismiss="modal">&times;</button>

				<h4 class="modal-title">Update LibraryBranch</h4>

			</div>
			<form action="updateLibraryBranch1" method="post">
				<div class="modal-body">


					<input type="text" style="width: 400px;" name="name"
						value='<%=branch.getBranchname()%>' class="form-control"
						placeholder="Enter Branch name to Edit" required autofocus /><br />


					<input type="text" style="width: 400px;" name="address"
						value='<%=branch.getBranchaddress()%>' class="form-control"
						placeholder="Enter Branch address to Edit" required autofocus /><br />

					<input type="hidden" name="branchid"
						value=<%=branch.getBranchid()%>>


				</div>

				<div class="modal-footer">

					<button type="submit" class="btn btn-primary">Update</button>

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