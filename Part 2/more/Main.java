import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class Main {

	public static void main (String[] args){
		if ( args.length < 1 ) {
			System.out.println("Error: Not enough argument");
		} else {

			File f = new File(args[0]);
			Scanner scanner = new Scanner(f);
			List<Symbol> symbols = scanner.getSymbols();
			TreeMap<String, Integer> identifiers = scanner.getIdentifiers();
			//for (Symbol s : symbols) {
			//	System.out.println(s.toString());
			//}
			//System.out.println("Identifiers");
			//for (Map.Entry<String,Integer> entry : identifiers.entrySet()) {
			//	System.out.print(entry.getKey()+" ");
			//	System.out.println(entry.getValue());
			//}
			Parser parser = new Parser(symbols);
		}
	}

}