package edu.odu.cs.cs350;
import java.util.*;

public class Token {	
	// ---- Constructors
	
	public Token(int line, int col) {
		this.line = line;
		this.column = col;
	}
	
	public Token(String lexeme, int line, int col) {
		this.lexeme = lexeme;
		this.determineTokenAttributes(lexeme);
		this.line = line;
		this.column = col;
		this.length = lexeme.length();
	}
	
	// ----- Determine Token Attributes
	
	// Still need to spilt up preprocessors
	public void determineTokenAttributes(String restOfLine) {
			int curr = 0;
			
			
			if(Character.isLetter(restOfLine.charAt(curr))) {
				String lexeme = "";
				for(int i = 0; i < restOfLine.length(); i++) {
					if(Character.isLetterOrDigit(restOfLine.charAt(i)) || restOfLine.charAt(i) == ':' || restOfLine.charAt(i) == '_') {
						// if letter or digit, keep adding to the lexeme.
						// or if :, ie std::cout 
						lexeme += restOfLine.charAt(i);
					} else if(restOfLine.charAt(i) == ':') {
						lexeme += restOfLine.charAt(i);
					}
					else {
						// if not a letter or digit, the lexeme is done, terminate loop
						i = restOfLine.length();
					}
					
				}
				// if reserved, set reserved keyword type. else normal identifier
				testIfReserved(lexeme);
				this.setLength(lexeme.length());
				this.setLexeme(lexeme);
			} else if(restOfLine.charAt(curr) == '#') {
				String lexeme = "";
				while(restOfLine.length() != curr) {
					lexeme += restOfLine.charAt(curr);
					curr++;
				}
				this.setTokenAttributes("preprocessor-op", lexeme, lexeme.length());
				
			} 
			else if(Character.isDigit(restOfLine.charAt(curr))) {
				String lexeme = "";
				for(int i = 0; i < restOfLine.length(); i++) {
					if(Character.isDigit(restOfLine.charAt(i))) {
						lexeme += restOfLine.charAt(i);
					} else {
						i = restOfLine.length();
					}
				}
				this.setTokenAttributes("numeric-literal", lexeme, lexeme.length());
			}
			else if (restOfLine.charAt(curr) == '\'' || restOfLine.charAt(curr) == '"') {
				// add opening quote of string ", or '
				char quotationMark = restOfLine.charAt(curr);
				String lexeme = Character.toString(quotationMark);
				curr++;
				while(restOfLine.charAt(curr) != quotationMark) {
					// add contents of string "str, or 'c
					lexeme += restOfLine.charAt(curr);
					curr++;
				}
				// add closing quotation mark, "str", or 'c'
				lexeme += quotationMark;
				this.setTokenAttributes("string", lexeme, lexeme.length());
			}
			else {
				determineOperator(restOfLine, curr);
			}
			// to-do:
			// *,/,comma,semicolon,[,],<,>,!,dot,&,|,?,^,%, numeric literals
			// https://www.tutorialspoint.com/cplusplus/cpp_operators.htm 
			// ^^ handling for operators listed in here
			
	}
	
	// Might change name to determineOperator
	public void determineOperator(String restOfLine, int curr)
	{
		int count = 0;
		
		if(restOfLine.length() >= 3) {
			for (int i = 0; i<threeLetterTokenStrings.length; i++)
			{
				if(restOfLine.substring(curr, curr+3).equals(threeLetterTokenStrings[i]))
				{
					this.setTokenAttributes(tokenNames[threeLetterTokenNameIndexes[count]], threeLetterTokenStrings[count], 3);
					return;
				}
				count++;	
			}
		}
		
		
		count = 0;
		
		if(restOfLine.length() >= 2) {
			for (int j = 0; j<twoLetterTokenStrings.length; j++)
			{
				
				if(restOfLine.substring(curr, curr+2).equals(twoLetterTokenStrings[j]))
				{
					this.setTokenAttributes(tokenNames[twoLetterTokenNameIndexes[count]], twoLetterTokenStrings[count], 2);
					return;
				}
				count++;
			}
		}
		
		count = 0;
		
		if(restOfLine.length() >= 1) {
			for (int k = 0; k<tokenChars.length;k++)
			{
				if(restOfLine.charAt(curr) == tokenChars[k])
				{
					this.setTokenAttributes(tokenNames[oneLetterTokenNameIndexes[count]], Character.toString(tokenChars[count]), 1);
					return;
				}
				count++;
			}
		}
		
	}
	
