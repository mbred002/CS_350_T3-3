import java.util.Vector;

public class TokenSequence {
	
	TokenSequence() {
		this.sourceFile = new CPPSourceFile("helloworld.cpp");
		this.numTokens = 0;
	}
	
	void pushToSequence(Token token) {
		sequence.add(token);
	}
	
	Token returnToken(int tokenIndex) {
		return sequence.elementAt(tokenIndex);
	}
	
	
	
	Vector<Token> sequence;
	CPPSourceFile sourceFile;
	int numTokens;
}
