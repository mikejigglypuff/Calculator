package level3.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum OperationTypes {
    ADD('+') {
        // Java Generic은 제네릭 값을 원시 자료형으로 한정지을 수 없음
        // 따라서 숫자형 Wrapper 클래스들의 상위 클래스인 Number로 한정시킨 후 doubleValue를 추출해 연산 수행
        public <T extends Number> double apply(T x, T y) {
            double num1 = x.doubleValue(), num2 = y.doubleValue(), result = num1 + num2;
            if(Double.isInfinite(result)) { throw new ArithmeticException("범위 초과"); }
            return result;
        }
    },
    SUB('-') {
        public <T extends Number> double apply(T x, T y) {
            double num1 = x.doubleValue(), num2 = y.doubleValue(), result = num1 - num2;
            if(Double.isInfinite(result)) { throw new ArithmeticException("범위 초과"); }
            return result;
        }
    },
    MUL('*') {
        public <T extends Number> double apply(T x, T y) {
            double num1 = x.doubleValue(), num2 = y.doubleValue(), result = num1 * num2;
            if(Double.isInfinite(result)) { throw new ArithmeticException("범위 초과"); }
            return result;
        }
    },
    DIV('/') {
        public <T extends Number> double apply(T x, T y) {
            double num1 = x.doubleValue(), num2 = y.doubleValue();
            if(num2 == 0) throw new ArithmeticException("0으로 나눠짐");
            return num1 / num2;
        }
    };

    private final char symbol;
    public abstract <T extends Number> double apply(T x, T y);

    OperationTypes(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() { return symbol; }

    public static OperationTypes of(char c) {
        return Arrays.stream(values())
                .filter(val -> val.getSymbol() == c)
                .findFirst().orElse(null);
    }

    public static String concatOperations() {
        StringBuilder sb = new StringBuilder();
        for(OperationTypes ot : values()) {
            sb.append(ot.getSymbol());
        }

        return sb.toString();
    }
}

