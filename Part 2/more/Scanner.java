import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map;
import java.util.regex.PatternSyntaxException;
import java.util.LinkedList;
import java.util.List;

public class Scanner {

	private TreeMap<String, Integer> 	identifiers = new TreeMap<String, Integer>();
	private List<Symbol>				symbols = new LinkedList<Symbol>();

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

	public List<Symbol> getSymbols() {
		return symbols;
	}

	public TreeMap<String, Integer> getIdentifiers() {
		return identifiers;
	}

}