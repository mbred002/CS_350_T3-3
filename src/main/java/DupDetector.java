import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class DupDetector {
public static void main(String[] args) throws FileNotFoundException {
	
		if(args.length != 1 && args.length != 2) {
			System.err.println("Usage: java DupDetector inputFileName, optional additional outputFileName arg");
			System.exit(-1);
		}
		
		/*
		 * ./gradlew build
		 * ./gradlew run --args "inputFile", or .\gradlew run --args "inputFile outputFile"
		 * */
		
		CPPSourceFile cppFile = new CPPSourceFile(args[0]);
		CPPSourceFile cppFile2 = new CPPSourceFile(args[0]);
		
		if(args.length == 2) {
			String outputFile = args[1];
			System.out.println("Output sent to " + outputFile);
			PrintStream output = new PrintStream(new FileOutputStream(outputFile));
			System.setOut(output);
		}
		
		//Output the C++ Source file found placeholder
		System.out.println("Files scanned:\n");
		System.out.println(cppFile.getFile() + ",\n");
		cppFile.getTokenSequence().outputAllTokens();
		System.out.println("Number of tokens: " + cppFile.getTokenSequence().getNumTokens());
		System.out.println("Number of lines: " + cppFile.getLineNum());
		cppFile.getTokenSequence().outputAllTokens();
		if(cppFile.getTokenSequence().isEqual(cppFile2.getTokenSequence()))
			System.out.println("The two files are Equal");
		else
			System.out.println("The two files are not Equal");
		
		
		/*
		outputting reserved word
		Token reservedWord = new Token("case", 0, 1);
		System.out.println(reservedWord.getLexeme());
		System.out.println(reservedWord.getTokenType());
		
		outputting not reserved word
		Token normalId = new Token("Not a reserved word", 0, 0);
		System.out.println(normalId.getLexeme());
		System.out.println(normalId.getTokenType());
		*/

	}
}
