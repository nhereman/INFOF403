import java.util.List;
import java.util.LinkedList;

public class LLVMWriter {

	private List<Symbol> _symbols;
	private List<Integer> _parsingRules;
	private int _count = 1;
	private int _sym = 0;
	private int _rule = 0;
	private List<String> _lines;
	private List<String> _vars;

	public LLVMWriter(List<Symbol> symbols, List<Integer> parsingRules) {
		_symbols = new LinkedList<Symbol>(symbols);
		_parsingRules = new LinkedList<Integer>(parsingRules);
		_lines = new LinkedList<String>();
		_vars = new LinkedList<String>();

		executeRules();
	}

	private void executeRules() {
		if (_parsingRules.get(_rule) == 1) { rule1(); }
	}

	/*
	*		RULES methods
	*
	*/

	private void rule1() {
		// <Program> 	->	begin <Code> end
		++_rule;
		++_sym; // begin

		_lines.add("define i32 @main() {");

		// <Code>
		if (_parsingRules.get(_rule) == 2) { ++_rule; } // -> Epsilon
		else if(_parsingRules.get(_rule) == 3) { rule3(); }

		++_sym; // end
		++_rule;
		_lines.add("\tret i32 0");
		_lines.add("}");
	}

	private void rule3() {
		// <Code> 	->	<InstList>
		++_rule;

		// <InstList>
		if(_parsingRules.get(_rule) == 4) { rule4(); }
	}

	private void rule4() {
		// <InstList>	->	<Instruction> <NextInst>
		++_rule;

		// <Instruction>
		if ( _parsingRules.get(_rule) == 11 ) { rule11(); }
		else if (_parsingRules.get(_rule) == 12) { rule12(); }
		// TODO: Other <Instruction> rules

		// <NextInst>
		if (_parsingRules.get(_rule) == 5 ) { ++_rule; } // -> Epsilon
		else if ( _parsingRules.get(_rule) == 6) { rule6(); }
	}

	private void rule6() {
		// <NextInst>	->	; <InstList>
		++_rule;

		++_sym; // ;

		// <InstList>
		if ( _parsingRules.get(_rule) == 4 ) { rule4(); }
	}

	private void rule11() {
		// <Instruction> 	->	<Print>
		++_rule;

		// <Print>
		if (_parsingRules.get(_rule) == 48) { rule48(); }
	}

	private void rule12() {
		// <Instruction>	-> 	<Read>
		++_rule;

		// <Read>
		if (_parsingRules.get(_rule) == 49) { rule49(); }
	}

	private void rule48() {
		// <Print>	->	print([VarName])
		++_rule;

		++_sym; // print
		++_sym; // (
		String var = _symbols.get(_sym).getValue().toString();
		++_sym; // [VarName]
		++_sym; // )
		
		_lines.add("\t; print var");
		_lines.add("\t%"+(new Integer(_count)).toString()+" = load i32* %"+var);
		++_count;
		_lines.add("\tcall void @putInt(i32 %"+(new Integer(_count-1)).toString()+")");
	}

	private void rule49() {
		// <Read>	->	read([VarName])
		++_rule;

		++_sym; // read
		++_sym; // (

		String var = _symbols.get(_sym).getValue().toString();
		allocateVar(var);

		++_sym; // [VarName]
		++_sym; // )

		_lines.add("\t; read var");
		_lines.add("\t%"+(new Integer(_count)).toString()+" = call i32 @readInt()");
		++_count;
		_lines.add("\tstore i32 %"+(new Integer(_count-1)).toString()+", i32* %"+var);
	}




	/*
	*		Manage vars methods
	*/
	private void allocateVar(String var) {
		if (!varExists(var)) {
			_vars.add(var);
			_lines.add("\t%"+var+" = alloca i32");
		}
	}

	private boolean varExists(String var) {
		return _vars.contains(var);
	}



	/*
	*		Writing LLVM methods
	*/

	public void write() {
		for (int i = 0; i < _lines.size(); ++i) {
			System.out.println(_lines.get(i));
		}
	}
}