package com.askme.app.common;

public class ApiResult {

	private String code;
	private String message;
	private Object data;
	
	public ApiResult(){
		
	}
	
	public ApiResult(String code,String message,Object data){
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public ApiResult success(Object data){
		ApiResult apiResult = new ApiResult(CodeStatus.SUCCESS,CodeStatus.SUCCESS_MESSAGE,data);
		return apiResult;
	}
	
	public ApiResult fail(String code,String message){
		ApiResult apiResult = new ApiResult(code,message,null);
		return apiResult;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
