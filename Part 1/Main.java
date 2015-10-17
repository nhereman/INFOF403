import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class Main {

	public static void main (String[] args){
		if ( args.length < 1 ) {
			System.out.println("Error: Not enough argument");
		} else {
			File f = new File(args[0]);
			try {
				FileReader reader = new FileReader(f);
				LexicalAnalyzer analyzer = new LexicalAnalyzer(reader);

				Symbol symbol = null;

				do {
					symbol = analyzer.nextToken();
					if ( symbol != null && symbol.getType() != LexicalUnit.END_OF_STREAM) {
						System.out.println(symbol.toString());
					}
				} while(symbol == null || symbol.getType() != LexicalUnit.END_OF_STREAM);
			} catch(IOException exception) {
				 System.out.println ("Erreur during read : " + exception.getMessage());
			}
		}
	}

}