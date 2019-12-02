package com.example.demo.error;

public class IdRequestFailsException extends RuntimeException {
	public IdRequestFailsException () {
		super(" id project wrong format");
	}
}
