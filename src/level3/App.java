package level3;

import level3.enums.Answers;
import level3.enums.OperationTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App {
    Calculator<Double> calculator = new Calculator<>();
    Parser<Double> parser = new Parser<>(Double.class);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public boolean start() throws Exception {
        System.out.print("연산자 기호를 입력해주세요 [" + OperationTypes.concatOperations() + "]: ");
        char operation = parser.parseOperation(br.readLine());

        int operandNum = OperationTypes.of(operation).getOperandNum();
        System.out.print("연산할 " + operandNum +  "개의 수를 입력하세요: ");
        Double[] nums = Arrays.stream(br.readLine().split(" "))
                .map(val -> parser.parseNumber(val))
                .toArray(Double[]::new);

        System.out.println("연산 결과: " + calculator.calculate(operation, nums));

        boolean continueActions = true;
        while(calculator.getRecordSize() > 0 && continueActions) {
            continueActions = recordAction();
        }

        return continueApp();
    }

    private boolean recordAction() throws Exception {
        System.out.print(
                "현재 연산 기록: "+calculator.getRecord()+"\n연산 기록들에 대해 수행할 작업을 선택하세요 (delete/compare/none): "
        );
        String actionMeaning = Answers.getMeaningForAnswer(br.readLine());

        switch (actionMeaning) {
            case "delete":
                deleteAction();
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

    private void deleteAction() throws Exception {
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

    private boolean continueApp() throws IOException {
        while (true) {
            System.out.print("더 계산하시겠습니까? (yes/no 또는 exit): ");

            // 사용자 편의를 위해 대소문자 구별하지 않고 입력받음
            String continueCheck = Answers.getMeaningForAnswer(br.readLine());

            switch (continueCheck) {
                case "yes":
                    return true;

                case "no":
                case "exit":
                    return false;
            }
        }
    }
}
