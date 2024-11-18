package level3.enums;

import java.util.Arrays;

public enum OperationTypes {
    ADD('+', 2) {
        // Java Generic은 제네릭 값을 원시 자료형으로 한정지을 수 없음
        // 따라서 숫자형 Wrapper 클래스들의 상위 클래스인 Number로 한정시킨 후 doubleValue를 추출해 연산 수행

        public final <T extends Number> double apply(T[] numbers) {
            validateOperandNum(numbers);
            double result = numbers[0].doubleValue() + numbers[1].doubleValue();
            checkInfinityResult(result);
            return result;
        }
    },
    SUB('-', 2) {
        public final <T extends Number> double apply(T[] numbers) {
            validateOperandNum(numbers);
            double result = numbers[0].doubleValue() - numbers[1].doubleValue();
            checkInfinityResult(result);
            return result;
        }
    },
    MUL('*', 2) {
        public final <T extends Number> double apply(T[] numbers) {
            validateOperandNum(numbers);
            double result = numbers[0].doubleValue() * numbers[1].doubleValue();
            checkInfinityResult(result);
            return result;
        }
    },
    DIV('/', 2) {
        public final <T extends Number> double apply(T[] numbers) {
            validateOperandNum(numbers);
            double num1 = numbers[0].doubleValue(), num2 = numbers[1].doubleValue();
            if(num2 == 0) throw new ArithmeticException("0으로 나눠짐");
            return num1 / num2;
        }
    },
    MOD('%', 2) {
        public final <T extends Number> double apply(T[] numbers) {
            validateOperandNum(numbers);
            double num1 = numbers[0].doubleValue(), num2 = numbers[1].doubleValue();
            if(num2 == 0) throw new ArithmeticException("0으로 나눠짐");
            return num1 % num2;
        }
    },
    POW2('^', 1) {
        public final <T extends Number> double apply(T[] numbers) {
            validateOperandNum(numbers);
            double num = numbers[0].doubleValue();
            double result = num * num;
            checkInfinityResult(result);
            return result;
        }
    };

    private final char symbol;
    private final int operandNum;
    public abstract <T extends Number> double apply(T[] numbers);

    OperationTypes(char symbol, int operandNum) {
        this.symbol = symbol;
        this.operandNum = operandNum;
    }

    public char getSymbol() { return symbol; }
    public int getOperandNum() { return operandNum; }

    public static OperationTypes of(char c) {
        return Arrays.stream(values())
                .filter(val -> val.getSymbol() == c)
                .findFirst().orElse(null);
    }

    public static String concatOperations() {
        StringBuilder sb = new StringBuilder();
        for(OperationTypes ot : values()) {
            sb.append(ot.getSymbol()).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    protected <T extends Number> void validateOperandNum(T[] numbers) {
        int operandNum = this.getOperandNum();
        if(numbers.length != operandNum) {
            throw new IllegalArgumentException(operandNum + "개의 수를 입력해주세요.");
        }
    }
    
    protected void checkInfinityResult(double result) {
        if(Double.isInfinite(result)) { throw new ArithmeticException("범위 초과"); }
    }
}

