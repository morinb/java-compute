package org.bm.compute;

import java.util.Deque;

import org.bm.compute.exceptions.ParsingException;

public enum Operator {
   PLUS("+", true, 100) {
      @Override
      public String compute(Deque<Token> args) throws ParsingException {
         Token arg2 = args.pop();
         Token arg1 = args.pop();

         return Double.toString(arg1.asNumber().doubleValue() + arg2.asNumber().doubleValue());
      }
   };

   String code;

   boolean leftAssociative;

   int precedence;

   public String getCode() {
      return code;
   }

   public boolean isLeftAssociative() {
      return leftAssociative;
   }

   public boolean isPrecedenceHigherThan(Operator otherOperator) {
      return precedence > otherOperator.precedence;
   }

   public boolean isPrecedenceLowerThan(Operator otherOperator) {
      return precedence < otherOperator.precedence;
   }

   public boolean isPrecedenceHigherThanOrEqual(Operator otherOperator) {
      return precedence >= otherOperator.precedence;
   }

   public boolean isPrecedenceLowerThanOrEqual(Operator otherOperator) {
      return precedence <= otherOperator.precedence;
   }

   private Operator(String code, boolean leftAssociative, int precedence) {
      this.code = code;
      this.leftAssociative = leftAssociative;
      this.precedence = precedence;
   }

   public abstract String compute(Deque<Token> args) throws ParsingException;

   public static Operator fromToken(Token token) throws ParsingException {
      if (token.isOperator()) {
         return fromCode(token.getTokenString());
      }
      throw new ParsingException("Token " + token + " is not a valid Operator.");
   }

   public static Operator fromCode(String code) throws ParsingException {
      for (Operator operator : values()) {
         if (operator.getCode().equals(code)) {
            return operator;
         }
      }
      throw new ParsingException("Token " + code + " is not a valid Operator.");
   }

   public static boolean isOperator(String code) {
      for (Operator operator : values()) {
         if (operator.getCode().equals(code)) {
            return true;
         }
      }
      return false;
   }
}
