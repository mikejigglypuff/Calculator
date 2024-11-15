package level1;

/*
    1. 양의 정수 두 개 입력받기
        1-1. 두 개가 아닐 경우 InputFormatException 반환할 것
        1-2. 입력받은 수가 양수 또는 0이 아닐 경우 Exception 반환할 것
        1-3. 입력받은 수가 자료형을 초과하면 InputMismatchException이 발생한다는 것 기억하기
        1-4. ArithmeticException을 제외한 예외가 던져지면 처음부터 다시 입력받기

    2. 연산자 하나 입력받기
        2-1. 입력이 유효한 연산자인지 확인하기
        2-2. 유효한 연산자가 아닐 경우 Exception 던질 것
        2-3. ArithmeticException을 제외한 예외가 던져지면 처음부터 다시 입력받기

    3. 입력받은 값들을 기반으로 연산 수행하기
        3-1. switch로 나눠 각 경우에 맞는 연산 수행할 것
        3-2. / 연산의 경우 두 번째 수가 0이라면 ArithmeticException 발생시킬 것
        3-3. Math.addExact() 등을 활용해 연산 후 오버/언더플로우가 발생하는 경우를 예외로 잡을 수 있게 할 것
        3-4. ArithmeticException이 발생하면 예외 원인 출력할 것

    4. 1 ~ 3 수행 이후 계속 연산할 것인지에 대한 여부를 입력받을 것
        4-1. exit 입력 시 반복을 종료하도록 할 것
        4-2. ArithmeticException을 제외한 Exception 발생 시에는 물어보지 말아야 함
*/

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final String OPERATIONS = "+-*/";
    private static final String NUMBER_REGEX = "^[0-9]*$";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            try {
                System.out.print("연산할 두 양의 정수를 입력하세요: ");
                // 정수가 정확히 2개만 들어오는지 검증하기 위해 nextLong() 대신 nextLine() 사용
                // nextLong()으로 입력받을 시 정수가 3개 이상 들어가면 다른 next() 함수들에 잘못 들어가게 됨
                String[] nums = sc.nextLine().split(" ");
                if(nums.length > 2) throw new Exception("2개의 양의 정수 또는 0만 입력해주세요.");

                long firstNum, secondNum;

                if(Pattern.matches(NUMBER_REGEX, nums[0]) && Pattern.matches(NUMBER_REGEX, nums[1])) {
                    firstNum = Long.parseLong(nums[0]);
                    secondNum = Long.parseLong(nums[1]);
                } else {
                    throw new Exception("양의 정수 또는 0을 입력해주세요.");
                }

                System.out.print("사칙연산 기호를 입력해주세요: ");

                // 메모리 절약을 위해 String 대신 char 형식으로 입력받음
                char operation = sc.nextLine().charAt(0);
                if(OPERATIONS.indexOf(operation) != -1) {
                    // 나누기 결과까지 커버할 수 있도록 double 자료형 사용
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
                    System.out.println("연산 결과: " + result);

                } else {
                    throw new Exception("+, -, *, / 중 하나를 입력해주세요.");
                }

            } catch (ArithmeticException e) {
                // 입력 자체는 문제가 없는 경우이므로 연산 문제사항만 출력
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("\n" + e.getMessage() + "\n");
                // 입력이 잘못된 경우이므로 다시 입력받도록 함
                continue;
            }

            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료): ");
            // 사용자 편의를 위해 대소문자 구별하지 않고 입력받음
            if(sc.nextLine().trim().equalsIgnoreCase("exit")) {
                break;
            }

        }
    }
}