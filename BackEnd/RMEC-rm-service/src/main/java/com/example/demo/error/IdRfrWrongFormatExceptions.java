package com.example.demo.error;

public class IdRfrWrongFormatExceptions  extends RuntimeException {
	public IdRfrWrongFormatExceptions(String id) {
		super("id RFR "+id+" Wrong format");
	}
}
