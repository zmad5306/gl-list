package com.example.gl.list.model;

public class GenericApiResponse {
	
	private Boolean success = true;
	private String errorCode;
	private String message;
	
	public GenericApiResponse() {
		super();
	}
	
	public GenericApiResponse(Boolean success, String errorCode, String message) {
		super();
		this.success = success;
		this.errorCode = errorCode;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
