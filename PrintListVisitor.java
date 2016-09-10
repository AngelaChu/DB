package cs4321.project1;

import cs4321.project1.list.*;

/**
 * Provide a comment about what your class does and the overall logic
 * 
 * @author Your names and netids go here
 */

public class PrintListVisitor implements ListVisitor {
	String r;
	public PrintListVisitor() {
		// TODO fill me in
		r = "";
	}

	public String getResult() {
		// TODO fill me in
		if (r.length() > 0) {
			return r.substring(0, r.length()-1);
		} else {
			return r;
		}
	}

	@Override
	public void visit(NumberListNode node) {
		// TODO fill me in
		r = r + node.getData() + ' ';
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}
		
	}

	@Override
	public void visit(AdditionListNode node) {
		// TODO fill me in
		r = r + '+' + ' ';
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}
	}

	@Override
	public void visit(SubtractionListNode node) {
		// TODO fill me in
		r = r + '-' + ' ';
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}
	}

	@Override
	public void visit(MultiplicationListNode node) {
		// TODO fill me in
		r = r + '*' + ' ';
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}

	}

	@Override
	public void visit(DivisionListNode node) {
		// TODO fill me in
		r = r + '/' + ' ';
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}

	}

	@Override
	public void visit(UnaryMinusListNode node) {
		// TODO fill me in
		r = r + '~' + ' ';
		ListNode nextNode = node.getNext();
		if (nextNode != null) {
			nextNode.accept(this);
		}
	}

}
