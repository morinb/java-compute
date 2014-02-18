import java.util.HashMap;
import java.util.Map;

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
import org.bm.writer.Writer;
import org.bm.writer.impl.StringWriter;

import junit.framework.TestCase;

/**
 * Copyright 2012 B. MORIN
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * 
 * @author morinb
 * 
 */
public class RPNParserTestCase extends TestCase {
   private static final Logger logger = Logger.getLogger(RPNParserTestCase.class);

   public void testOperatorPlus() {
      Operator plus = Operator.ADDITION;

      Operator parsed = Operator.get("+");
      Operator wrong = Operator.get("*");

      assertEquals(plus, parsed);
      assertNotSame(plus, wrong);

   }

   public void testSYAlgo() throws MathematicalAnalysisException {
      String wanted = "3 _ x 2 * Z0 5 - 2 35 ^ ^ / +";
      String calcul = "(-3)+x*2/(Z0-5 )^2^y'";

      Writer<String> stringWriter = new StringWriter();

      Map<String, String> map = new HashMap<>();
      map.put("x", null);
      map.put("y'", "35");
      map.put("Z0", null);
      map.put("m", null);
      map.put("g", null);

      RPNParser algo = RPNParserFactory.getParser(true, map);
      String resultat = stringWriter.write(algo.parse(calcul));
      assertEquals(wanted, resultat);
      if (logger.isInfoEnabled()) {
         logger.info("RPN : '" + calcul + "' = '" + resultat + "'");
         logger.info("");
      }
      calcul = "5+((1+2)*4)-3";
      wanted = "5 1 2 + 4 * + 3 -";

      resultat = stringWriter.write(algo.parse(calcul));
      assertEquals(wanted, resultat);
      if (logger.isInfoEnabled()) {
         logger.info("RPN : '" + calcul + "' = '" + resultat + "'");
         logger.info("");
      }

      wanted = "1 2 / m g * 2 ^ m log + g exp + * sqrt";
      calcul = "sqrt((1/2)*(m*g)^2+log(m) + exp(g))";
      resultat = stringWriter.write(algo.parse(calcul));
      assertEquals(wanted, resultat);
      if (logger.isInfoEnabled()) {
         logger.info("RPN : '" + calcul + "' = '" + resultat + "'");
         logger.info("");
      }

   }

   public void testFunctions() throws MathematicalAnalysisException {
      String actual = Function.SQRT.compute("4");
      String expected = Double.toString(Math.sqrt(4));

      assertEquals(expected, actual);
      if (logger.isInfoEnabled()) {
         logger.info("Function : '" + Function.SQRT + "(4)' = '" + actual + "'");
         logger.info("");
      }

      actual = Function.LOG.compute("10");
      expected = Double.toString(Math.log10(10));

      assertEquals(expected, actual);
      if (logger.isInfoEnabled()) {
         logger.info("Function : '" + Function.LOG + "(10)' = '" + actual + "'");
         logger.info("");
      }

      actual = Function.EXP.compute(Double.toString(Double.NEGATIVE_INFINITY));
      expected = Double.toString(Math.exp(Double.NEGATIVE_INFINITY));

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
      Map<String, String> variables = new HashMap<>();
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
      variables = new HashMap<>();
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

      formula = "3^10^2";
      variables = new HashMap<>();

      actual = FormulaCompute.compute(formula, variables, false);

      expected = "" + Math.pow(3, Math.pow(10, 2));
      assertEquals(expected, actual);

      if (logger.isInfoEnabled()) {
         logger.info("Formula : '" + formula + "' = '" + actual + "'");
         logger.info("With : " + ComputeUtils.concat(ComputeUtils.displayVariables(variables), ", "));
         logger.info("");
      }

      formula = "(3^10)^2";
      variables = new HashMap<>();

      actual = FormulaCompute.compute(formula, variables, false);

      expected = "" + Math.pow(Math.pow(3, 10), 2);
      assertEquals(expected, actual);

      if (logger.isInfoEnabled()) {
         logger.info("Formula : '" + formula + "' = '" + actual + "'");
         logger.info("With : " + ComputeUtils.concat(ComputeUtils.displayVariables(variables), ", "));
         logger.info("");
      }
   }

   public void testSub() throws MathematicalAnalysisException {
      String formula = "(-1) + (-2)";
      Map<String, String> variables = new HashMap<>();

      String actual = FormulaCompute.compute(formula, variables, true);

      String expected = "-3.0";
      assertEquals(expected, actual);
      if (logger.isInfoEnabled()) {
         logger.info("Formula : '" + formula + "' = '" + actual + "'");
         logger.info("");
      }

      formula = "3 +(" + actual + ")";
      actual = FormulaCompute.compute(formula, variables, false);

      expected = "0.0";
      assertEquals(expected, actual);
      if (logger.isInfoEnabled()) {
         logger.info("Formula : '" + formula + "' = '" + actual + "'");
         logger.info("");
      }
   }

   public void testVars() throws MathematicalAnalysisException {
      String formula = "x^2-x-1";
      Map<String, String> variables = new HashMap<>();
      variables.put("x", "(sqrt(5)+1)/2");

      String actual = FormulaCompute.compute(formula, variables, true);
      String expected = "0.0";
      assertEquals(expected, actual);
      if (logger.isInfoEnabled()) {
         logger.info("Formula : '" + formula + "' = '" + actual + "'");
         logger.info("");
         logger.info("With : " + ComputeUtils.concat(ComputeUtils.displayVariables(variables), ", "));
      }
   }
}
