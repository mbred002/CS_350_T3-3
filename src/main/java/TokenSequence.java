import java.util.Vector;
import java.io.File;

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
