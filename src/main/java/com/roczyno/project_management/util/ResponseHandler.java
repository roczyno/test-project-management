package com.roczyno.project_management.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHandler {
	private HttpStatus httpStatus;
	private Object data;

	public static ResponseEntity<Object> successResponse(Object data, HttpStatus httpStatus) {
		return response("success",httpStatus,data);
	}

//	public static ResponseEntity<Object> successResponse(String message,Object data, HttpStatus httpStatus) {
//		return response(message,httpStatus,data);
//	}

	public static ResponseEntity<Object> successResponse(String message, HttpStatus httpStatus) {
		return response(message,httpStatus);
	}

	public static ResponseEntity<Object> errorResponse(Object data,HttpStatus httpStatus) {
		return response("error",httpStatus,data);
	}

	public static ResponseEntity<Object> response(String statusMessage,HttpStatus httpStatus,Object data) {
		Map<String,Object> response = new HashMap<>();
		response.put("status",statusMessage);
		response.put("content",data);
		return new ResponseEntity<>(response,httpStatus);
	}

	public static ResponseEntity<Object> response(String statusMessage, HttpStatus httpStatus) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", statusMessage);
		return new ResponseEntity<>(response, httpStatus);
	}

}
