import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CPPSourceFile {
	
	public CPPSourceFile(String cppFile) {
		File theFile = new File(cppFile);
		this.filePath = theFile;
		
		
		// probably shouldn't make these variables of CPPSourceFile
		// as it's only used to interate through in tokenize(), need to refactor
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
	
	// way to iterate through each line in the file used in a loop in tokenize
	public void getNextLine(int lineNum, Scanner sc, TokenSequence tS) {
		
		String line = sc.nextLine();
		while(this.colNum < line.length()) {
			Token nextToken = getNextToken(line, lineNum, this.colNum);
			tS.pushToSequence(nextToken);
		}
		this.lineNum++;
	}
	
	// iterate through all the tokens in a line
	public Token getNextToken(String line, int lineNum, int colNum) {
		Token newToken = new Token(lineNum, colNum);
		
		
		while(line.charAt(colNum) != '\n') {
			String restOfLine = line.substring(colNum, line.length());
			newToken.setTokenTypeAndLength(restOfLine);
			colNum += newToken.getLength();
		}
		
		return newToken;
	}

	private File filePath; // filePath of C++ file File getAbsolutePath()
	private TokenSequence fileTokens; // TokenSequence output of the entire C++ file after tokenize() has executed
	private int lineNum;
	private int colNum;
}
