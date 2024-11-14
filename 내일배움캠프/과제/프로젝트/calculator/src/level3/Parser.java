package level3;

import level3.enums.NumberConverter;

import java.util.regex.Pattern;

// App에서 입력값들을 검증하고 파싱하는 책임 분리
public class Parser<T extends Number> {
    private static final String OPERATIONS = "+-*/";
    private static final String NUMBER_REGEX = "^[0-9]*$";
    private final Class<T> classType;

    public Parser(Class<T> classType) {
        this.classType = classType;
    }

    public T parseNumber(String number) throws Exception {
        return NumberConverter.convert(number, classType);
    }

    public char parseOperation(String input) throws Exception {
        char operation = input.charAt(0);
        if(OPERATIONS.indexOf(operation) != -1) {
            return operation;
        }
        throw new Exception("+, -, *, / 중 하나를 입력해주세요.");
    }
}
