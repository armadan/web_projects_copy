package com.library.gcit.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.gcit.data.BookCopiesDAO;
import com.library.gcit.data.BookLoansDAO;
import com.library.gcit.data.BorrowerDAO;
import com.library.gcit.data.ConnectionUtils;
import com.library.gcit.entity.Book;
import com.library.gcit.entity.BookCopies;
import com.library.gcit.entity.BookLoans;
import com.library.gcit.entity.Borrower;
import com.library.gcit.entity.LibraryBranch;

import conn.library.gcit.Exceptions.LibraryExceptions;

public class BorrowerService {

	// Lazy Singleton
	private static BorrowerService instance = null;

	private BorrowerService() {

	}

	public static BorrowerService getInstance() {
		// double lock Synchronization
		if (instance == null) {
			synchronized (BorrowerService.class) {
				if (instance == null)
					instance = new BorrowerService();
			}
		}
		return instance;

	}

	// check card validity

	public boolean CardValidation(Borrower a) throws SQLException,
			LibraryExceptions {

		boolean  cardFlag = true; 
		Connection conn = ConnectionUtils.getConnection();
		BorrowerDAO bDAO = new BorrowerDAO(conn);
		

		try {
			if (bDAO.read(new Integer[] { a.getCardno() }) == null) {
				cardFlag = false;	
			}
		} catch (Exception e) {
		       e.printStackTrace();
		} finally {
			conn.close();
		}
		return  cardFlag;
	}

	// check out a book

	public boolean checkOutBook(BookLoans bl) throws SQLException,
			LibraryExceptions {

		Connection conn = ConnectionUtils.getConnection();

		// check the number of books
		try {
			BookLoansDAO blDAO = new BookLoansDAO(conn);
			BookCopiesDAO bkDAO = new BookCopiesDAO(conn);
			BookCopies bc = new BookCopies();
			bc.setBook(bl.getBook());
			bc.setBranchid(bl.getBranch());
			BookCopies bookcopyObject = bkDAO.read(new Integer[] { bc.getBook().getBookid(),bc.getBranchid().getBranchid()});
			int noofcopies = bookcopyObject.getNoofcopies();
			int newnoofcopies = noofcopies - 1;
			bc.setNoofcopies(newnoofcopies);
			bkDAO.update(bc);
			
			BookLoans bookloanOject=blDAO.read(new Integer[] { bl.getBook().getBookid(),	bl.getBranch().getBranchid(),bl.getBorrower().getCardno()});
			
			
			if (bookloanOject == null){
                blDAO.create(bl);
			} 
			else if(bookloanOject.getDatein()!=null){
				blDAO.delete(bl);
				blDAO.create(bl);
		  	}
			
			
			
			conn.commit();

		} catch (Exception e) {
			conn.rollback();

		} finally {
			conn.close();
		}
		return true;

	}

	// return a book

	public void returBook(BookLoans bl) throws SQLException, LibraryExceptions {

		Connection conn = ConnectionUtils.getConnection();
		BookLoansDAO blDAO = new BookLoansDAO(conn);

		try {

			// increase the no copies
			BookCopies bc = new BookCopies();
			bc.setBook(bl.getBook());
			bc.setBranchid(bl.getBranch());
			BookCopiesDAO bkDAO = new BookCopiesDAO(conn);
			BookCopies bookcopyObject = bkDAO.read(new Integer[] { bc.getBook().getBookid(),bc.getBranchid().getBranchid()});

			
			int noofcopies = bookcopyObject .getNoofcopies();
			int newnoofcopies = noofcopies + 1;
			bc.setNoofcopies(newnoofcopies);
			bkDAO.update(bc);
			BookLoans bookloanOject=blDAO.read(new Integer[] { bl.getBook().getBookid(),	bl.getBranch().getBranchid(),bl.getBorrower().getCardno()});
					
						
		

			if (bookloanOject!= null) 
			{
				blDAO.updateReturnTxn(bl);
			} else {
				conn.rollback();
				throw new LibraryExceptions("The user has not lent a book");
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();

		} finally {
			conn.close();
		}

	}

	public List<Book> CheckAllBook(LibraryBranch lb) throws SQLException,
			LibraryExceptions {
		List<Book> books = new ArrayList<Book>();
		List<BookCopies> bookcopy = new ArrayList<BookCopies>();
		Connection conn = ConnectionUtils.getConnection();
		BookCopiesDAO bDAO = new BookCopiesDAO(conn);
		String sql = "SELECT * FROM  tbl_book_copies where branchId=? AND noOfCopies>0";
		bookcopy = bDAO.readAll(sql, new Integer[] { lb.getBranchid() });
		for (BookCopies a : bookcopy) {
			books.add(a.getBook());
		}

		return books;
	}
		
		
		
	public List<Book> ReturnAllBook(LibraryBranch lb) throws SQLException,
		LibraryExceptions {
	List<Book> books = new ArrayList<Book>();
	List<BookCopies> bookcopy = new ArrayList<BookCopies>();
	Connection conn = ConnectionUtils.getConnection();
	BookCopiesDAO bDAO = new BookCopiesDAO(conn);
	String sql = "SELECT * FROM  tbl_book_copies where branchId=?";
	bookcopy = bDAO.readAll(sql, new Integer[] { lb.getBranchid() });
	for (BookCopies a : bookcopy) {
		books.add(a.getBook());
	}

	return books;
	}

}
