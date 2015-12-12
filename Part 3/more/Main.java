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
				System.out.println("LLVM code :");
				printLlvmHeader();
				printReadIntFunc();
				printPutIntFunc();
				List<Integer> ruleList = parser.parse();
				System.out.println("List of rules of parser :");
				for (Integer rule : ruleList ) {
					System.out.println(rule);
				}
			} catch ( Exception e )	{
				System.out.println(e.getMessage());
			}
			
		}
	}

	private static void printLlvmHeader() {
		System.out.println("; External function declaration");
		System.out.println("declare i32 @getchar()");
		System.out.println("declare i32 @putchar(i32)\n");
	}

	private static void printReadIntFunc() {
		System.out.println("; Defining a function wich read integer");
		System.out.println("define i32 @readInt() {");
		System.out.println("entry:");
		System.out.println("	%res = allocate i32");
		System.out.println("	%digit = allocate i32");
		System.out.println("	%mult = allocate i32");
		System.out.println("	store i32 0, i32* %res");
		System.out.println("	store i32 1, i32* %mult");
		System.out.println("	br label %read");
		System.out.println("read:");
		System.out.println("	%0 = call i32 @getchar()");
		System.out.println("	%1 = icmp eq i32 %0, 45");
		System.out.println("	br i1 %1, label %getdigit, label %getminus");
		System.out.println("getdigit:");
		System.out.println("	%2 = sub i32 %0, 48");
		System.out.println("	store i32 %2, i32* %digit");
		System.out.println("	%3 = icmp ne i32 %0, 10");
		System.out.println("	br i1 %3, label %save, label %exit");
		System.out.println("save:");
		System.out.println("	%4 = load i32* %res");
		System.out.println("	%5 = load i32* %digit");
		System.out.println("	%6 = mul i32 %4, 10");
		System.out.println("	%7 = add i32 %6; %5");
		System.out.println("	store i32 %7, i32* %res");
		System.out.println("	br label %read");
		System.out.println("getminus:");
		System.out.println("	store i32 -1, i32* %mult");
		System.out.println("	br label %read");
		System.out.println("exit:");
		System.out.println("	%8 = load i32* %res");
		System.out.println("	%9 = load i32* %mult");
		System.out.println("	%10 = mul i32 %8, %9");
		System.out.println("	ret i32 %10");
		System.out.println("}\n");
	}

	private static void printPutIntFunc() {
		System.out.println("; Defining a function wich print integer");
		System.out.println("define void @putInt(i32 %a) {");
		System.out.println("entry:");
		System.out.println("\t%size = allocate i32");
		System.out.println("\tstore i32 1, i32* %size");
		System.out.println("\tbr label negativetest");
		System.out.println("negativetest:");
		System.out.println("\t%0 = cmp slt i32 %a, 0");
		System.out.println("\tbr i1 %0, label %minus, label %sizecmp");
		System.out.println("minus:");
		System.out.println("\tcall i32 @putchar(i32 45); print minus");
		System.out.println("computesize:");
		System.out.println("\t%1 = load i32* %size");
		System.out.println("\t%2 = mul i32 %1, 10");
		System.out.println("\tstore i32 %2, i32* %size");
		System.out.println("\tbr label sizecmp");
		System.out.println("sizecmp:");
		System.out.println("\t%3 = load i32* %size");
		System.out.println("\t%4 = icmp ugt i32 %3, %a");
		System.out.println("\tbr i1 %4, label %printloop, label %computesize");
		System.out.println("printloop:");
		System.out.println("\t%5 = load i32* %size");
		System.out.println("\t%6 = udiv i32 %5, 10");
		System.out.println("\tstore i32 %6, i32* %size");
		System.out.println("\t%7 = udiv i32 %a, %6");
		System.out.println("\t%8 = urem i32 %7, 10");
		System.out.println("\t%9 = add i32 %8, 48");
		System.out.println("\tcall i32 @putchar(i32 %9)");
		System.out.println("\t%10 = cmp ugt i32 %6, 0");
		System.out.println("\tbr i1 %9, label %printloop, label %exit");
		System.out.println("exit:");
		System.out.println("\tret void");
		System.out.println("}\n");
	}

}