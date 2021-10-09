package fileReader;

import java.util.Scanner;

import fileReader.text.*;

public class Main {

	public static void main(String[] args) {
		ClassicTextFileReader classic = new ClassicTextFileReader();
		ReverseTextFileReader reverse = new ReverseTextFileReader();
		PalindromicTextFileReader palindromic = new PalindromicTextFileReader();
		DiffTextFileReader diff = new DiffTextFileReader();
		
		Scanner scanner = new Scanner(System.in);
		String filepath = "";
		String filepath2 = "";
		
		System.out.println("Enter file path : ");
		filepath = scanner.nextLine();
		
		String separator = "-----------------------------------";
		System.out.println(separator + "\n\tClassic file reader\n" + separator);
		classic.openFile(filepath);
		
		System.out.println("\n\n" + separator + "\n\tReverse file reader\n" + separator);
		reverse.openFile(filepath);
		
		System.out.println("\n\n" + separator + "\n\tPalindromic file reader\n" + separator);
		palindromic.openFile(filepath);
		
		System.out.println("\n\n" + separator + "\nEnter file path : ");
		filepath2 = scanner.nextLine();
		scanner.close();
		
		System.out.println("\n\n" + separator + "\n\tDiff file reader\n" + separator);
		diff.openFile(filepath);
		diff.openFile(filepath2);
	}

}
