import java.util.List;
import java.util.Stack;

public class Parser {

	private Stack<Symbol> symbolsStack;
	private Stack<Enum>	llStack;
	private GrammarRules rules;
	private GrammarTable table;

	public Parser(List<Symbol> symbols) {
		symbolsStack = new Stack<Symbol>();
		symbolsStack.addAll(symbols);

		llStack = new Stack<Enum>();
		llStack.push(LexicalUnit.END_OF_STREAM); // End of the parse
		llStack.push(GrammarVariable.PROGRAM); // Starting state

		rules = new GrammarRules();
		table = new GrammarTable();
	}
}