package level3;

import level3.enums.OperationTypes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Calculator<T extends Number> {

    // double만으로도 거의 모든 연산 결과를 표현할 수 있다고 판단
    private Queue<Double> record = new LinkedList<>();

    public double calculate(T firstNum, T secondNum, char operation) throws ArithmeticException {
        double result = OperationTypes.of(operation).apply(firstNum, secondNum);
        record.add(result);
        return result;
    }

    // record를 사용할 곳에 맞춰 배열 형식의 문자열로 반환
    public String getRecord() {
        // 많은 문자열을 합치는 데 용이한 클래스 사용
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for(double d : record) {
            sb.append(d).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length()).append("]");

        return sb.toString();
    }

    // 외부에서 setter를 통해 record를 수정하도록 하는 것은 연산 후 record를 무조건 수정해야 한다는 책임을 외부로 떠넘기는 것이라 생각했습니다.
    // 따라서 calculate() 내부에서 자체적으로 record에 값을 추가하도록 했습니다.

    public String deleteRecord() {
        return record.remove().toString();
    }

    public String compareRecord(double comp) {
        return record.stream()
                .filter(r -> r > comp)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}