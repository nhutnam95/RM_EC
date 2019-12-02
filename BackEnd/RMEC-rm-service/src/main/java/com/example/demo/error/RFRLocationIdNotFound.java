package com.example.demo.error;

public class RFRLocationIdNotFound  extends RuntimeException{
		public RFRLocationIdNotFound () {
			super("RFR locations id not found");
		}
}
