package cs4321.project1;

import cs4321.project1.list.DivisionListNode;
import cs4321.project1.list.ListNode;
import cs4321.project1.list.SubtractionListNode;
import cs4321.project1.list.NumberListNode;

import java.util.Stack;

import cs4321.project1.list.AdditionListNode;
import cs4321.project1.list.MultiplicationListNode;
import cs4321.project1.list.UnaryMinusListNode;

/**
 * Provide a comment about what your class does and the overall logic
 * 
 * @author Your names and netids go here
 */
public class EvaluatePostfixListVisitor implements ListVisitor {
	Stack<Double> stack;
	
	
	public EvaluatePostfixListVisitor() {
		// TODO fill me in
		stack = new Stack<Double>();
		
	}

	public double getResult() {
		// TODO fill me in
		if (stack.isEmpty()) {
			return 0;
		} else {
			return stack.pop(); // so that skeleton code compiles
		}
	}

	@Override
	public void visit(NumberListNode node) {
		// TODO fill me in
		stack.push(node.getData());
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}
		
	}

	@Override
	public void visit(AdditionListNode node) {
		// TODO fill me in
		double left = stack.pop();
		double right = stack.pop();
		double r = left + right;
		stack.push(r);
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}
		
	}

	@Override
	public void visit(SubtractionListNode node) {
		// TODO fill me in
		double right = stack.pop();
		double left = stack.pop();
		double r = left - right;
		stack.push(r);
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}
		
	}

	@Override
	public void visit(MultiplicationListNode node) {
		// TODO fill me in
		double right = stack.pop();
		double left = stack.pop();
		double r = left * right;
		stack.push(r);
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}
	}

	@Override
	public void visit(DivisionListNode node) {
		// TODO fill me in
		double right = stack.pop();
		double left = stack.pop();
		double r = left/right;
		stack.push(r);
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}
	}

	@Override
	public void visit(UnaryMinusListNode node) {
		// TODO fill me in
		double num = stack.pop();
		stack.push(-num);
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}
	}

}
