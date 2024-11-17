package com.springboot.examples.controller;

/**
 * Controller class to perform CRUD operations on IP Addresses
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.examples.model.IPInfo;
import com.springboot.examples.model.Result;
import com.springboot.examples.service.IPService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class IPController {
	
	@Autowired
	private IPService ipservice;
	
	@PostMapping("/ip-address")
	@Operation(summary = "Add new IP information")
    public ResponseEntity<Result<String>> addIp(@Valid @RequestBody IPInfo ip) {
		Result<String> result = new Result<>();
		try {
			Long saved = ipservice.addIp(ip);
			result.setSuccess(true);
			result.setMessage("IP Saved Successfully");
			result.setData("ID: "+saved);
		    return new ResponseEntity<>(result,HttpStatus.CREATED);
		} catch (Exception ex) {
			log.warn("Exception occurred while saving IP",ex);
			result.setMessage("Save failed. Retry after sometime.");
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@GetMapping("/ip-addresses/{ip-value}")
	@Operation(summary = "Retrieve the IP Information based on value")
	public Result<List<IPInfo>> getIPList(@PathVariable("ip-value") String ipValue) {
		Result<List<IPInfo>> result = new Result<>();
		try {
			result.setSuccess(true);
			List<IPInfo> ipList = ipservice.findByIpValue(ipValue);
			result.setData(ipList);
			result.setMessage(String.format("Retrieved %d results", ipList.size()));
			return result;
		}catch (Exception ex) {
			log.warn("Exception occurred while searching IP",ex);
			result.setMessage("Search failed. Retry after sometime.");
			return result;
		}
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
	public Result<String> handleValidationExceptions(Exception ex) {
		Result<String> r = new Result<>();
		r.setSuccess(false);
		r.setMessage("Input data not correct for one or more field. Refer errors section for the details");
	    Map<String, String> errors = new HashMap<>();
	    r.setErrors(errors);
	    if(ex instanceof MethodArgumentNotValidException) {
	    	MethodArgumentNotValidException maex = (MethodArgumentNotValidException)ex;
		    maex.getBindingResult().getAllErrors().forEach(error -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
	    } else {
	    	errors.put("firstSeen", "firstSeen format should be ISO_8601 format");
	    }
	    return r;
	}
}
