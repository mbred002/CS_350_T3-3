import java.io.FileNotFoundException;
import java.util.Scanner;

public class DupDetector {
public static void main(String[] args) {
		
		// outputting reserved word
		Token reservedWord = new Token("case", 0, 1);
		System.out.println(reservedWord.getLexeme());
		System.out.println(reservedWord.getTokenType());
		
		// outputting not reserved word
		Token normalId = new Token("Not a reserved word", 0, 0);
		System.out.println(normalId.getLexeme());
		System.out.println(normalId.getTokenType());
		
		CPPSourceFile cppFile = new CPPSourceFile("src/main/resources/helloworld.cpp");
		
		System.out.println(cppFile.getFile());

		//Output the C++ Source file found placeholder
		System.out.print("Files scanned:\n");
		System.out.print(cppFile.getFile() + ",\n");
		cppFile.getTokenSequence().outputAllTokens();
		System.out.println("Number of tokens: " + cppFile.getTokenSequence().getNumTokens());
		System.out.println("Number of lines: " + cppFile.getLineNum());

	}
}
