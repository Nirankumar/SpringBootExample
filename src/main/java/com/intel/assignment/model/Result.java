package com.intel.assignment.model;

/**
 * General result object
 */

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Result<T> {
	//holds the status of the response
	private boolean success;
	//Description on the response 
	private String message;
	//holds the actual errors if not successful
	private Map<String, String> errors;
	//holds the actual data if successful
	private T data;
}
