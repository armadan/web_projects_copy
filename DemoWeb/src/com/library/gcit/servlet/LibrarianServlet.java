package com.library.gcit.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.gcit.entity.Book;
import com.library.gcit.entity.BookCopies;
import com.library.gcit.entity.LibraryBranch;
import com.library.gcit.service.Librarian;
import com.library.gcit.servlet.AdministratorServlet;

/**
 * Servlet implementation class LibrarianServlet
 */
@WebServlet(name = "LibrarianServlet", urlPatterns = { "/updateLibrary",
		"/showLibraries", "/showBookBranch", "/getNoBooks", "/editNocopies" })
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
			case "/updateLibrary": {
				updateLibrary(request);
				request.setAttribute("result", "library updated Succesfully!");
				break;
			}

			case "/showLibraries": {
				showLibraries(request);
				rd = getServletContext().getRequestDispatcher(
						"/updateLibrary.jsp");
				break;
			}

			case "/showBookBranch": {
				showLibraries(request);
				AdministratorServlet admin = new AdministratorServlet();
				admin.showBooks(request);
				rd = getServletContext()
						.getRequestDispatcher("/getNoBooks.jsp");
				break;
			}

			case "/getNoBooks": {
				getNoBooks(request, response);
				rd = getServletContext().getRequestDispatcher(
						"/editNoCopies.jsp");
				break;
			}

			case "/editNocopies": {
				editNocopies(request);
				request.setAttribute("result",
						"no of copies updated Succesfully!");
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

	private void updateLibrary(HttpServletRequest request) throws Exception {

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

	private void getNoBooks(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int noofcopies = 0;
		String branchid = request.getParameter("branchid");
		String bookId = request.getParameter("bookid");

		LibraryBranch a = new LibraryBranch();
		Book b = new Book();
		BookCopies c = new BookCopies();

		a.setBranchid(Integer.parseInt(branchid));
		b.setBookid(Integer.parseInt(bookId));
		c.setBook(b);
		c.setBranchid(a);
		Librarian libService = Librarian.getInstance();
		noofcopies = libService.getCopies(c);
		request.setAttribute("noofcopies", noofcopies);
		request.setAttribute("bookid", bookId);
		request.setAttribute("branchid", branchid);
	}

	private void editNocopies(HttpServletRequest request) throws Exception {
		String newcopies = request.getParameter("newcopies");

		String branchid = request.getParameter("branchid");
		String bookId = request.getParameter("bookid");

		LibraryBranch a = new LibraryBranch();
		Book b = new Book();
		BookCopies c = new BookCopies();

		a.setBranchid(Integer.parseInt(branchid));
		b.setBookid(Integer.parseInt(bookId));
		c.setNoofcopies(Integer.parseInt(newcopies));
		c.setBook(b);
		c.setBranchid(a);

		Librarian libService = Librarian.getInstance();
		libService.addCopiesOfBook(c);
	}

	private void showLibraries(HttpServletRequest request) throws Exception {
		Librarian libService = Librarian.getInstance();
		List<LibraryBranch> branches = libService.getAllBranches();
		request.setAttribute("branches", branches);
	}

}