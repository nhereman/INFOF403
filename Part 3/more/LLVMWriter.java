import java.util.List;
import java.util.LinkedList;

public class LLVMWriter {

	private List<Symbol> _symbols;
	private List<Integer> _parsingRules;
	private List<String> _lines;
	private List<String> _vars;
	private int _count = 1;
	private int _sym = 0;
	private int _rule = 0;
	private int _ifCount = 0;
	private int _whileCount = 0;
	private int _forCount = 0;
	private boolean _hasRead = false;
	private boolean _hasPrint = false;

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
		if (_parsingRules.get(_rule) == 7) { rule7(); }
		else if (_parsingRules.get(_rule) == 8) { rule8(); }
		else if (_parsingRules.get(_rule) == 9) { rule9(); }
		else if (_parsingRules.get(_rule) == 10) { rule10(); }
		else if ( _parsingRules.get(_rule) == 11 ) { rule11(); }
		else if (_parsingRules.get(_rule) == 12) { rule12(); }

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

	private void rule7() {
		// <Instruction>	->	<Assign>
		++_rule;

		// <Assign>
		if ( _parsingRules.get(_rule) == 13 ) { rule13(); }
	}

	private void rule8() {
		// <Instruction>	-> <If>
		++_rule;

		// <If>
		if ( _parsingRules.get(_rule) == 28 ) { rule28(); }
	}

	private void rule9() {
		// <Instruction>	-> <While>
		++_rule;

		// <While>
		if (_parsingRules.get(_rule) == 46 ) { rule46(); }
	}

