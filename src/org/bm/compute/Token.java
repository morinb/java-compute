package org.bm.compute;

import org.bm.compute.exceptions.ParsingException;

/**
 * A Token stands for the minimal symbol of a formula like a number or an operator
 */
public abstract class Token {
   private final String token;

   public Token(String token) {
      this.token = token;
   }

   public String getTokenString() {
      return token;
   }

   public Number asNumber() throws ParsingException {
      Number n = null;
      try {
         n = Double.parseDouble(token);
      } catch (NumberFormatException nfe) {
         throw new ParsingException(token + " is not a number.", nfe);
      }
      return n;
   }

   public boolean isOperator() {
      return Operator.isOperator(token);
   }

   public boolean isNumber() {
      try {
         Double.parseDouble(token);
         return true;
      } catch (NumberFormatException nfe) {}
      return false;
   }

   public boolean isFunctionArgumentSeparator() {
      return ",".equals(token);
   }

   public boolean isLeftParenthesis() {
      return "(".equals(token);
   }

   public boolean isRightParenthesis() {
      return ")".equals(token);
   }

   public abstract boolean isFunction();

   @Override
   public String toString() {
      return token;
   }
}
