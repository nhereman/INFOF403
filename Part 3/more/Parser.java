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
	private List<Integer> rulesList;
	private List<Symbol> _symbols;

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
		_symbols = new LinkedList<Symbol>(symbols);

		_llStack = new Stack<Enum>();
		_llStack.push(LexicalUnit.END_OF_STREAM); // End of the parse
		_llStack.push(GrammarVariable.PROGRAM); // Starting state

		rules = new GrammarRules();
		table = new GrammarTable();
		rulesList = new LinkedList<Integer>();
	}

	/**
	*	parse the list of symbol given in the constructor.
	*	@return The list of used rules
	*	@throws Exception If an unexpected symbol is found.
	*/
	public List<Integer> parse() throws Exception {

		while ( !_llStack.empty() ) {

			if ( _llStack.peek() == LexicalUnit.END_OF_STREAM) { // The parser must be finished

				if ( _symbolsStack.empty() ) {
					_llStack.pop();
				} else {
					Symbol symbol = _symbolsStack.peek();
					throw new Exception("The code should be ended but is not ( line : " + symbol.getLine() + " column : " + symbol.getColumn() + " )");
				}

			} else if ( _llStack.peek().getClass() == LexicalUnit.class ) {

				// We found a LexicalUnit, so we compare with the symbol stack because they should be the same.

				if ( _symbolsStack.peek().getType() == _llStack.peek() ) {
					_symbolsStack.pop();
					_llStack.pop();
				} else {
					Symbol symbol = _symbolsStack.peek();
					throw new Exception("LexicalUnit :Unexpected symbol found \""+ symbol.getValue() +
										"\" ( line : " + symbol.getLine() + " column : " + symbol.getColumn() +
										" ) " + "Expected : " + _llStack.peek());
				}

			} else if ( _llStack.peek().getClass() == GrammarVariable.class ) {

				// We found a GrammarVariable so we look for the corresponding rules.

				GrammarVariable var = (GrammarVariable) _llStack.peek();
				LexicalUnit lex = (LexicalUnit) _symbolsStack.peek().getType();

				int ruleNb = table.getRule(var,lex);

				if ( ruleNb == -1 ) {
					Symbol symbol = _symbolsStack.peek();
					throw new Exception("GrammarVariable : Unexpected symbol found \""+ symbol.getValue() + 
										"\" ( line : " + symbol.getLine() + " column : " + symbol.getColumn() +
										" )" + "Expected : " + _llStack.peek());
				}

				// We apply the rule
				rulesList.add(new Integer(ruleNb));
				_llStack.pop();
				List<Enum> rule = rules.getRule(ruleNb);
				ListIterator<Enum> listIterator = rule.listIterator(rule.size());
				while(listIterator.hasPrevious()) {
					_llStack.push(listIterator.previous());
				}

			}

		}

		return rulesList;
	}

	/**
	*	Write LLVM code on the standard output
	*/
	public void writeLLVM() {
		LLVMWriter llvm = new LLVMWriter(_symbols, rulesList);
		llvm.write();
	}

}