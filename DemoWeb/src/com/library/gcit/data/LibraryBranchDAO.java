package com.library.gcit.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.gcit.entity.BookCopies;
import com.library.gcit.entity.LibraryBranch;


public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {
	
	
	
	private static final String SELECT_ALL = "select * from tbl_library_branch";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_library_branch where branchName like ?";

	public LibraryBranchDAO(Connection conn) {
		super(conn);

	}

	@Override
	public void create(LibraryBranch be) throws SQLException {
		save("insert into tbl_library_branch(branchName,branchAddress) values (?,?)",
				new Object[] { be.getBranchname(), be.getBranchaddress() });

	}

	@Override
	public void update(LibraryBranch be) throws SQLException {
		save("update tbl_library_branch set branchname = ?,branchAddress=? where branchId = ?",
				new Object[] { be.getBranchname(), be.getBranchaddress(),
						be.getBranchid() });
	}

	@Override
	public void delete(LibraryBranch be) throws SQLException {
		save("DELETE FROM tbl_library_branch WHERE branchId=?",
				new Object[] { be.getBranchid() });

	}

	@Override
	public LibraryBranch read(Integer[] pk) throws SQLException {
		return read("SELECT * FROM tbl_library_branch Where branchId=?", pk);
	}

	@Override
	public List<LibraryBranch> readAll() throws SQLException {
		return readAll("SELECT * FROM tbl_library_branch", new Object[] {});
	}
	
	
	 public Integer getLibraryBranchesBySearch(String searchString) throws SQLException{
			
			if(searchString !=null && !"".equals(searchString)){
				searchString = "%"+searchString+"%";
				return  getCount1(SELECT_ALL_SEARCH,new String[]{searchString});
			}else{
				return getCount1(SELECT_ALL, new String[]{});
			}
		
		}
	 
 public List<LibraryBranch> readAll(Integer pageNo,Integer pageSize,String searchString) throws SQLException {
			setPageNo(pageNo);
			setPageSize(pageSize);
			if(searchString !=null && !"".equals(searchString)){
				searchString = "%"+searchString+"%";
				return  readAllNew(SELECT_ALL_SEARCH,new String[]{searchString});
			}else{
				return readAllNew(SELECT_ALL, new String[]{});
			}
		}
 
	

	@Override
	public List<LibraryBranch> readResult(ResultSet rs) throws SQLException {

		List<LibraryBranch> list = new ArrayList<LibraryBranch>();
		while (rs.next()) {
			BookCopies bc= new BookCopies();
			LibraryBranch a = new LibraryBranch();
			 a.setBranchname(rs.getString("branchName"));
			 a.setBranchaddress(rs.getString("branchAddress"));	
			 a.setBranchid(rs.getInt("branchId"));	 
			 list.add(a);
		}

		return list;
	}


}
