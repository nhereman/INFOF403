import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.lang.Exception;


/**
*
*	The LL(1) parser for the compilator.
*
*/
public class Parser {

	private Stack<Symbol> _symbolsStack;
	private Stack<Enum>	_llStack;
	private GrammarRules rules;
	private GrammarTable table;

	/**
	*	Parser constructor
	*	@param symbols The list of all symbols you have to parse.
	**/
	public Parser(List<Symbol> symbols) {
		_symbolsStack = new Stack<Symbol>();
		ListIterator<Symbol> listIterator = symbols.listIterator(symbols.size());
		while(listIterator.hasPrevious()) {
			_symbolsStack.push(listIterator.previous());
		}

		_llStack = new Stack<Enum>();
		_llStack.push(LexicalUnit.END_OF_STREAM); // End of the parse
		_llStack.push(GrammarVariable.PROGRAM); // Starting state

		rules = new GrammarRules();
		table = new GrammarTable();
	}

	/**
	*	Parse the list of symbols to check it it respect the grammar.
	*	@param symbolsStack The stack of symbol to parse. If null initialized with the constructor parameters.
	*	@param llStack The stack used for the llParser. If null it is initialized with the basics llStack [PROGRAM, $]
	*	@param rulesList The list of all rules used previously.
	*	@return The list of used rules.
	*	@throws Exception If an unexpected symbol is found.
	*/
	private List<Integer> parse(Stack<Symbol> symbolsStack, Stack<Enum> llStack, List<Integer> rulesList) throws Exception {
		
		// If one of these parameter is null, we initialize all of them.
		if (symbolsStack == null || llStack == null || rulesList == null) {
			symbolsStack = (Stack<Symbol>) _symbolsStack.clone();
			llStack = (Stack<Enum>) _llStack;
			rulesList = new LinkedList<Integer>();
		}

		while ( !llStack.empty() ) {

			if ( llStack.peek() == LexicalUnit.END_OF_STREAM) { // The parser must be finished

				if ( symbolsStack.empty() ) {
					llStack.pop();
				} else {
					Symbol symbol = symbolsStack.peek();
					throw new Exception("The code should be ended but is not ( line : " + symbol.getLine() + " column : " + symbol.getColumn() + " )");
				}

			} else if ( llStack.peek().getClass() == LexicalUnit.class ) {

				// We found a LexicalUnit, so we compare with the symbol stack because they should be the same.

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

				// We found a GrammarVariable so we look for the corresponding rules.

				GrammarVariable var = (GrammarVariable) llStack.peek();
				LexicalUnit lex = (LexicalUnit) symbolsStack.peek().getType();

				Integer[] rulesNb = table.getRules(var,lex);

				if ( rulesNb.length == 0 ) {
					Symbol symbol = symbolsStack.peek();
					throw new Exception("GrammarVariable : Unexpected symbol found \""+ symbol.getValue() + 
										"\" ( line : " + symbol.getLine() + " column : " + symbol.getColumn() +
										" )" + "Expected : " + llStack.peek());
				}

				boolean continu = true;
				int i = 0;
				while ( continu ) { // We loop in case of multiple rules
					// We apply the rule
					int ruleNb = rulesNb[i].intValue();
					rulesList.add(new Integer(ruleNb));
					Enum oldStack = llStack.pop();
					List<Enum> rule = rules.getRule(ruleNb);
					ListIterator<Enum> listIterator = rule.listIterator(rule.size());
					while(listIterator.hasPrevious()) {
						llStack.push(listIterator.previous());
					}
					// If there is more than one rule, we call parse() recursively so if there is an error we can come back to try an other rule.
					if ( rulesNb.length > 1 ) {
						try {
							return parse((Stack<Symbol>) symbolsStack.clone(), (Stack<Enum>) llStack.clone(), new LinkedList<Integer>(rulesList));
						} catch( Exception e ) {
							// If an exception is throw we cancel the rule and try the next one.
							for ( Enum en : rule ) {
								llStack.pop();
							}
							llStack.push(oldStack);
							rulesList.remove(rulesList.size()-1);
							++i;
							if ( i == rulesNb.length ) { throw e;} // If there is no more rules, we throw the Exception.
						}
					} else {
						continu = false;
					}
				}

			}

		}

		return rulesList;
	}


	/**
	*	parse the list of symbol given in the constructor.
	*	@return The list of used rules
	*	@throws Exception If an unexpected symbol is found.
	*/
	public List<Integer> parse() throws Exception {
		return parse(null,null, null);
	}
}