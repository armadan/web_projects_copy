package com.library.gcit.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.gcit.entity.Author;
import com.library.gcit.entity.Book;


public class BookDAO extends BaseDAO<Book> {
	
	
	private static final String SELECT_ALL = "select * from tbl_book";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_book where title like ?";

	public BookDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Book be) throws SQLException {
		save("insert into tbl_book(title) values (?)",
				new Object[] { be.getTitle() });

	}

	@Override
	public void update(Book be) throws SQLException {
		save("update tbl_book set title = ? where bookid = ?", new Object[] {
				be.getTitle(), be.getBookid() });

	}

	@Override
	public void delete(Book be) throws SQLException {
		save("DELETE FROM tbl_book WHERE bookid=?",
				new Object[] { be.getBookid() });

	}
	
	public void updateforPubID(Book be) throws SQLException {
		   save("UPDATE tbl_book set pubId=? where bookId=?", new Object[]{be.getPublisher().getPublisherid(),be.getBookid()});
	}
	
	
	public Integer  addBookID(Book be) throws SQLException {
		return saveAndGetId("insert into tbl_book (title, pubId) values (?,?)", new Object[] {be.getTitle(), be.getPublisher().getPublisherid()});
	}
	
	
	public void  insertforAuthID(Book be,Author aut) throws SQLException {
		  save("INSERT into tbl_book_authors(bookId,authorId) values(?,?)",new Object[]{be.getBookid(),aut.getAuthorid()});
		
	}
	
	
	
	
	public void  deleteforAuthID(Book be) throws SQLException {
		  save("DELETE FROM  tbl_book_authors WHERE bookId=?",new Object[]{be.getBookid()});
		
	}

	@Override
	public Book read(Integer[] pk) throws SQLException {
		return read("SELECT * FROM tbl_book Where bookId=?", pk);
	}
	


	@Override
	public List<Book> readAll() throws SQLException {
		return readAll("SELECT * FROM tbl_book", new Object[] {});
	}
	
	public List<Book> readAll(Integer pageNo,Integer pageSize,String searchString) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if(searchString !=null && !"".equals(searchString)){
			searchString = "%"+searchString+"%";
			return  readAllNew(SELECT_ALL_SEARCH,new String[]{searchString});
		}else{
			return readAllNew(SELECT_ALL, new String[]{});
		}
	}
	
	
	public Integer getBookBySearch(String searchString) throws SQLException{
		
		if(searchString !=null && !"".equals(searchString)){
			searchString = "%"+searchString+"%";
			//valueList.add(searchString);
			return  getCount1(SELECT_ALL_SEARCH,new String[]{searchString});
		}else{
			return getCount1(SELECT_ALL, new String[]{});
		}

		
	}
	
	
	@Override
	public List<Book> readResult(ResultSet rs) throws SQLException {
		List<Book> list = new ArrayList<Book>();
		Connection conn = ConnectionUtils.getConnection();
		
			 AuthorDAO authDao =new AuthorDAO(conn);
			 PublisherDAO pbDao =new PublisherDAO(conn);
			 
			while (rs.next()) {
				 
				 Book a =new Book(rs.getString("title"));
				  a.setTitle(rs.getString("title"));
				  a.setBookid(rs.getInt("bookId"));
				 
				  a.setPublisher(pbDao.read(new Integer[]{rs.getInt("pubId")}));
				 
			     a.setAuthors(authDao.getAuthorForBook(a));
				list.add(a);
			}
			   
	            return list;

		
	}
	
	

	/*public List<Book> getBooksForAuthor(Author a) throws SQLException{
		String sql="SELECT * FROM tbl_book WHERE bookid IN(SELECT bookid FROM tbl_book_authors where bookid is not null and authorid =?)";
		return readAll(sql, new Object[] {a.getAuthorid()});
	}*/

	
	
}