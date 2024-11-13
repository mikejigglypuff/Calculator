package level2;



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

        System.out.print(
            "현재 연산 기록: "+calculator.getRecord()+"\n연산 기록을 삭제하시겠습니까? (yes 입력 시 삭제): "
        );
        String deleteRecordCheck = sc.nextLine().trim();

        if(deleteRecordCheck.equalsIgnoreCase("yes")) {
            System.out.println(
                "삭제된 연산 기록: " + calculator.deleteRecord() + "\n현재 연산 기록: " + calculator.getRecord()
            );
        }
    }
}
