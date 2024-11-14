package level3;

/*
    1. Calculator 클래스 생성
        1-1. 연산 작업을 Calculator에 위임
        1-2. Calculator에서 연산 결과를 저장하도록 함
            1-2-1. 저장에 사용할 컬렉션은 선입선출을 활용할 수 있어야 함
            1-2-2. private 접근자로 외부에서 컬렉션에 직접 접근하지 못하도록 해야 함
        1-3. App에서 Calculator를 사용하도록 함

    2. Calculator의 연산 결과 삭제하도록 하기
        2-1. 연산 이후 연산결과를 삭제할 것인지 입력받을 것
        2-2. Calculator의 컬렉션에서 맨 처음 들어온 값을 삭제하도록 할 것

 */

import java.util.Scanner;

public class App {
    Calculator calculator = new Calculator();
    Parser parser = new Parser();

    public void start(Scanner sc) throws Exception {
        System.out.print("연산할 두 양의 정수를 입력하세요: ");
        // 정수가 정확히 2개만 들어오는지 검증하기 위해 nextLong 대신 nextLine() 사용
        // nextLong()으로 입력받을 시 정수가 3개 이상 들어가면 다른 next() 함수들에 잘못 들어가게 됨
        String[] nums = sc.nextLine().split(" ");
        if (nums.length != 2) throw new Exception("2개의 양의 정수 또는 0만 입력해주세요.");

        long firstNum = parser.parsePositiveNumber(nums[0]);
        long secondNum = parser.parsePositiveNumber(nums[1]);

        System.out.print("사칙연산 기호를 입력해주세요: ");

        char operation = parser.parseOperation(sc.nextLine());
        System.out.println("연산 결과: " + calculator.calculate(firstNum, secondNum, operation));

        boolean continueActions = true;
        while(continueActions) {
            System.out.print(
                "현재 연산 기록: "+calculator.getRecord()+"\n연산 기록들에 대해 수행할 작업을 선택하세요 (delete/compare/none): "
            );
            String recordAction = sc.nextLine().trim();

            switch (recordAction) {
                case "delete":
                    System.out.print("연산 기록을 삭제하시겠습니까? (yes 입력 시 삭제): ");
                    String deleteRecordCheck = sc.nextLine().trim();

                    if (deleteRecordCheck.equalsIgnoreCase("yes")) {
                        System.out.println("삭제된 연산 기록: " + calculator.deleteRecord());
                    }
                    break;

                case "compare":
                    System.out.print("비교할 수를 입력하세요: ");
                    long comp = parser.parsePositiveNumber(sc.nextLine());
                    System.out.println(comp + "보다 큰 수들: " + calculator.compareRecord(comp));
                    break;

                default:
                    continueActions = false;
                    break;
            }

        }
    }
}
