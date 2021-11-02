import java.io.FileNotFoundException;
import java.util.Scanner;

public class DupDetector {
public static void main(String[] args) {
		
		
		System.out.println("Hello world");
		
		// outputting reserved word
		Token reservedWord = new Token("case", 0, 1);
		System.out.println(reservedWord.getLexeme());
		System.out.println(reservedWord.getTokenType());
		
		// outputting not reserved word
		Token normalId = new Token("Not a reserved word", 0, 0);
		System.out.println(normalId.getLexeme());
		System.out.println(normalId.getTokenType());
		
		CPPSourceFile cppFile = new CPPSourceFile("src/main/resources/helloworld.cpp");

		//Output the C++ Source file found placeholder
		System.out.print("Files scanned:\n");
		System.out.print(cppFile.getFile() + ",\n");

		//Output the size of the sequence vector?
		//TokenSequence test = new TokenSequence();
		//System.out.print(test.sequence.size());

		Token a = new Token(0, 0);
		String restOfLine = "== integer";
		System.out.println(restOfLine.substring(0, 2));
		int curr = 0;
		a.determineToken(restOfLine, curr);
		System.out.println(a.getLexeme());
		System.out.println(a.getLength());
		System.out.println(a.getTokenType());
		
		/*
		// just debugging here, outputting the input file
		try {
			Scanner fileScanner = new Scanner(cppFile.getFile());
			while(fileScanner.hasNextLine()) {
				System.out.println(fileScanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
}
