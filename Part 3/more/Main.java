import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Map;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.lang.Exception;

public class Main {

	public static void main (String[] args){
		if ( args.length < 1 ) {
			System.out.println("Error: Not enough argument");
		} else {

			File f = new File(args[0]);
			Scanner scanner = new Scanner(f);
			List<Symbol> symbols = scanner.getSymbols();
			TreeMap<String, Integer> identifiers = scanner.getIdentifiers();

			try {
				Parser parser = new Parser(symbols);
				List<Integer> ruleList = parser.parse();
				parser.writeLLVM();
			} catch ( Exception e )	{
				System.out.println(e.getMessage());
			}
			
		}
	}


}