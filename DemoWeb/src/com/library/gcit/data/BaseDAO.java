package com.library.gcit.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Statement;





public abstract class BaseDAO<BaseEntity> {

	private Connection conn = null;
	
	public BaseDAO(Connection conn) {
		this.conn = conn;

	}

	private Integer pageNo;

	private Integer pageSize;

	

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public   abstract void create(BaseEntity be) throws SQLException;

	public abstract void update(BaseEntity be) throws SQLException;

	public abstract void delete(BaseEntity be) throws SQLException;

	public abstract BaseEntity read(Integer[] pk) throws SQLException;

	public abstract List<BaseEntity> readAll() throws SQLException;
	
	public abstract List<BaseEntity> readResult(ResultSet rs) throws SQLException;
	
	

	public BaseEntity read(String sql, Object[] vals) throws SQLException {
		PreparedStatement stmt = makeStatement(sql, vals);
		ResultSet rs = stmt.executeQuery();
		List<BaseEntity> beList = readResult(rs);
		if (beList.isEmpty()) {
			return null;
		} else {
			return beList.get(0);
		}

	}

	public List<BaseEntity> readAll(String sql, Object[] vals)
			throws SQLException {
				
		PreparedStatement stmt = makeStatement(sql, vals);
		ResultSet rs = stmt.executeQuery();
		return readResult(rs);

	}
	
	public Integer getCount(String sql) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
		    stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				return rs.getInt(1);
				
			}
		}finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
		}
		return -1;
	}
	
	
	
	
	public Integer getCount1(String sql, Object[] valueList) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int size=0;
		try{
		      stmt = makeStatement(sql, valueList);
			  rs = stmt.executeQuery();
		      rs.last();
			  size= rs.getRow();
			  rs.beforeFirst();	
		    }catch(Exception e){
				return 0;
			}finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
		}
		return size;
	}
	
	

	
	public List<BaseEntity> readAllNew(String sql,  Object[] valueList)
			throws SQLException {
	PreparedStatement stmt=null;
	ResultSet rs = null;

	try {
		pageNo = getPageNo();
		pageSize = getPageSize();
		
		if(pageNo >0){
			int index = (pageNo-1) * pageSize;
			sql+=" LIMIT "+index+" , "+pageSize;
		}
		 stmt = makeStatement(sql, valueList);
		
		
		//rs has all the returned records
		rs =  stmt.executeQuery();
		
		//go process the records in the caller class Dao  
		return readResult(rs);
		
	} finally {
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
	}

}
	

	protected void save(String sql, Object[] vals) throws SQLException {
		PreparedStatement stmt = makeStatement(sql, vals);
		stmt.executeUpdate();

	}

	private PreparedStatement makeStatement(String sql, Object[] vals)
			throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		int idx = 1;
		for (Object o : vals) {
			stmt.setObject(idx, o);
			idx++;
		}
		return stmt;
	}

	
	protected int saveAndGetId(String sql, Object[] vals) throws SQLException {

		PreparedStatement stmt=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs=null;
    	int lastInsertId=-1;
		int idx = 1;
		for(Object o : vals) {
     	stmt.setObject(idx, o);
		idx++;
	}
	stmt.executeUpdate();
    rs=stmt.getGeneratedKeys();
	if (rs.next()) {
		lastInsertId = rs.getInt(1);
		} 
		return lastInsertId;
		}
 

	
}
