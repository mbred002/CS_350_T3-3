import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class testCPPSourceFile {
	@Test 
	public void testCPPSourceFile()
	{	
		CPPSourceFile testFile = new CPPSourceFile("src/main/resources/helloworld.cpp");
		assertTrue(testFile.getLineNum()==17);
		assertTrue(testFile.getColumnNum()==1); // end of file is bracket on line 17. Should be end due to tokenize() executing
		// handling for both Windows and linux machines in path check
		assertThat(testFile.getFile().getPath(), Matchers.anyOf(Matchers.is("src/main/resources/helloworld.cpp"), Matchers.is("src\\main\\resources\\helloworld.cpp")));
		
	}
	@Test
	public void testTokenize()
	{
		CPPSourceFile testFile = new CPPSourceFile("src/main/resources/helloworld.cpp");
		testFile.tokenize();
		assertTrue(testFile.getLineNum()==17);
		assertEquals(testFile.getTokenSequence().getNumTokens(), 15);
		assertEquals(testFile.getTokenSequence().getToken(0).getLexeme(), "#include<iostream>");
		assertEquals(testFile.getTokenSequence().getToken(9).getTokenType(), "string");
		
	}
	
	/*
	 * Used as an iteration function inside of tokenize(), implicitly tested in above test
	@Test
	public void testGetNextLine()
	{
		Scanner scan = new Scanner("src/amin/resources/helloworld.cpp");
		CPPSourceFile lineFile = new CPPSourceFile("src/main/resources/helloworld.cpp");
		
		lineFile.getNextLine(1, scan, lineFile.getTokenSequence());
	}
	*/
	
	@Test
	public void testSkipComment()
	{
		CPPSourceFile placeholderFile = new CPPSourceFile();
		Scanner scan = new Scanner(placeholderFile.getFile().getPath());
		String str = "/* This is a multiline line comment */int main()";
		placeholderFile.skipComment(str, scan);
		assertEquals(placeholderFile.getColumnNum(), 38);
		int theColNum = placeholderFile.getColumnNum();
		Token aToken = placeholderFile.getNextToken(str, 0, theColNum);
		assertEquals(aToken.getLexeme(), "int");
		assertEquals(aToken.getTokenType(), "Reserved keyword");

		
	}
	
	@Test
	public void testGetNextToken()
	{
		CPPSourceFile testFile = new CPPSourceFile("src/main/resources/helloworld.cpp");
		try {
			Scanner scan = new Scanner(testFile.getFile());
			String testStr = scan.nextLine();

			Token nextToken = testFile.getNextToken(testStr, 1, 0);
			
			assertTrue(nextToken.getLexeme().equals("#include<iostream>"));
			assertTrue(nextToken.getTokenType().equals("preprocessor-op"));
			assertEquals(nextToken.getLength(), 18);
			assertEquals(nextToken.getLineNum(), 1);
			assertEquals(nextToken.getColumn(), 0);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
	}
}
