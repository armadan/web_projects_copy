package com.library.gcit.entity;

import java.util.ArrayList;
import java.util.List;

public class Genre implements BaseEntity {

	private int genreid;
	private String genrename;
	List<Book> books;

	public Genre(String genrename) {
		this.genrename = genrename;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getGenreid() {
		return genreid;
	}

	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}

	public String getGenrename() {
		return genrename;
	}

	public void setGenrename(String genrename) {
		this.genrename = genrename;
	}

	public void addbook(Book a) {
		if (books == null) {
			books = new ArrayList<Book>();
			books.add(a);
		}
	}

}
