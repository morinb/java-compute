import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.bm.FormulaCompute;
import org.bm.analysis.Analyzer;
import org.bm.analysis.exception.MathematicalAnalysisException;
import org.bm.analysis.impl.AnalyzerDefaultImpl;
import org.bm.parser.RPNParser;
import org.bm.parser.RPNParserFactory;
import org.bm.utils.ComputeUtils;
import org.bm.utils.Function;
import org.bm.utils.Operator;

public class RPNParserTestCase extends TestCase {
    private static final Logger logger = Logger.getLogger(RPNParserTestCase.class);

    public void testOperatorPlus() {
        Operator plus = Operator.ADDITION;

        Operator parsed = Operator.get("+");
        Operator wrong = Operator.get("*");

        assertEquals(plus, parsed);
        assertNotSame(plus, wrong);

    }

    public void testSYAlgo() {
        String wanted = "0 3 - x 2 * Z0 5 - 2 35 ^ ^ / +";
        String calcul = "(0-3)+x*2/(Z0-5 )^2^y'";

        Map<String, String> map = new HashMap<String, String>();
        map.put("x", null);
        map.put("y'", "35");
        map.put("Z0", null);
        map.put("m", null);
        map.put("g", null);

        Map<String, Integer> func = new HashMap<String, Integer>();
        func.put("eval", 2);
        func.put("sqrt", 1);
        func.put("log", 1);
        func.put("exp", 1);
        func.put("ln", 1);

        RPNParser algo = RPNParserFactory.getParser(false, map);
        String resultat = algo.parse(calcul);
        assertEquals(wanted, resultat);
        if (logger.isInfoEnabled()) {
            logger.info("RPN : '" + calcul + "' = '" + resultat + "'");
            logger.info("");
        }
        calcul = "5+((1+2)*4)-3";
        wanted = "5 1 2 + 4 * + 3 -";

        resultat = algo.parse(calcul);
        assertEquals(wanted, resultat);
        if (logger.isInfoEnabled()) {
            logger.info("RPN : '" + calcul + "' = '" + resultat + "'");
            logger.info("");
        }

        wanted = "1 2 / m g * 2 ^ m log + g exp + * sqrt";
        calcul = "sqrt((1/2)*(m*g)^2+log(m) + exp(g))";
        resultat = algo.parse(calcul);
        assertEquals(wanted, resultat);
        if (logger.isInfoEnabled()) {
            logger.info("RPN : '" + calcul + "' = '" + resultat + "'");
            logger.info("");
        }

    }

    public void testFunctions() throws MathematicalAnalysisException {
        String actual = Function.SQRT.compute("4");
        String expected = new Double(Math.sqrt(4)).toString();

        assertEquals(expected, actual);
        if (logger.isInfoEnabled()) {
            logger.info("Function : '" + Function.SQRT + "(4)' = '" + actual + "'");
            logger.info("");
        }

        actual = Function.LOG.compute("10");
        expected = new Double(Math.log10(10)).toString();

        assertEquals(expected, actual);
        if (logger.isInfoEnabled()) {
            logger.info("Function : '" + Function.LOG + "(10)' = '" + actual + "'");
            logger.info("");
        }

        actual = Function.EXP.compute(Double.toString(Double.NEGATIVE_INFINITY));
        expected = new Double(Math.exp(Double.NEGATIVE_INFINITY)).toString();

        assertEquals(expected, actual);
        if (logger.isInfoEnabled()) {
            logger.info("Function : '" + Function.EXP + "(" + Double.NEGATIVE_INFINITY + ")' = '" + actual + "'");
            logger.info("");
        }

    }

    public void testAnalyzer() throws MathematicalAnalysisException {
        String formula = "3 4 -";
        Analyzer analyzer = new AnalyzerDefaultImpl();
        String expected = "-1.0";

        String actual = analyzer.compute(formula);

        assertEquals(expected, actual);
        if (logger.isInfoEnabled()) {
            logger.info("Formula : '" + formula + "' = '" + actual + "'");
            logger.info("");
        }
        formula = "10 log";
        expected = "1.0";
        actual = analyzer.compute(formula);
        assertEquals(expected, actual);
        if (logger.isInfoEnabled()) {
            logger.info("Formula : '" + formula + "' = '" + actual + "'");
            logger.info("");
        }

    }

    public void testAllProcess() throws MathematicalAnalysisException {
        String formula = "sqrt(a^2+b^2)";
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("a", "3");
        variables.put("b", "4");

        String actual = FormulaCompute.compute(formula, variables, false);

        String expected = "5.0";
        assertEquals(expected, actual);

        if (logger.isInfoEnabled()) {
            logger.info("Formula : '" + formula + "' = '" + actual + "'");
            logger.info("");
        }

        formula = "sqrt((1/4)*(m*g)^2) + log(10) - exp(0)";
        variables = new HashMap<String, String>();
        variables.put("m", "3");
        variables.put("g", "4");

        actual = FormulaCompute.compute(formula, variables, false);

        expected = "6.0";
        assertEquals(expected, actual);

        if (logger.isInfoEnabled()) {
            logger.info("Formula : '" + formula + "' = '" + actual + "'");
            logger.info("With : " + ComputeUtils.concat(ComputeUtils.displayVariables(variables), ", "));
            logger.info("");
        }

    }
}
