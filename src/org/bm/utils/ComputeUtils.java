package org.bm.utils;

import java.util.Iterator;
import java.util.Map;

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
public class ComputeUtils {
   public static boolean isOperator(String token) {
      return (null != Operator.get(token));
   }

   public static boolean isFunction(String token) {
      return (null != Function.get(token));
   }

   public static String[] displayVariables(Map<String, String> variables) {
      String[] lines = new String[variables.size()];

      Iterator<String> keys = variables.keySet().iterator();

      int i = 0;
      while (keys.hasNext()) {
         String key = keys.next();
         String val = variables.get(key);

         lines[i++] = key + " = " + val;
      }

      return lines;
   }

   public static String concat(String[] lines, String separator) {
      if (null != lines && null != separator && lines.length > 0) {
         StringBuilder sb = new StringBuilder();

         for (String line : lines) {
            sb.append(line).append(separator);
         }

         return sb.substring(0, sb.length() - separator.length());
      }
      return null;
   }
}
