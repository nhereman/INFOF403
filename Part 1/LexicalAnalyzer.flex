import java.util.regex.PatternSyntaxException;

%%// Options of the scanner

%class LexicalAnalyzer	//Name
%unicode						//Use unicode
%line							//Use line counter (yyline variable)
%column						//Use character counter by line (yycolumn variable)
%function nextToken
%type Symbol
%yylexthrow PatternSyntaxException

%eofval{
	return new Symbol(LexicalUnit.END_OF_STREAM,yyline, yycolumn);
%eofval}

//Extended Regular Expressions

VarName		=	[A-Za-z][A-Za-z0-9]*
Number		=	[0-9]*
Comment		=	"co"("\n" | " ")

//Declare exclusive states

%xstate YYINITIAL, COMMENT_STATE

%%//Identification of tokens

<YYINITIAL> {
	{Comment}		{yybegin(COMMENT_STATE);}

	"begin" 	{return new Symbol(LexicalUnit.BEG,yyline,yycolumn,new String(yytext()));}
	"end" 		{return new Symbol(LexicalUnit.END,yyline,yycolumn,new String(yytext()));}

	";" 		{return new Symbol(LexicalUnit.SEMICOLON,yyline,yycolumn,new String(yytext()));}

	":=" 		{return new Symbol(LexicalUnit.ASSIGN,yyline,yycolumn,new String(yytext()));}
	"(" 		{return new Symbol(LexicalUnit.LEFT_PARENTHESIS,yyline,yycolumn,new String(yytext()));}
	")" 		{return new Symbol(LexicalUnit.RIGHT_PARENTHESIS,yyline,yycolumn,new String(yytext()));}
	"-" 		{return new Symbol(LexicalUnit.MINUS,yyline,yycolumn,new String(yytext()));}
	"+" 		{return new Symbol(LexicalUnit.PLUS,yyline,yycolumn,new String(yytext()));}
	"*" 		{return new Symbol(LexicalUnit.TIMES,yyline,yycolumn,new String(yytext()));}
	"/" 		{return new Symbol(LexicalUnit.DIVIDE,yyline,yycolumn,new String(yytext()));}

	"if" 		{return new Symbol(LexicalUnit.IF,yyline,yycolumn,new String(yytext()));}
	"then" 		{return new Symbol(LexicalUnit.THEN,yyline,yycolumn,new String(yytext()));}
	"fi" 		{return new Symbol(LexicalUnit.FI,yyline,yycolumn,new String(yytext()));}
	"else" 		{return new Symbol(LexicalUnit.ELSE,yyline,yycolumn,new String(yytext()));}

	"not" 		{return new Symbol(LexicalUnit.NOT,yyline,yycolumn,new String(yytext()));}
	"and" 		{return new Symbol(LexicalUnit.AND,yyline,yycolumn,new String(yytext()));}
	"or" 		{return new Symbol(LexicalUnit.OR,yyline,yycolumn,new String(yytext()));}
	"=" 		{return new Symbol(LexicalUnit.EQUAL,yyline,yycolumn,new String(yytext()));}
	">=" 		{return new Symbol(LexicalUnit.GREATER_EQUAL,yyline,yycolumn,new String(yytext()));}
	">" 		{return new Symbol(LexicalUnit.GREATER,yyline,yycolumn,new String(yytext()));}
	"<=" 		{return new Symbol(LexicalUnit.SMALLER_EQUAL,yyline,yycolumn,new String(yytext()));}
	"<" 		{return new Symbol(LexicalUnit.SMALLER,yyline,yycolumn,new String(yytext()));}
	"/=" 		{return new Symbol(LexicalUnit.DIFFERENT,yyline,yycolumn,new String(yytext()));}

	"while" 	{return new Symbol(LexicalUnit.WHILE,yyline,yycolumn,new String(yytext()));}
	"do" 		{return new Symbol(LexicalUnit.DO,yyline,yycolumn,new String(yytext()));}
	"od" 		{return new Symbol(LexicalUnit.OD,yyline,yycolumn,new String(yytext()));}
	"for" 		{return new Symbol(LexicalUnit.FOR,yyline,yycolumn,new String(yytext()));}
	"from" 		{return new Symbol(LexicalUnit.FROM,yyline,yycolumn,new String(yytext()));}
	"by" 		{return new Symbol(LexicalUnit.BY,yyline,yycolumn,new String(yytext()));}
	"to" 		{return new Symbol(LexicalUnit.TO,yyline,yycolumn,new String(yytext()));}
	"print" 	{return new Symbol(LexicalUnit.PRINT,yyline,yycolumn,new String(yytext()));}
	"read" 		{return new Symbol(LexicalUnit.READ,yyline,yycolumn,new String(yytext()));}

	{VarName} 	{return new Symbol(LexicalUnit.VARNAME,yyline,yycolumn,new String(yytext()));}
	{Number}	{return new Symbol(LexicalUnit.NUMBER,yyline,yycolumn,new String(yytext()));}
	
	" "			{return null;}
	"\n"		{return null;}

	[^]			{throw new PatternSyntaxException("Pattern error at line " + yyline + " " + yytext(),"", 0);}
}

<COMMENT_STATE> {
	{Comment}		{yybegin(YYINITIAL);}
	[^]			{ return null; }
}

