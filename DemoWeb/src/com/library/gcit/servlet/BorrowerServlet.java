package com.library.gcit.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.gcit.entity.Book;
import com.library.gcit.entity.BookCopies;
import com.library.gcit.entity.BookLoans;
import com.library.gcit.entity.Borrower;
import com.library.gcit.entity.LibraryBranch;
import com.library.gcit.service.BorrowerService;

import conn.library.gcit.Exceptions.LibraryExceptions;

/**
 * Servlet implementation class Borrower
 */
@WebServlet(name = "BorrowerServlet", urlPatterns = { "/cardNo",
		"/borrowerBranch", "/borrowerBooks", "/cardNo2", "/borrowerBranch2",
		"/borrowerBooks2","/errorCard" })
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowerServlet() {
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

			case "/cardNo": {	
				if(cardNo(request, response)==false){
					rd=request.getRequestDispatcher("/errorCard.jsp");
				}else{
					rd=request.getRequestDispatcher("/borrowerBranch.jsp");
				}
				
				break;
			}

			case "/borrowerBranch": {
				borrowerBranch(request, response);
				rd = request.getRequestDispatcher("/borrowerBooks.jsp");
				break;
			}

			case "/borrowerBooks": {
				borrowerBooks(request);
				request.setAttribute("result",
						"you have successfully checked out a book");
				break;
			}

			case "/cardNo2": {
				if(cardNo2(request, response)==false){
					rd=request.getRequestDispatcher("/errorCard.jsp");
				}else{
					rd=request.getRequestDispatcher("/borrowerBranch2.jsp");
				}
				break;
			}

			case "/borrowerBranch2": {
				borrowerBranch2(request, response);
				rd = request.getRequestDispatcher("/borrowerBooks2.jsp");
				break;
			}

			case "/borrowerBooks2": {
				borrowerBooks2(request);
				request.setAttribute("result",
						"you have successfully  returned  a book");
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

	private boolean cardNo(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

       return cardMethod(request);
	}
	
	private boolean cardNo2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return cardMethod(request);
	}

	private boolean cardMethod(HttpServletRequest request) throws SQLException,
			LibraryExceptions {
		String cardno = request.getParameter("cardno");

		Borrower a = new Borrower();

		a.setCardno(Integer.parseInt(cardno));
		BorrowerService borrowService = BorrowerService.getInstance();
		boolean cardFlag = borrowService.CardValidation(a);
		request.setAttribute("cardFlag", cardFlag);
        request.setAttribute("cardno", cardno);     
        return cardFlag;
   
}

	private void borrowerBranch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String cardno = request.getParameter("cardno");
		String branchid = request.getParameter("branchid");

		Borrower a = new Borrower();
		LibraryBranch b = new LibraryBranch();
		BookLoans c = new BookLoans();
		a.setCardno(Integer.parseInt(cardno));
		b.setBranchid(Integer.parseInt(branchid));
		c.setBorrower(a);
		c.setBranch(b);
		BorrowerService borrowService = BorrowerService.getInstance();
		List<Book> books = borrowService.CheckAllBook(b);
		request.setAttribute("cardno", cardno);
		request.setAttribute("branchid", branchid);
		request.setAttribute("books", books);
	}

	

	private void borrowerBranch2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String cardno = request.getParameter("cardno");
		String branchid = request.getParameter("branchid");

		Borrower a = new Borrower();
		LibraryBranch b = new LibraryBranch();
		BookLoans c = new BookLoans();

		a.setCardno(Integer.parseInt(cardno));
		b.setBranchid(Integer.parseInt(branchid));
		c.setBorrower(a);
		c.setBranch(b);
		BorrowerService borrowService = BorrowerService.getInstance();
		List<Book> books = borrowService.ReturnAllBook(b);
		request.setAttribute("cardno", cardno);
		request.setAttribute("branchid", branchid);
		request.setAttribute("books", books);
	}

	private void borrowerBooks(HttpServletRequest request) throws Exception {

		BookLoans c = bookMethod(request);

		BorrowerService borrowService = BorrowerService.getInstance();
		borrowService.checkOutBook(c);
	}

	private BookLoans bookMethod(HttpServletRequest request) {
		String cardno = request.getParameter("cardno");
		String branchid = request.getParameter("branchid");
		String bookid = request.getParameter("bookid");

		Borrower a = new Borrower();
		LibraryBranch b = new LibraryBranch();
		BookLoans c = new BookLoans();
		Book d = new Book();
		BookCopies e = new BookCopies();

		a.setCardno(Integer.parseInt(cardno));
		b.setBranchid(Integer.parseInt(branchid));
		d.setBookid(Integer.parseInt(bookid));
		c.setBorrower(a);
		c.setBranch(b);
		c.setBook(d);
		e.setBook(d);
		e.setBranchid(b);
		return c;
	}

	private void borrowerBooks2(HttpServletRequest request) throws Exception {

		BookLoans c = bookMethod(request);

		BorrowerService borrowService = BorrowerService.getInstance();
		borrowService.returBook(c);
	}

}
