package cs4321.project1;

import static org.junit.Assert.*;

import org.junit.Test;

import cs4321.project1.tree.TreeNode;

public class ParserTest {

	/*
	 * This class depends on the correct functioning of PrintTreeVisitor(), which is provided for you.
	 */
			
	@Test
	public void testSingleNumber() {
		Parser p1 = new Parser("1.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("1.0", v1.getResult());

	}
	
	@Test
	public void testUnaryMinusSimple() {
		Parser p1 = new Parser("- 1.0");
		TreeNode parseResult1 = p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(-1.0)", v1.getResult());

	}
	
	@Test
	public void testUnaryMinusComplex() {
		Parser p1 = new Parser("- - 1.0");
		TreeNode parseResult1 =  p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(-(-1.0))", v1.getResult());

	}
	@Test
	public void testExpressionComplex() {
		Parser p1 = new Parser("( ( ( - 1.0 + 2.0 ) - ( 3.0 - - 4.0 ) ) )");
		TreeNode parseResult1 =  p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("(((-1.0)+2.0)-(3.0-(-4.0)))", v1.getResult());

	}
	
	@Test
	public void testMixComplex() {
		Parser p1 = new Parser("( ( ( ( - 1.0 / 2.0 + 20.1 ) ) / - 10.0 - ( 3.0 * - 4.0 ) ) + 1.0 / 2.0 )");
		TreeNode parseResult1 =  p1.parse();
		PrintTreeVisitor v1 = new PrintTreeVisitor();
		parseResult1.accept(v1);
		assertEquals("((((((-1.0)/2.0)+20.1)/(-10.0))-(3.0*(-4.0)))+(1.0/2.0))", v1.getResult());

	}

}
