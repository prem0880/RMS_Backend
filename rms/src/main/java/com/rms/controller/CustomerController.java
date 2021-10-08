package com.rms.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.constants.ApplicationConstants;
import com.rms.dto.CustomerDto;
import com.rms.exception.BusinessLogicException;
import com.rms.response.HttpResponseStatus;
import com.rms.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	/**
	 *@param This method takes customer DTO object as input  
     *@return This method returns success message if customer is created successfully
	 */
	@PostMapping
	public ResponseEntity<HttpResponseStatus> addCustomer(@Valid @RequestBody CustomerDto customerDto) {
		logger.debug("Entering addCustomer method");
		try {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.CUSTOMER_SAVE_SUCCESS,
					customerService.addCustomer(customerDto)), HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 *@param This method takes no input
	 *@return This method returns list of customer currently in the database if data exists.If no data present,it return empty list
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllCustomer() {
		logger.debug("Entering getAllCustomer method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CUSTOMER_FETCH_SUCCESS, customerService.getAllCustomer()),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	
	/**
	 *@param This method takes customer id as input  
	 *@return This method returns customer object for given id currently in the database.If no data present,it return empty list
	 */
	
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getCustomerById(@PathVariable("id") Long id) {
		logger.debug("Entering getCustomerById method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.CUSTOMER_FETCH_SUCCESS, customerService.getCustomerById(id)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	
	/**
	 *@param This method takes customer DTO object and id as input 
     *@return This method  returns message if customer is updated successfully with id
	 */
	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateCustomer(@PathVariable("id") Long id,
			@Valid @RequestBody CustomerDto customerDto) {
		logger.debug("Entering updateCustomer method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), customerService.updateCustomer(id, customerDto)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 *@param This method takes customer email as input 
	 *@return This method returns customer object identifier currently in the database. If no data present,it return empty list
	 */
	
	@GetMapping("/mail/{email}")
	public ResponseEntity<HttpResponseStatus> getCustomerByMail(@PathVariable("email") String mail) {
		logger.debug("Entering getCustomerByMail method");
		try {
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(), ApplicationConstants.CUSTOMER_FETCH_SUCCESS, customerService.getCustomerByMail(mail)),
					HttpStatus.OK);
		} catch (BusinessLogicException e) {
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}
}
