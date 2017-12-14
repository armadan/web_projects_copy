package com.library.gcit.entity;

public class BookCopies implements BaseEntity {

	public int noc;
	private Book book;
	private LibraryBranch branchid;
	private int noofcopies;

	public BookCopies() {

	}

	public BookCopies(int noofcopies) {
		this.noofcopies = noofcopies;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryBranch getBranchid() {
		return branchid;
	}

	public void setBranchid(LibraryBranch branchid) {
		this.branchid = branchid;
	}

	public int getNoofcopies() {
		return noofcopies;
	}

	public void setNoofcopies(int noofcopies) {
		this.noofcopies = noofcopies;
	}

}
