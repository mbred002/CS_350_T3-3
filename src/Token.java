import java.util.*;

public class Token {
	private String lexeme;
	private String tokenType;
	private int line;
	private int column;
	private int length;
	// I don't believe a token needs to have an associated file, only sequences
	
	public Token(int line, int col) {
		this.line = line;
		this.column = col;
	}
	
	public Token(String lexeme, int line, int col) {
		this.lexeme = lexeme;
		this.setTokenAttributes(lexeme);
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
	
	public void setTokenAttributes(String restOfLine) {
			int curr = 0;
			
			// skip whitespace, to next Token
			
			if(Character.isLetter(restOfLine.charAt(curr))) {
				String lexeme = "";
				for(int i = 0; i < restOfLine.length(); i++) {
					if(Character.isLetterOrDigit(restOfLine.charAt(i))) {
						// if letter or digit, keep adding to the lexeme.
						lexeme += restOfLine.charAt(i);
					} else {
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
			else if(restOfLine.charAt(curr) == '(') {
				this.setTokenType("left-paren-op");
				this.setLength(1);
				this.setLexeme("(");
			} else if (restOfLine.charAt(curr) == ')') {
				this.setTokenType("right-paren-op");
				this.setLength(1);
				this.setLexeme(")");
			} else if (restOfLine.charAt(curr) == '{') {
				this.setTokenType("left-bracket-op");
				this.setLength(1);
				this.setLexeme("{");
			} else if (restOfLine.charAt(curr) == '}') {
				this.setTokenType("right-bracket-op");
				this.setLength(1);
				this.setLexeme("}");
			} else if(restOfLine.charAt(curr) == '#') {
				String lexeme = "";
				while(restOfLine.charAt(curr) != '\n') {
					lexeme += restOfLine.charAt(curr);
					curr++;
				}
				this.setTokenType("preprocessor");
				this.setLength(lexeme.length());
				this.setLexeme(lexeme);
			}
			// to-do:
			// +,-,*,/,",',comma,semicolon, [,],=,<,>,!, probably other things I'm forgetting
			
			
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
	
	List<String> keywordList = Arrays.asList(keywords);
	
}
