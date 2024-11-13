package level3;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    private Queue<Double> record = new LinkedList<>();

    public double calculate(long firstNum, long secondNum, char operation) throws ArithmeticException {
        double result = 0;

        switch (operation) {
            case ('+'):
                result = Math.addExact(firstNum, secondNum);
                break;
            case ('-'):
                result = Math.subtractExact(firstNum, secondNum);
                break;
            case ('*'):
                result = Math.multiplyExact(firstNum, secondNum);
                break;
            case ('/'):
                if(secondNum == 0) throw new ArithmeticException("0으로 나눠짐");
                result = (double)firstNum / secondNum;
                break;
        }

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

    public Double deleteRecord() {
        return record.remove();
    }
}