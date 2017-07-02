package com.xdShop.common.pojo;

public class PictureResult {
	
	private String url;
	private String message;
	private int error;
	
	public PictureResult(){
		
	}
	public PictureResult(String url, String message, int error) {
		this.url = url;
		this.message = message;
		this.error = error;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}

}
