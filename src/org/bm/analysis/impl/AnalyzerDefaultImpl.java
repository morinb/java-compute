package org.bm.analysis.impl;

import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.bm.analysis.Analyzer;
import org.bm.analysis.exception.MathematicalAnalysisException;
import org.bm.utils.ComputeUtils;
import org.bm.utils.Function;
import org.bm.utils.Operator;

public class AnalyzerDefaultImpl implements Analyzer {

    @Override
    public String compute(String formula) throws MathematicalAnalysisException {
        Deque<String> stack = new LinkedList<String>();
        StringTokenizer st = new StringTokenizer(formula, " ");

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if (ComputeUtils.isFunction(token)) {
                Function f = Function.get(token);
                int nbArgs = f.getNbArgs();

                if (stack.size() < nbArgs) {
                    throw new MathematicalAnalysisException("The function " + f.getName() + " needs more arguments than the ones supplied.");
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

                if (stack.size() < nbArgs) {
                    throw new MathematicalAnalysisException("The operator " + operator.getValue() + " needs more arguments than the ones supplied.");
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
