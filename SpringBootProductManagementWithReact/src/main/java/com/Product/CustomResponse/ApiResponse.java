package com.Product.CustomResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
	public boolean success;
	public String message;
	public Integer status;
	private Object data;


	public ApiResponse(boolean success, String message, Integer status, Object data)
	{
		this.success = success;
		this.message = message;
		this.status = status;
		this.data = data;
	}

}
