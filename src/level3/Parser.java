package level3;

import level3.enums.NumberConverter;
import level3.enums.OperationTypes;

// App에서 입력값들을 검증하고 파싱하는 책임 분리
public class Parser<T extends Number> {
    private final Class<T> classType;

    public Parser(Class<T> classType) {
        this.classType = classType;
    }

    public T parseNumber(String number) throws IllegalArgumentException {
        return NumberConverter.convert(number, classType);
    }

    public char parseOperation(String input) throws Exception {
        char operation = input.charAt(0);
        String operations = OperationTypes.concatOperations();
        if(operations.indexOf(operation) != -1) {
            return operation;
        }
        throw new Exception(operations + " 중 하나를 입력해주세요.");
    }
}
