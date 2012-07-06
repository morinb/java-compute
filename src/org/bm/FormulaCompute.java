package org.bm;

import java.util.Map;

import org.bm.analysis.Analyzer;
import org.bm.analysis.exception.MathematicalAnalysisException;
import org.bm.analysis.impl.AnalyzerDefaultImpl;
import org.bm.parser.RPNParser;
import org.bm.parser.RPNParserFactory;

public class FormulaCompute {

    public static String compute(String formula, Map<String, String> variables, boolean verbose) throws MathematicalAnalysisException {
        RPNParser parser = RPNParserFactory.getParser(verbose, variables);
        Analyzer analyzer = new AnalyzerDefaultImpl();
        String actual = analyzer.compute(parser.parse(formula));
        return actual;
    }
}
