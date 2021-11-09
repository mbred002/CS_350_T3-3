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