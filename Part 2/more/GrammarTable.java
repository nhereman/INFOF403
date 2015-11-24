import java.util.HashMap;

/**
*	A class who manage the GrammarTable used for the LL(1) parser.
*
*/
public class GrammarTable {

	private HashMap<GrammarVariable, HashMap<LexicalUnit, Integer> > table;

	/**
	*	The constructor of the class
	*/
	public GrammarTable() {
		table = new HashMap<GrammarVariable, HashMap<LexicalUnit, Integer> >();

		// Program :
		table.put(GrammarVariable.PROGRAM ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.PROGRAM).put(LexicalUnit.BEG, 1);

		// Code :
		table.put(GrammarVariable.CODE ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.CODE).put(LexicalUnit.VARNAME, 3);
		table.get(GrammarVariable.CODE).put(LexicalUnit.END, 2);
		table.get(GrammarVariable.CODE).put(LexicalUnit.IF, 3);
		table.get(GrammarVariable.CODE).put(LexicalUnit.FI, 2);
		table.get(GrammarVariable.CODE).put(LexicalUnit.ELSE, 2);
		table.get(GrammarVariable.CODE).put(LexicalUnit.WHILE, 3);
		table.get(GrammarVariable.CODE).put(LexicalUnit.OD, 2);
		table.get(GrammarVariable.CODE).put(LexicalUnit.FOR, 3);
		table.get(GrammarVariable.CODE).put(LexicalUnit.PRINT, 3);
		table.get(GrammarVariable.CODE).put(LexicalUnit.READ, 3);

		// InstList :
		table.put(GrammarVariable.INSTLIST ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.VARNAME,  4);
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.IF, 4);
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.WHILE, 4);
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.FOR, 4);
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.PRINT, 4);
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.READ, 4);

		// NextInst : 
		table.put(GrammarVariable.NEXTINST ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.NEXTINST).put(LexicalUnit.END, 5);
		table.get(GrammarVariable.NEXTINST).put(LexicalUnit.SEMICOLON, 6);
		table.get(GrammarVariable.NEXTINST).put(LexicalUnit.FI, 5);
		table.get(GrammarVariable.NEXTINST).put(LexicalUnit.ELSE, 5);
		table.get(GrammarVariable.NEXTINST).put(LexicalUnit.OD, 5);

		// Instruction :
		table.put(GrammarVariable.INSTRUCTION ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.VARNAME, 7);
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.IF, 8);
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.WHILE, 9);
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.FOR, 10);
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.PRINT, 11);
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.READ, 12);

		// Assign : 
		table.put(GrammarVariable.ASSIGN ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.ASSIGN).put(LexicalUnit.VARNAME, 13);

		// ExprArith : 
		table.put(GrammarVariable.EXPRARITH ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.EXPRARITH).put(LexicalUnit.VARNAME, 14);
		table.get(GrammarVariable.EXPRARITH).put(LexicalUnit.NUMBER, 14);
		table.get(GrammarVariable.EXPRARITH).put(LexicalUnit.LEFT_PARENTHESIS, 14);
		table.get(GrammarVariable.EXPRARITH).put(LexicalUnit.MINUS, 14);

		// ExprArith2 : 
		table.put(GrammarVariable.EXPRARITH2 ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.VARNAME, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.NUMBER, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.END, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.SEMICOLON, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.LEFT_PARENTHESIS, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.RIGHT_PARENTHESIS, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.MINUS, 15);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.PLUS, 15);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.TIMES, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.DIVIDE, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.THEN, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.FI, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.ELSE, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.NOT, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.AND, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.OR, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.EQUAL, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.GREATER_EQUAL, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.GREATER, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.SMALLER_EQUAL, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.SMALLER, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.DIFFERENT, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.DO, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.OD, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.BY, 16);
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.TO, 16);

		// Term :
		table.put(GrammarVariable.TERM ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.TERM).put(LexicalUnit.VARNAME, 17);
		table.get(GrammarVariable.TERM).put(LexicalUnit.NUMBER, 17);
		table.get(GrammarVariable.TERM).put(LexicalUnit.LEFT_PARENTHESIS, 17);
		table.get(GrammarVariable.TERM).put(LexicalUnit.MINUS, 17);

		// Term 2 :
		table.put(GrammarVariable.TERM2 ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.TERM2).put(LexicalUnit.END, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.SEMICOLON, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.RIGHT_PARENTHESIS, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.MINUS, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.PLUS, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.TIMES, 18);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.DIVIDE, 18);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.THEN, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.FI, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.ELSE, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.AND, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.OR, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.EQUAL, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.GREATER_EQUAL, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.GREATER, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.SMALLER_EQUAL, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.SMALLER, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.DIFFERENT, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.DO, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.OD, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.BY, 19);
		table.get(GrammarVariable.TERM2).put(LexicalUnit.TO, 19);

		// Factor :
		table.put(GrammarVariable.FACTOR ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.FACTOR).put(LexicalUnit.VARNAME, 22);
		table.get(GrammarVariable.FACTOR).put(LexicalUnit.NUMBER, 23);
		table.get(GrammarVariable.FACTOR).put(LexicalUnit.LEFT_PARENTHESIS, 20);
		table.get(GrammarVariable.FACTOR).put(LexicalUnit.MINUS, 21);

		// TermOp : 
		table.put(GrammarVariable.TERMOP ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.TERMOP).put(LexicalUnit.MINUS, 25);
		table.get(GrammarVariable.TERMOP).put(LexicalUnit.PLUS, 24);

		// FactorOp:
		table.put(GrammarVariable.FACTOROP ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.FACTOROP).put(LexicalUnit.TIMES, 26);
		table.get(GrammarVariable.FACTOROP).put(LexicalUnit.DIVIDE, 27);

		// If : 
		table.put(GrammarVariable.IF ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.IF).put(LexicalUnit.IF, 28);

		// EndIf : 
		table.put(GrammarVariable.ENDIF ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.ENDIF).put(LexicalUnit.FI, 29);
		table.get(GrammarVariable.ENDIF).put(LexicalUnit.ELSE, 30);

		// Cond : 
		table.put(GrammarVariable.COND ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.COND).put(LexicalUnit.VARNAME, 31);
		table.get(GrammarVariable.COND).put(LexicalUnit.NUMBER, 31);
		table.get(GrammarVariable.COND).put(LexicalUnit.LEFT_PARENTHESIS, 31);
		table.get(GrammarVariable.COND).put(LexicalUnit.MINUS, 31);
		table.get(GrammarVariable.COND).put(LexicalUnit.NOT, 31);

		// Cond 2 : 
		table.put(GrammarVariable.COND2 ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.COND2).put(LexicalUnit.THEN, 33);
		table.get(GrammarVariable.COND2).put(LexicalUnit.OR, 32);
		table.get(GrammarVariable.COND2).put(LexicalUnit.DO, 33);

		// AndCond : 
		table.put(GrammarVariable.ANDCOND ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.ANDCOND).put(LexicalUnit.VARNAME, 34);
		table.get(GrammarVariable.ANDCOND).put(LexicalUnit.NUMBER, 34);
		table.get(GrammarVariable.ANDCOND).put(LexicalUnit.LEFT_PARENTHESIS, 34);
		table.get(GrammarVariable.ANDCOND).put(LexicalUnit.MINUS, 34);
		table.get(GrammarVariable.ANDCOND).put(LexicalUnit.NOT, 34);

		// AndCond2 : 
		table.put(GrammarVariable.ANDCOND2 ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.ANDCOND2).put(LexicalUnit.THEN, 36);
		table.get(GrammarVariable.ANDCOND2).put(LexicalUnit.AND, 35);
		table.get(GrammarVariable.ANDCOND2).put(LexicalUnit.OR, 36);
		table.get(GrammarVariable.ANDCOND2).put(LexicalUnit.DO, 36);

		// CondTerm : 
		table.put(GrammarVariable.CONDTERM ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.CONDTERM).put(LexicalUnit.VARNAME, 37);
		table.get(GrammarVariable.CONDTERM).put(LexicalUnit.NUMBER, 37);
		table.get(GrammarVariable.CONDTERM).put(LexicalUnit.LEFT_PARENTHESIS, 37);
		table.get(GrammarVariable.CONDTERM).put(LexicalUnit.MINUS, 37);
		table.get(GrammarVariable.CONDTERM).put(LexicalUnit.NOT, 38);

		// SimpleCond : 
		table.put(GrammarVariable.SIMPLECOND ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.SIMPLECOND).put(LexicalUnit.VARNAME, 39);
		table.get(GrammarVariable.SIMPLECOND).put(LexicalUnit.NUMBER, 39);
		table.get(GrammarVariable.SIMPLECOND).put(LexicalUnit.LEFT_PARENTHESIS, 39);
		table.get(GrammarVariable.SIMPLECOND).put(LexicalUnit.MINUS, 39);

		// Comp :
		table.put(GrammarVariable.COMP ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.COMP).put(LexicalUnit.EQUAL, 40);
		table.get(GrammarVariable.COMP).put(LexicalUnit.GREATER_EQUAL, 41);
		table.get(GrammarVariable.COMP).put(LexicalUnit.GREATER, 42);
		table.get(GrammarVariable.COMP).put(LexicalUnit.SMALLER_EQUAL, 43);
		table.get(GrammarVariable.COMP).put(LexicalUnit.SMALLER, 44);
		table.get(GrammarVariable.COMP).put(LexicalUnit.DIFFERENT, 45);

		// While :
		table.put(GrammarVariable.WHILE ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.WHILE).put(LexicalUnit.WHILE, 46);

		// For :
		table.put(GrammarVariable.FOR ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.FOR).put(LexicalUnit.FOR, 47);

		// Print :
		table.put(GrammarVariable.PRINT ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.PRINT).put(LexicalUnit.PRINT, 48);

		// Read :
		table.put(GrammarVariable.READ ,new HashMap<LexicalUnit, Integer>());
		table.get(GrammarVariable.READ).put(LexicalUnit.READ, 49);

	}


	/**
	*	Give the rules number corresponding to the parameters.
	*	@param var A GrammarVariable found in the LL(1) parser stack.
	*	@param lex A LexicalUnit found in the list of scanned symbols.
	*	@return The rule number corresponding to the parameters. -1 is no corresponding rule.
	*	@see GrammarVariable
	*	@see GrammarRules
	*/
	public Integer getRule(GrammarVariable var, LexicalUnit lex) {
		if ( !table.get(var).containsKey(lex) ) {
			return -1;
		} else {
			return table.get(var).get(lex);
		}
	}
}