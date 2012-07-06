package org.bm.utils;

import org.bm.analysis.exception.MathematicalAnalysisException;

public abstract class DelegateFunction {
    protected int nbArgs;

    public DelegateFunction(int nbArgs) {
        super();
        this.nbArgs = nbArgs;
    }

    public abstract String compute(String... args) throws MathematicalAnalysisException;

}