	private void rule10() {
		// <Instruction>	-> <For>
		++_rule;

		// <For>
		if (_parsingRules.get(_rule) == 47 ) { rule47(); }

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

	private void rule13() {
		// <Assign>		-> [VarName] := <ExprArith>
		++_rule;

		String var = _symbols.get(_sym).getValue().toString();
		allocateVar(var);
		++_sym; // [VarName]
		++_sym; // :=

		// <ExprArith>
		if (_parsingRules.get(_rule) == 14) { rule14(); }
		String res = (new Integer(_count-1)).toString();
		_lines.add("\tstore i32 %"+res+", i32* %"+var);

	}

	private void rule14() {
		// <ExprArith>	->	<Term> <ExprArith2>
		++_rule;

		// <Term>
		if (_parsingRules.get(_rule) == 17) { rule17(); }

		// <ExprArith2>
		if (_parsingRules.get(_rule) == 15) { rule15(); }
		else if (_parsingRules.get(_rule) == 16) { ++_rule;} // -> Epsilon

	}

	private void rule15() {
		// <ExprArith2> -> <TermOp> <Term> <ExprArith2>
		++_rule;

		String left = (new Integer(_count-1)).toString();

		// <TermOp>
		String op = new String();
		if (_parsingRules.get(_rule) == 24) {
			rule24();
			op = "add";
		} else if (_parsingRules.get(_rule) == 25) {
			rule25();
			op = "sub";
		}

		// <Term>
		if (_parsingRules.get(_rule) == 17) { rule17(); }

		String right = (new Integer(_count-1)).toString();
		_lines.add("\t%"+(new Integer(_count)).toString()+" = "+op+" i32 %"+left+", %"+right);
		++_count;

		// <ExprArith2>
		if (_parsingRules.get(_rule) == 15) { rule15(); }
		else if (_parsingRules.get(_rule) == 16) { ++_rule;} // -> Epsilon


	}

	private void rule17() {
		// <Term>	->	<Factor> <Term2>
		++_rule;

		// <Factor>
		if (_parsingRules.get(_rule) == 20) { rule20(); }
		else if (_parsingRules.get(_rule) == 21) { rule21(); }
		else if (_parsingRules.get(_rule) == 22) { rule22(); }
		else if (_parsingRules.get(_rule) == 23) { rule23(); }

		// <Term2>
		if (_parsingRules.get(_rule) == 18) { rule18(); }
		else if (_parsingRules.get(_rule) == 19) { ++_rule; } // -> Epsilon
	}

	private void rule18() {
		// <Term2>	->	<FactorOp>	<Factor> <Term2>
		++_rule;

		String left = (new Integer(_count-1)).toString();

		// <FactorOp>
		String op = new String();
		if (_parsingRules.get(_rule) == 26) {
			rule26();
			op = "mul";
		} else if (_parsingRules.get(_rule) == 27) {
			rule27();
			op = "sdiv";
		}

		// <Factor>
		if (_parsingRules.get(_rule) == 20) { rule20(); }
		else if (_parsingRules.get(_rule) == 21) { rule21(); }
		else if (_parsingRules.get(_rule) == 22) { rule22(); }
		else if (_parsingRules.get(_rule) == 23) { rule23(); }

		String right = (new Integer(_count-1)).toString();
		_lines.add("\t%"+(new Integer(_count)).toString()+" = "+op+" i32 %"+left+", %"+right);
		++_count;

		// <Term2>
		if (_parsingRules.get(_rule) == 18) { rule18(); }
		else if (_parsingRules.get(_rule) == 19) { ++_rule; } // -> Epsilon

	}

	private void rule20() {
		// <Factor>		->	(<ExprArith>)
		++_rule;

		++_sym; // (

		// <ExprArith>
		if (_parsingRules.get(_rule) == 14) { rule14(); }

		++_sym; // )
	}

	private void rule21() {
		// <Factor>	->	- <Factor>
		++_rule;

		++_sym; // -

		// <Factor>
		if (_parsingRules.get(_rule) == 20) { rule20(); }
		else if (_parsingRules.get(_rule) == 21) { rule21(); }
		else if (_parsingRules.get(_rule) == 22) { rule22(); }
		else if (_parsingRules.get(_rule) == 23) { rule23(); }

		_lines.add("\t%"+(new Integer(_count)).toString()+" = mul i32 %"+(new Integer(_count-1)).toString()+", -1");
		++_count;
	}

	private void rule22() {
		// <Factor>	->	[VarName]
		++_rule;

		String var = _symbols.get(_sym).getValue().toString();
		++_sym; // [VarName]

		_lines.add("\t%"+(new Integer(_count)).toString()+" = load i32* %"+var);
		++_count;

	}

	private void rule23() {
		// <Factor>	->	[Number]
		++_rule;

		String number = _symbols.get(_sym).getValue().toString();
		++_sym; // [Number]

		// TODO: Find another way to assign
		_lines.add("\t%"+(new Integer(_count)).toString()+" = add i32 0, "+number+";ASSIGN");
		++_count;
	}

	private void rule24() {
		// <TermOp>	->	+
		++_rule;
		++_sym; // +
	}

	private void rule25() {
		// <TermOp>	->	-
		++_rule;
		++_sym; // -
	}

	private void rule26() {
		// <FactorOp>	->	*
		++_rule;
		++_sym; // *
	}

	private void rule27() {
		// <FactorOp>	->	/
		++_rule;
		++_sym; // /
	}

	private void rule28() {
		// <If>	-> if <Cond> then <Code> <EndIf>
		++_rule;

		++_sym; // if
		_lines.add("\t; if statement");
		String ifCount = (new Integer(_ifCount)).toString();
		++_ifCount;

		// <Cond>
		if (_parsingRules.get(_rule) == 31) { rule31(); }
		String cond = (new Integer(_count-1)).toString();

		_lines.add("\tbr i1 %"+cond+", label  %iftrue"+ifCount+", label %iffalse"+ifCount);
		_lines.add("iftrue"+ifCount+":");

		++_sym; // then

		// <Code>
		if (_parsingRules.get(_rule) == 2) { ++_rule; } // -> Epsilon
		else if(_parsingRules.get(_rule) == 3) { rule3(); }

		// <Endif>
		if ( _parsingRules.get(_rule) == 29) { rule29(ifCount); }
		else if ( _parsingRules.get(_rule) == 30) { rule30(ifCount); }

	}

	private void rule29(String ifCount) {
		// <EndIf>	-> fi
		++_rule;

		++_sym; // fi

		_lines.add("\tbr label %endif"+ifCount);
		_lines.add("iffalse"+ifCount+":");
		_lines.add("\tbr label %endif"+ifCount);
		_lines.add("endif"+ifCount+":");
	}

	private void rule30(String ifCount) {
		// <EndIf>	-> else <Code> fi
		++_rule;

		++_sym; // else

		_lines.add("\tbr label %endif"+ifCount);
		_lines.add("iffalse"+ifCount+":");

		// <Code>
		if (_parsingRules.get(_rule) == 2) { ++_rule; } // -> Epsilon
		else if(_parsingRules.get(_rule) == 3) { rule3(); }

		++_sym; // fi
		_lines.add("\tbr label %endif"+ifCount);
		_lines.add("endif"+ifCount+":");
	}

	private void rule31() {
		// <Cond>	->	<AndCond>	<Cond2>
		++_rule;

		// <AndCond>
		if (_parsingRules.get(_rule) == 34) { rule34(); }

		// <Cond2>
		if (_parsingRules.get(_rule) == 32) { rule32(); }
		else if (_parsingRules.get(_rule) == 33) { ++_rule; } // -> Epsilon
	}

	private void rule32() {
		// <Cond2>	->	or <AndCond> <Cond2>
		++_rule;

		++_sym; // or
		String left = (new Integer(_count-1)).toString();

		// <AndCond>
		if (_parsingRules.get(_rule) == 34) { rule34(); }

		String right = (new Integer(_count-1)).toString();
		_lines.add("\t%"+(new Integer(_count)).toString()+" = or i1 %"+left+", %"+right);
		++_count;

		// <Cond2>
		if (_parsingRules.get(_rule) == 32) { rule32(); }
		else if (_parsingRules.get(_rule) == 33) { ++_rule; } // -> Epsilon

	}

	private void rule34() {
		// <AndCond>	->	<CondTerm>	<AndCond2>
		++_rule;

		// <CondTerm>
		if (_parsingRules.get(_rule) == 37) { rule37(); }
		else if (_parsingRules.get(_rule) == 38) { rule38(); }

		// <AndCond2>
		if (_parsingRules.get(_rule) == 35) { rule35(); }
		else if (_parsingRules.get(_rule) == 36) { ++_rule; } // -> Epsilon
	}

	private void rule35() {
		// <AndCond2>	->	and <CondTerm> <AndCond2>
		++_rule;

		++_sym; // and
		String left = (new Integer(_count-1)).toString();

		// <CondTerm>
		if (_parsingRules.get(_rule) == 37) { rule37(); }
		else if (_parsingRules.get(_rule) == 38) { rule38(); }

		String right = (new Integer(_count-1)).toString();
		_lines.add("\t%"+(new Integer(_count)).toString()+" = and i1 %"+left+", %"+right);
		++_count;

		// <AndCond2>
		if (_parsingRules.get(_rule) == 35) { rule35(); }
		else if (_parsingRules.get(_rule) == 36) { ++_rule; } // -> Epsilon
	}

	private void rule37() {
		// <CondTerm>	->	<SimpleCond>
		++_rule;

		// <SimpleCond>
		if (_parsingRules.get(_rule) == 39) { rule39(); }
	}

	private void rule38() {
		// <CondTerm>	->	not <SimpleCond>
		++_rule;

		++_sym; // not

		// <SimpleCond>
		if (_parsingRules.get(_rule) == 39) { rule39(); }
		String cond = (new Integer(_count-1)).toString();

		// TODO: Find the way to do a NOT and not use a XOR
		_lines.add("\t%"+(new Integer(_count)).toString()+" = xor i1 %"+cond+", 1;NOT");
		++_count;
	}

	private void rule39() {
		// <SimpleCond>		-> 	<ExprArith> <Comp>	<ExprArith>
		++_rule;

		// <ExprArith>
		if (_parsingRules.get(_rule) == 14) { rule14(); }
		String left = (new Integer(_count-1)).toString();

		// <Comp>
		String comp = new String();
		if (_parsingRules.get(_rule) == 40) { comp = "eq"; }
		else if (_parsingRules.get(_rule) == 41) { comp = "sge"; }
		else if (_parsingRules.get(_rule) == 42) { comp = "sgt"; }
		else if (_parsingRules.get(_rule) == 43) { comp = "sle"; }
		else if (_parsingRules.get(_rule) == 44) { comp = "slt"; }
		else if (_parsingRules.get(_rule) == 45) { comp = "ne"; }
		rule40To45();

		// <ExprArith>
		if (_parsingRules.get(_rule) == 14) { rule14(); }
		String right = (new Integer(_count-1)).toString();

		_lines.add("\t%"+(new Integer(_count)).toString()+" = icmp "+comp+" i32 %"+left+", %"+right);
		++_count;


	}

	private void rule40To45() {
		// <Comp> -> comp
		++_rule;
		++_sym; // comp
	}

	private void rule46() {
		// <While>	->	while <Cond> do <Code> od
		++_rule;

		++_sym; // while
		_lines.add("\t; while statement");
		String whileCount = (new Integer(_whileCount)).toString();
		++_whileCount;

		_lines.add("\tbr label %condwhile"+whileCount);
		_lines.add("condwhile"+whileCount+":");

		// <Cond>
		if (_parsingRules.get(_rule) == 31) { rule31(); }
		String cond = (new Integer(_count-1)).toString();

		_lines.add("\tbr i1 %"+cond+", label %codewhile"+whileCount+", label %endwhile"+whileCount);
		_lines.add("codewhile"+whileCount+":");

		++_sym; // do

		// <Code>
		if (_parsingRules.get(_rule) == 2) { ++_rule; } // -> Epsilon
		else if(_parsingRules.get(_rule) == 3) { rule3(); }

		++_sym; // od

		_lines.add("\tbr label %condwhile"+whileCount);
		_lines.add("endwhile"+whileCount+":");

	}

	private void rule47() {
		// <For>	->	for [VarName] from <ExprArith> by <ExprArith> to <ExprArith> do <Code> od
		++_rule;

		_lines.add("\t; for statement");
		String forCount = (new Integer(_forCount)).toString();
		++_forCount;

		++_sym; // for
		String var = _symbols.get(_sym).getValue().toString();
		++_sym; // [VarName]
		++_sym; // from

		// <ExprArith>
		if (_parsingRules.get(_rule) == 14) { rule14(); }
		String from = (new Integer(_count-1)).toString();
		++_sym; // by

		// <ExprArith>
		if (_parsingRules.get(_rule) == 14) { rule14(); }
		String by = (new Integer(_count-1)).toString();
		++_sym; // to

		// <ExprArith>
		if (_parsingRules.get(_rule) == 14) { rule14(); }
		String to = (new Integer(_count-1)).toString();
		++_sym; // do

		// Init
		allocateVar(var);
		_lines.add("\tstore i32 %"+from+", i32* %"+var);
		_lines.add("\tbr label %condfor"+forCount);


		// Condition
		_lines.add("condfor"+forCount+":");
		_lines.add("\t%"+(new Integer(_count)).toString()+" = load i32* %"+var);
		String loadvar = (new Integer(_count)).toString();
		++_count;
		_lines.add("\t%"+(new Integer(_count)).toString()+" = icmp slt i32 %"+loadvar+", %"+to);
		String condRes = (new Integer(_count)).toString();
		++_count;
		_lines.add("\tbr i1 %"+condRes+", label %codefor"+forCount+", label %endfor"+forCount);

		// Code
		_lines.add("codefor"+forCount+":");

		// <Code>
		if (_parsingRules.get(_rule) == 2) { ++_rule; } // -> Epsilon
		else if(_parsingRules.get(_rule) == 3) { rule3(); }

		++_sym; // od

		// increment
		_lines.add("\t%"+(new Integer(_count)).toString()+" = load i32* %"+var);
		loadvar = (new Integer(_count)).toString();
		++_count;
		_lines.add("\t%"+(new Integer(_count)).toString()+" = add i32 %"+loadvar+", %"+by);
		String addRes = (new Integer(_count)).toString();
		++_count;
		_lines.add("\tstore i32 %"+addRes+", i32* %"+var);
		_lines.add("\tbr label %condfor"+forCount);
		_lines.add("endfor"+forCount+":");
	}

	private void rule48() {
		// <Print>	->	print([VarName])
		++_rule;

		_hasPrint = true;

		++_sym; // print
		++_sym; // (
		String var = _symbols.get(_sym).getValue().toString();
		++_sym; // [VarName]
		++_sym; // )
		
		_lines.add("\t; print var");
		_lines.add("\t%"+(new Integer(_count)).toString()+" = load i32* %"+var);
		++_count;
		_lines.add("\tcall void @putInt(i32 %"+(new Integer(_count-1)).toString()+")");
		_lines.add("\tcall i32 @putchar(i32 10)"); // print end of line
		++_count;
	}

	private void rule49() {
		// <Read>	->	read([VarName])
		++_rule;

		_hasRead = true;

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
		printLlvmHeader();
		if(_hasRead) { printReadIntFunc(); }
		if(_hasPrint) { printPutIntFunc(); }
		for (int i = 0; i < _lines.size(); ++i) {
			System.out.println(_lines.get(i));
		}
	}

	private void printLlvmHeader() {
		System.out.println("; External function declaration");
		if (_hasRead) { System.out.println("declare i32 @getchar()"); }
		if (_hasPrint) { System.out.println("declare i32 @putchar(i32)"); }
		System.out.println(""); // Skip a line
	}

	private void printReadIntFunc() {
		System.out.println("; Defining a function wich read integer");
		System.out.println("define i32 @readInt() {");
		System.out.println("entry:");
		System.out.println("	%res = alloca i32");
		System.out.println("	%digit = alloca i32");
		System.out.println("	%mult = alloca i32");
		System.out.println("	store i32 0, i32* %res");
		System.out.println("	store i32 1, i32* %mult");
		System.out.println("	br label %firstread");
		System.out.println("firstread:");
		System.out.println("	%a = call i32 @getchar()");
		System.out.println("	%b = icmp eq i32 %a, 45");
		System.out.println("	br i1 %b, label %firstminus, label %firstdigit");
		System.out.println("firstminus:");
		System.out.println("	store i32 -1, i32* %mult");
		System.out.println("	br label %read");
		System.out.println("firstdigit:");
		System.out.println("	%c = sub i32 %a, 48");
		System.out.println("	store i32 %c, i32* %digit");
		System.out.println("	%d = icmp ne i32 %a, 10");
		System.out.println("	br i1 %d, label %save, label %exit");
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

	private void printPutIntFunc() {
		System.out.println("; Defining a function wich print integer");
		System.out.println("define void @putInt(i32 %a) {");
		System.out.println("entry:");
		System.out.println("	%size = alloca i32");
		System.out.println("	%int = alloca i32");
		System.out.println("	store i32 10, i32* %size");
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