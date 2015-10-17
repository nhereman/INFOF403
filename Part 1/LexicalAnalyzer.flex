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

// Put your regular expressions here...
VarName		=	[A-Za-z][A-Za-z0-9]*
Number		=	[1-9]+[0-9]*

//Declare exclusive states

%%//Identification of tokens

// Put the rules of your parser here...

