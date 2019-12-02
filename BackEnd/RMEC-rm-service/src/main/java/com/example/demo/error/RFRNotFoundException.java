package com.example.demo.error;

public class RFRNotFoundException extends Exception {
	public RFRNotFoundException (Long id) {
		super("RFR:"+ id +"not exist");
	}
}
