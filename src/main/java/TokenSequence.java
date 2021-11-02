import java.util.Vector;

public class TokenSequence {
	
	TokenSequence() {
		this.sourceFile = new CPPSourceFile("src/main/resources/helloworld.cpp");
		this.numTokens = 0;
		
		this.sequence = new Vector<Token>();
	}
	
	void pushToSequence(Token token) {
		this.sequence.add(token);
		this.numTokens++;
	}
	
	Token returnToken(int tokenIndex) {
		return sequence.elementAt(tokenIndex);
		
	}
	
	//////// Count each token type /////////
	
	public int getNumTokens() {
		return numTokens;
	}
	
	public int CountTokenType(String TypeX){
		
		int i = 0, num = sequence.size();
		int count =0;
		for (i =0; i < num; i++ );
		{
			if (sequence.get(i).getTokenType().equals(TypeX))
			{
				this.numTokens++;
				count++;
			}
		}
		return count;
	};
	
	
	
	
	Vector<Token> sequence;
	CPPSourceFile sourceFile;
	int numTokens;
}
