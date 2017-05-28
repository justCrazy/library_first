package com.web.library.service;


@SuppressWarnings("serial")
public class LibraryException extends Exception {

	public LibraryException() {
		super();
	}	
	
	public LibraryException(String messge,Throwable cause){
		super(messge,cause);
	}
	
	public LibraryException(String message){
		super(message);
	}
	
	public LibraryException(Throwable cause){
		super(cause);
	}
}
