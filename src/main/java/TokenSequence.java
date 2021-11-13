import java.util.Vector;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TokenSequence {
	
	// ----- Constructors
	
	TokenSequence() {
		this.sourceFile = new CPPSourceFile("src/main/resources/helloworld.cpp");
		this.numTokens = 0;
		this.sequence = new Vector<Token>();
	}
	
	TokenSequence(CPPSourceFile file) {
		this.sourceFile = file;
		this.numTokens = 0;
		this.sequence = new Vector<Token>();
	}
	
	// ----- Manipulators
	
	void pushToSequence(Token token) {
		this.sequence.add(token);
		this.numTokens++;
	}
	
	// ----- Booleans
	
	public boolean isEqual (TokenSequence a) {
		boolean b = true;
		//or less than minSequenceLength
		if (this.sequence.size() != a.getNumTokens()) {
			b = false;
		}
		else {
			for (int i = 0; i < this.sequence.size(); i++) {
				if(this.getToken(i).isEqual(a.getToken(i))) {
					b = true;
				}
				else {
					b = false;
					break;
				}
			}	
		}
		return b;
	}
	
	// ----- Output
	
	public void outputAllTokens() {
		for(int i = 0; i < this.sequence.size(); i++) {
			System.out.println(sequence.get(i).getLexeme());
		
		}
		
	}
	
	void outputNumTokens(){
	try {
	File numTokens = new File("numTokens.txt");
	FileWriter myWriter = new FileWriter("numTokens.txt");
	myWriter.write("The total number of tokens in the file is: " + this.getNumTokens());
	myWriter.close();
		}
	catch (IOException e){
	System.out.println("An error occured.");
	e.printStackTrace();
		}
	}

	
	// ----- Getters
	
	public Vector<Token> getSequence() {
		return this.sequence;
	}
	
	public Token getToken(int index) {
		return sequence.get(index);
	}
	
	public CPPSourceFile getSourceFile() {
		return this.sourceFile;
	}

	public int getNumTokens() {
		return this.numTokens;
	}
	
	// ----- Private data members
	
	Vector<Token> sequence;
	CPPSourceFile sourceFile;
	int numTokens;
}
