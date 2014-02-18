package org.bm.parser;

import org.bm.analysis.exception.MathematicalAnalysisException;

import java.util.List;

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
public interface RPNParser {

   /**
    * Translate an infix notation to a postfix notation, also called Reverse
    * Polish Notation.
    * 
    * @param formula
    *            in infix notation.
    * @return the corresponding postfix notation in form of a list of tokens.
    */
   public abstract List<String> parse(String formula) throws MathematicalAnalysisException;

}