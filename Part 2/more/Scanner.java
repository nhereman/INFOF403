import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map;
import java.util.regex.PatternSyntaxException;
import java.util.LinkedList;
import java.util.List;


/**
*
*	The scanner of the compilator.
*/
public class Scanner {

	private TreeMap<String, Integer> 	identifiers = new TreeMap<String, Integer>();
	private List<Symbol>				symbols = new LinkedList<Symbol>();

	/**
	*	The constructor of the scanner.
	*	It scan the given file.
	*	@param file The file to scan
	*/
	public Scanner(File file) {

		try {
			FileReader reader = new FileReader(file);
			LexicalAnalyzer analyzer = new LexicalAnalyzer(reader);

			Symbol symbol = null;

			do {
				symbol = analyzer.nextToken();
				if ( symbol != null && symbol.getType() != LexicalUnit.END_OF_STREAM) {
					symbols.add(symbol);
					if ( symbol.getType() == LexicalUnit.VARNAME && !identifiers.containsKey((String) symbol.getValue()) ) {
						identifiers.put((String) symbol.getValue(), symbol.getLine());
					}
				}
			} while(symbol == null || symbol.getType() != LexicalUnit.END_OF_STREAM);

		} catch(IOException exception) {
			System.out.println ("Error during read : " + exception.getMessage());
		} catch(PatternSyntaxException e) {
			System.out.println ("Parse Error : " + e.getDescription() );
		}
	}

	/**
	*	Give the list of all symbols scanned.
	*	@return The list of all scanned symbols
	*/
	public List<Symbol> getSymbols() {
		return symbols;
	}

	/**
	*	Give a map with the identifiers and the first line we saw them.
	*	@return A TreeMap with the identifiers and the first line we saw them
	*/
	public TreeMap<String, Integer> getIdentifiers() {
		return identifiers;
	}

}