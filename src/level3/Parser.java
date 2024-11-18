package level3;

import level3.enums.NumberConverter;
import level3.enums.OperationTypes;

// App에서 입력값들을 검증하고 파싱하는 책임 분리
public class Parser<T extends Number> {
    private final Class<T> classType;

    public Parser(Class<T> classType) {
        this.classType = classType;
    }

    // 문자열로 입력된 수를 제네릭 타입에 따라 변환
    // switch 문으로 일일이 타입을 확인하는 것보다 더 우아하게 구현하려 함
    public T parseNumber(String number) throws IllegalArgumentException {
        return NumberConverter.convert(number, classType);
    }

    // 연산자가 정의된 상수들 내에 있는지 검증
    public char parseOperation(String input) throws Exception {
        char operation = input.charAt(0);
        String operations = OperationTypes.concatOperations();
        if (operations.indexOf(operation) != -1) {
            return operation;
        }
        throw new Exception(operations + " 중 하나를 입력해주세요.");
    }
}
