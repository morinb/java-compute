package org.bm.analysis;

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
 * 
 * @author morinb
 * 
 */
public interface Analyzer {
   /**
    * Interpret a postfix notation formula. All formula's token must be
    * calculable. No undefined variable may remains in the formula.
    * 
    * Here we assume that every token are severed with at least one space.
    * 
    * @param formula
    *            in postfix notation. (RPN)
    * @return the corresponding result.
    * @throws MathematicalAnalysisException
    *             if a function has not enough arguments while interperting the
    *             formula.
    * 
    */
   public abstract String compute(String formula) throws MathematicalAnalysisException;
}
