package com.library.gcit.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





import com.library.gcit.entity.BookCopies;


public class BookCopiesDAO extends BaseDAO<BookCopies> {
		
	
	public  BookCopiesDAO(Connection conn){
		super(conn);
	}

	@Override
	public void create(BookCopies be) throws SQLException {
		save("insert into tbl_book_copies(bookId,branchId,noOfCopies) values (?,?,?)", new Object[] { be.getBook().getBookid(),be.getBranchid().getBranchid(),be.getNoofcopies()});	
	}

	@Override
	public void update(BookCopies be) throws SQLException {
		save("update tbl_book_copies set noOfCopies= ? where bookId=? AND branchId = ?",
				new Object[] { be.getNoofcopies(),be.getBook().getBookid(),be.getBranchid().getBranchid()});
	}
		
	

	@Override
	public void delete(BookCopies be) throws SQLException {
		  save("DELETE FROM tbl_book_copies WHERE bookId=?,branchId, = ?",new Object[] {be.getBook().getBookid(),be.getBranchid().getBranchid()});
		
	}

	@Override
	public BookCopies read(Integer[] pk) throws SQLException {
		return  read("SELECT * FROM tbl_book_copies WHERE bookId=? AND branchId=?",pk);
	}
	
	

	@Override
	public List<BookCopies> readAll() throws SQLException {
		return  readAll("SELECT * FROM tbl_book_copies WHERE bookId=?AND branchId, =?",new Object[] {});
	}
	
	
	

	@Override
	public List<BookCopies> readResult(ResultSet rs) throws SQLException {
		List<BookCopies> list =new ArrayList<BookCopies>();
		Connection conn=ConnectionUtils .getConnection();
		while(rs.next()){
			LibraryBranchDAO lb =new LibraryBranchDAO(conn);
			 BookDAO bk=new BookDAO(conn);
			 BookCopies a = new BookCopies();
			 a.setNoofcopies(rs.getInt("noOfCopies"));
			 a.setBook(bk.read(new Integer[]{rs.getInt("bookId")})); 
			 a.setBranchid(lb.read(new Integer[]{rs.getInt("branchId")}));  
			 list.add(a);		 
		 }
		 
		 return list;
	}

}
