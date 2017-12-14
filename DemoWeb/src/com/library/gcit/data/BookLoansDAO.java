package com.library.gcit.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.gcit.entity.Author;
import com.library.gcit.entity.Book;
import com.library.gcit.entity.BookLoans;

public class BookLoansDAO extends BaseDAO<BookLoans> {

	public BookLoansDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(BookLoans be) throws SQLException {
		save("insert into tbl_book_loans(bookid,branchid,cardno,dateout,duedate,datein) values (?,?,?,curdate(),cast(curdate()+7 as datetime),null)",
				new Object[] { be.getBook().getBookid(),
						be.getBranch().getBranchid(),
						be.getBorrower().getCardno() });

	}

	@Override
	public void update(BookLoans be) throws SQLException {
		save("update tbl_book_loans set dateout = ?,duedate=?,datein=? where bookid=? AND branchid=? AND cardno=?",
				new Object[] { be.getDateout(), be.getDuedate(),
						be.getDatein(), be.getBook().getBookid(),
						be.getBranch().getBranchid(),
						be.getBorrower().getCardno() });

	}

	public void updateReturnTxn(BookLoans be) throws SQLException {
		save("update tbl_book_loans set datein=curdate() where bookid=? AND branchid=? AND cardno=?",
				new Object[] { be.getBook().getBookid(),
						be.getBranch().getBranchid(),
						be.getBorrower().getCardno() });

	}
	
	public void updateDuedat(BookLoans be) throws SQLException {
		save("update tbl_book_loans set duedate=? where bookid=? AND branchid=? AND cardno=?",
				new Object[] { be.getDuedate(),
						be.getBook().getBookid(),be.getBranch().getBranchid(),be.getBorrower().getCardno()});

	}
	
	
	

	@Override
	public void delete(BookLoans be) throws SQLException {
		save("DELETE FROM tbl_book_loans WHERE bookid=? AND branchid=? AND cardno=?",
				new Object[] { be.getBook().getBookid(),
						be.getBranch().getBranchid(),
						be.getBorrower().getCardno() });

	}

	@Override
	public BookLoans read(Integer[] pk) throws SQLException {
		return read(
				"SELECT * FROM tbl_book_loans WHERE bookid=? AND branchid=? AND cardno=?",
				pk);
	}
	
	
	public BookLoans readCardNo(Integer[] pk) throws SQLException {
		return read("SELECT * FROM tbl_book_loans WHERE cardno=?",pk);
			
	}
	
	
	public BookLoans readBranchid(Integer[] pk) throws SQLException {
		return read("SELECT * FROM tbl_book_loans WHERE branchid=?",pk);
			
	}

	@Override
	public List<BookLoans> readAll() throws SQLException {
		return readAll("SELECT * FROM tbl_book_loans", new Object[] {});
	}
	
	
	
	

	@Override
	public List<BookLoans> readResult(ResultSet rs) throws SQLException {
		List<BookLoans> list = new ArrayList<BookLoans>();
		Connection conn = ConnectionUtils.getConnection();
		while (rs.next()) {

			BookDAO bk = new BookDAO(conn);
			LibraryBranchDAO lb = new LibraryBranchDAO(conn);
			BorrowerDAO br = new BorrowerDAO(conn);
			BookLoans a = new BookLoans(rs.getDate("dateout"),
					rs.getDate("duedate"), rs.getDate("datein"));
			a.setBook(bk.read(new Integer[] { rs.getInt("bookId") }));
			a.setBranch(lb.read(new Integer[] { rs.getInt("branchId") }));
			a.setBorrower(br.read(new Integer[] { rs.getInt("cardNo") }));
			list.add(a);
		}

		return list;
	}

	

}
