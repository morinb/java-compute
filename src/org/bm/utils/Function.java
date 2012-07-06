package org.bm.utils;

import org.bm.analysis.exception.MathematicalAnalysisException;

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
            Double d = null;
            try {
                d = Double.parseDouble(arg);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("SQRT: the argument must be a Number.", e);
            }

            if (null != d) {
                d = Math.sqrt(d);
            }

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
            Double d = null;
            try {
                d = Double.parseDouble(arg);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("LOG: the argument must be a Number.", e);
            }

            if (null != d) {
                d = Math.log10(d);
            }

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
            Double d = null;
            try {
                d = Double.parseDouble(arg);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("EXP: the argument must be a Number.", e);
            }

            if (null != d) {
                d = Math.exp(d);
            }

            return d.toString();
        }
    }), ;

    private String name;
    private DelegateFunction delegate;
    private int nbArgs;

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

    public void setName(String name) {
        this.name = name;
    }

    public int getNbArgs() {
        return nbArgs;
    }

    public void setNbArgs(int nbArgs) {
        this.nbArgs = nbArgs;
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

        for (int i = 0; i < functions.length; i++) {
            String op = functions[i].name;
            if (op.equals(value)) {
                return functions[i];
            }
        }

        return null;
    }
}
