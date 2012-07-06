package org.bm.compute.impl;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

import org.bm.compute.FormulaParser;
import org.bm.compute.Operator;
import org.bm.compute.Token;
import org.bm.compute.exceptions.ParsingException;

public class RPNParser implements FormulaParser {

   @Override
   public String parse(Deque<Token> formula) throws ParsingException {
      Deque<Token> operatorStack = new ArrayDeque<Token>();
      Deque<Token> outputQueue = new ArrayDeque<Token>();

      // Run the shunting-yard algorithm.

      // While there are tokens to be read
      while (!formula.isEmpty()) {
         // Read a token
         Token token = formula.pop();

         // If the token is a number, add it to the output queue
         if (token.isNumber()) {
            outputQueue.push(token);
            break;
         }

         // If token is a function token, then push it onto the stack
         if (token.isFunction()) {
            operatorStack.push(token);
            break;
         }

         // If token is a function argument separator (e.g., a comma)
         if (token.isFunctionArgumentSeparator()) {
            // until the token at the top of the stack is a left parenthesis, pop operators off the stack onto the output queue. 
            //If no parentheses are encountered, either the separator was misplaced or parenthesis were mismatched.
            while (!operatorStack.peek().isLeftParenthesis()) {
               outputQueue.push(operatorStack.pop());
            }
            checkEmpty(operatorStack);
            break;
         }

         // If the token is an operator o1, then:
         if (token.isOperator()) {
            Operator o1 = Operator.fromToken(token);
            // while there is an operator token, o2, at the top of the stack, and

            while ((formula.peek()).isOperator()) {
               // either o1 is left-associative and its precedence is less than or equal to that of o2,
               // or o1 has precedence less than that of o2,
               Operator o2 = Operator.fromToken(formula.peek());
               if ((o1.isLeftAssociative() && o2.isPrecedenceLowerThanOrEqual(o1)) || o1.isPrecedenceLowerThan(o2)) {

                  // pop o2 off the stack, onto the output queue;
                  outputQueue.push(operatorStack.pop());
               }
            }
            // push o1 onto the stack
            outputQueue.push(token);
            break;
         }

         // If the token is a left parenthesis, then push it onto the stack.
         if (token.isLeftParenthesis()) {
            operatorStack.push(token);
            break;
         }

         // If the token is a right parenthesis:
         if (token.isRightParenthesis()) {
            // Until the token at the top of the stack is a left parenthesis, pop operators off the stack onto the output queue.
            checkEmpty(operatorStack);
            while (!operatorStack.peek().isLeftParenthesis()) {
               outputQueue.push(operatorStack.pop());
            }

            // Pop the left parenthesis from the stack, but not onto the output queue.
            outputQueue.push(operatorStack.pop());

            //If the token at the top of the stack is a function token, pop it onto the output queue.
            if (operatorStack.peek().isFunction()) {
               outputQueue.push(operatorStack.pop());
            }
            // If the stack runs out without finding a left parenthesis, then there are mismatched parentheses.
            checkEmpty(operatorStack);
            break;
         }

         // When there are no more tokens to read:
         if (formula.isEmpty()) {
            // While there are still operator tokens in the stack:
            while (!operatorStack.isEmpty()) {
               // If the operator token on the top of the stack is a parenthesis, then there are mismatched parentheses.
               if (operatorStack.peek().isLeftParenthesis()) {
                  throw new ParsingException("Parenthesis were mismatched.");
               }
               // Pop the operator onto the output queue.
               outputQueue.push(operatorStack.pop());
            }
         }
      }

      return join(" ", outputQueue);
   }

   private void checkEmpty(Deque<Token> operatorStack) throws ParsingException {
      if (operatorStack.isEmpty()) {
         throw new ParsingException("Separator was misplaced or parenthesis were mismatched.");
      }
   }

   @Override
   public Deque<Token> tokenize(String formula) {
      String f = new String(formula.trim());
      // put some space around operators
      for (Operator o : Operator.values()) {
         f = f.replaceAll(o.getCode(), " " + o.getCode() + " ");
      }
      // remove duplicate spaces
      f = f.replaceAll("\\s+", " ");
      String[] array = f.split(" ");
      Deque<Token> queue = new ArrayDeque<Token>();

      for (String element : array) {

      }

      return quere;
   }

   private String join(String join, Collection<Token> array) {
      StringBuilder sb = new StringBuilder();
      for (Token element : array) {
         sb.append(element).append(join);
      }

      return sb.toString().trim();
   }
}
