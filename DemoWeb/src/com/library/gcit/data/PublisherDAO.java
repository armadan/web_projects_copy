package com.library.gcit.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.gcit.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> {
	
	
	
	private static final String SELECT_ALL = "select * from tbl_publisher";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_publisher where publisherName like ?";

	public PublisherDAO(Connection conn) {
		super(conn);

	}

	@Override
	public void create(Publisher be) throws SQLException {
		save("insert into tbl_publisher(publishername,publisheraddress,publisherPhone) values (?,?,?)",
				new Object[] { be.getPublishername(), be.getPublisheraddress(),
						be.getPublisherphone() });

	}

	@Override
	public void update(Publisher be) throws SQLException {
		save("update tbl_publisher set publishername = ?,publisheraddress=?,publisherPhone=? where publisherid = ?",
				new Object[] { be.getPublishername(), be.getPublisheraddress(),
						be.getPublisherphone(), be.getPublisherid() });
	}

	@Override
	public void delete(Publisher be) throws SQLException {
		save("DELETE FROM tbl_publisher WHERE publisherid=?",
				new Object[] { be.getPublisherid() });
	}

	@Override
	public Publisher read(Integer[] pk) throws SQLException {
		return read("SELECT * FROM tbl_publisher Where publisherid=?", pk);
	}

	@Override
	public List<Publisher> readAll() throws SQLException {
		return readAll("SELECT * FROM tbl_publisher", new Object[] {});
	}
	
	
	
	
 public List<Publisher> readAll(Integer pageNo,Integer pageSize,String searchString) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if(searchString !=null && !"".equals(searchString)){
			searchString = "%"+searchString+"%";
			return  readAllNew(SELECT_ALL_SEARCH,new String[]{searchString});
		}else{
			return readAllNew(SELECT_ALL, new String[]{});
		}
	}
	
	
 public Integer getPublishersBySearch(String searchString) throws SQLException{
		
		if(searchString !=null && !"".equals(searchString)){
			searchString = "%"+searchString+"%";
			return  getCount1(SELECT_ALL_SEARCH,new String[]{searchString});
		}else{
			return getCount1(SELECT_ALL, new String[]{});
		}

		
	}
	


	@Override
	public List<Publisher> readResult(ResultSet rs) throws SQLException {
		List<Publisher> list = new ArrayList<Publisher>();
		while (rs.next()) {
			Publisher a = new Publisher(rs.getString("publishername"),
					rs.getString("publisheraddress"),
					rs.getString("publisherphone"));
			a.setPublisherid(rs.getInt("publisherid"));
			list.add(a);
		}

		return list;
	}


}
