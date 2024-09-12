package io.compiler.core.exceptions;

import java.util.List;
import java.util.ArrayList;

public class WarningHandler {
	
	
	private static List<String> warnings = new ArrayList<>();

	public static final String YELLOW = "\033[0;33m";  // Yellow
    public static final String RESET = "\033[0m";  // Text Reset
    
	public static void sendWarning(String message) {
		warnings.add(YELLOW+"Warning: " + message+RESET);
	}

	public static void printAllWarnings() {
		for (String warning : warnings) {
			System.out.println(warning);
		}
	}
}
