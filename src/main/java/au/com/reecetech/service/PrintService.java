package au.com.reecetech.service;

import java.util.stream.IntStream;

/**
 * To avoid a lot of sysouts creating this class to handle printing of messages
 * 
 * @author nolan
 *
 */
public class PrintService {

	public static void printMessage(String message) {
		System.out.println(message);
		IntStream.range(1, 100)
				.forEach((e) -> System.out.print("-"));
		System.out.println();
	}
}
