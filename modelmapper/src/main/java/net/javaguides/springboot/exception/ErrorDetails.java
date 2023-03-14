package net.javaguides.springboot.exception;

import java.util.Date;

public class ErrorDetails {

	private Date timeastamp;
	private String message;
	private String detailString;
	
	
	
	
	public ErrorDetails(Date timeastamp, String message, String detailString) {
		super();
		this.timeastamp = timeastamp;
		this.message = message;
		this.detailString = detailString;
	}
	
	
	
	public Date getTimeastamp() {
		return timeastamp;
	}
	public void setTimeastamp(Date timeastamp) {
		this.timeastamp = timeastamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetailString() {
		return detailString;
	}
	public void setDetailString(String detailString) {
		this.detailString = detailString;
	}
	
}
