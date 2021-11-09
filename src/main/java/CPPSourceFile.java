import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CPPSourceFile {
	
	public CPPSourceFile(String cppFile) {
		File theFile = new File(cppFile);
		this.filePath = theFile;
		
		// this.fileTokens = tokenize(); once tokenize is functional
		
		// probably shouldn't make these variables of CPPSourceFile
		// as it's only used to iterate through in tokenize(), need to refactor
		this.lineNum = 1;
		this.colNum = 0;
	}

	
	// loop through all of the input in the file
	public TokenSequence tokenize() {
		TokenSequence tSequence = new TokenSequence();
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
		
		// Placeholder until complete and can return a TokenSequence
		
		return tSequence;
	}
	
	public File getFile() {
		return this.filePath;
	}
	
	public TokenSequence getTokenSequence() {
		return this.fileTokens;
	}
	
	// way to iterate through each line in the file used in a loop in tokenize
	public void getNextLine(int lineNum, Scanner sc, TokenSequence tS) {
		
		String line = sc.nextLine();
		this.colNum = 0;
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
				Token nextToken = getNextToken(line, lineNum);
				tS.pushToSequence(nextToken);
			}
			
		}
		
		this.lineNum++;
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
	
	public int getNumLines() {
		return this.lineNum;
	}


	// iterate through all the tokens in a line
	public Token getNextToken(String line, int lineNum) {
		Token newToken = new Token(lineNum, this.colNum);
		
		String restOfLine = line.substring(this.colNum, line.length());
		newToken.determineTokenAttributes(restOfLine);
		// newToken.setLocation(int lineNum, int colNum, File srcFile); -- to be impl.
		this.colNum += newToken.getLength();

		return newToken;
	}

	private File filePath; // filePath of C++ file File getAbsolutePath()
	private TokenSequence fileTokens; // TokenSequence output of the entire C++ file after tokenize() has executed
	private int lineNum;
	private int colNum;
}