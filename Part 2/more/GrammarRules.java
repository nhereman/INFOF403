import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

public class GrammarRules {

	private List< List<Enum> > rules;

	public GrammarRules() {
		rules = new LinkedList< List<Enum> >();

		rules.add(Arrays.asList(new Enum[]{LexicalUnit.BEG, GrammarVariable.CODE, LexicalUnit.END})); // Rules 1
		rules.add(new LinkedList<Enum>()); 	// Rules 2 // EPSILON
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.INSTLIST})); // Rules 3
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.INSTRUCTION, GrammarVariable.NEXTINST})); // Rules 4
		rules.add(new LinkedList<Enum>()); // Rules 5 // EPSILON
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.SEMICOLON, GrammarVariable.INSTLIST})); // Rules 6
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.ASSIGN})); // Rules 7
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.IF})); // Rules 8
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.WHILE})); // Rules 9
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.FOR})); // Rules 10
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.PRINT})); // Rules 11
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.READ})); // Rules 12
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.VARNAME, LexicalUnit.ASSIGN, GrammarVariable.EXPRARITH})); // Rules 13
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.TERM,GrammarVariable.EXPRARITH2})); // Rules 14
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.TERMOP, GrammarVariable.TERM,GrammarVariable.EXPRARITH2})); // Rules 15
		rules.add(new LinkedList<Enum>()); // Rules 16 // EPSILON
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.FACTOR, GrammarVariable.TERM2})); // Rules 17
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.FACTOROP, GrammarVariable.FACTOR, GrammarVariable.TERM2})); // Rules 18
		rules.add(new LinkedList<Enum>()); // Rules 19 // EPSILON
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.LEFT_PARENTHESIS, GrammarVariable.EXPRARITH, LexicalUnit.RIGHT_PARENTHESIS})); // Rules 20
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.MINUS, GrammarVariable.EXPRARITH})); // Rules 21
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.VARNAME})); // Rules 22
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.NUMBER})); // Rules 23
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.PLUS})); // Rules 24
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.MINUS})); // Rules 25
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.TIMES})); // Rules 26
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.DIVIDE})); // Rules 27
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.IF, GrammarVariable.COND, LexicalUnit.THEN, GrammarVariable.CODE, GrammarVariable.ENDIF})); // Rules 28
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.FI})); // Rules 29
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.ELSE, GrammarVariable.CODE, LexicalUnit.FI})); // Rules 30
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.ANDCOND, GrammarVariable.COND2})); // Rules 31
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.OR, GrammarVariable.ANDCOND, GrammarVariable.COND2})); // Rules 32
		rules.add(new LinkedList<Enum>()); // Rules 33 // EPSILON
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.CONDTERM, GrammarVariable.ANDCOND2})); // Rules 34
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.AND, GrammarVariable.CONDTERM ,GrammarVariable.ANDCOND2})); // Rules 35
		rules.add(new LinkedList<Enum>()); // Rules 36 // EPSILON
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.SIMPLECOND})); // Rules 37
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.NOT, GrammarVariable.SIMPLECOND})); // Rules 38
		rules.add(Arrays.asList(new Enum[]{GrammarVariable.EXPRARITH, GrammarVariable.COMP, GrammarVariable.EXPRARITH})); // Rules 39
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.EQUAL})); // Rules 40
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.GREATER_EQUAL})); // Rules 41
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.GREATER})); // Rules 42
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.SMALLER_EQUAL})); // Rules 43
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.SMALLER})); // Rules 44
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.DIFFERENT})); // Rules 45
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.WHILE, GrammarVariable.COND, LexicalUnit.DO, GrammarVariable.CODE, LexicalUnit.OD})); // Rules 46
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.FOR, LexicalUnit.VARNAME, LexicalUnit.FROM, GrammarVariable.EXPRARITH, LexicalUnit.BY,
											GrammarVariable.EXPRARITH, LexicalUnit.TO, GrammarVariable.EXPRARITH, LexicalUnit.DO,
											GrammarVariable.CODE, LexicalUnit.OD})); // Rules 47
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.PRINT, LexicalUnit.LEFT_PARENTHESIS, LexicalUnit.VARNAME, 
											LexicalUnit.RIGHT_PARENTHESIS})); // Rules 48
		rules.add(Arrays.asList(new Enum[]{LexicalUnit.READ, LexicalUnit.LEFT_PARENTHESIS, LexicalUnit.VARNAME, 
											LexicalUnit.RIGHT_PARENTHESIS})); // Rules 49
	}

	public List<Enum> getRule(int rule) {
		return rules.get(rule-1);
	}
}