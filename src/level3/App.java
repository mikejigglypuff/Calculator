package level3;

import level3.enums.Answers;
import level3.enums.OperationTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class App {
    Calculator<Double> calculator = new Calculator<>();
    Parser<Double> parser = new Parser<>(Double.class);

    public void start(BufferedReader br) throws Exception {
        System.out.print("연산자 기호를 입력해주세요[" + OperationTypes.concatOperations() + "]: ");
        char operation = parser.parseOperation(br.readLine());

        int operandNum = OperationTypes.of(operation).getOperandNum();

        System.out.print("연산할 " + operandNum +  "개의 수를 입력하세요: ");
        // 정수가 정확히 2개만 들어오는지 검증하기 위해 nextLong 대신 nextLine() 사용
        // nextLong()으로 입력받을 시 정수가 operandNum를 초과해 들어가면 다른 next() 함수들에 잘못 들어가게 됨
        Double[] nums = Arrays.stream(br.readLine().split(" "))
                .map(Double::valueOf)
                .toArray(Double[]::new);

        System.out.println("연산 결과: " + calculator.calculate(operation, nums));

        boolean continueActions = true;
        while(calculator.getRecordSize() > 0 && continueActions) {
            continueActions = recordAction(br);
        }
    }

    private boolean recordAction(BufferedReader br) throws Exception {
        System.out.print(
                "현재 연산 기록: "+calculator.getRecord()+"\n연산 기록들에 대해 수행할 작업을 선택하세요 (delete/compare/none): "
        );
        String actionMeaning = Answers.getMeaningForAnswer(br.readLine().trim());

        switch (actionMeaning) {
            case "delete":
                deleteAction(br);
                return true;

            case "compare":
                System.out.print("비교할 수를 입력하세요: ");
                double comp = parser.parseNumber(br.readLine());
                System.out.println(comp + "보다 큰 수들: " + calculator.compareRecord(comp));
                return true;

            case "no":
            case "inAction":
                return false;

            default:
                return true;
        }
    }

    private void deleteAction(BufferedReader br) throws Exception {
        System.out.print("연산 기록을 삭제하시겠습니까? (yes 입력 시 삭제): ");
        String deleteRecordCheck = Answers.getMeaningForAnswer(br.readLine());

        if (deleteRecordCheck.equals("yes")) {
            System.out.println("삭제된 연산 기록: " + calculator.deleteRecord());
        }

        // 계산 기록 삭제를 연속적으로 할 수 있게 하기 위함
        boolean continueDeletion = true;

        while (continueDeletion) {
            System.out.print("계속 삭제하시겠습니까?: ");
            String continueDeletionCheck = Answers.getMeaningForAnswer(br.readLine());

            if (continueDeletionCheck.equals("yes")) {
                System.out.println("삭제된 연산 기록: " + calculator.deleteRecord());
            }

            continueDeletion = continueDeletionCheck.equals("yes") && calculator.getRecordSize() > 0;
        }
    }
}
