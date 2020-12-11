import java.io.*;
import java.util.*;

public class ChemistryMeme {

	private static List<String> elements;
	private static TreeMap<String, String> periodicTable;
	private static List<String> elementsForChecking;
	private Scanner elementFileScanner;

	public ChemistryMeme(File elementList) throws FileNotFoundException {
		elements = new ArrayList<String>();
		periodicTable = new TreeMap<String, String> ();
		elementFileScanner = new Scanner(elementList);
	}

	// List of elements (symbol)
	public void createList() {
		elements.add(elementFileScanner.nextLine());
		while (elementFileScanner.hasNextLine()) {
			elements.add(elementFileScanner.nextLine());
		}
		elementFileScanner.close();
		elementsForChecking = listToLowerCase(elements);
	}

	// Converts to lowercase
	private List<String> listToLowerCase(List<String> list) {
		List<String> newList = new ArrayList<String>();
		for (String str : list) {
			newList.add(str.toLowerCase());
		}
		return newList;
	}

	// Shows all possible combinations
	public static void elementize(String input) throws IOException {
		char[] arr = input.toCharArray();
		System.out.println();
		System.out.print("All possible combinations: ");
		elementize(arr, "", 0);
	}

	// Spells out the word using periodic table elements
	private static void elementize(char[] arr, String output, int combinations) {
		if (arr.length <= 0) {
			System.out.println("\n");
			System.out.println("The word can be symbolised as " + output + " using " + combinations + " element(s): ");
		} else {
			combinations++;
			// for two-letter element symbols
			if (arr.length >= 2 && hasElement(arr, 2)) {
				String element = returnElement(arr, 2);
				elementize(truncateArray(arr, 2), output + element, combinations);
				System.out.print(periodicTable.get(element) + " ");
			}
			// for one-letter element symbols
			if (hasElement(arr, 1)) {
				String element = returnElement(arr, 1);
				elementize(truncateArray(arr, 1), output + element, combinations);
				System.out.print(periodicTable.get(element) + " ");
			}
		}
	}

	// Check if the element exists
	private static boolean hasElement(char arr[], int end) {
		String stringToCheck = arrayToString(arr, end);
		return elementsForChecking.contains(stringToCheck);
	}

	// Return the element at the given index
	private static String returnElement(char arr[], int end) {
		String stringToCheck = arrayToString(arr, end);
		int index = elementsForChecking.indexOf(stringToCheck);
		return elements.get(index);
	}

	private static String arrayToString(char arr[], int end) {
		String stringToCheck = "";
		for (int i = 0; i < end; i++) {
			stringToCheck = stringToCheck + arr[i];
		}
		return stringToCheck.toLowerCase();
	}

	// truncates array by either length 1 or length 2 (depending on the length of the element symbol)
	private static char[] truncateArray(char[] arr, int start) {
		return Arrays.copyOfRange(arr, start, arr.length);
	}

	// run the game & repeat it or end it
	public static void playGame(BufferedReader br) throws IOException {
		System.out.println();
		System.out.println("Check if a word can be spelled using elements from the periodic table ");
		System.out.println("and make your chemistry class less boring by making bad chemistry PuNS all day");
		System.out.print("Enter word to be spelled: ");
		String inputWord = br.readLine();
		elementize(inputWord);

		boolean repeat = false;
		while (!repeat) {
			System.out.println();
			System.out.print("Meme with another word? Yes/No: ");
			String repeatGame = br.readLine();
			if (repeatGame.equalsIgnoreCase("yes")) {
				repeat = true;
				playGame(br);
			} else if (repeatGame.equalsIgnoreCase("no")) {
				repeat = true;
				System.out.println("The game has ended");
				System.out.println();
			}
		}
	}

	public static void main(String args[]) throws IOException {
		// create list of elements
		File elements = new File("Elements.txt");
		ChemistryMeme elementer = new ChemistryMeme(elements);
		elementer.createList();

		// to get the map of elements
		BufferedReader reader = new BufferedReader(new FileReader("elementlist.txt"));
		String str = null;
        while ((str = reader.readLine()) != null) {
            String[] element = str.split("	");
            periodicTable.put(element[0], element[1]);
		}
		reader.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		playGame(br);
	}

}
