package com.example.demo.error;

public class RFRRankIdNotFound extends RuntimeException {
	public RFRRankIdNotFound () {
		super("RFR rank id not found");
	}
}
