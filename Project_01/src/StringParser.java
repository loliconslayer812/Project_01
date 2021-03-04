/**
 * Project_01
 * @author Huy Tran Quang
 * Date: 02/16/2020
 * Class: CSE 271 - Section D
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StringParser {
static Scanner fin = null;
	@SuppressWarnings("resource")
	public static void main(String[] args){//The program start here
		Scanner in = null;
		try {
		in = new Scanner(System.in);
			System.out.print("Enter input filename: ");
		
	    String inputFileName = in.next();
	    File inputFile = new File(inputFileName);
		fin = new Scanner(inputFile);
		System.out.println("Found. What do you want to output?");
		System.out.print("1. Raw word list\n2. Palindromes"+
							"\n3. $100 words\nChoose: ");
		int num = in.nextInt();
		switch(num) {
		case 1 : System.out.print("Enter output filename: ");
		    String outputFileName = in.next();
		    File outputFile = new File(outputFileName);
		   
			rawOutput(inputFile, outputFile);
		break;
		case 2 : System.out.print("Enter output filename: ");
	            outputFileName = in.next();
	            outputFile = new File(outputFileName);
	            
			palindromeOutput(inputFile, outputFile);
		break;
		case 3 : System.out.print("Enter output filename: ");
	     outputFileName = in.next();
	     outputFile = new File(outputFileName); 
	     
			hundredDollarWordOutput(inputFile, outputFile);
		break;
		default: System.out.println("Invalid input!");
		}
		}
		catch(IOException e) {
			System.out.println("File does not exist. Goodbye");
		}
		}
	public static void rawOutput(File in, File out) {
		
		PrintWriter fout = null;
		try {
			fout = new PrintWriter(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 ArrayList<String> list = new ArrayList<String>();
			while(fin.hasNext()) {
				String word = fin.next();
				list.add(word);
			}
			fin.close();
			for(int i = 0; i < list.size(); i++) {
				fout.println(list.get(i));
			}
			fout.close();
			System.out.println("Finish printing raw word list.");
		}
		
	
	public static void palindromeOutput(File in, File out) {
		PrintWriter fout = null;
		try {
				fout = new PrintWriter(out);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ArrayList<String> list = new ArrayList<String>();
			while(fin.hasNext()) {
				String word = fin.next();
				String newWord = cleanup(word);
				if(isPalindrome(newWord) && !(list.contains(newWord))) {
					list.add(newWord);
				}
			}
			fin.close();
			for(int i = 0; i < list.size(); i++) {
				fout.println(list.get(i));
			}
			fout.close();
			System.out.println("Finished printing palindromes");
		}
		
	
	public static void hundredDollarWordOutput(File in, File out) {
		PrintWriter fout = null;
			try {
				fout = new PrintWriter(out);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			ArrayList<String> list = new ArrayList<String>();
			while(fin.hasNext()) {
				String word = fin.next();
				String newWord = cleanup(word);
				int value = wordValue(newWord);
				if(value==100 && !(list.contains(newWord))) {
					list.add(newWord);
				}
			}
			fin.close();
			for(int i = 0; i < list.size(); i++) {
				fout.println(list.get(i));
			}
			System.out.println("Finished printing $100 words");
			fout.close();
		}
		
	
	public static boolean isPalindrome(String word) {
		if(word.length()<2)
			return false;
		int mid = word.length()/2;
		
		for(int i = 0; i < mid; i++) {
			if(word.charAt(i)!=word.charAt(word.length()-1-i))
				return false;
		}
		return true;
	}
	public static String cleanup(String word) {
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) < 'A' || word.charAt(i) > 'Z'
					&& word.charAt(i) < 'a' || word.charAt(i) >'z') {
				word = word.substring(0, i) + word.substring(i+1);
				i--;
			}
		}
		return word.toUpperCase();
	}
	public static int wordValue(String word) {
		int sum = 0;
		for(int i = 0; i < word.length(); i++) {
			int num = word.charAt(i) - 'A' + 1;
			sum += num;
		}
		return sum;
	}
}
