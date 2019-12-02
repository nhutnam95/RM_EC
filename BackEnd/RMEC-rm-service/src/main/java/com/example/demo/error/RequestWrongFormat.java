package com.example.demo.error;

public class RequestWrongFormat  extends RuntimeException {
	public RequestWrongFormat() {
		super("request wrong format");
	}
}
