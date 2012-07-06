package org.bm.parser;

import java.util.Map;

import org.bm.parser.impl.SYAlgo;

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
public class RPNParserFactory {
	public static RPNParser getParser(boolean enableLogging,
			Map<String, String> variables) {
		return new SYAlgo(enableLogging, variables);
	}
}
