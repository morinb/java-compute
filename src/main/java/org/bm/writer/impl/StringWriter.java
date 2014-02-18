package org.bm.writer.impl;

import org.bm.writer.Writer;

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
public class StringWriter implements Writer<String> {
   @Override
   public String write(List<String> tokens) {
      StringBuilder sb = new StringBuilder();
      for (String item : tokens) {
         sb.append(convert(item));
         sb.append(' ');
      }
      return sb.toString().trim();
   }

   @Override
   public String convert(String toConvert) {
      return toConvert;
   }
}
