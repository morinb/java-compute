package org.bm.parser;

import java.util.Map;

import org.bm.parser.impl.SYAlgo;

public class RPNParserFactory {
    public static RPNParser getParser(boolean enableLogging, Map<String, String> variables) {
        return new SYAlgo(enableLogging, variables);
    }
}
