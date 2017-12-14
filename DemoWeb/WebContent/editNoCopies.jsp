<%@include file="includes.jsp"%>
<%@page import = "com.library.gcit.entity.Book"%>
<%@page import = "com.library.gcit.entity.LibraryBranch"%>
<%@page import="java.util.List"%>

<html>
<h2>Update no of Copies</h2>

<form action="editNocopies" method="post">
 
     <input type="hidden"  name="bookid" class="form-control" 
			value="<%= request.getAttribute("bookid")%>"  /><br/>
		

   <input type="hidden"  name="branchid" class="form-control" 
			value="<%= request.getAttribute("branchid")%>"  /><br/>
	
No of Copies:	
<input type="text" style="width: 400px;" name="noofcopies" class="form-control" 
			value="<%= request.getAttribute("noofcopies")%>"  readonly /><br/>
			
 <input type="text" style="width: 400px;" name="newcopies"  class="form-control" 
                placeholder="Enter new copies of books" required autofocus /><br/><br/>			

 		<button type="submit">SUBMIT</button>
</form>
</html>

	