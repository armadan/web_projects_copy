package com.library.gcit.entity;

import java.util.ArrayList;
import java.util.List;


public class Author implements BaseEntity{
	
	private int authorid;
	public Author(int authorid) {
		this.authorid = authorid;
	}

	private String authorname;
	List<Book> books;
	
	public Author() {
		
	}

	public Author(String authorname) {
		
		this.authorname = authorname;
	}


	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	
	public void addBook(Book b){
		if(books==null){
			books=new ArrayList<Book>();
			books.add(b);
		}
	}
	
	
}
