package edu.odu.cs.cs350;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CPPSourceFile {
	
	public CPPSourceFile() {
		this.filePath = new File("src/main/resources/helloworld.cpp");
		this.filePath2 = new File("src/main/resources/test.cpp");
		this.lineNum = 0;
		this.colNum = 0;
	}
	
	public CPPSourceFile(String cppFile) {
		File theFile = new File(cppFile);
		
		
		this.filePath = theFile;
		// probably shouldn't make these variables of CPPSourceFile
		// as it's only used to iterate through in tokenize(), need to refactor
		this.lineNum = 0;
		this.colNum = 0;
		
		this.fileTokens = this.tokenize(filePath); // once tokenize is functional
	}
	
	public CPPSourceFile(String cppFile, String cppFile2) {
		File theFile = new File(cppFile);
		File theFile2 = new File(cppFile2);
		
		this.filePath = theFile;
		this.filePath2 = theFile2;
		// probably shouldn't make these variables of CPPSourceFile
		// as it's only used to iterate through in tokenize(), need to refactor
		this.lineNum = 0;
		this.colNum = 0;
		
		this.fileTokens = this.tokenize(filePath);
		this.fileTokens2 = this.tokenize(filePath2); // once tokenize is functional
	}

	
	// ----- Tokenizing
	
	// loop through all of the input in the file
	public TokenSequence tokenize(File filePath) {
		TokenSequence tSequence = new TokenSequence(this);
		
		this.lineNum = 0;
		this.colNum = 0;
		
		try {
			Scanner sc = new Scanner(filePath);
			// while file has another line, continue
			while(sc.hasNextLine()) {
				getNextLine(this.lineNum, sc, tSequence);	
			}
		} catch (FileNotFoundException e) {
			// mandatory error handling
			e.printStackTrace();
		}
		
	
		
		return tSequence;
	}
	
	// way to iterate through each line in the file used in a loop in tokenize
	public void getNextLine(int lineNum, Scanner sc, TokenSequence tS) {
		
		String line = sc.nextLine();
		this.colNum = 0;
		this.lineNum = lineNum;
		this.lineNum++;
		while(this.colNum < line.length()) {
			if(Character.isWhitespace(line.charAt(colNum))) {
				this.colNum++;
			} else if (this.checkForComment(line, colNum)) {
				// discard comment, move on to the next line, reset colNum to start of line
				this.skipComment(line, sc);
				line = sc.nextLine();
				this.lineNum++;
				this.colNum = 0;
				// logic for if a comment ends with code  remaining on the line to be implemented
			} else {
				Token nextToken = getNextToken(line, lineNum, this.colNum);
				tS.pushToSequence(nextToken);
			}
			
		}
		
		
	}
	
	// iterate through all the tokens in a line
	public Token getNextToken(String line, int lineNum, int colNum) {
		Token newToken = new Token(lineNum, colNum);
		
		String restOfLine = line.substring(colNum, line.length());
		newToken.determineTokenAttributes(restOfLine);
		this.colNum += newToken.getLength();

		return newToken;
	}
	
	public boolean checkForComment(String line, int colNum) {
		if(line.charAt(colNum) == '/') {
			if(line.charAt(colNum+1) == '*' || line.charAt(colNum) == '/') {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	public void skipComment(String line, Scanner sc) {
		this.colNum++;
		if(line.charAt(colNum) == '/') {
			while(this.colNum < line.length()) {
				this.colNum++;
			}
			
		// implied else if char = *, for multi-line comments	
		} else {
			this.colNum++;
			boolean endOfComment = false;
			while(!endOfComment) {
				while(this.colNum < line.length() && !endOfComment) {
					
					if(line.charAt(colNum) == '*') {
						if(line.charAt(colNum+1) == '/') {
							this.colNum += 2;
							endOfComment = true;
						} else {
							this.colNum++;;
						}
					} else {
						this.colNum++;
					}
				}
				// end of the line is reached, or end of comment is reached
				// if end of line is reached, and comment is not, execute this
				if(!endOfComment) {
					line = sc.nextLine();
					this.lineNum++;
					this.colNum = 0;
				}
			}
		}
	}
	
	// ----- Getters
	
	public File getFile() {
		return this.filePath;
	}
	
	public File getFile2() {
		return this.filePath2;
	}
	
	
	public TokenSequence getTokenSequence() {
		return this.fileTokens;
	}
	public TokenSequence getTokenSequence2() {
		return this.fileTokens2;
	}
	
	public int getLineNum() {
		return this.lineNum;
	}
	
	public int getColumnNum() {
		return this.colNum;
	}
	
	// ----- Private Data Members
	
	private File filePath; // filePath of C++ file File getAbsolutePath()
	private File filePath2;
	private TokenSequence fileTokens; // TokenSequence output of the entire C++ file after tokenize() has executed
	private TokenSequence fileTokens2;
	private int lineNum;
	private int colNum;
}