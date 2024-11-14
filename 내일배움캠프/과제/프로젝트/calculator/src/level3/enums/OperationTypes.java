package level3.enums;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum OperationTypes {
    ADD('+') {
        public double apply(long x, long y) { return Math.addExact(x, y); }
    },
    SUB('-') {
        public double apply(long x, long y) { return Math.subtractExact(x, y); }
    },
    MUL('*') {
        public double apply(long x, long y) { return Math.multiplyExact(x, y); }
    },
    DIV('/') {
        public double apply(long x, long y) {
            if(y == 0) throw new ArithmeticException("0으로 나눠짐");
            return (double) x / y;
        }
    };

    private final char symbol;
    public abstract double apply(long x, long y);

    OperationTypes(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() { return symbol; }

    public OperationTypes of(char c) {
        return Arrays.stream(values())
                .filter(val -> val.getSymbol() == c)
                .findFirst().orElse(null);
    }
}

