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
	
	//////// Count each token type /////////
	
	int CountTokenType(String TypeX){
		
		int i = 0, num = sequence.size();
		
		for (i =0; i < num; i++ );
		{
			if (sequence[i].getTokenType().equals(TypeX))
			{
				this.numTokens++;
			}
		}
	};
	
	
	
	
	Vector<Token> sequence;
	CPPSourceFile sourceFile;
	int numTokens;
}





