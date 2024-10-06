package com.TextCraft.serviceImpl;

import org.springframework.stereotype.Service;

import com.TextCraft.services.TextService;

@Service
public class TextServiceImpl implements TextService{

	public String lowerToUpperCase(String text) {
		String newText = text.toUpperCase();
		return newText;
	}
	
	public String upperToLowerCase(String text) {
		String newText = text.toLowerCase();
		return newText;
	}
}
