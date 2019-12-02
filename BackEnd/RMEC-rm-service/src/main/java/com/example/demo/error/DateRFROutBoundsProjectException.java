package com.example.demo.error;

public class DateRFROutBoundsProjectException  extends RuntimeException{
	 public  DateRFROutBoundsProjectException(Long id) {
	        super(" Date RFR id : " + id + " Out Bounds Project ");
	  
	    }
}
