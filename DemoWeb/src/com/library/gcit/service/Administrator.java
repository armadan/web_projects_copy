package com.library.gcit.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.library.gcit.data.AuthorDAO;
import com.library.gcit.data.BookDAO;
import com.library.gcit.data.BookLoansDAO;
import com.library.gcit.data.BorrowerDAO;
import com.library.gcit.data.ConnectionUtils;
import com.library.gcit.data.LibraryBranchDAO;
import com.library.gcit.data.PublisherDAO;
import com.library.gcit.entity.Author;
import com.library.gcit.entity.Book;
import com.library.gcit.entity.BookLoans;
import com.library.gcit.entity.Borrower;
import com.library.gcit.entity.LibraryBranch;
import com.library.gcit.entity.Publisher;

import conn.library.gcit.Exceptions.LibraryExceptions;

public class Administrator {

	// Lazy Singleton
	private static Administrator instance = null;

	private Administrator() {

	}

	public static Administrator getInstance() {
		// double lock Synchronization
		if (instance == null) {
			synchronized (Administrator.class) {
				if (instance == null)
					instance = new Administrator();
			}
		}
		return instance;

	}

	// addAuthor

	public void addAuthor(Author a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		AuthorDAO aDAO = new AuthorDAO(conn);

		if (a == null || a.getAuthorname() == null || a.getAuthorname().trim().length() == 0) {
			throw new LibraryExceptions(" Author cannot be null or blank");
		} else if (a.getAuthorname().trim().length() > 45) {
			throw new LibraryExceptions(" Author names cannot more tahn 45 charcters");
		}

		try {
			aDAO.create(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// update author

	public void updateAuthor(Author a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		AuthorDAO aDAO = new AuthorDAO(conn);

		if (a == null || a.getAuthorname() == null || a.getAuthorname().trim().length() == 0) {
			throw new LibraryExceptions(" Author cannot be null or blank");
		} else if (a.getAuthorname().trim().length() > 45) {
			throw new LibraryExceptions(" Author names cannot more tahn 45 charcters");
		}

		try {
			aDAO.update(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// Delete author

	public void deleteAuthor(List<Author> authList) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		AuthorDAO aDAO = new AuthorDAO(conn);

		try {
			for (Author a : authList) {
				aDAO.delete(a);
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// add Book

	public void addBook(Book a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		BookDAO aDAO = new BookDAO(conn);

		if (a == null || a.getTitle() == null || a.getTitle().trim().length() == 0) {
			throw new LibraryExceptions(" Book title cannot be null or blank");
		} else if (a.getTitle().trim().length() > 45) {
			throw new LibraryExceptions(" Book title names cannot more tahn 45 charcters");
		}

		try {
			a.setBookid(aDAO.addBookID(a));

			for (Author auth : a.getAuthors()) {
				aDAO.insertforAuthID(a, auth);
			}

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	public void updateBook(Book a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		BookDAO aDAO = new BookDAO(conn);

		if (a == null || a.getTitle() == null || a.getTitle().trim().length() == 0) {
			throw new LibraryExceptions(" Book title cannot be null or blank");
		} else if (a.getTitle().trim().length() > 45) {
			throw new LibraryExceptions(" Book title names cannot more tahn 45 charcters");
		}

		try {
			aDAO.update(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// update author,publisher for book

	public void updateDeleteforBookID(Book a, List<Author> authorList) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		BookDAO aDAO = new BookDAO(conn);

		try {
			aDAO.update(a);
			aDAO.updateforPubID(a);
			aDAO.deleteforAuthID(a);

			for (Author auth : authorList) {
				aDAO.insertforAuthID(a, auth);
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// delete book

	public void deleteBook(List<Book> books) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		BookDAO aDAO = new BookDAO(conn);

		try {
			for (Book a : books) {
				aDAO.delete(a);
			}

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// add Library branch

	public void addLibraryBranch(LibraryBranch a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		LibraryBranchDAO aDAO = new LibraryBranchDAO(conn);

		if (a == null || a.getBranchname() == null || a.getBranchname().trim().length() == 0) {
			throw new LibraryExceptions(" Branch  name be null or blank");
		} else if (a.getBranchname().trim().length() > 45) {
			throw new LibraryExceptions(" Branch name names cannot more tahn 45 charcters");
		}

		if (a == null || a.getBranchaddress() == null || a.getBranchaddress().trim().length() == 0) {
			throw new LibraryExceptions(" Branch address be null or blank");
		} else if (a.getBranchaddress().trim().length() > 45) {
			throw new LibraryExceptions(" Branch address cannot more tahn 45 charcters");
		}

		try {
			aDAO.create(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// Delete Libarary Branch

	public void deleteLibraryBranch(LibraryBranch a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		LibraryBranchDAO aDAO = new LibraryBranchDAO(conn);

		try {
			aDAO.delete(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// update Library branch

	public void upadteLibraryBranch(LibraryBranch a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		LibraryBranchDAO aDAO = new LibraryBranchDAO(conn);

		if (a == null || a.getBranchname() == null || a.getBranchname().trim().length() == 0) {
			throw new LibraryExceptions(" Branch  name cannot be null or blank");
		} else if (a.getBranchname().trim().length() > 45) {
			throw new LibraryExceptions(" Branch name names cannot more tahn 45 charcters");
		}

		if (a == null || a.getBranchaddress() == null || a.getBranchaddress().trim().length() == 0) {
			throw new LibraryExceptions(" Branch address cannot be null or blank");
		} else if (a.getBranchaddress().trim().length() > 45) {
			throw new LibraryExceptions(" Branch address cannot more tahn 45 charcters");
		}

		try {
			aDAO.update(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// add publisher
	public void addPublisher(Publisher a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		PublisherDAO aDAO = new PublisherDAO(conn);

		if (a == null || a.getPublishername() == null || a.getPublishername().trim().length() == 0) {
			throw new LibraryExceptions(" publisher  name cannot be null or blank");
		} else if (a.getPublishername().trim().length() > 45) {
			throw new LibraryExceptions(" publisher names cannot more tahn 45 charcters");
		}

		if (a == null || a.getPublisheraddress() == null || a.getPublisheraddress().trim().length() == 0) {
			throw new LibraryExceptions(" publisher address cannot be null or blank");
		} else if (a.getPublisheraddress().trim().length() > 45) {
			throw new LibraryExceptions(" publisher address cannot more tahn 45 charcters");
		}

		if (a == null || a.getPublisherphone() == null || a.getPublisherphone().trim().length() == 0) {
			throw new LibraryExceptions(" publisher phone  cannot be null or blank");
		} else if (a.getPublisherphone().trim().length() > 45) {
			throw new LibraryExceptions("publisher address cannot more tahn 45 charcters");
		}

		try {
			aDAO.create(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// update publisher

	public void updatePublisher(Publisher a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		PublisherDAO aDAO = new PublisherDAO(conn);

		if (a == null || a.getPublishername() == null || a.getPublishername().trim().length() == 0) {
			throw new LibraryExceptions(" publisher  name cannot be null or blank");
		} else if (a.getPublishername().trim().length() > 45) {
			throw new LibraryExceptions(" publisher names cannot more tahn 45 charcters");
		}

		try {
			aDAO.update(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// delete publisher

	public void deletePublisher(List<Publisher> Pub) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		PublisherDAO aDAO = new PublisherDAO(conn);

		try {

			for (Publisher p : Pub) {
				aDAO.delete(p);
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// add borrower
	public void addBorrower(Borrower a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		BorrowerDAO aDAO = new BorrowerDAO(conn);

		if (a == null || a.getBorrowername() == null || a.getBorrowername().trim().length() == 0) {
			throw new LibraryExceptions(" Borrower  name cannot be null or blank");
		} else if (a.getBorrowername().trim().length() > 45) {
			throw new LibraryExceptions(" Borrower names cannot more tahn 45 charcters");
		}

		try {
			aDAO.create(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// update borrower
	public void updateBorrower(Borrower a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		BorrowerDAO aDAO = new BorrowerDAO(conn);

		if (a == null || a.getBorrowername() == null || a.getBorrowername().trim().length() == 0) {
			throw new LibraryExceptions(" Borrower  name cannot be null or blank");
		} else if (a.getBorrowername().trim().length() > 45) {
			throw new LibraryExceptions(" Borrower names cannot more tahn 45 charcters");
		}

		try {
			aDAO.update(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	// delete borrower

	public void deleteBorrower(Borrower a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		BorrowerDAO aDAO = new BorrowerDAO(conn);

		try {
			aDAO.delete(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	public List<Author> getAllAuthors() throws Exception {
		AuthorDAO a = new AuthorDAO(ConnectionUtils.getConnection());
		return a.readAll();
	}

	public List<Publisher> getAllPublishers() throws Exception {
		PublisherDAO a = new PublisherDAO(ConnectionUtils.getConnection());
		return a.readAll();
	}

	public List<Borrower> getAllBorrower() throws Exception {
		BorrowerDAO a = new BorrowerDAO(ConnectionUtils.getConnection());
		return a.readAll();
	}

	public List<Book> getAllBooks() throws Exception {
		BookDAO a = new BookDAO(ConnectionUtils.getConnection());
		return a.readAll();
	}

	public List<BookLoans> getAllLoans() throws Exception {
		BookLoansDAO a = new BookLoansDAO(ConnectionUtils.getConnection());
		return a.readAll();
	}

	public Author getAuthorByID(Integer authorID) throws Exception {
		AuthorDAO authDao = new AuthorDAO(com.library.gcit.data.ConnectionUtils.getConnection());
		Author author = new Author();
		author.setAuthorid(authorID);
		return authDao.read(new Integer[] { author.getAuthorid() });
	}

	public BookLoans getDueDateByID(Integer cardNo, Integer bookid, Integer branchid) throws Exception {
		BookLoansDAO loanDao = new BookLoansDAO(com.library.gcit.data.ConnectionUtils.getConnection());
		BookLoans loan = new BookLoans();
		Borrower br = new Borrower();
		Book b = new Book();
		LibraryBranch lb = new LibraryBranch();
		b.setBookid(bookid);
		loan.setBook(b);
		lb.setBranchid(branchid);
		loan.setBranch(lb);
		br.setCardno(cardNo);
		loan.setBorrower(br);
		return loanDao.read(new Integer[] { loan.getBook().getBookid(), loan.getBranch().getBranchid(),
				loan.getBorrower().getCardno() });
	}

	public Publisher getPublisherByID(Integer Publisherid) throws Exception {

		Connection conn = ConnectionUtils.getConnection();

		PublisherDAO pubDao = new PublisherDAO(conn);

		try {
			Publisher Pub = new Publisher();
			Pub.setPublisherid(Publisherid);
			return pubDao.read(new Integer[] { Pub.getPublisherid() });

		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public LibraryBranch getLibraryBranchByID(Integer branchid) throws Exception {

		Connection conn = ConnectionUtils.getConnection();

		LibraryBranchDAO lbDao = new LibraryBranchDAO(conn);

		try {
			LibraryBranch lb = new LibraryBranch();
			lb.setBranchid(branchid);
			return lbDao.read(new Integer[] { lb.getBranchid() });

		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<Author> getAuthors(Integer pageNo, String searchString) throws Exception {
		Connection conn = ConnectionUtils.getConnection();
		AuthorDAO authDao = new AuthorDAO(conn);
		try {
			return authDao.readAll(pageNo, 10, searchString);

		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<Book> getBooks(Integer pageNo, String searchString) throws Exception {
		Connection conn = ConnectionUtils.getConnection();
		BookDAO bookDao = new BookDAO(conn);
		try {
			return bookDao.readAll(pageNo, 10, searchString);

		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<Borrower> getBorrowers(Integer pageNo, String searchString) throws Exception {
		Connection conn = ConnectionUtils.getConnection();
		BorrowerDAO brDao = new BorrowerDAO(conn);
		try {
			return brDao.readAll(pageNo, 10, searchString);

		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<Publisher> getPublishers(Integer pageNo, String searchString) throws Exception {

		Connection conn = ConnectionUtils.getConnection();
		PublisherDAO pubDao = new PublisherDAO(conn);

		try {
			return pubDao.readAll(pageNo, 10, searchString);

		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<LibraryBranch> getLibraryBranches(Integer pageNo, String searchString) throws Exception {

		Connection conn = ConnectionUtils.getConnection();
		LibraryBranchDAO lbDao = new LibraryBranchDAO(conn);

		try {
			return lbDao.readAll(pageNo, 10, searchString);

		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public Book getBookByID(Integer bookID) throws Exception {
		Connection conn = ConnectionUtils.getConnection();
		BookDAO bkDao = new BookDAO(conn);

		try {
			Book book = new Book();
			book.setBookid(bookID);
			return bkDao.read(new Integer[] { book.getBookid() });

		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public Borrower getBorrowerByID(Integer BorrowerID) throws Exception {

		Connection conn = ConnectionUtils.getConnection();
		BorrowerDAO brDao = new BorrowerDAO(conn);

		try {
			Borrower brr = new Borrower();
			brr.setCardno(BorrowerID);
			return brDao.read(new Integer[] { brr.getCardno() });
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public Integer getAuthorsCount() throws SQLException {

		Connection conn = ConnectionUtils.getConnection();
		AuthorDAO authDao = new AuthorDAO(conn);

		try {
			return authDao.getCount();
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public Integer getAuthorscountBySearch(String searchString) throws SQLException {

		Connection conn = ConnectionUtils.getConnection();
		AuthorDAO authDao = new AuthorDAO(conn);

		try {
			return authDao.getAuthorBySearch(searchString);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public Integer getBookscountBySearch(String searchString) throws SQLException {

		Connection conn = ConnectionUtils.getConnection();
		BookDAO bookDao = new BookDAO(conn);

		try {
			return bookDao.getBookBySearch(searchString);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public Integer getBorrowerscountBySearch(String searchString) throws SQLException {

		Connection conn = ConnectionUtils.getConnection();
		BorrowerDAO brDao = new BorrowerDAO(conn);
		try {
			return brDao.getBorrowersBySearch(searchString);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public Integer getPublisherscountBySearch(String searchString) throws SQLException {

		Connection conn = ConnectionUtils.getConnection();
		PublisherDAO pbDao = new PublisherDAO(conn);

		try {
			return pbDao.getPublishersBySearch(searchString);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	public Integer getLibraryBranchescountBySearch(String searchString) throws SQLException {

		Connection conn = ConnectionUtils.getConnection();
		LibraryBranchDAO lbDao = new LibraryBranchDAO(conn);

		try {
			return lbDao.getLibraryBranchesBySearch(searchString);
		} catch (Exception e) {
			throw e;
		} finally {
			conn.close();
		}
	}

	// overide the duedate

	public void OverrideDueDate(BookLoans a) throws LibraryExceptions, SQLException {

		Connection conn = ConnectionUtils.getConnection();
		BookLoansDAO aDAO = new BookLoansDAO(conn);

		try {
			aDAO.updateDuedat(a);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

}
