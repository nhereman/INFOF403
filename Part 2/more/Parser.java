import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.lang.Exception;

public class Parser {

	private Stack<Symbol> symbolsStack;
	private Stack<Enum>	llStack;
	private GrammarRules rules;
	private GrammarTable table;
	private List<Integer> rulesList;

	public Parser(List<Symbol> symbols) {
		symbolsStack = new Stack<Symbol>();
		ListIterator<Symbol> listIterator = symbols.listIterator(symbols.size());
		while(listIterator.hasPrevious()) {
			symbolsStack.push(listIterator.previous());
		}

		llStack = new Stack<Enum>();
		llStack.push(LexicalUnit.END_OF_STREAM); // End of the parse
		llStack.push(GrammarVariable.PROGRAM); // Starting state

		rules = new GrammarRules();
		table = new GrammarTable();
		rulesList = new LinkedList<Integer>();
	}

	public List<Integer> parse() throws Exception {
		while ( !llStack.empty() ) {

			if ( llStack.peek() == LexicalUnit.END_OF_STREAM) {

				if ( symbolsStack.empty() ) {
					llStack.pop();
				} else {
					Symbol symbol = symbolsStack.peek();
					throw new Exception("The code should be ended but is not ( line : " + symbol.getLine() + " column : " + symbol.getColumn() + " )");
				}

			} else if ( llStack.peek().getClass() == LexicalUnit.class ) {

				if ( symbolsStack.peek().getType() == llStack.peek() ) {
					symbolsStack.pop();
					llStack.pop();
				} else {
					Symbol symbol = symbolsStack.peek();
					throw new Exception("LexicalUnit :Unexpected symbol found \""+ symbol.getValue() +
										"\" ( line : " + symbol.getLine() + " column : " + symbol.getColumn() +
										" ) " + "Expected : " + llStack.peek());
				}

			} else if ( llStack.peek().getClass() == GrammarVariable.class ) {

				GrammarVariable var = (GrammarVariable) llStack.peek();
				LexicalUnit lex = (LexicalUnit) symbolsStack.peek().getType();

				Integer[] rulesNb = table.getRules(var,lex);

				if ( rulesNb.length == 0 ) {
					Symbol symbol = symbolsStack.peek();
					throw new Exception("GrammarVariable : Unexpected symbol found \""+ symbol.getValue() + 
										"\" ( line : " + symbol.getLine() + " column : " + symbol.getColumn() +
										" )" + "Expected : " + llStack.peek());
				}

				int ruleNb = rulesNb[0].intValue();
				rulesList.add(new Integer(ruleNb));
				llStack.pop();
				List<Enum> rule = rules.getRule(ruleNb);
				ListIterator<Enum> listIterator = rule.listIterator(rule.size());
				while(listIterator.hasPrevious()) {
					llStack.push(listIterator.previous());
				}

			}

		}

		return rulesList;
	}
}