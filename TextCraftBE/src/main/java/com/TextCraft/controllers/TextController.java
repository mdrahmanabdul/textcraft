package com.TextCraft.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.TextCraft.services.TextService;


@RestController
@RequestMapping("/text")
public class TextController {
	
	@Autowired
	private TextService textService;
	
	private static final String LANGUAGE_TOOL_API_URL = "https://api.languagetoolplus.com/v2/check";

	//api to convert lowercase to uppercase
	@PostMapping(value = "/small-to-upper-case", consumes = MediaType.TEXT_PLAIN_VALUE)
	private ResponseEntity<String> lowerToUpperCase(@RequestBody(required = true) String text){
		String newText = textService.lowerToUpperCase(text);
		return ResponseEntity.status(HttpStatus.OK).body(newText);
	}
	
	//api to convert from uppercase to lowercase
	@PostMapping(value = "/upper-to-lower-case", consumes = MediaType.TEXT_PLAIN_VALUE)
	private ResponseEntity<String> upperToLowerCase(@RequestBody(required = true) String text){
		String newText = textService.upperToLowerCase(text);
		return ResponseEntity.status(HttpStatus.OK).body(newText);
	}
	
	//api to check for grammatical mistakes.
	@PostMapping("/grammer-check")
	private ResponseEntity<String> grammerCheck(@RequestBody String text){
		String reqBody = "text="+text+"&language=en-US";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity<String> entity = new HttpEntity(reqBody, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(LANGUAGE_TOOL_API_URL, HttpMethod.POST,entity,String.class);
		 return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	}
}
