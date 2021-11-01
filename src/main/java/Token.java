import java.util.*;

public class Token {
	private String lexeme;
	private String tokenType;
	private int line;
	private int column;
	private int length;
	// I don't believe a token needs to have an associated file, only sequences
	public Token()
	{
		
	}
	
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
	
	public String getTokenType() {
		return this.tokenType;
	}
	
	public String getLexeme() {
		return this.lexeme;
	}
	
	public int getLength() {
		return this.length;
	}
	
	/*
	 * Need boolean checks for if first character of the lexeme is:
	 * A letter
	 * A digit
	 * Whitespace
	 * An operator, and the appropriate logic for each operator to proceed
	 * A comment, in which case it is ignored
	 * 
	 * */
	
	
	// For now I'm throwing this all in one function, however it will need to be refactored
	// Should all of the tokenType logic go in a separate class?
	// NOT COMPLETE
	
	/*
	 * By end of function, tokenType, length, and lexeme should be defined and set for the Token object
	 * 
	 * To be handled: 
	 * Letters (variables, reserved words) 
	 * Brackets 
	 * Parenthesis 
	 * #(include statements), 
	 * <, >, =, -, +, *, /, 
	 * comments, 
	 * whitespace
	 * 
	 * 
	 * */
	public void determineToken(String restOfLine, int curr)
	{
		int count = 0;
		
		for (int i = 0; i<threeLetterTokenStrings.length; i++)
		{
			if(restOfLine.substring(curr, curr+2) == threeLetterTokenStrings[i])
			{
				this.setTokenAttributes(tokenNames[threeLetterTokenNameIndexes[count]], threeLetterTokenStrings[count], 3);
				break;
			}
			count++;	
		}
		
		count = 0;
		for (int j = 0; j<twoLetterTokenStrings.length; j++)
		{
			if(restOfLine.substring(curr, curr+1) == twoLetterTokenStrings[j])
			{
				this.setTokenAttributes(tokenNames[twoLetterTokenNameIndexes[count]], twoLetterTokenStrings[count], 2);
				break;
			}
			count++;
		}
		count = 0;
		for (int k = 0; k<tokenChars.length;k++)
		{
			if(restOfLine.charAt(curr) == tokenChars[k])
			{
				this.setTokenAttributes(tokenNames[oneLetterTokenNameIndexes[count]], Character.toString(tokenChars[count]), 1);
				break;
			}
			count++;
		}
	}
	
	public void determineTokenAttributes(String restOfLine) {
			int curr = 0;
			
			// skip whitespace, to next Token
			
			if(Character.isLetter(restOfLine.charAt(curr))) {
				String lexeme = "";
				for(int i = 0; i < restOfLine.length(); i++) {
					if(Character.isLetterOrDigit(restOfLine.charAt(i)) || restOfLine.charAt(i) == ':') {
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
			} 
			/*
			 * Need an overarching function for all characters that
			 * end a token to move to the next i.e {, }, (, ) instead of a bunch of else ifs
			 * */
			/*
			else if(restOfLine.charAt(curr) == '(') {
				this.setTokenAttributes("left-paren-op", "(", 1);
			} else if (restOfLine.charAt(curr) == ')') {
				this.setTokenAttributes("right-paren-op", ")", 1);
			} else if (restOfLine.charAt(curr) == '{') {
				this.setTokenAttributes("left-bracket-op", "{", 1);
			} else if (restOfLine.charAt(curr) == '}') {
				this.setTokenAttributes("right-bracket-op", "}", 1);
			} else if(restOfLine.charAt(curr) == '#') {
				String lexeme = "";
				while(restOfLine.charAt(curr) != '\n') {
					lexeme += restOfLine.charAt(curr);
					curr++;
				}
				this.setTokenAttributes("preprocessor-op", lexeme, lexeme.length());
			} else if(restOfLine.charAt(curr) == '+') {
				String lexeme = "+";
				// every plus op, check if there is an equals sign following it
				if(restOfLine.charAt(curr+1) == '=') {
					lexeme += '=';
					this.setTokenAttributes("plus-eq-op", lexeme, 2);
				} else {
					this.setTokenAttributes("plus-op", lexeme, 1);
				}
			} else if(restOfLine.charAt(curr) == '-') {
				String lexeme = "+";
				// same check as last, only w/ minus-equals
				if(restOfLine.charAt(curr+1) == '=') {
					lexeme += '=';
					this.setTokenAttributes("minus-eq-op", lexeme, 2);
				} else {
					this.setTokenAttributes("minus-op", lexeme, 1);
				}
			} else if(restOfLine.charAt(curr) == '=') {
				String lexeme = "=";
				if(restOfLine.charAt(curr+1) == '=') {
					lexeme += '=';
					this.setTokenAttributes("set-equal-op", lexeme, 2);
				} else {
					this.setTokenAttributes("assignment-op", lexeme, 1);
				}
				
			} 
			*/
			else if (restOfLine.charAt(curr) == '/') {
				//to be implemented
			}
			else if (restOfLine.charAt(curr) == '\'' || restOfLine.charAt(curr) == '"') {
				// add opening quote of string ", or '
				char quotationMark = restOfLine.charAt(curr);
				String lexeme = Character.toString(quotationMark);
				curr++;
				while(restOfLine.charAt(curr) != quotationMark) {
					// add contents of string "str, or 'c
					lexeme += restOfLine.charAt(curr);
				}
				// add closing quotation mark, "str", or 'c'
				lexeme += quotationMark;
				this.setTokenAttributes("string", lexeme, lexeme.length());
			}
			else {
				determineToken(restOfLine, curr);
			}
			// to-do:
			// *,/,comma,semicolon,[,],<,>,!,dot,&,|,?,^,%, numeric literals
			// https://www.tutorialspoint.com/cplusplus/cpp_operators.htm 
			// ^^ handling for operators listed in here
			
	}
	
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
	
	
	private String[] keywords  = {
			"abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "extends", "false",
            "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long", "native",
            "new", "null", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "true",
            "try", "void", "volatile", "while" 
	};
	
	private String[] tokenNames = {
			"left-paren-op", "right-paren-op", "left-bracket-op", "right-bracket-op", "preprocessor-op",
			"plus-eq-op","inc-op", "plus-op", "minus-eq-op", "dec-op",
			"access-op", "minus-op", "set-equal-op", "assignment-op","mult-eq-op",
			"mult-op","divide-op","divide-eq-op", "comma-op", "term-op",
			"left-sq-bracket-op", "right-sq-bracket-op", "less-eq-op","shift-bit-left-op", "shift-bit-left-eq-op",
			"less-op", "greater-eq-op","shift-bit-right-op", "shift-bit-right-eq-op", "greater-op",
			"not-equal-op","not-op", "dot-op", "ref-op", "and-op",
			"and-eq-op", "or-op", "disjunction-op", "conditional-op","exclusive-or-op",
			"exclusive-or-eq-op", "mod-equal-op", "mod-op", "dot-point-op", "arrow-point-op"
	};
	private int[] oneLetterTokenNameIndexes = {
			0,1,2,3,4,7,11,12,15,16,18,19,20,21,25,29,31,32,33,36,39,42
	};
	private int[] twoLetterTokenNameIndexes  ={
			5,6,8,9,10,13,14,17,22,23,26,27,30,34,35,37,38,40,41,43
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
