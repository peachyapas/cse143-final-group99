import java.io.*;
import java.util.*;

public class ChemistryMemes {

	private static TreeMap<String, String> periodicTable;
	
	// constructor for elementSearch
	public ChemistryMemes() {
		periodicTable = new TreeMap<String, String> ();
	}
	
	// play the game
	public void playGame(BufferedReader br) throws IOException {
		System.out.println();
		System.out.println("Check if a word can be spelled using elements from the periodic table ");
		System.out.println("and make your chemistry class less boring by making bad chemistry PuNS all day");
		System.out.print("Enter word to be spelled: ");
		String inputWord = br.readLine();
		elementSearch(inputWord);
	}

	// spell out the words
    private void elementSearch(String word) throws IOException {
		ArrayList<String> symb = elementSearch(word, new ArrayList<String>());
		
		//Printing the array (if it matches)
        if (symb != null) {
			String spelled = wordInSymbol(symb);
			System.out.println("The word spelled with elements' symbol: " + spelled);

			String elements = elementsInWord(symb);
			System.out.println("The word was spelled using the elements: " + elements);
			
		//if the word doesn't match
		} else {
			System.out.println("A combination of elements for that word does not exist");
		} 
		
		//repeating game     
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean repeat = false;
		while (!repeat) {
			System.out.print("Meme with another word? Yes/No: ");
			String repeatGame = br.readLine();
			if (repeatGame.equalsIgnoreCase("yes")) {
				repeat = true;
				System.out.println();
				playGame(br);
			} else if (repeatGame.equalsIgnoreCase("no")) {
				repeat = true;
				System.out.println("The game has ended");
				System.out.println();
			}
		}
		br.close();
    }

	// see if word can be spelled using elemennts
    private ArrayList<String> elementSearch(String inputText, ArrayList<String> soFar) {
        // if empty it just returns an empty set
        if (inputText.length() == 0) {
            return soFar;
        }

        // otherwise we check the map's keysets for the symbols
		for (String symb : periodicTable.keySet()) {
			/* 
			 * if the length of the input text is longer than the symbol
             * then we get a substring to the length of the symbol and check if the text is the same
			 */
			if (symb.length() <= inputText.length()) { //to not run into errors
				if (inputText.substring(0, symb.length()).equalsIgnoreCase(symb)) {
                    /* if text's substring == symbol add it to soFar. For example
                     * We're on H. We check it against Hello & check if Hello contains substring He
					 * If we're on H & we check it against Hello, we check if Hello has substring H
					 */
					soFar.add(symb);
					//recursively calls this method and once a substring doesn't exist (null)
					//then we return soFar
					if (elementSearch(inputText.substring(symb.length()), soFar) != null) {
                        return soFar;
                    } 
				}
			}
        }
        // returns null if no elements
        return null;
	}

	// printing word spelled out in symbol
	private String wordInSymbol(ArrayList<String> symb) throws IOException {
		if (symb != null) {
            //for printing it out correctly (rather than just printing the set)
            String append = "";
			for (String s: symb) {
				append += s;
			}
			return append;
		}
		return null;
	}

	// to print out the elements associated with the symbol (periodic table element names)
	private String elementsInWord(ArrayList<String> symb) throws IOException {
		if (symb != null) {
			
			StringBuilder buildString = new StringBuilder();
			for (String symbol: symb) {
				buildString.append(periodicTable.get(symbol));
				buildString.append(" "); //fencepost if i do ,
			}
			return buildString.toString();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		ChemistryMemes meme = new ChemistryMemes();

		//to get the list of elements
		BufferedReader reader = new BufferedReader(new FileReader("../elementlist.txt"));
		String str = null;
        while ((str = reader.readLine()) != null) {
            String[] element = str.split("	");
            periodicTable.put(element[0], element[1]);
        }
        reader.close();

		//for user to ennter word
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		meme.playGame(br);
		br.close();
	}
}