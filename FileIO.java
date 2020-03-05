import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FileIO {

	/**DONE - Open inFile for reading and outFile for writing.
	 * Print out each character 5 times if it is NOT a space, period, comma, question mark, or exclamation point.
	 * If it is any of these characters, it only gets printed once.
	 * See output example given.
	 * @param inFile
	 * @param outFile
	 */
	public static void stretch(String inFile, String outFile) {
		System.out.println("stretch opening files " + inFile + ", " + outFile);
		String[] punctuation = new String[]{" ", ".", ",", "?", "!"};
		
		try(Scanner stretch = new Scanner(new File(inFile));
			PrintWriter pw = new PrintWriter(new File(outFile))) {
			while(stretch.hasNextLine()) {
				char[] words = stretch.nextLine().toCharArray();
				for(char x: words) {
					if(Character.toString(x).equals(" ") || Character.toString(x).equals(".") || Character.toString(x).equals(",") || Character.toString(x).equals("?") || Character.toString(x).equals("!")) {
						for(String y: punctuation){
							if(Character.toString(x).equals(y) == true) {
								pw.print(y);
							}
						}
					}else {
						for(int y = 1; y < 6; y++) {
							pw.print(x);
						}
					}
				}
				pw.println();
			}
			pw.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}catch(InputMismatchException e) {
			System.out.println("Type mismatch!");
		}		
		System.out.println("stretch finished");
	}

	/**DONE - Open inFile for reading and outFile for writing.
	 * inFile is supposed to contain only whole integers, but sometimes they don't.
	 * Read all numbers, sort them in increasing order, and write them to the output file.
	 * You will need to finish sortNumbers to help sort your numbers.
	 * If you a read a line that is not an int or is not a number, your program must not crash.
	 * Instead, ignore this line.
	 * See output example given.
	 * 
	 * @param inFile
	 * @param outFile
	 */
	public static void sortNumbers(String inFile, String outFile) {
		System.out.println("sortNumbers opening files " + inFile + ", " + outFile);
		List<Integer> tobeSorted = new ArrayList<>();
		String use;
		int test = 0;
		
		try(Scanner numbers = new Scanner(new File(inFile));
			PrintWriter pw = new PrintWriter(new File(outFile))) {
			while(numbers.hasNextLine()) {
				try{
					test = Integer.parseInt(numbers.nextLine());
					use = "YES";
				}catch(NumberFormatException e) {
					use = "NO";
				}
				if(use.equals("YES")) {
					tobeSorted.add(test);
				}
			}
			if(!tobeSorted.isEmpty()) {
				sortNumbers(tobeSorted);
				for(Integer x: tobeSorted) {
					pw.println(x);
				}
			}
			pw.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}catch(InputMismatchException e) {
			System.out.println("Type mismatch!");
		}
		System.out.println("sortNumbers finished");
	}

	/**DONE - Open inFile for reading and outFile for writing.
	 * This is a method that counts all occurrences of all words in a file.
	 * When done, output each word found and its count to the output file in sorted order.
	 * You will need to call sortWords to help sort your words at the end.
	 * After writing all words, give the total count of words for all words in the file.
	 * See output example given.
	 * 
	 * @param inFile
	 * @param outFile
	 */
	public static void wordCount(String inFile, String outFile) {
		System.out.println("wordCount opening files " + inFile + ", " + outFile);
		HashMap<String, Integer> hm = new HashMap<>();
		List<String> tokenList = new ArrayList<>();
		
		try(Scanner word = new Scanner(new File(inFile));
			PrintWriter pw = new PrintWriter(new File(outFile))) {
			while(word.hasNextLine()) {
				String sentence = word.nextLine();
				String[] bits = sentence.split(" ");
				for(String x: bits) {
					x = x.toLowerCase();
					if(x.contains(" ") || x.contains(".") || x.contains(",") || x.contains("?") || x.contains("!")) {
						x = x.substring(0, x.length()-1);
					}
					if(!x.equals(" ") || !x.equals(".") || !x.equals(",") || !x.equals("?") || !x.equals("!")) {
						if(hm.containsKey(x) == true) {
							hm.put(x, hm.get(x) + 1);
						}else {
							hm.put(x, 1);
							tokenList.add(x);
						}
					}
				}
			}
			sortWords(tokenList);
			
			int totalWords = 0;
			for(String x: tokenList) {
				int numToks = hm.get(x);
				pw.println(x + ": " + numToks);
				totalWords += numToks;
			}
			pw.println();
			pw.print("Total Words: " + totalWords);
			
			pw.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}catch(InputMismatchException e) {
			System.out.println("Type mismatch!");
		}
		
		System.out.println("wordCount finished");
	}

	/**DONE - Open inFile1 and inFile2 for reading and outFile for writing.
	 * The contents of inFile1 and inFile2 will contain a list of numbers and names in the following format.
	 * 
	 * <File>
	 * <id>:<name>
	 * <id>:<name>
	 * <id>:<name>
	 * and so on.
	 * 
	 * Assume the files are always in this format, and the ids are always sorted in increasing order.
	 * Assume each file does not contain duplicates with in their file.
	 * You do NOT need to verify the file is in the correct format.
	 * You do NOT need to sort anything, they should already be sorted.
	 * Perform the merge of the two lists and write out the contents to the output file.
	 * A merge is exactly the same as a union.  All <id>:<name> from each file should be merged into
	 * one file.  If a duplicate <id>:<name> occurs in both files, only one <id>:<name> is written to the output file.
	 * 
	 * Merge example
	 * 
	 * inFile1.txt				inFile2.txt
	 * 2:Nate					4:Alan
	 * 4:Alan					12:Chelsea
	 * 15:Kristin
	 * 18:Valory
	 * 
	 * mergeOutput.txt
	 * 2:Nate
	 * 4:Alan
	 * 12:Chelsea
	 * 15:Kristin
	 * 18:Valory
	 * 
	 * Also see output example given.
	 * 
	 * @param inFile
	 * @param outFile
	 */
	public static void mergeFileContents(String inFile1, String inFile2, String outFile) {
		System.out.println("merge opening file " + inFile1 + ", " + inFile2 + ", " + outFile);
		
		try(Scanner merge1 = new Scanner(new File(inFile1));
			Scanner merge2 = new Scanner(new File(inFile2));
			PrintWriter pw = new PrintWriter(new File(outFile))) {
			HashMap<Integer, String> hm = new HashMap<>();
			List<Integer> keys = new ArrayList<>();
			
			while(merge1.hasNextLine()) {
				String entry = merge1.nextLine();
				int index = entry.indexOf(":");
				
				int valKey = Integer.parseInt(entry.substring(0, index));
				String name = entry.substring(index+1);
				
				if(hm.containsKey(valKey) == false) {
					hm.put(valKey, name);
					keys.add(valKey);
				}
			}
			
			while(merge2.hasNextLine()) {
				String entry = merge2.nextLine();
				int index = entry.indexOf(":");
				
				int valKey = Integer.parseInt(entry.substring(0, index));
				String name = entry.substring(index+1);
				
				if(hm.containsKey(valKey) == false) {
					hm.put(valKey, name);
					keys.add(valKey);
				}
			}
			
			sortNumbers(keys);
			for(int x: keys) {
				pw.println(x + ":" + hm.get(x));
			}			
			pw.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}catch(InputMismatchException e) {
			System.out.println("Type mismatch!");
		}
		
		System.out.println("merge finished");
	}
	
	/**DONE - Sort list from smallest to largest numbers.
	 * @param list
	 */
	public static void sortNumbers(List<Integer> list) {
		for(int i = 1; i < list.size(); ++i){
			int j = i;
			while(j > 0) {
				if(list.get(j) < list.get(j-1)) {
					int temp = list.get(j-1);
					list.set(j-1, list.get(j));
					list.set(j, temp);
				}
				j--;
			}
		}
	}

	/**DONE - Sort list alphabetically, meaning a - z
	 * @param list
	 */
	public static void sortWords(List<String> list) {
		for(int i = 0; i < list.size(); i++) {
			int minVal = i;
			for(int j = i + 1; j < list.size(); j++) {
				if(list.get(j).compareTo(list.get(minVal)) < 0) {
					minVal = j;
				}
			}
			String temp = list.get(minVal);
			list.set(minVal, list.get(i));
			list.set(i, temp);
		}		
	}
}
