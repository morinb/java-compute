package org.bm.utils;

import java.util.Iterator;
import java.util.Map;

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
        } else {
            return null;

        }
    }
}
