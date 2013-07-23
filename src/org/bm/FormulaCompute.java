package org.bm;

import java.util.HashMap;
import java.util.Map;

import org.bm.analysis.Analyzer;
import org.bm.analysis.exception.MathematicalAnalysisException;
import org.bm.analysis.impl.AnalyzerDefaultImpl;
import org.bm.parser.RPNParser;
import org.bm.parser.RPNParserFactory;
import org.bm.writer.Writer;
import org.bm.writer.impl.StringWriter;

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
 * @author morinb
 */
public class FormulaCompute {

   public static String compute(String formula, Map<String, String> variables, boolean verbose)
      throws MathematicalAnalysisException {
      if (null == variables) {
         variables = new HashMap<>();
      }

      RPNParser parser = RPNParserFactory.getParser(verbose, variables);
      Writer<String> stringWriter = new StringWriter();
      Analyzer analyzer = new AnalyzerDefaultImpl();

      return analyzer.compute(stringWriter.write(parser.parse(formula)));
   }
}
