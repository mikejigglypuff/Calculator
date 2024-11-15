package level3;

import java.io.BufferedReader;

public class App {
    Calculator<Double> calculator = new Calculator<>();
    Parser<Double> parser = new Parser<>(Double.class);

    public void start(BufferedReader br) throws Exception {
        System.out.print("연산할 두 수를 입력하세요: ");
        // 정수가 정확히 2개만 들어오는지 검증하기 위해 nextLong 대신 nextLine() 사용
        // nextLong()으로 입력받을 시 정수가 3개 이상 들어가면 다른 next() 함수들에 잘못 들어가게 됨
        String[] nums = br.readLine().split(" ");
        if (nums.length != 2) throw new Exception("2개의 수만 입력해주세요.");

        double firstNum = parser.parseNumber(nums[0]);
        double secondNum = parser.parseNumber(nums[1]);

        System.out.print("사칙연산 기호를 입력해주세요: ");

        char operation = parser.parseOperation(br.readLine());
        System.out.println("연산 결과: " + calculator.calculate(firstNum, secondNum, operation));

        boolean continueActions = true;
        while(continueActions) {
            System.out.print(
                    "현재 연산 기록: "+calculator.getRecord()+"\n연산 기록들에 대해 수행할 작업을 선택하세요 (delete/compare/none): "
            );
            String recordAction = br.readLine().trim();

            switch (recordAction) {
                case "delete":
                    System.out.print("연산 기록을 삭제하시겠습니까? (yes 입력 시 삭제): ");
                    String deleteRecordCheck = br.readLine().trim();

                    if (deleteRecordCheck.equalsIgnoreCase("yes")) {
                        System.out.println("삭제된 연산 기록: " + calculator.deleteRecord());
                    }
                    break;

                case "compare":
                    System.out.print("비교할 수를 입력하세요: ");
                    double comp = parser.parseNumber(br.readLine());
                    System.out.println(comp + "보다 큰 수들: " + calculator.compareRecord(comp));
                    break;

                default:
                    continueActions = false;
                    break;
            }

        }
    }
}
