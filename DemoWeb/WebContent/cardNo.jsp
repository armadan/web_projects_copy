<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Book"%>
<%@page import="java.util.List"%>

<html>
<h2>Borrower Card No</h2>

<form action="cardNo" method="post">
 
 <input type="text" style="width: 400px;" name="cardno"  class="form-control" 
			placeholder="Enter Card Number" required autofocus /><br/>
			
 	<button type="submit">ENTER</button>
</form>
</html>


