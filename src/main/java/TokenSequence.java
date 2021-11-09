import java.util.Vector;
import java.io.File;

public class TokenSequence {
	
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
	
	void pushToSequence(Token token) {
		this.sequence.add(token);
		this.numTokens++;
	}
	
	Token returnToken(int tokenIndex) {
		return sequence.elementAt(tokenIndex);
	}
	
	public void outputAllTokens() {
		for(int i = 0; i < this.sequence.size(); i++) {
			System.out.println(sequence.get(i).getLexeme());
		}
	}
	
	
	public int getNumTokens() {
		return sequence.size();
	}
	
	public Token getToken(int index)
	{
		return sequence.get(index);
	}
	
	public boolean isEqual (TokenSequence a)
	{
		boolean b = true;
		//or less than minSequenceLength
		if (this.sequence.size() != a.getNumTokens())
		{
			b = false;
		}
		else
		{
			for (int i = 0; i < this.sequence.size(); i++)
			{
				if(this.getToken(i).isEqual(a.getToken(i)))
					b = true;
				else
				{
					b = false;
					break;
				}
			}	
		}
		return b;
	}
	
	
	Vector<Token> sequence;
	CPPSourceFile sourceFile;
	int numTokens;
}
