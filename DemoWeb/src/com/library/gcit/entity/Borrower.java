package com.library.gcit.entity;

public class Borrower implements BaseEntity {

	private int cardno;
	private String borrowername;
	private String borroweraddress;
	private String borrowerphone;

	public Borrower(String borrowername, String borroweraddress,
			String borrowerphone) {

		this.borrowername = borrowername;
		this.borroweraddress = borroweraddress;
		this.borrowerphone = borrowerphone;
	}

	public Borrower() {

	}

	public int getCardno() {
		return cardno;
	}

	public void setCardno(int cardno) {
		this.cardno = cardno;
	}

	public String getBorrowername() {
		return borrowername;
	}

	public void setBorrowername(String borrowername) {
		this.borrowername = borrowername;
	}

	public String getBorroweraddress() {
		return borroweraddress;
	}

	public void setBorroweraddress(String borroweraddress) {
		this.borroweraddress = borroweraddress;
	}

	public String getBorrowerphone() {
		return borrowerphone;
	}

	public void setBorrowerphone(String borrowerphone) {
		this.borrowerphone = borrowerphone;
	}

}
