package com.library.gcit.entity;

import java.util.ArrayList;
import java.util.List;

public class Book implements BaseEntity {
	private int bookid;
	private String title;
	private Publisher publisher;
	private List<Author> authors;
	private List<Genre> genres;

	public Book() {

	}

	public Book(int bookid) {
	
		this.bookid = bookid;
	}

	public Book(String title) {
		this.title = title;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addAuthor(Author a) {
		if (authors == null) {
			authors = new ArrayList<Author>();
			authors.add(a);
		}
	}

	public void addGenre(Genre b) {
		if (genres == null) {
			genres = new ArrayList<Genre>();
			genres.add(b);
		}
	}

}
