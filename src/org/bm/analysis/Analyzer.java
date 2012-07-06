package org.bm.analysis;

import org.bm.analysis.exception.MathematicalAnalysisException;

public interface Analyzer {
    /**
     * Interpret a postfix notation formula. All formula's token must be
     * calculable. No undefined variable may remains in the formula.
     * 
     * Here we assume that every token are severed with at least one space.
     * 
     * @param formula
     *            in postfix notation. (RPN)
     * @return the corresponding result.
     * @throws MathematicalAnalysisException
     *             if a function has not enough arguments while interperting the
     *             formula.
     */
    public abstract String compute(String formula) throws MathematicalAnalysisException;
}
