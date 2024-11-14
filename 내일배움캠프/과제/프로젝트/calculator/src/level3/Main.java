package level3;

/*
    1. Enum 활용하기
        1-1. 연산을 Enum으로 정의하기
            1-1-1. 연산 기호 및 연산 방식을 연관짓기
            1-1-2. 연산 방식은 내부 메서드로 정의하기
            1-1-3. 질문 입력 또한 Enum으로 정의하기

     2. 입력값보다 큰 연산결과 값들 출력하기
            2-1. stream을 이용해 결과들을 배열 형식의 문자열로 반환하기

     3. Calculator, Parser를 제네릭 클래스로 변경하기
            3-1. 제네릭 타입에 따라 operation() 메서드가 매개변수를 받도록 할 것
            3-2. 파싱 결과 타입이 제네릭 타입을 따르도록 할 것
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        App app = new App();
        // 예외처리 및 무한루프는 메인함수에서 담당
        while(true) {
            try {
                app.start(sc);
            } catch (ArithmeticException e) {
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
