package com.library.gcit.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.gcit.entity.Author;
import com.library.gcit.entity.Book;
import com.library.gcit.entity.BookLoans;
import com.library.gcit.entity.Borrower;
import com.library.gcit.entity.LibraryBranch;
import com.library.gcit.entity.Publisher;
import com.library.gcit.service.Administrator;
import com.library.gcit.service.Librarian;

/**
 * Servlet implementation class AdministratorServlet
 */
@WebServlet(name = "AdministratorServlet", urlPatterns = { "/admin",
		"/addAuthor", "/addPublisher", "/showPublishers", "/updatePublisher","/updateLibraryBranch1",
		"/deletePublisher","/deleteLibraryBranch", "/showAuthorsdelete", "/showPublisherdelete",
		"/showBooksdelete", "/deleteAuthor", "/deleteBook", "/deleteBorrower",
		"/showBorrowers", "/showBorrowersUpdate", "/updateBorrower",
		"/addBorrower", "/showBooks", "/showAddBook", "/addBook",
		"/showAuthors", "/updateBook", "/updateAuthor", "/BookLoans1",
		"/pageAuthors", "/searchAuthors", "/searchBooks", "/pageBooks",
		"/searchBorrowers", "/pageBorrowers", "/searchPublishers","/searchLibraryBranches",
		"/pagePublishers","/pageLibraryBranches", "/editBookAuthPublisher", "/updatedate","/addBranch" })
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String function = request.getServletPath();
		Boolean ajax = false;

		try {
			switch (function) {
			case "/pageAuthors": {
				Administrator service = Administrator.getInstance();
				Integer pageCount = 0;
				Integer totalCount = 0;
				StringBuilder str = new StringBuilder();
				String searchString = request.getParameter("searchString");
				if (searchString == null || searchString.length() < 0) {
					searchString = null;
				}
				Integer pageNo = 1;
				if (request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
				}

				totalCount = service.getAuthorscountBySearch(searchString);
				Integer pageSize = 10;

				if (totalCount % pageSize > 0) {
					pageCount = totalCount / pageSize + 1;
				} else {
					pageCount = totalCount / pageSize;
				}
				str.append("<ul class='pagination'>");
				for (int i = 1; i <= pageCount; i++) {
					str.append("<li><a  href='javascript:search(" + i + ")'>"
							+ i + "</a></li>");
				}
				str.append("</ul>");
				response.getWriter().append(str);
				ajax = true;
				break;
			}

			case "/pageBooks": {
				Administrator service = Administrator.getInstance();
				Integer pageCount = 0;
				Integer totalCount = 0;
				StringBuilder str = new StringBuilder();
				String searchString = request.getParameter("searchString");
				if (searchString == null || searchString.length() < 0) {
					searchString = null;
				}
				Integer pageNo = 1;
				if (request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
				}

				totalCount = service.getBookscountBySearch(searchString);
				Integer pageSize = 10;

				if (totalCount % pageSize > 0) {
					pageCount = totalCount / pageSize + 1;
				} else {
					pageCount = totalCount / pageSize;
				}
				str.append("<ul class='pagination'>");
				for (int i = 1; i <= pageCount; i++) {
					str.append("<li><a  href='javascript:search(" + i + ")'>"
							+ i + "</a></li>");
				}
				str.append("</ul>");
				response.getWriter().append(str);
				ajax = true;
				break;
			}

			case "/pageBorrowers": {
				Administrator service = Administrator.getInstance();
				Integer pageCount = 0;
				Integer totalCount = 0;
				StringBuilder str = new StringBuilder();
				String searchString = request.getParameter("searchString");
				if (searchString == null || searchString.length() < 0) {
					searchString = null;
				}
				Integer pageNo = 1;
				if (request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
				}

				totalCount = service.getBorrowerscountBySearch(searchString);
				Integer pageSize = 10;

				if (totalCount % pageSize > 0) {
					pageCount = totalCount / pageSize + 1;
				} else {
					pageCount = totalCount / pageSize;
				}
				str.append("<ul class='pagination'>");
				for (int i = 1; i <= pageCount; i++) {
					str.append("<li><a  href='javascript:search(" + i + ")'>"
							+ i + "</a></li>");
				}
				str.append("</ul>");
				response.getWriter().append(str);
				ajax = true;
				break;
			}

			case "/pagePublishers": {
				Administrator service = Administrator.getInstance();
				List<Publisher> Publishers = new ArrayList<Publisher>();
				Integer pageNo = 1;
				if (request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
				}
				Publishers = service.getPublishers(pageNo, null);
				request.setAttribute("Publishers", Publishers);
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/listPublishers.jsp");
				rd.forward(request, response);
				break;
			}

			case "/pageLibraryBranches": {
				Administrator service = Administrator.getInstance();
				List<LibraryBranch> branches = new ArrayList<LibraryBranch>();
				Integer pageNo = 1;
				if (request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
				}
				branches = service.getLibraryBranches(pageNo, null);
				request.setAttribute("branches", branches);
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/listLibraryBranches.jsp");
				rd.forward(request, response);
				break;
			}
			
			case "/searchAuthors": {
				searchAuthors(request, response);
				ajax = true;
				break;
			}

			case "/searchBooks": {
				searchBooks(request, response);
				ajax = true;
				break;
			}

			case "/searchBorrowers": {
				searchBorrowers(request, response);
				ajax = true;
				break;
			}

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Operation Failed! " + e.getMessage());
		}

		if (!ajax) {

			doPost(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String function = request.getServletPath();
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/index.jsp");
		try {
			switch (function) {
			case "/addAuthor": {
				addAuthor(request);
				request.setAttribute("result", "Author Added Succesfully!");
				break;
			}

			case "/addBorrower": {
				addBorrower(request);
				request.setAttribute("result", "Borrower Added Succesfully!");
				break;
			}
			
			
			case "/addBranch": {
				addBranch(request);
				request.setAttribute("result", "Branch Added Succesfully!");
				break;
			}

			case "/searchAuthors": {
				searchAuthors(request, response);
				rd = getServletContext().getRequestDispatcher(
						"/listAuthors.jsp");
				break;
			}

			case "/searchPublishers": {
				searchPublishers(request);
				rd = getServletContext().getRequestDispatcher(
						"/listPublishers.jsp");
				break;
			}
			
			
			case "/searchLibraryBranches": {
				searchLibraryBranches(request);
				rd = getServletContext().getRequestDispatcher(
						"/listLibraryBranches.jsp");
				break;
			}

			case "/showAuthorsdelete": {
				showAuthors(request);
				rd = getServletContext().getRequestDispatcher(
						"/deleteAuthor.jsp");
				break;
			}

			case "/showBorrowersUpdate": {
				showBorrowers(request);
				rd = getServletContext().getRequestDispatcher(
						"/updateBorrower.jsp");
				break;
			}

			case "/updatedate": {
				updatedate(request);
				request.setAttribute("result", "Due Date updated Succesfully!");
				rd = getServletContext().getRequestDispatcher(
						"/BookLoans.jsp");
				break;
			}

			case "/": {
				showBorrowers(request);
				rd = getServletContext().getRequestDispatcher(
						"/updateBorrower.jsp");
				break;
			}

			case "/updateBorrower": {
				updateBorrower(request);
				request.setAttribute("result", "Borrower updated Succesfully!");
				rd = getServletContext().getRequestDispatcher(
						"/listBorrowers.jsp");
				
				break;
			}

			case "/showBorrowers": {
				showBorrowers(request);
				rd = getServletContext().getRequestDispatcher(
						"/deleteBorrower.jsp");
				break;
			}

			case "/deleteAuthor": {
				deleteAuthor(request);
				request.setAttribute("result", "Author deleted Succesfully!");
				rd = getServletContext().getRequestDispatcher(
						"/listAuthors.jsp");

				break;

			}

			case "/showPublisherdelete": {
				showPublishers(request);
				rd = getServletContext().getRequestDispatcher(
						"/deletePublisher.jsp");
				break;
			}

			case "/deletePublisher": {
				deletePublisher(request);
				request.setAttribute("result", "Publisher deleted Succesfully!");
				break;
			}

			case "/deleteLibraryBranch": {
				deleteLibraryBranch(request);
				request.setAttribute("result", "Publisher deleted Succesfully!");
				break;
			}
			
			
			
			case "/deleteBorrower": {
				deleteBorrower(request);
				request.setAttribute("result", "Borrower deleted Succesfully!");
				rd = getServletContext()
						.getRequestDispatcher("/listBorrowers.jsp");
				
				break;
			}

			case "/showBooksdelete": {
				showBooks(request);
				rd = getServletContext()
						.getRequestDispatcher("/deleteBook.jsp");
				break;
			}

			case "/deleteBook": {
				deleteBook(request);
				request.setAttribute("result", "Book deleted Succesfully!");
				rd = getServletContext().getRequestDispatcher("/listBooks.jsp");
				break;
			}

			case "/addPublisher": {
				addPublisher(request);
				request.setAttribute("result", "publisher Added Succesfully!");
				break;
			}

			case "/addBook": {
				addBook(request);
				request.setAttribute("result", "Book Added Succesfully!");
				break;
			}

			case "/updateBook": {
				updateBook(request);
				request.setAttribute("result", "Book updated Succesfully!");
				break;
			}

			case "/showBooks": {
				showBooks(request);
				rd = getServletContext()
						.getRequestDispatcher("/updateBook.jsp");
				break;
			}

			case "/showAddBook": {
				showAddBook(request);
				rd = getServletContext().getRequestDispatcher("/addBook.jsp");
				break;
			}

			case "/showAuthors": {
				showAuthors(request);
				rd = getServletContext().getRequestDispatcher(
						"/updateAuthor.jsp");
				break;
			}

			case "/BookLoans1": {
				showBookLoans(request);
				rd = getServletContext().getRequestDispatcher("/BookLoans.jsp");
				break;
			}

			case "/showPublishers": {
				showPublishers(request);
				rd = getServletContext().getRequestDispatcher(
						"/updatePublisher.jsp");
				break;
			}
			
			case "/updateLibraryBranch1": {
				updateLibraryBranch1(request);
				request.setAttribute("result", "library updated Succesfully!");
				rd = getServletContext().getRequestDispatcher(
						"/listLibraryBranches.jsp");
				break;
			}

			case "/editBookAuthPublisher": {
				editBookAuthPublisher(request);
				request.setAttribute("result", "Book updated Succesfully!");
				rd = getServletContext().getRequestDispatcher("/listBooks.jsp");
				break;
			}

			case "/updatePublisher": {
				updatePublisher(request);
				request.setAttribute("result", "Publisher updated Succesfully!");
				break;
			}

			case "/updateAuthor": {
				updateAuthor(request);
				request.setAttribute("result", "Author updated Succesfully!");
				rd = getServletContext().getRequestDispatcher(
						"/listAuthors.jsp");
				break;
			}

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Operation Failed! " + e.getMessage());
		}

		rd.forward(request, response);
	}

	private void searchAuthors(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));

		Administrator admService = Administrator.getInstance();
		if (searchString == null || searchString.length() < 0) {
			searchString = null;
		}

		List<Author> authors = new ArrayList<Author>();
		authors = admService.getAuthors(pageNo, searchString);

		request.setAttribute("authors", authors);
		request.setAttribute("searchString", searchString);

		StringBuilder str = new StringBuilder();
		str.append("<tr><th>Index</th><th>Author</th><th>Edit</th><th>Delete</th></tr>");

		for (Author a : authors) {

			str.append("<tr><td>" + authors.indexOf(a) + 1 + "</td>");
			str.append("<td>"
					+ a.getAuthorname()
					+ "</td><td><button class='btn btn-sm btn-success' data-toggle='modal'data-target='#myModal1' ");
			str.append("href='editAuthor.jsp?authorId ="
					+ a.getAuthorid()
					+ "'>Edit</button><td><button class='btn btn-sm btn-danger' data-toggle='modal' data-target='#myModal1'");
			str.append("href='deleteAuthor1.jsp?authorId=" + a.getAuthorid()
					+ "'>Delete</button><td></tr>");

		}
		response.getWriter().append(str);

	}

	private void searchBooks(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));

		Administrator admService = Administrator.getInstance();
		if (searchString == null || searchString.length() < 0) {
			searchString = null;
		}

		List<Book> books = new ArrayList<Book>();
		books = admService.getBooks(pageNo, searchString);

		request.setAttribute("books", books);
		request.setAttribute("searchString", searchString);

		StringBuilder str = new StringBuilder();
		str.append("<tr><th>Index</th><th>Book</th><th>Author</th> <th>Publisher</th><th>Edit</th><th>Delete</th></tr>");

		for (Book a : books) {

			str.append("<tr><td>" + books.indexOf(a) + 1 + "</td>");
			str.append("<td>" + a.getTitle() + "</td> <td>");

			if (a.getAuthors() != null && a.getAuthors().size() > 0) {
				String space = "";
				for (Author b : a.getAuthors()) {

					str.append(space + b.getAuthorname());
					space = ",";
					
				}
			}
			str.append("</td> <td>");
			if (a.getPublisher() != null) {

				str.append(a.getPublisher().getPublishername());
			}
			str.append("</td>");

			str.append("<td><button class='btn btn-sm btn-success' data-toggle='modal'data-target='#myModal1' ");
			str.append("href='editBook.jsp?bookID ="
					+ a.getBookid()
					+ "'>Edit</button><td><button class='btn btn-sm btn-danger' data-toggle='modal' data-target='#myModal1'");
			str.append("href='deleteBook1.jsp?bookID=" + a.getBookid()
					+ "'>Delete</button><td></tr>");
		}
		response.getWriter().append(str);

	}

	private void searchBorrowers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));

		Administrator admService = Administrator.getInstance();
		if (searchString == null || searchString.length() < 0) {
			searchString = null;
		}

		List<Borrower> Borrowers = new ArrayList<Borrower>();
		Borrowers = admService.getBorrowers(pageNo, searchString);

		request.setAttribute("Borrowers", Borrowers);
		request.setAttribute("searchString", searchString);

		StringBuilder str = new StringBuilder();
		str.append("<tr><th>Index</th><th>Borrower</th><th>Edit</th><th>Delete</th></tr>");

		for (Borrower a : Borrowers) {

			str.append("<tr><td>" + Borrowers.indexOf(a) + 1 + "</td>");
			str.append("<td>"
					+ a.getBorrowername()
					+ "</td><td><button class='btn btn-sm btn-success' data-toggle='modal'data-target='#myModal1' ");
			str.append("href='editBorrower.jsp?borrowerID= ="
					+ a.getCardno()
					+ "'>Edit</button><td><button class='btn btn-sm btn-danger' data-toggle='modal' data-target='#myModal1'");
			str.append("href='deleteBorrower1.jsp?borrowerID=" + a.getCardno()
					+ "'>Delete</button><td></tr>");
		}
		response.getWriter().append(str);

	}

	private void searchPublishers(HttpServletRequest request) throws Exception {
		String searchString = request.getParameter("searchString");
		Administrator admService = Administrator.getInstance();
		List<Publisher> Publishers = new ArrayList<Publisher>();
		Publishers = admService.getPublishers(1, searchString);
		request.setAttribute("Publishers", Publishers);
		request.setAttribute("searchString", searchString);
	}
	
	
	private void searchLibraryBranches(HttpServletRequest request) throws Exception {
		String searchString = request.getParameter("searchString");
		Administrator admService = Administrator.getInstance();
		List<LibraryBranch>  branches = new ArrayList<LibraryBranch>();
		 branches = admService.getLibraryBranches(1, searchString);
		request.setAttribute(" branches",  branches);
		request.setAttribute("searchString", searchString);
		}

	private void addBook(HttpServletRequest request) throws Exception {
		String title = request.getParameter("bookTitle");
		String[] authorId = request.getParameterValues("authorId");
		String pubId = request.getParameter("pubId");

		Book b = new Book();
		Publisher p = new Publisher();

		b.setTitle(title);
		p.setPublisherid(Integer.parseInt(pubId));
		b.setPublisher(p);

		List<Author> authors = new ArrayList<Author>();
		for (String auth : authorId) {
			Author a = new Author();
			a.setAuthorid(Integer.parseInt(auth));
			authors.add(a);
		}

		b.setAuthors(authors);
		Administrator admService = Administrator.getInstance();
		admService.addBook(b);
	}

	private void updateAuthor(HttpServletRequest request) throws Exception {

		String authorId = request.getParameter("authorId");
		String author = request.getParameter("Author");

		Author a = new Author();
		a.setAuthorid(Integer.parseInt(authorId));
		a.setAuthorname(author);

		Administrator admService = Administrator.getInstance();
		admService.updateAuthor(a);

	}

	private void updateBook(HttpServletRequest request) throws Exception {

		String bookId = request.getParameter("bookid");
		String title = request.getParameter("booktitle");

		Book a = new Book();
		a.setBookid(Integer.parseInt(bookId));
		a.setTitle(title);

		Administrator admService = Administrator.getInstance();
		admService.updateBook(a);

	}

	private void updatePublisher(HttpServletRequest request) throws Exception {

		String pubId = request.getParameter("pubId");
		String name = request.getParameter("publisher");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		Publisher a = new Publisher();
		a.setPublisherid(Integer.parseInt(pubId));
		a.setPublishername(name);
		a.setPublisheraddress(address);
		a.setPublisherphone(phone);

		Administrator admService = Administrator.getInstance();
		admService.updatePublisher(a);

	}
	
	private void updateLibraryBranch1(HttpServletRequest request) throws Exception {

		String branchid = request.getParameter("branchid");
		String name = request.getParameter("name");
		String address = request.getParameter("address");

		LibraryBranch a = new LibraryBranch();

		a.setBranchid(Integer.parseInt(branchid));
		a.setBranchname(name);
		a.setBranchaddress(address);

		Librarian libService = Librarian.getInstance();
		libService.upadteLibraryBranch(a);
	}
	


	private void addAuthor(HttpServletRequest request) throws Exception {
		String authorName = request.getParameter("authorName");

		Author a = new Author();
		a.setAuthorname(authorName);

		Administrator admService = Administrator.getInstance();
		admService.addAuthor(a);
	}
	
	
	private void addBranch(HttpServletRequest request) throws Exception {
		String branchName = request.getParameter("branchName");
		String branchAddress= request.getParameter("branchAddress");

		LibraryBranch lb = new LibraryBranch();
		lb.setBranchname(branchName);
		lb.setBranchaddress(branchAddress);
		

		Administrator admService = Administrator.getInstance();
		admService.addLibraryBranch(lb);
	}
	
	
	
	

	private void updatedate(HttpServletRequest request) throws Exception {

		String dueDate = request.getParameter("dueDate");
		String cardNo = request.getParameter("cardNo");
		String bookid = request.getParameter("bookid");
		String branchid = request.getParameter("branchid");
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(dueDate);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		BookLoans bl = new BookLoans();
		Borrower br = new Borrower();
		Book b=new Book();
		LibraryBranch lb = new LibraryBranch();
		b.setBookid(Integer.parseInt(bookid ));
		bl.setBook(b);
		lb.setBranchid(Integer.parseInt(branchid));
		bl.setBranch(lb);
		br.setCardno(Integer.parseInt(cardNo));
		bl.setBorrower(br);
		bl.setDuedate(sqlDate);
		Administrator admService = Administrator.getInstance();
		admService.OverrideDueDate(bl);
	}

	private void addPublisher(HttpServletRequest request) throws Exception {

		String publisherName = request.getParameter("publisherName");
		String publisheraddress = request.getParameter("publisheraddress");
		String publisherphone = request.getParameter("publisherphone");

		Publisher a = new Publisher();
		a.setPublishername(publisherName);
		a.setPublisheraddress(publisheraddress);
		a.setPublisherphone(publisherphone);

		Administrator admService = Administrator.getInstance();
		admService.addPublisher(a);
	}

	private void deletePublisher(HttpServletRequest request) throws Exception {

		String[] pubId = request.getParameterValues("pubId");

		List<Publisher> pub= new ArrayList<Publisher>();
		
          	for (String pubid : pubId) {
				Publisher p=new Publisher();
				p.setPublisherid(Integer.parseInt(pubid));
				pub.add(p);
			}
		
	Administrator admService = Administrator.getInstance();
		admService.deletePublisher(pub);
	}
	
	
	
	private void deleteLibraryBranch(HttpServletRequest request) throws Exception {

		String branchid = request.getParameter("branchid");

				LibraryBranch lb=new LibraryBranch();
				lb.setBranchid(Integer.parseInt(branchid));
		
	Administrator admService = Administrator.getInstance();
		admService.deleteLibraryBranch(lb);
	}
	

	private void deleteBook(HttpServletRequest request) throws Exception {

		String[] bookId = request.getParameterValues("bookid");

		List<Book> books = new ArrayList<Book>();

		for (int i = 0; i < bookId.length; i++) {
			books.add(new Book(Integer.parseInt(bookId[i])));
		}

		Administrator admService = Administrator.getInstance();
		admService.deleteBook(books);

	}

	private void editBookAuthPublisher(HttpServletRequest request)
			throws Exception {

		String bookId = request.getParameter("bookid");
		String[] authorId = request.getParameterValues("authorId");
		String pubId = request.getParameter("pubId");
		String booktitle = request.getParameter("booktitle");
		Administrator admService = Administrator.getInstance();

		Book a = new Book();
		Publisher b = new Publisher();
		List<Author> authList = new ArrayList<Author>();

		a.setBookid(Integer.parseInt(bookId));
		a.setTitle(booktitle);

		b.setPublisherid(Integer.parseInt(pubId));
		a.setPublisher(b);

		for (int i = 0; i < authorId.length; i++) {
			authList.add(new Author(Integer.parseInt(authorId[i])));
		}

		admService.updateDeleteforBookID(a, authList);
	}

	private void deleteAuthor(HttpServletRequest request) throws Exception {

		String[] authorId = request.getParameterValues("authorId");
		List<Author> auth = new ArrayList<Author>();

		for (int i = 0; i < authorId.length; i++) {
			auth.add(new Author(Integer.parseInt(authorId[i])));

		}

		Administrator admService = Administrator.getInstance();
		admService.deleteAuthor(auth);

	}

	private void deleteBorrower(HttpServletRequest request) throws Exception {

		String cardno = request.getParameter("cardno");

		Borrower a = new Borrower();
		a.setCardno(Integer.parseInt(cardno));

		Administrator admService = Administrator.getInstance();
		admService.deleteBorrower(a);

	}

	private void updateBorrower(HttpServletRequest request) throws Exception {

		String cardno = request.getParameter("cardno");
		String Borrower = request.getParameter("Borrower");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		Borrower a = new Borrower();
		a.setCardno(Integer.parseInt(cardno));
		a.setBorrowername(Borrower);
		a.setBorroweraddress(address);
		a.setBorrowerphone(phone);

		Administrator admService = Administrator.getInstance();
		admService.updateBorrower(a);

	}

	private void addBorrower(HttpServletRequest request) throws Exception {

		String Borrower = request.getParameter("Borrower");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");

		Borrower a = new Borrower();

		a.setBorrowername(Borrower);
		a.setBorroweraddress(address);
		a.setBorrowerphone(phone);

		Administrator admService = Administrator.getInstance();
		admService.addBorrower(a);

	}

	private void showAddBook(HttpServletRequest request) throws Exception {
		Administrator admService = Administrator.getInstance();
		List<Author> authorList = admService.getAllAuthors();
		List<Publisher> pubList = admService.getAllPublishers();
		request.setAttribute("authorList", authorList);
		request.setAttribute("pubList", pubList);

	}

	private void showAuthors(HttpServletRequest request) throws Exception {
		Administrator admService = Administrator.getInstance();
		List<Author> authorList = admService.getAllAuthors();
		request.setAttribute("authorList", authorList);

	}

	public void showBooks(HttpServletRequest request) throws Exception {
		Administrator admService = Administrator.getInstance();
		List<Book> booklist = admService.getAllBooks();
		request.setAttribute("booklist", booklist);
	}

	private void showPublishers(HttpServletRequest request) throws Exception {
		Administrator admService = Administrator.getInstance();
		List<Publisher> pubList = admService.getAllPublishers();
		request.setAttribute("pubList", pubList);
	}

	private void showBorrowers(HttpServletRequest request) throws Exception {
		Administrator admService = Administrator.getInstance();
		List<Borrower> BorrowList = admService.getAllBorrower();
		request.setAttribute("BorrowList", BorrowList);
	}

	private void showBookLoans(HttpServletRequest request) throws Exception {
		Administrator admService = Administrator.getInstance();
		List<BookLoans> BookLoan = admService.getAllLoans();
		request.setAttribute("BookLoan", BookLoan);

	}

}
