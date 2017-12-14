<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon"
	href="http://54.83.8.59/site/wp-content/uploads/2014/02/favicon.png">
<!-- Bootstrap core CSS -->
<link href="http://getbootstrap.com/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap theme -->
<link href="http://getbootstrap.com/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="http://getbootstrap.com/examples/theme/theme.css"
	rel="stylesheet">
<link href="css/bootstrap-select.min.css" rel="stylesheet">


<style type="text/css">
body{
	margin:30px;
}
.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px 6px;
	border-radius: 0 6px 6px 6px;
}
/*.dropdown-submenu:hover>.dropdown-menu{display:block;}*/
.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #cccccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #ffffff;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}
</style>
<body role="document">

	<div class="navbar  navbar-light navbar-fixed-top" style="background-color: #D3D3D3;" role="navigation">
      
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">GCIT Library System</a>
			</div>

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<!-- Administrator Menu -->
					<li class="menu-item dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Administrator<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Book
									Services</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="showAddBook">Add Book</a></li>
									<li class="menu-item "><a href="showBooks">Update Book</a></li>
									<li class="menu-item "><a href="showBooksdelete">Delete Book</a></li>
									<li class="menu-item "><a href="listBooks.jsp">Books Table</a></li>
								</ul></li>
								
								
								
								
								<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Author
									Services</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="addAuthor.jsp">Add Author</a></li>
									<li class="menu-item "><a href="showAuthors">Update Author</a></li>
									<li class="menu-item "><a href="showAuthorsdelete">Delete Author</a></li>
									<li class="menu-item "><a href="listAuthors.jsp">Author Table</a></li>
								</ul></li>
								
							
									

							<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Publisher
									Services</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="addPublisher.jsp">Add Publisher</a></li>
									<li class="menu-item "><a href="showPublishers">Update Publisher</a></li>
									<li class="menu-item "><a href="showPublisherdelete">Delete Publisher</a></li>	
									<li class="menu-item "><a href="listPublishers.jsp">Publishers List</a></li>		
								</ul></li>
						
						
							
								<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Borrower
									Services</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="addBorrower.jsp">Add Borrower</a></li>
									<li class="menu-item "><a href="showBorrowersUpdate">Update Borrower</a></li>
									<li class="menu-item "><a href="showBorrowers">Delete Borrower</a></li>
									<li class="menu-item "><a href="listBorrowers.jsp">borrower List</a></li>
								</ul></li>
								
								
								
								 
								<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Library Services
								</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="addBranch.jsp">Add Branch</a></li>
									 <li class="menu-item "><a href="listLibraryBranches.jsp">List Branches</a></li>
								</ul></li>
								
	
				
							<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Overide DueDate</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="BookLoans.jsp">Bookloans List</a></li>
									
								</ul></li>
										</ul></li>
						
						
							

					<!-- Librarian Menu -->
					<li class="menu-item dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Librarian<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="menu-item "><a href="showLibraries">Update Branch</a></li>
							<li class="menu-item "><a href="showBookBranch">Add Book Copies</a></li>
						</ul>
					</li>

					<!-- Borrower Menu -->
					<li class="menu-item dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Borrower<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="menu-item "><a href="cardNo.jsp">Checkout Book</a></li>
							<li class="menu-item "><a href="cardNo2.jsp">Return Book</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	

	<script type='text/javascript'
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type='text/javascript'
		src="//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
	<script type='text/javascript' src="js/bootstrap-select.min.js"></script>
	
	<script type='text/javascript'>
		$(document).ready(
				function() {

					$('ul.dropdown-menu [data-toggle=dropdown]').on(
							'click',
							function(event) {
								// Avoid following the href location when clicking
								event.preventDefault();
								// Avoid having the menu to close when clicking
								event.stopPropagation();
								// If a menu is already open we close it
								$('ul.dropdown-menu [data-toggle=dropdown]').parent().removeClass('open');
								
								// opening the one you clicked on
								$(this).parent().addClass('open');

								var menu = $(this).parent().find("ul");
								var menupos = menu.offset();

								if ((menupos.left + menu.width()) + 30 > $(
										window).width()) {
									var newpos = -menu.width();
								} else {
									var newpos = $(this).parent().width();
								}
								menu.css({
									left : newpos
								});

							});

				});
	</script>
	
</html>


