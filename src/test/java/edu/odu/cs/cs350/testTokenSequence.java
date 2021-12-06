package edu.odu.cs.cs350;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader; 

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

public class testTokenSequence {
	
	@Test 
	public void testConstructor()
	{
		CPPSourceFile file = new CPPSourceFile("src/main/resources/helloworld.cpp");
		TokenSequence tSeq = new TokenSequence(file);
		
		assertEquals(tSeq.getSourceFile(), file);
		assertTrue(tSeq.getSequence().isEmpty());
		assertEquals(tSeq.getNumTokens(), 0);
		
		tSeq.pushToSequence(new Token("var", 5, 4));
		assertFalse(tSeq.getSequence().isEmpty());
	}
	
	@Test 
	public void testPushToSequence() {
		TokenSequence tSeq = new TokenSequence();
		Token aToken = new Token("int", 3, 6);
		
		tSeq.pushToSequence(aToken);
		
		assertFalse(tSeq.getSequence().isEmpty());
		assertTrue(tSeq.getSequence().contains(aToken));
		assertEquals(tSeq.getNumTokens(), 1);
		assertEquals(tSeq.getToken(0), aToken);
		
	}
	
	@Test
	public void testIsEqual()
	{
		Token a = new Token(0,0);
		Token b = new Token(0,4);
		Token c = new Token(0,7);
		Token d = new Token(0,10);
		
		a.setLexeme("==");
		b.setLexeme("<=");
		c.setLexeme("!=");
		d.setLexeme("=");
		
		a.setTokenType("check-equal-op");
		b.setTokenType("less-eq-op");
		c.setTokenType("not-equal-op");
		d.setTokenType("assignment-op");
		
		a.setLength(2);
		b.setLength(2);
		c.setLength(2);
		d.setLength(1);
		
		TokenSequence seq1 = new TokenSequence();
		TokenSequence seq2 = new TokenSequence();
		TokenSequence seq3= new TokenSequence();
		
		seq1.pushToSequence(a);
		seq1.pushToSequence(b);
		seq1.pushToSequence(c);
		
		seq2.pushToSequence(a);
		seq2.pushToSequence(b);
		seq2.pushToSequence(c);
		
		seq3.pushToSequence(a);
		seq3.pushToSequence(b);
		seq3.pushToSequence(d);
		
		
		assertTrue(seq1.isEqual(seq2));
		assertFalse(seq1.isEqual(seq3));
		assertFalse(seq2.isEqual(seq3));
	}

	@Test
	public void testGetNumTokens ()
	{
		TokenSequence seq = new TokenSequence();
		Token a = new Token(1,0);
		Token b = new Token(1,3);
		Token c = new Token(1,6);
		
		seq.pushToSequence(a);
		seq.pushToSequence(b);
		seq.pushToSequence(c);
		
		assertThat(seq.getNumTokens(), is (3));
	}
	

	
}
