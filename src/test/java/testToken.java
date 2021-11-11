import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class testToken
{
	@Test
	public void testDetermineToken()
	{
		Token a = new Token(0, 0);
		String restOfLine = "== integer";
		int curr = 0;
		a.determineToken(restOfLine, curr);
		assertFalse(a.getLexeme().isEmpty());
		assertFalse(a.getTokenType().isEmpty());
		assertTrue(a.getLexeme().equals("=="));
		assertTrue(a.getTokenType().equals("check-equal-op"));
		assertThat(a.getLength(), is(2));
		
		
	}
	
	@Test
	public void testDetermineTokenAttributes()
	{
		// String handling
		String str = "\"This is a string\";";
		Token b = new Token(10, 5);
		b.determineTokenAttributes(str);
		assertEquals(b.getLexeme(), "\"This is a string\"");
		assertEquals(b.getTokenType(), "string");
		assertEquals(b.getLength(), 18);
		assertEquals(b.getLineNum(), 10);
		assertEquals(b.getColumn(), 5);
		
		// Identifier and reserved word handling
		String reserved = "for(int i =";
		Token c = new Token(2, 3);
		c.determineTokenAttributes(reserved);
		assertEquals(c.getLexeme(), "for");
		assertEquals(c.getTokenType(), "Reserved keyword");
		assertEquals(c.getLength(), 3);
		assertEquals(c.getLineNum(), 2);
		assertEquals(c.getColumn(), 3);
		
		String identifier = "std::getline(std::cin, name);";
		Token d = new Token(3, 4);
		d.determineTokenAttributes(identifier);
		assertEquals(d.getLexeme(), "std::getline");
		assertEquals(d.getTokenType(), "Identifier");
		assertEquals(d.getLength(), 12);
		assertEquals(d.getLineNum(), 3);
		assertEquals(d.getColumn(), 4);
		
	}
	
	@Test
	public void testSetTokenAttributes()
	{
		
	}
	

	@Test
	public void testIsEqual()
	{
		//Test string = "== ==" 
		Token a = new Token(0,0);
		Token b = new Token(0,3);
		a.setLexeme("==");
		b.setLexeme("==");
		a.setTokenType("check-equal-op");
		b.setTokenType("check-equal-op");
		a.setLength(2);
		b.setLength(2);
		
		assertTrue(a.isEqual(b));
		
		//Test string = "== !="
		b.setLexeme("!=");
		b.setTokenType("not-equal-op");
		
		assertFalse(a.isEqual(b));
	}
}