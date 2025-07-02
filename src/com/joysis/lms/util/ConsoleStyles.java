package com.joysis.lms.util;

public class ConsoleStyles {
	public static final String RESET = "\u001B[0m";

	// Regular Colors
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";

	// Bold
	public static final String BOLD = "\u001B[1m";

	// Underline
	public static final String UNDERLINE = "\u001B[4m";

	// Background Colors
	public static final String BG_BLACK = "\u001B[40m";
	public static final String BG_RED = "\u001B[41m";
	public static final String BG_GREEN = "\u001B[42m";
	public static final String BG_YELLOW = "\u001B[43m";
	public static final String BG_BLUE = "\u001B[44m";
	public static final String BG_PURPLE = "\u001B[45m";
	public static final String BG_CYAN = "\u001B[46m";
	public static final String BG_WHITE = "\u001B[47m";

	// Apply color and reset
	public static String colorize(String text, String... styles) {
		StringBuilder sb = new StringBuilder();
		for (String style : styles) {
			sb.append(style);
		}
		sb.append(text).append(RESET);
		return sb.toString();
	}
	
	public static String limitStringWithEllipsis(String input, int maxLength) {
	    if (input == null) return null;
	    if (input.length() <= maxLength) return input;
	    return input.substring(0, maxLength - 3) + "...";
	}

	/*
	 * 
	 * System.out.println(ConsoleColors.colorize("Success!", ConsoleColors.GREEN));
	 * System.out.println(ConsoleColors.colorize("Warning!", ConsoleColors.YELLOW,
	 * ConsoleColors.BOLD)); System.out.println(ConsoleColors.colorize("Error!",
	 * ConsoleColors.RED, ConsoleColors.UNDERLINE));
	 * System.out.println(ConsoleColors.colorize("Note", ConsoleColors.CYAN,
	 * ConsoleColors.BG_WHITE));
	 */
}
