import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map;

public class Main {

	public static void main (String[] args){
		if ( args.length < 1 ) {
			System.out.println("Error: Not enough argument");
		} else {
			File f = new File(args[0]);
			try {
				FileReader reader = new FileReader(f);
				LexicalAnalyzer analyzer = new LexicalAnalyzer(reader);

				TreeMap<String, Integer> identifiers = new TreeMap<String, Integer>();

				Symbol symbol = null;

				do {
					symbol = analyzer.nextToken();
					if ( symbol != null && symbol.getType() != LexicalUnit.END_OF_STREAM) {
						System.out.println(symbol.toString());
						if ( symbol.getType() == LexicalUnit.VARNAME && !identifiers.containsKey((String) symbol.getValue()) ) {
							identifiers.put((String) symbol.getValue(), symbol.getLine());
						}
					}
				} while(symbol == null || symbol.getType() != LexicalUnit.END_OF_STREAM);

				System.out.println("Identifiers");

				for (Map.Entry<String,Integer> entry : identifiers.entrySet()) {
					System.out.print(entry.getKey()+" ");
					System.out.println(entry.getValue());
				}

			} catch(IOException exception) {
				 System.out.println ("Erreur during read : " + exception.getMessage());
			}
		}
	}

}