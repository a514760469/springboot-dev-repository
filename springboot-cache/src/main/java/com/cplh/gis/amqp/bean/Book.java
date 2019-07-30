package com.cplh.gis.amqp.bean;

public class Book {
	
	private String bookName;
	
	private String author;
	
	private Book book;
	
	
	public Book(String bookName, String author) {
		this.bookName = bookName;
		this.author = author;
	}

	public Book() {
	}

	public Book(String bookName, String author, Book book) {
		this.bookName = bookName;
		this.author = author;
		this.book = book;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book {bookName=" + bookName + ", author=" + author + ", book=" + book + "}";
	}
	
}
