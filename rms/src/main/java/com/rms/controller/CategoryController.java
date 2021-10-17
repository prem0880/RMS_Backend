package com.rms.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.constants.ApplicationConstants;
import com.rms.dto.CategoryDto;
import com.rms.response.HttpResponseStatus;
import com.rms.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	private static final Logger logger = LogManager.getLogger(CategoryController.class);

	
	/**
	 *@param This method takes no input
	 *@return This method returns List of Category along with success message as HttpResponseStatus 
	 */
	@GetMapping
	public ResponseEntity<HttpResponseStatus> getAllCategory() {
		logger.debug("Entering getAllCategory method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CATEGORY_FETCH_SUCCESS, categoryService.getAllCategory()),
					HttpStatus.OK);
		

	}

	/**
	 *@param This method takes category id as input
	 *@return This method returns category object for given id currently in the database with the corresponding id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> getCategoryById(@PathVariable Long id) {
		logger.debug("Entering getCategoryById method");
			return new ResponseEntity<>(
					new HttpResponseStatus(HttpStatus.OK.value(),ApplicationConstants.CATEGORY_FETCH_SUCCESS, categoryService.getCategoryById(id)),
					HttpStatus.OK);
		
	}

	/**
	 *@param This method takes category DTO object as input
	 *@return This method returns success message if category is created successfully
     */
	@PostMapping
	@Validated
	public ResponseEntity<HttpResponseStatus> addCategory(@Valid @RequestBody  CategoryDto categoryDto) {
		logger.debug("Entering addCategory method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.CREATED.value(), categoryService.addCategory(categoryDto)), HttpStatus.CREATED);
		
	}

	/**
	 *@param This method takes category object and id as input 
	 *@return This method returns message if category is updated successfully with id
     */
	@PutMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryDto categoryDto) {
		logger.debug("Entering updateCategory method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), categoryService.updateCategory(id, categoryDto)), HttpStatus.OK);
		
	}

	/**
	 *@param This method takes category id as input 
	 *@return This method returns success message if category is deleted successfully with id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpResponseStatus> deleteCategory(@PathVariable Long id) {
		logger.debug("Entering deleteCategory method");
			return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.OK.value(), categoryService.deleteCategory(id)),
					HttpStatus.OK);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HttpResponseStatus> validationFailed(MethodArgumentNotValidException e) {
	logger.error("Validation fails, Check your input!");
	ResponseEntity<HttpResponseStatus> responseEntity = null;
	responseEntity = new ResponseEntity<>(new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Failed!"),
			HttpStatus.UNPROCESSABLE_ENTITY);
	return responseEntity;
	}

	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<HttpResponseStatus> inputMismatch(HttpMessageNotReadableException e) {
		logger.error(e.getMessage());
		return new ResponseEntity<>(new HttpResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Wrong Inputs are provided"),
				HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
