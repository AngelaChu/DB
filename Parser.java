package cs4321.project1;

import cs4321.project1.tree.*;

/**
 * Class for a parser that can parse a string and produce an expression tree. To
 * keep the code simple, this does no input checking whatsoever so it only works
 * on correct input.
 * 
 * An expression is one or more terms separated by + or - signs. A term is one
 * or more factors separated by * or / signs. A factor is an expression in
 * parentheses (), a factor with a unary - before it, or a number.
 * 
 * @author Lucja Kot
 * @author Your names and netids go here
 */
public class Parser {

	private String[] tokens;
	private int currentToken; // pointer to next input token to be processed

	/**
	 * @precondition input represents a valid expression with all tokens
	 *               separated by spaces, e.g. "3.0 - ( 1.0 + 2.0 ) / - 5.0. All
	 *               tokens must be either numbers that parse to Double, or one
	 *               of the symbols +, -, /, *, ( or ), and all parentheses must
	 *               be matched and properly nested.
	 */
	public Parser(String input) {
		this.tokens = input.split("\\s+");
		currentToken = 0;
	}

	/**
	 * Parse the input and build the expression tree
	 * 
	 * @return the (root node of) the resulting tree
	 */
	public TreeNode parse() {
		return expression();
	}

	/**
	 * Parse the remaining input as far as needed to get the next factor
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode factor() {

		// TODO fill me in
		String cur = tokens[currentToken];
		currentToken++;
		
		if (Character.isDigit(cur.charAt(0))) {
			double num = Double.parseDouble(cur);
			return new LeafTreeNode(num);
		} else if (cur.charAt(0) == '-') {
			TreeNode child = factor();
			return new UnaryMinusTreeNode(child);
		} else if (cur.charAt(0) == '('){
			TreeNode r = expression();
			currentToken++;
			return r;
		} else {
			throw new IllegalArgumentException("This is not a factor");
		}
		
		
		//return null;
	}

	/**
	 * Parse the remaining input as far as needed to get the next term
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode term() {

		// TODO fill me in
		int i = currentToken;
		int count = 0;
		while (i < tokens.length) {
			char cur = tokens[i].charAt(0);
			if (cur == ')' || (cur == '+') || (cur == '-' && i != 0 && 
					tokens[i-1].charAt(0) != '/' && tokens[i-1].charAt(0) != '*' && tokens[i-1].charAt(0) != '(') ) {
				break;
			}
			if (cur == '(') {
				int countPa = 1;
				i++;
				while (countPa  > 0) {
					cur = tokens[i].charAt(0);
					if (cur == '(') {
						countPa++;
					} else if (cur == ')'){
						countPa--;
					}
					i++;
				}
			} else if (cur == '*' || cur == '/') {
				count++;
				i++;
			} else {
				i++;
			}
		}
			
		return termHelper(count);

	}
	
	private TreeNode termHelper(int count) {
		if (count == 0) {
			return factor();
		}
		TreeNode left = termHelper(count-1);
		String cur = tokens[currentToken];
		currentToken++;
		TreeNode right = factor();
		TreeNode self;
		if (cur.charAt(0) == '*') {
			self = new MultiplicationTreeNode(left, right);
		} else {
			self = new DivisionTreeNode(left, right);
		}
		return self;
		
	}

	/**
	 * Parse the remaining input as far as needed to get the next expression
	 * 
	 * @return the (root node of) the resulting subtree
	 */
	private TreeNode expression() {

		// TODO fill me in
		int i = currentToken;
		int count = 0;
		while (i < tokens.length) {
			if (tokens[i].charAt(0) == ')') {
				break;
			}
			if (tokens[i].charAt(0) == '(') {
				int countPa = 1;
				i++;
				while (countPa  > 0) {
					if (tokens[i].charAt(0) == '(') {
						countPa++;
					} else if (tokens[i].charAt(0) == ')'){
						countPa--;
					}
					i++;
				}
			} else if (tokens[i].charAt(0) == '+' || tokens[i].charAt(0) == '-') {
				if (tokens[i].charAt(0) == '-' && (i == 0  || tokens[i-1].charAt(0) == '+' || 
						tokens[i-1].charAt(0) == '-' ||tokens[i-1].charAt(0) == '*' ||
						tokens[i-1].charAt(0) == '/' || tokens[i-1].charAt(0) == '(')) {//check this condition later
					
				} else {
					count++;
				}
				i++;
			} else {
				i++;
			}
			
			
		}
		
		return expressionHelper(count);
		
		
		//return null;

	}
	
	private TreeNode expressionHelper(int count) {
		if (count == 0) {
			return term();
		}
		
		TreeNode left = expressionHelper(count-1);
		String cur = tokens[currentToken];
		currentToken++;
		TreeNode right = term();
		TreeNode self;
		if (cur.charAt(0) == '+') {
			self = new AdditionTreeNode(left, right);
		} else {
			self = new SubtractionTreeNode(left, right);
		}
		return self;
	}
}
