package com.library.gcit.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.gcit.entity.Genre;

public class GenreDAO extends BaseDAO<Genre> {

	public GenreDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Genre be) throws SQLException {
		save("insert into tbl_genre(genre_name) values (?)",
				new Object[] { be.getGenrename() });

	}

	@Override
	public void update(Genre be) throws SQLException {
		save("update  tbl_genre set  genre_name= ? where genre_id = ?",
				new Object[] { be.getGenrename(), be.getGenreid() });

	}

	@Override
	public void delete(Genre be) throws SQLException {
		save("DELETE FROM  tbl_genre WHERE genre_id =?",
				new Object[] { be.getGenreid() });

	}

	@Override
	public Genre read(Integer[] pk) throws SQLException {
		return read("SELECT * FROM  tbl_genre WHERE genre_id =?", pk);

	}

	@Override
	public List<Genre> readAll() throws SQLException {
		return readAll("SELECT * FROM  tbl_genre", new Object[] {});
	}

	@Override
	public List<Genre> readResult(ResultSet rs) throws SQLException {
		List<Genre> list = new ArrayList<Genre>();
		while (rs.next()) {
			Genre a = new Genre(rs.getString("genre_name"));
			a.setGenreid(rs.getInt("genre_id"));
			list.add(a);
		}

		return list;
	}


}
