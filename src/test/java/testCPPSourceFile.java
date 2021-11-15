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
		assertTrue(testFile.getLineNum()==0);
		assertTrue(testFile.getColumnNum()==0);
	}
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
