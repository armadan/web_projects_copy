<%@page import="com.library.gcit.entity.Publisher"%>
<%@page import="com.library.gcit.service.Administrator"%>
<%
	Administrator service = Administrator.getInstance();
	Integer Publisherid = Integer.parseInt(request
			.getParameter("Publisherid"));
	Publisher Publisher = service.getPublisherByID(Publisherid);
%>

<div class="modal-dialog">

	<div class="modal-content">

		<div class="modal-header">

			<button type="button" class="close" data-dismiss="modal">&times;</button>

			<h4 class="modal-title">Update Publisher</h4>

		</div>
		<form action="updatePublisher" method="post">
			<div class="modal-body">


				<input type="text" style="width: 400px;" name="publisher"
					value='<%=Publisher.getPublishername()%>' class="form-control"
					placeholder="Enter Publisher's name to Edit" required autofocus /><br />

				<input type="hidden" name="pubId"
					value=<%=Publisher.getPublisherid()%>>



			</div>

			<div class="modal-footer">

				<button type="submit" id="btnUpdateBook" class="btn btn-primary">Update</button>

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




