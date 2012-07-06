package org.bm.parser;

public interface RPNParser {

	/**
	 * Translate an infix notation to a postfix notation, also called Reverse
	 * Polish Notation.
	 * 
	 * @param formula
	 *            in infix notation.
	 * @return the corresponding postfix notation.
	 */
	public abstract String parse(String formula);

}