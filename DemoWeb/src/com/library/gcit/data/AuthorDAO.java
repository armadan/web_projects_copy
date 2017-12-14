package com.library.gcit.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.gcit.entity.Author;
import com.library.gcit.entity.Book;

public class AuthorDAO extends BaseDAO<Author> {

	public AuthorDAO(Connection conn) {
		super(conn);
	}

	private static final String GET_COUNT_sql = "select count(*) from tbl_author";
	private static final String SELECT_ALL = "select * from tbl_author";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_author where authorName like ?";

	@Override
	public void create(Author be) throws SQLException {
		save("insert into tbl_author(authorName) values (?)",
				new Object[] { be.getAuthorname() });
		
		
		

	}

	@Override
	public void update(Author be) throws SQLException {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { be.getAuthorname(), be.getAuthorid() });

	}

	@Override
	public void delete(Author be) throws SQLException {
		save("DELETE FROM tbl_author WHERE authorid=?",
				new Object[] { be.getAuthorid() });
	}

	@Override
	public Author read(Integer[] pk) throws SQLException {
		return read("SELECT * FROM tbl_author Where authorid=?", pk);
	}

	public List<Author> readAll() throws SQLException {
		return readAll("SELECT * FROM tbl_author", new Object[] {});

	}

	public List<Author> readAll(Integer pageNo, Integer pageSize,
			String searchString) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if (searchString != null && !"".equals(searchString)) {
			searchString = "%" + searchString + "%";
			// valueList.add(searchString);
			return readAllNew(SELECT_ALL_SEARCH, new String[] { searchString });
		} else {
			return readAllNew(SELECT_ALL, new String[] {});
		}

	}

	public Integer getCount() throws SQLException {
		return getCount(GET_COUNT_sql);

	}

	public Integer getAuthorBySearch(String searchString) throws SQLException {

		if (searchString != null && !"".equals(searchString)) {
			searchString = "%" + searchString + "%";
			// valueList.add(searchString);
			return getCount1(SELECT_ALL_SEARCH, new String[] { searchString });
		} else {
			return getCount1(SELECT_ALL, new String[] {});
		}

	}

	public List<Author> readResult(ResultSet rs) throws SQLException {
		List<Author> list = new ArrayList<Author>();
		
		
			while (rs.next()) {
				Author a = new Author();
				a.setAuthorname(rs.getString("authorName"));
				a.setAuthorid(rs.getInt("authorId"));
				//a.setBooks(bdao.getBooksForAuthor(a));
				list.add(a);
			}

			return list;

		
	}

	public List<Author> getAuthorForBook(Book b) throws SQLException {
		String sql = "SELECT * FROM tbl_author WHERE authorId IN(SELECT authorId FROM tbl_book_authors where authorId is not null and bookId =? )";
		return readAll(sql, new Object[] { b.getBookid() });
	}

}