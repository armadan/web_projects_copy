package com.library.gcit.entity;

import java.sql.Date;

public class BookLoans implements BaseEntity {

	private Book book;
	private Borrower borrower;
	private LibraryBranch branch;
	private Date dateout;
	private Date datein;
	private Date duedate;

	public BookLoans() {

	}

	public BookLoans(Date dateout,Date duedate, Date datein) {
		this.dateout = dateout;
		this.duedate = duedate;
		this.datein = datein;		
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public LibraryBranch getBranch() {
		return branch;
	}

	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	public Date getDateout() {
		return dateout;
	}

	public void setDateout(Date dateout) {
		this.dateout = dateout;
	}

	public Date getDatein() {
		return datein;
	}

	public void setDatein(Date datein) {
		this.datein = datein;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

}
