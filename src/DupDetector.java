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
		
		CPPSourceFile cppFile = new CPPSourceFile("CS_350_T3-3/src/helloworld.cpp");
		
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
		
	}
}
