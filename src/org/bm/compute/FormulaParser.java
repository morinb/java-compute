package org.bm.compute;

import java.util.Deque;

import org.bm.compute.exceptions.ParsingException;

public interface FormulaParser {
   String parse(Deque<Token> formula) throws ParsingException;

   Deque<Token> tokenize(String formula);
}
