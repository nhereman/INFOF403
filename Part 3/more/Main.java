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
				parser.writeLLVM();
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
		System.out.println("	%res = alloca i32");
		System.out.println("	%digit = alloca i32");
		System.out.println("	%mult = alloca i32");
		System.out.println("	store i32 0, i32* %res");
		System.out.println("	store i32 1, i32* %mult");
		System.out.println("	br label %read");
		System.out.println("read:");
		System.out.println("	%0 = call i32 @getchar()");
		System.out.println("	%1 = sub i32 %0, 48");
		System.out.println("	store i32 %1, i32* %digit");
		System.out.println("	%2 = icmp ne i32 %0, 10");
		System.out.println("	br i1 %2, label %save, label %exit");
		System.out.println("save:");
		System.out.println("	%3 = load i32* %res");
		System.out.println("	%4 = load i32* %digit");
		System.out.println("	%5 = mul i32 %3, 10");
		System.out.println("	%6 = add i32 %5, %4");
		System.out.println("	store i32 %6, i32* %res");
		System.out.println("	br label %read");
		System.out.println("getminus:");
		System.out.println("	store i32 -1, i32* %mult");
		System.out.println("	br label %read");
		System.out.println("exit:");
		System.out.println("	%7 = load i32* %res");
		System.out.println("	%8 = load i32* %mult");
		System.out.println("	%9 = mul i32 %7, %8");
		System.out.println("	ret i32 %9");
		System.out.println("}\n");
	}

	private static void printPutIntFunc() {
		System.out.println("; Defining a function wich print integer");
		System.out.println("define void @putInt(i32 %a) {");
		System.out.println("entry:");
		System.out.println("	%size = alloca i32");
		System.out.println("	%int = alloca i32");
		System.out.println("	store i32 1, i32* %size");
		System.out.println("	store i32 %a, i32* %int");
		System.out.println("	br label %negativetest");
		System.out.println("negativetest:");
		System.out.println("	%0 = icmp slt i32 %a, 0");
		System.out.println("	br i1 %0, label %minus, label %sizecmp");
		System.out.println("minus:");
		System.out.println("	call i32 @putchar(i32 45)");
		System.out.println("	%2 = load i32* %int");
		System.out.println("	%3 = mul i32 %2, -1");
		System.out.println("	store i32 %3, i32* %int");
		System.out.println("	br label %sizecmp");
		System.out.println("computesize:");
		System.out.println("	%4 = load i32* %size");
		System.out.println("	%5 = mul i32 %4, 10");
		System.out.println("	store i32 %5, i32* %size");
		System.out.println("	br label %sizecmp");
		System.out.println("sizecmp:");
		System.out.println("	%6 = load i32* %size");
		System.out.println("	%i = load i32* %int");
		System.out.println("	%7 = icmp ugt i32 %6, %i");
		System.out.println("	br i1 %7, label %printloop, label %computesize");
		System.out.println("printloop:");
		System.out.println("	%j = load i32* %int");
		System.out.println("	%8 = load i32* %size");
		System.out.println("	%9 = udiv i32 %8, 10");
		System.out.println("	store i32 %9, i32* %size");
		System.out.println("	%10 = udiv i32 %j, %9");
		System.out.println("	%11 = urem i32 %10, 10");
		System.out.println("	%12 = add i32 %11, 48");
		System.out.println("	call i32 @putchar(i32 %12)");
		System.out.println("	%14 = icmp ugt i32 %9, 1");
		System.out.println("	br i1 %14, label %printloop, label %exit");
		System.out.println("exit:");
		System.out.println("	ret void");
		System.out.println("}\n");
	}

}