import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class testCPPSourceFile {

	@Test
	public void testTokenize()
	{
		
	}
	
	@Test
	public void testGetNextLine()
	{
		
	}
	
	@Test
	public void testSkipComment()
	{
		
	}
	
	// Not sure if this needs to be tested, as it is technically tested in testGetNextLine()
	@Test
	public void testGetNextToken()
	{
		CPPSourceFile testFile = new CPPSourceFile("src/main/resources/helloworld.cpp");
		try {
			Scanner scan = new Scanner(testFile.getFile());
			String testStr = scan.nextLine();
			
			Token nextToken = testFile.getNextToken(testStr, testFile.getLineNum());
			
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