	// ----- Set Token Attributes
	
	public void setTokenAttributes(String type, String lex, int len) {
		this.tokenType = type;
		this.lexeme = lex;
		this.length = len;
	}
	
	public void setTokenType(String type) {
		this.tokenType = type;
	}
	
	public void setLength(int len) {
		this.length = len;
	}
	
	public void setLexeme(String lex) {
		this.lexeme = lex;
	}
	
	public void testIfReserved(String lexeme) {
		if(keywordList.contains(lexeme)) {
			this.tokenType = "Reserved keyword";
		} else {
			this.tokenType = "Identifier";
		}
	}
	
	public boolean isEqual(Token a)
	{
		boolean b = true;
		if (this.getTokenType().equals(a.getTokenType())) //only comparing token types allows for lexical mapping to occur, no longer choosing to account that lexeme values are equal
			b= true;
		else
			b = false;
		
		
		return b;
	}
	
	// ----- Getters
	
	public String getTokenType() {
		return this.tokenType;
	}
	
	public String getLexeme() {
		return this.lexeme;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public int getLineNum() {
		return this.line;
	}
	
	// ---- Private Data members
	
	private String lexeme;
	private String tokenType;
	private int line;
	private int column;
	private int length;
	
	// ----- Arrays of TokenTypes and respective lexemes
	
	private String[] keywords  = {
			"alignas", "alignof", "and",
            "and_eq", "asm", "auto", "bitand", "bitor", "bool", "break",
            "case", "catch", "char", "char8_t", "char16_t", "char32_t", "class",
            "compl", "concept", "const", "consteval", "constexpr", "constinit", "const_cast",
            "continue", "co_await", "co_return", "co_yield", "decltype", "default",
            "delete", "do", "double", "dyanmic_cast", "else", "enum",
            "explicit", "export", "extern", "false", "float", "for",
            "friend", "goto", "if", "inline", "int", "long", "mutable", 
			"namespace", "new", "noexcept", "not", "not_eq", "nullptr",
			"operator", "or", "or_eq", "private", "protected", "public", 
			"register", "reinterpret_cast", "requires", "return", "short",
			"signed", "sizeof", "static", "static_assert", "static_cast",
			"struct", "switch", "template", "this", "thread_local", "throw",
			"true", "try", "typedef", "typeid", "typename", "union", "unsigned",
			"using", "virtual", "void", "volatile", "wchar_t", "while", "xor", "xor_eq"
	};
	
	private String[] tokenNames = {
			"left-paren-op", "right-paren-op", "left-bracket-op", "right-bracket-op", "preprocessor-op",
			"plus-eq-op","inc-op", "plus-op", "minus-eq-op", "dec-op",
			"access-op", "minus-op", "check-equal-op", "assignment-op","mult-eq-op",
			"mult-op","divide-op","divide-eq-op", "comma-op", "term-op",
			"left-sq-bracket-op", "right-sq-bracket-op", "less-eq-op","shift-bit-left-op", "shift-bit-left-eq-op",
			"less-op", "greater-eq-op","shift-bit-right-op", "shift-bit-right-eq-op", "greater-op",
			"not-equal-op","not-op", "dot-op", "ref-op", "and-op",
			"and-eq-op", "or-op", "disjunction-op", "conditional-op","exclusive-or-op",
			"exclusive-or-eq-op", "mod-equal-op", "mod-op", "dot-point-op", "arrow-point-op"
	};
	private int[] oneLetterTokenNameIndexes = {
			0,1,2,3,4,7,11,13,15,16,18,19,20,21,25,29,31,32,33,36,39,42
	};
	private int[] twoLetterTokenNameIndexes  ={
			5,6,8,9,10,12,14,17,22,23,26,27,30,34,35,37,38,40,41,43
	};
	private int[] threeLetterTokenNameIndexes =
		{
			24,28,44
		};
	private char[] tokenChars = {
			'(',')','{', '}', '#',
			'+','-', '=', '*', '/',
			',', ';', '[', ']','<',
			'>','!','.','&','|',
			'^','%'
	};
	private String[] twoLetterTokenStrings = {
		"+=", "++", "-=", "--", "->", "==", "*=", "/=", "<=", "<<", ">=", ">>", "!=", "&&", "&=", "||", "?:", "^=", "%=", ".*"
	};
	private String[] threeLetterTokenStrings = {
		"<<=", ">>=", "->*"
	};
	
	List<String> keywordList = Arrays.asList(keywords);
	
}
