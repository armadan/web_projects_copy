package com.library.gcit.entity;

public class LibraryBranch implements BaseEntity {

	private int branchid;
	private String branchname;
	private String branchaddress;

	public LibraryBranch() {
	}

	public LibraryBranch(String branchname, String branchaddress) {
		super();
		this.branchname = branchname;
		this.branchaddress = branchaddress;
	}

	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getBranchaddress() {
		return branchaddress;
	}

	public void setBranchaddress(String branchaddress) {
		this.branchaddress = branchaddress;
	}

}
