package org.bm.utils;

import org.bm.analysis.exception.MathematicalAnalysisException;

/**
 * Describe an operator, with its String representation, its precedence, and if
 * it is left-associative or not.
 * 
 * @author X082271
 * 
 */
public enum Operator {
    /**
     * The addition operator.
     */
    ADDITION("+", 10, true, 2, new DelegateFunction(2) {
        @Override
        public String compute(String... args) throws MathematicalAnalysisException {
            if (args == null) {
                throw new MathematicalAnalysisException("ADDITION: Args must not be null");
            }
            if (args.length != nbArgs) {
                throw new MathematicalAnalysisException("ADDITION: function needs " + nbArgs + " exactly argument(s).");
            }

            // As the stack stores in LIFO mode, the 2nd parameter is popped
            // first.
            String arg2 = args[0];
            String arg1 = args[1];
            Double d1 = null;
            Double d2 = null;
            try {
                d1 = Double.parseDouble(arg1);
                d2 = Double.parseDouble(arg2);
            } catch (NumberFormatException e) {
                throw new MathematicalAnalysisException("ADDITION: the arguments must be Numbers.", e);
            }

            Double d = null;
            if (null != d1 & null != d2) {
                d = d1 + d2;
            }

            return d.toString();
        }
    }),
    /**
     * The substraction operator
     */
    SUBSTRACTION("-", 10, true, 2, new DelegateFunction(2) {
        @Override
        public String compute(String... args) throws MathematicalAnalysisException {
            if (args == null) {
                throw new MathematicalAnalysisException("SUBSTRACTION: Args must not be null");
            }
            if (args.length != nbArgs) {
                throw new MathematicalAnalysisException("SUBSTRACTION: function needs " + nbArgs + " exactly argument(s).");
            }

            // As the stack stores in LIFO mode, the 2nd parameter is popped
            // first.
            String arg2 = args[0];
            String arg1 = args[1];
            Double d1 = null;
            Double d2 = null;
            try {
                d1 = Double.parseDouble(arg1);
                d2 = Double.parseDouble(arg2);
            } catch (NumberFormatException e) {
                throw new MathematicalAnalysisException("SUBSTRACTION: the arguments must be Numbers.", e);
            }

            Double d = null;
            if (null != d1 & null != d2) {
                d = d1 - d2;
            }

            return d.toString();
        }
    }),
    /**
     * The multiplication operator.
     */
    MULTIPLICATION("*", 100, true, 2, new DelegateFunction(2) {
        @Override
        public String compute(String... args) throws MathematicalAnalysisException {
            if (args == null) {
                throw new MathematicalAnalysisException("MULTIPLICATION: Args must not be null");
            }
            if (args.length != nbArgs) {
                throw new MathematicalAnalysisException("MULTIPLICATION: function needs " + nbArgs + " exactly argument(s).");
            }

            // As the stack stores in LIFO mode, the 2nd parameter is popped
            // first.
            String arg2 = args[0];
            String arg1 = args[1];
            Double d1 = null;
            Double d2 = null;
            try {
                d1 = Double.parseDouble(arg1);
                d2 = Double.parseDouble(arg2);
            } catch (NumberFormatException e) {
                throw new MathematicalAnalysisException("MULTIPLICATION: the arguments must be Numbers.", e);
            }

            Double d = null;
            if (null != d1 & null != d2) {
                d = d1 * d2;
            }

            return d.toString();
        }
    }),
    /**
     * The division operator.
     */
    DIVISION("/", 100, true, 2, new DelegateFunction(2) {
        @Override
        public String compute(String... args) throws MathematicalAnalysisException {
            if (args == null) {
                throw new MathematicalAnalysisException("DIVISION: Args must not be null");
            }
            if (args.length != nbArgs) {
                throw new MathematicalAnalysisException("DIVISION: function needs " + nbArgs + " exactly argument(s).");
            }

            // As the stack stores in LIFO mode, the 2nd parameter is popped
            // first.
            String arg2 = args[0];
            String arg1 = args[1];
            Double d1 = null;
            Double d2 = null;
            try {
                d1 = Double.parseDouble(arg1);
                d2 = Double.parseDouble(arg2);
            } catch (NumberFormatException e) {
                throw new MathematicalAnalysisException("DIVISION: the arguments must be Numbers.", e);
            }

            Double d = null;
            if (null != d1 & null != d2) {
                d = d1 / d2;
            }

            return d.toString();
        }
    }),
    /**
     * The power operator.
     */
    POWER("^", 1000, false, 2, new DelegateFunction(2) {
        @Override
        public String compute(String... args) throws MathematicalAnalysisException {
            if (args == null) {
                throw new MathematicalAnalysisException("POWER: Args must not be null");
            }
            if (args.length != nbArgs) {
                throw new MathematicalAnalysisException("POWER: function needs " + nbArgs + " exactly argument(s).");
            }

            // As the stack stores in LIFO mode, the 2nd parameter is popped
            // first.
            String arg2 = args[0];
            String arg1 = args[1];
            Double d1 = null;
            Double d2 = null;
            try {
                d1 = Double.parseDouble(arg1);
                d2 = Double.parseDouble(arg2);
            } catch (NumberFormatException e) {
                throw new MathematicalAnalysisException("POWER: the arguments must be Numbers.", e);
            }

            Double d = null;
            if (null != d1 & null != d2) {
                d = Math.pow(d1, d2);
            }

            return d.toString();
        }
    });

    private Operator(String valeur, int priorite, boolean leftAssociative, int nbArgs, DelegateFunction delegate) {
        this.value = valeur;
        this.precedence = priorite;
        this.leftAssociative = leftAssociative;
        this.nbArgs = nbArgs;
        this.delegate = delegate;
    }

    public String getValue() {
        return value;
    }

    public int getPrecedence() {
        return precedence;
    }

    public boolean isLeftAssociative() {
        return leftAssociative;
    }

    public boolean isRightAssociative() {
        return !leftAssociative;
    }

    public int getNbArgs() {
        return nbArgs;
    }

    /**
     * Gets an operator by passing its value.
     * 
     * @param value
     *            An operator string value, i.e. + - /
     * @return the operator corresponding to the value parameter. Or null if no
     *         operator was found.
     */
    public static Operator get(String value) {
        Operator[] operators = Operator.values();

        for (int i = 0; i < operators.length; i++) {
            String op = operators[i].value;
            if (op.equals(value)) {
                return operators[i];
            }
        }

        return null;
    }

    private String value;
    private int precedence;
    private boolean leftAssociative;
    private int nbArgs;
    private DelegateFunction delegate;

    @Override
    public String toString() {
        return value;
    }

    public String compute(String... args) throws MathematicalAnalysisException {
        return delegate.compute(args);
    }

}
