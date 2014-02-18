package org.bm.utils;

import org.bm.analysis.exception.MathematicalAnalysisException;

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
public enum Function {
   /**
    * The Sqrt function.
    */
   SQRT("sqrt", 1, new DelegateFunction(1) {
      @Override
      public String compute(String... args) {
         if (args == null) {
            throw new NullPointerException("SQRT: Args must not be null");
         }
         if (args.length != nbArgs) {
            throw new IllegalArgumentException("SQRT: function needs " + nbArgs + " exactly argument(s).");
         }

         String arg = args[0];
         Double d;
         try {
            d = Double.parseDouble(arg);
         } catch (NumberFormatException e) {
            throw new IllegalArgumentException("SQRT: the argument must be a Number.", e);
         }

         d = Math.sqrt(d);

         return d.toString();
      }
   }),

   /**
    * The Log base 10 function.
    */
   LOG("log", 1, new DelegateFunction(1) {
      @Override
      public String compute(String... args) {
         if (args == null) {
            throw new NullPointerException("LOG: Args must not be null");
         }
         if (args.length != nbArgs) {
            throw new IllegalArgumentException("LOG: function needs " + nbArgs + " exactly argument(s).");
         }

         String arg = args[0];
         Double d;
         try {
            d = Double.parseDouble(arg);
         } catch (NumberFormatException e) {
            throw new IllegalArgumentException("LOG: the argument must be a Number.", e);
         }

         d = Math.log10(d);

         return d.toString();
      }
   }),

   /**
     * 
     */
   EXP("exp", 1, new DelegateFunction(1) {
      @Override
      public String compute(String... args) {
         if (args == null) {
            throw new NullPointerException("EXP: Args must not be null");
         }
         if (args.length != nbArgs) {
            throw new IllegalArgumentException("EXP: function needs " + nbArgs + " exactly argument(s).");
         }

         String arg = args[0];
         Double d;
         try {
            d = Double.parseDouble(arg);
         } catch (NumberFormatException e) {
            throw new IllegalArgumentException("EXP: the argument must be a Number.", e);
         }

         d = Math.exp(d);

         return d.toString();
      }
   }), ;

   private final String name;

   private final DelegateFunction delegate;

   private final int nbArgs;

   private Function(String name, int nbArgs, DelegateFunction function) {
      this.name = name;
      this.delegate = function;
      this.nbArgs = nbArgs;
   }

   public String compute(String... args) throws MathematicalAnalysisException {
      return delegate.compute(args);
   }

   public String getName() {
      return name;
   }

   public int getNbArgs() {
      return nbArgs;
   }

   @Override
   public String toString() {
      return name;
   }

   /**
    * Gets a function by passing its name.
    * 
    * @param value
    *            An function string value, i.e. sqrt log exp
    * @return the function corresponding to the name parameter. Or null if no
    *         function was found.
    */
   public static Function get(String value) {
      Function[] functions = Function.values();

      for (Function function : functions) {
         String op = function.name;
         if (op.equals(value)) {
            return function;
         }
      }

      return null;
   }
}
