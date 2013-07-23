package org.bm.analysis.impl;

import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.bm.analysis.Analyzer;
import org.bm.analysis.exception.MathematicalAnalysisException;
import org.bm.utils.ComputeUtils;
import org.bm.utils.Function;
import org.bm.utils.Operator;

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
public class AnalyzerDefaultImpl implements Analyzer {

   @Override
   public String compute(String formula) throws MathematicalAnalysisException {
      Deque<String> stack = new LinkedList<>();
      StringTokenizer st = new StringTokenizer(formula, " ");

      while (st.hasMoreTokens()) {
         String token = st.nextToken();

         if (ComputeUtils.isFunction(token)) {
            Function f = Function.get(token);
            int nbArgs = f.getNbArgs();

            if (stack.size() < nbArgs) {
               throw new MathematicalAnalysisException("The function " + f.getName()
                  + " needs more arguments than the ones supplied.");
            }
            String[] args = new String[nbArgs];
            for (int i = 0; i < nbArgs; i++) {
               args[i] = stack.pop();
            }

            String result = f.compute(args);
            stack.push(result);

         } else if (ComputeUtils.isOperator(token)) {
            Operator operator = Operator.get(token);
            int nbArgs = operator.getNbArgs();

            if (Operator.SUBSTRACTION.equals(operator) && stack.size() == Operator.OPPOSITE.getNbArgs()) {
               // So we've got a "-", but with only 1 argument, so it means it's not the substraction operator but the minus one.
               operator = Operator.OPPOSITE;
               nbArgs = Operator.OPPOSITE.getNbArgs();
            }

            if (stack.size() < nbArgs) {
               throw new MathematicalAnalysisException("The operator " + operator.getValue()
                  + " needs more arguments than the ones supplied.");
            }

            String[] args = new String[nbArgs];
            for (int i = 0; i < nbArgs; i++) {
               args[i] = stack.pop();
            }

            String result = operator.compute(args);
            stack.push(result);

         } else {
            stack.push(token);

         }

      }

      if (stack.size() != 1) {
         throw new MathematicalAnalysisException("Some token are still on the stack, though all the formula has been analyzed.");
      }
      return stack.pop();
   }
}
