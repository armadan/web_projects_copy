package com.library.gcit.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.gcit.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> {
	
	
	private static final String SELECT_ALL = "select * from tbl_borrower";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_borrower where name like ?";


	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Borrower be) throws SQLException {
		save("insert into tbl_borrower(name,address,phone) values (?,?,?)",
				new Object[] { be.getBorrowername(), be.getBorroweraddress(),
						be.getBorrowerphone() });

	}

	@Override
	public void update(Borrower be) throws SQLException {
		save("update tbl_borrower set name = ?,address=?,phone=? where cardNo = ?",
				new Object[] { be.getBorrowername(), be.getBorroweraddress(),
						be.getBorrowerphone(), be.getCardno() });

	}

	@Override
	public void delete(Borrower be) throws SQLException {
		save("DELETE FROM tbl_borrower WHERE cardNo=?",
				new Object[] { be.getCardno() });

	}

	@Override
	public Borrower read(Integer[] i) throws SQLException {
		return read("SELECT *  FROM tbl_borrower WHERE cardNo=?", i);
	}

	@Override
	public List<Borrower> readAll() throws SQLException {
		return readAll("SELECT *  FROM tbl_borrower", new Object[] {});
	}

	
	
	public List<Borrower> readAll(Integer pageNo,Integer pageSize,String searchString) throws SQLException {
		setPageNo(pageNo);
		setPageSize(pageSize);
		if(searchString !=null && !"".equals(searchString)){
			searchString = "%"+searchString+"%";
			return  readAllNew(SELECT_ALL_SEARCH,new String[]{searchString});
		}else{
			return readAllNew(SELECT_ALL, new String[]{});
		}
	}
	
	
	public Integer getBorrowersBySearch(String searchString) throws SQLException{
		
		if(searchString !=null && !"".equals(searchString)){
			searchString = "%"+searchString+"%";
			//valueList.add(searchString);
			return  getCount1(SELECT_ALL_SEARCH,new String[]{searchString});
		}else{
			return getCount1(SELECT_ALL, new String[]{});
		}

		
	}
	

	
	@Override
	public List<Borrower> readResult(ResultSet rs) throws SQLException {
		List<Borrower> list = new ArrayList<Borrower>();
		while (rs.next()) {
			Borrower a = new Borrower(rs.getString("name"),
					rs.getString("address"), rs.getString("phone"));
			a.setCardno(rs.getInt("cardno"));
			list.add(a);
		}

		return list;
	}

	

}
