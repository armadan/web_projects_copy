package com.library.gcit.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import conn.library.gcit.Exceptions.LibraryExceptions;

import com.library.gcit.data.BookCopiesDAO;
import com.library.gcit.data.ConnectionUtils;
import com.library.gcit.data.LibraryBranchDAO;
import com.library.gcit.entity.BookCopies;
import com.library.gcit.entity.LibraryBranch;

public class Librarian {

	// Lazy Singleton
	private static Librarian instance = null;

	private Librarian() {

	}

	public static Librarian getInstance() {
		// double lock Synchronization
		if (instance == null) {
			synchronized (Librarian.class) {
				if (instance == null)
					instance = new Librarian();
			}
		}
		return instance;

	}

	// update Library branch

	public void upadteLibraryBranch(LibraryBranch a) throws LibraryExceptions,
			SQLException {

		Connection conn = ConnectionUtils.getConnection();
		LibraryBranchDAO aDAO = new LibraryBranchDAO(conn);

		if (a == null || a.getBranchname() == null
				|| a.getBranchname().trim().length() == 0) {
			throw new LibraryExceptions(" Branch  name cannot be null or blank");
		} else if (a.getBranchname().trim().length() > 45) {
			throw new LibraryExceptions(
					" Branch name names cannot more tahn 45 charcters");
		}

		if (a == null || a.getBranchaddress() == null
				|| a.getBranchaddress().trim().length() == 0) {
			throw new LibraryExceptions(
					" Branch address cannot be null or blank");
		} else if (a.getBranchaddress().trim().length() > 45) {
			throw new LibraryExceptions(
					" Branch address cannot more tahn 45 charcters");
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

	// Add copies to the branch

	public void addCopiesOfBook(BookCopies bc) throws LibraryExceptions,
			SQLException {
		Connection conn = ConnectionUtils.getConnection();
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn);

		try {
			bcDAO.update(bc);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();

		}
	}

	// get number of copies

	public int getCopies(BookCopies bc) throws LibraryExceptions, SQLException {
		Connection conn = ConnectionUtils.getConnection();
		BookCopiesDAO bcDAO = new BookCopiesDAO(conn);
		int noofcopies = 0;

		try {
			if ((bcDAO.read(new Integer[] { bc.getBook().getBookid(),
					bc.getBranchid().getBranchid() })) == null) {
				noofcopies = 0;
				bc.setNoofcopies(noofcopies);
				bcDAO.create(bc);

			} else {

				noofcopies = bcDAO.read(
						new Integer[] { bc.getBook().getBookid(),
								bc.getBranchid().getBranchid() })
						.getNoofcopies();
			}

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
		} finally {
			conn.close();

		}
		return noofcopies;
	}

	public List<LibraryBranch> getAllBranches() throws Exception {
		LibraryBranchDAO a = new LibraryBranchDAO(
				ConnectionUtils.getConnection());
		return a.readAll();
	}

}
