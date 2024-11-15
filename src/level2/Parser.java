package level2;

import java.util.regex.Pattern;

// App에서 입력값들을 검증하고 파싱하는 책임 분리
public class Parser {
    private static final String OPERATIONS = "+-*/";
    private static final String NUMBER_REGEX = "^[0-9]*$";

    public Long parsePositiveNumber(String number) throws Exception {
        if(Pattern.matches(NUMBER_REGEX, number)) {
            return Long.parseLong(number);
        }
        throw new Exception("양의 정수 또는 0을 입력해주세요.");
    }

    public char parseOperation(String input) throws Exception {
        char operation = input.charAt(0);
        if(OPERATIONS.indexOf(operation) != -1) {
            return operation;
        }
        throw new Exception("+, -, *, / 중 하나를 입력해주세요.");
    }
}
