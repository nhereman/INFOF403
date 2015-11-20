import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.lang.Exception;

public class Parser {

	private Stack<Symbol> _symbolsStack;
	private Stack<Enum>	_llStack;
	private GrammarRules rules;
	private GrammarTable table;

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

	public List<Integer> parse(Stack<Symbol> symbolsStack, Stack<Enum> llStack, List<Integer> rulesList, int deep) throws Exception {
		if (symbolsStack == null || llStack == null || rulesList == null) {
			symbolsStack = (Stack<Symbol>) _symbolsStack.clone();
			llStack = (Stack<Enum>) _llStack;
			rulesList = new LinkedList<Integer>();
		}
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

				boolean continu = true;
				int i = 0;
				while ( continu ) {
					int ruleNb = rulesNb[i].intValue();
					rulesList.add(new Integer(ruleNb));
					Enum oldStack = llStack.pop();
					List<Enum> rule = rules.getRule(ruleNb);
					ListIterator<Enum> listIterator = rule.listIterator(rule.size());
					while(listIterator.hasPrevious()) {
						llStack.push(listIterator.previous());
					}
					if ( rulesNb.length > 1 ) {
						try {
							return parse((Stack<Symbol>) symbolsStack.clone(), (Stack<Enum>) llStack.clone(), new LinkedList<Integer>(rulesList), deep+1);
						} catch( Exception e ) {
							for ( Enum en : rule ) {
								llStack.pop();
							}
							llStack.push(oldStack);
							rulesList.remove(rulesList.size()-1);
							++i;
							if ( i == rulesNb.length ) { throw e;}
						}
					} else {
						continu = false;
					}
				}

			}

		}

		return rulesList;
	}


	public List<Integer> parse() throws Exception {
		return parse(null,null, null, 0);
	}
}