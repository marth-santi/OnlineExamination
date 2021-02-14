package vn.kase.onlineExam.controller;

import java.util.Random;

public class RandomStringExmple {
	 private final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	 private final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	 private final String digits = "0123456789"; // 0-9
	 private final String specials = "~=+%^*/()[]{}/!@#$?|";
	 private final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	 private final String ALL = alpha + alphaUpperCase + digits + specials;
	 private Random generator = new Random();
	 public String randomAlphaNumeric(int numberOfCharactor) {
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < numberOfCharactor; i++) {
	            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
	            char ch = ALPHA_NUMERIC.charAt(number);
	            sb.append(ch);
	        }
	        return sb.toString();
	    }
	 public int randomNumber(int min, int max) {
	        return generator.nextInt((max - min) + 1) + min;
	    }
}
