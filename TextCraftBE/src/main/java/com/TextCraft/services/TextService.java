package com.TextCraft.services;

import org.springframework.stereotype.Service;

@Service
public interface TextService {

	String lowerToUpperCase(String text);
	String upperToLowerCase(String text);
	
}
