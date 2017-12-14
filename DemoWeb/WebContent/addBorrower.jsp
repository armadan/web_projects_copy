<%@ include file="includes.jsp" %>
<html>
<div class="form-group">
	<form action="addBorrower" method="post" >
		<input type="text" style="width: 400px;" name="Borrower" class="form-control" 
			placeholder="Enter Borrower name" required autofocus/><br/>
			
		<input type="text" style="width: 400px;" name="address" class="form-control" 
			placeholder="Enter address" required autofocus/><br/>	
			
		<input type="text" style="width: 400px;" name="phone" class="form-control" 
			placeholder="Enter phonenumber" required autofocus/><br/>
			
		<input type="submit" class="btn btn-primary"/> 
	</form>
</div>
</html>