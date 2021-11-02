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
		assertTrue(a.getTokenType().equals("set-equal-op"));
		assertThat(a.getLength(), is(2));
		
		
	}
	
	}