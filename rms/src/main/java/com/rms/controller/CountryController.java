package com.rms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rms.dto.CountryDto;
import com.rms.entity.Country;
import com.rms.exception.IdNotFoundException;
import com.rms.service.CountryService;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@GetMapping("/getAllCountry")
	public ResponseEntity<List<Country>> getAllCountry() {
		return new ResponseEntity<>(countryService.getAllCountry(),new HttpHeaders(),HttpStatus.OK);
	}

	@PostMapping("/addCountry") 
	public ResponseEntity<String> addCountry(@RequestBody CountryDto countryDto) {
		return new ResponseEntity<>(countryService.addCountry(countryDto),new HttpHeaders(),HttpStatus.OK);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
}
