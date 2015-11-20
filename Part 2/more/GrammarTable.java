import java.util.HashMap;

public class GrammarTable {

	private HashMap<GrammarVariable, HashMap<LexicalUnit, Integer[]> > table;

	public GrammarTable() {
		table = new HashMap<GrammarVariable, HashMap<LexicalUnit, Integer[]> >();

		// Program :
		table.put(GrammarVariable.PROGRAM ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.PROGRAM).put(LexicalUnit.BEG, new Integer[]{1});

		// Code :
		table.put(GrammarVariable.CODE ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.CODE).put(LexicalUnit.VARNAME, new Integer[]{3});
		table.get(GrammarVariable.CODE).put(LexicalUnit.END, new Integer[]{2});
		table.get(GrammarVariable.CODE).put(LexicalUnit.IF, new Integer[]{3});
		table.get(GrammarVariable.CODE).put(LexicalUnit.FI, new Integer[]{2});
		table.get(GrammarVariable.CODE).put(LexicalUnit.ELSE, new Integer[]{2});
		table.get(GrammarVariable.CODE).put(LexicalUnit.WHILE, new Integer[]{3});
		table.get(GrammarVariable.CODE).put(LexicalUnit.OD, new Integer[]{2});
		table.get(GrammarVariable.CODE).put(LexicalUnit.FOR, new Integer[]{3});
		table.get(GrammarVariable.CODE).put(LexicalUnit.PRINT, new Integer[]{3});
		table.get(GrammarVariable.CODE).put(LexicalUnit.READ, new Integer[]{3});

		// InstList :
		table.put(GrammarVariable.INSTLIST ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.VARNAME, new Integer[]{4});
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.IF, new Integer[]{4});
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.WHILE, new Integer[]{4});
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.FOR, new Integer[]{4});
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.PRINT, new Integer[]{4});
		table.get(GrammarVariable.INSTLIST).put(LexicalUnit.READ, new Integer[]{4});

		// NextInst : 
		table.put(GrammarVariable.NEXTINST ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.NEXTINST).put(LexicalUnit.END, new Integer[]{5});
		table.get(GrammarVariable.NEXTINST).put(LexicalUnit.SEMICOLON, new Integer[]{6});
		table.get(GrammarVariable.NEXTINST).put(LexicalUnit.FI, new Integer[]{5});
		table.get(GrammarVariable.NEXTINST).put(LexicalUnit.ELSE, new Integer[]{5});
		table.get(GrammarVariable.NEXTINST).put(LexicalUnit.OD, new Integer[]{5});

		// Instruction :
		table.put(GrammarVariable.INSTRUCTION ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.VARNAME, new Integer[]{7});
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.IF, new Integer[]{8});
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.WHILE, new Integer[]{9});
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.FOR, new Integer[]{10});
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.PRINT, new Integer[]{11});
		table.get(GrammarVariable.INSTRUCTION).put(LexicalUnit.READ, new Integer[]{12});

		// Assign : 
		table.put(GrammarVariable.ASSIGN ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.ASSIGN).put(LexicalUnit.VARNAME, new Integer[]{13});

		// ExprArith : 
		table.put(GrammarVariable.EXPRARITH ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.EXPRARITH).put(LexicalUnit.VARNAME, new Integer[]{14});
		table.get(GrammarVariable.EXPRARITH).put(LexicalUnit.NUMBER, new Integer[]{14});
		table.get(GrammarVariable.EXPRARITH).put(LexicalUnit.LEFT_PARENTHESIS, new Integer[]{14});
		table.get(GrammarVariable.EXPRARITH).put(LexicalUnit.MINUS, new Integer[]{14});

		// ExprArith2 : 
		table.put(GrammarVariable.EXPRARITH2 ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.VARNAME, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.NUMBER, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.END, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.LEFT_PARENTHESIS, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.RIGHT_PARENTHESIS, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.MINUS, new Integer[]{15,16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.PLUS, new Integer[]{15,16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.THEN, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.FI, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.ELSE, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.AND, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.OR, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.EQUAL, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.GREATER_EQUAL, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.GREATER, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.SMALLER_EQUAL, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.SMALLER, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.DIFFERENT, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.DO, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.OD, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.BY, new Integer[]{16});
		table.get(GrammarVariable.EXPRARITH2).put(LexicalUnit.TO, new Integer[]{16});

		// Term :
		table.put(GrammarVariable.TERM ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.TERM).put(LexicalUnit.VARNAME, new Integer[]{17});
		table.get(GrammarVariable.TERM).put(LexicalUnit.NUMBER, new Integer[]{17});
		table.get(GrammarVariable.TERM).put(LexicalUnit.LEFT_PARENTHESIS, new Integer[]{17});
		table.get(GrammarVariable.TERM).put(LexicalUnit.MINUS, new Integer[]{17});

		// Term 2 :
		table.put(GrammarVariable.TERM2 ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.TERM2).put(LexicalUnit.MINUS, new Integer[]{19});
		table.get(GrammarVariable.TERM2).put(LexicalUnit.PLUS, new Integer[]{19});
		table.get(GrammarVariable.TERM2).put(LexicalUnit.TIMES, new Integer[]{18});
		table.get(GrammarVariable.TERM2).put(LexicalUnit.DIVIDE, new Integer[]{18});

		// Factor :
		table.put(GrammarVariable.FACTOR ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.FACTOR).put(LexicalUnit.VARNAME, new Integer[]{22});
		table.get(GrammarVariable.FACTOR).put(LexicalUnit.NUMBER, new Integer[]{23});
		table.get(GrammarVariable.FACTOR).put(LexicalUnit.LEFT_PARENTHESIS, new Integer[]{20});
		table.get(GrammarVariable.FACTOR).put(LexicalUnit.MINUS, new Integer[]{21});

		// TermOp : 
		table.put(GrammarVariable.TERMOP ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.TERMOP).put(LexicalUnit.MINUS, new Integer[]{25});
		table.get(GrammarVariable.TERMOP).put(LexicalUnit.PLUS, new Integer[]{24});

		// FactorOp:
		table.put(GrammarVariable.FACTOROP ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.FACTOROP).put(LexicalUnit.TIMES, new Integer[]{26});
		table.get(GrammarVariable.FACTOROP).put(LexicalUnit.DIVIDE, new Integer[]{27});

		// If : 
		table.put(GrammarVariable.IF ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.IF).put(LexicalUnit.IF, new Integer[]{28});

		// EndIf : 
		table.put(GrammarVariable.ENDIF ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.ENDIF).put(LexicalUnit.FI, new Integer[]{29});
		table.get(GrammarVariable.ENDIF).put(LexicalUnit.ELSE, new Integer[]{30});

		// Cond : 
		table.put(GrammarVariable.COND ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.COND).put(LexicalUnit.VARNAME, new Integer[]{31});
		table.get(GrammarVariable.COND).put(LexicalUnit.NUMBER, new Integer[]{31});
		table.get(GrammarVariable.COND).put(LexicalUnit.LEFT_PARENTHESIS, new Integer[]{31});
		table.get(GrammarVariable.COND).put(LexicalUnit.MINUS, new Integer[]{31});
		table.get(GrammarVariable.COND).put(LexicalUnit.NOT, new Integer[]{31});

		// Cond 2 : 
		table.put(GrammarVariable.COND2 ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.COND2).put(LexicalUnit.THEN, new Integer[]{33});
		table.get(GrammarVariable.COND2).put(LexicalUnit.OR, new Integer[]{32});
		table.get(GrammarVariable.COND2).put(LexicalUnit.DO, new Integer[]{33});

		// AndCond : 
		table.put(GrammarVariable.ANDCOND ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.ANDCOND).put(LexicalUnit.VARNAME, new Integer[]{34});
		table.get(GrammarVariable.ANDCOND).put(LexicalUnit.NUMBER, new Integer[]{34});
		table.get(GrammarVariable.ANDCOND).put(LexicalUnit.LEFT_PARENTHESIS, new Integer[]{34});
		table.get(GrammarVariable.ANDCOND).put(LexicalUnit.MINUS, new Integer[]{34});
		table.get(GrammarVariable.ANDCOND).put(LexicalUnit.NOT, new Integer[]{34});

		// AndCond2 : 
		table.put(GrammarVariable.ANDCOND2 ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.ANDCOND2).put(LexicalUnit.THEN, new Integer[]{36});
		table.get(GrammarVariable.ANDCOND2).put(LexicalUnit.AND, new Integer[]{35});
		table.get(GrammarVariable.ANDCOND2).put(LexicalUnit.OR, new Integer[]{36});
		table.get(GrammarVariable.ANDCOND2).put(LexicalUnit.DO, new Integer[]{36});

		// CondTerm : 
		table.put(GrammarVariable.CONDTERM ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.CONDTERM).put(LexicalUnit.VARNAME, new Integer[]{37});
		table.get(GrammarVariable.CONDTERM).put(LexicalUnit.NUMBER, new Integer[]{37});
		table.get(GrammarVariable.CONDTERM).put(LexicalUnit.LEFT_PARENTHESIS, new Integer[]{37});
		table.get(GrammarVariable.CONDTERM).put(LexicalUnit.MINUS, new Integer[]{37});
		table.get(GrammarVariable.CONDTERM).put(LexicalUnit.NOT, new Integer[]{38});

		// SimpleCond : 
		table.put(GrammarVariable.SIMPLECOND ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.SIMPLECOND).put(LexicalUnit.VARNAME, new Integer[]{39});
		table.get(GrammarVariable.SIMPLECOND).put(LexicalUnit.NUMBER, new Integer[]{39});
		table.get(GrammarVariable.SIMPLECOND).put(LexicalUnit.LEFT_PARENTHESIS, new Integer[]{39});
		table.get(GrammarVariable.SIMPLECOND).put(LexicalUnit.MINUS, new Integer[]{39});

		// Comp :
		table.put(GrammarVariable.COMP ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.COMP).put(LexicalUnit.EQUAL, new Integer[]{40});
		table.get(GrammarVariable.COMP).put(LexicalUnit.GREATER_EQUAL, new Integer[]{41});
		table.get(GrammarVariable.COMP).put(LexicalUnit.GREATER, new Integer[]{42});
		table.get(GrammarVariable.COMP).put(LexicalUnit.SMALLER_EQUAL, new Integer[]{43});
		table.get(GrammarVariable.COMP).put(LexicalUnit.SMALLER, new Integer[]{44});
		table.get(GrammarVariable.COMP).put(LexicalUnit.DIFFERENT, new Integer[]{45});

		// While :
		table.put(GrammarVariable.WHILE ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.WHILE).put(LexicalUnit.WHILE, new Integer[]{46});

		// For :
		table.put(GrammarVariable.FOR ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.FOR).put(LexicalUnit.FOR, new Integer[]{47});

		// Print :
		table.put(GrammarVariable.PRINT ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.PRINT).put(LexicalUnit.PRINT, new Integer[]{48});

		// Read :
		table.put(GrammarVariable.READ ,new HashMap<LexicalUnit, Integer[]>());
		table.get(GrammarVariable.READ).put(LexicalUnit.READ, new Integer[]{49});

	}


	public Integer[] getRules(GrammarVariable var, LexicalUnit lex) {
		if ( !table.get(var).containsKey(lex) ) {
			return new Integer[]{};
		} else {
			return table.get(var).get(lex);
		}
	}
}