<%@ include file="includes.jsp" %>
<html>
<div class="form-group">
	<form action="addPublisher" method="post" >
		<input type="text" style="width: 400px;" name="publisherName" class="form-control" 
			placeholder="Enter Publisher name" required autofocus/><br/>
		<input type="text" style="width: 400px;" name="publisheraddress" class="form-control" 
			placeholder="Enter publisher address" required autofocus/><br/>
		<input type="text" style="width: 400px;" name="publisherphone" class="form-control" 
			placeholder="Enter publisher phone" required autofocus/><br/>			
		<input type="submit" class="btn btn-primary"/> 
	</form>
</div>
</html>


