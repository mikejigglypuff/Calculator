package level2;

import java.util.Scanner;

/*
    1. 책임을 나눌 클래스들 생성
        1-1. 연산 작업을 Calculator에 위임
            1-1-1. Calculator에서 연산 결과를 저장하도록 함
                1-1-1-1. 저장에 사용할 컬렉션은 선입선출을 활용할 수 있어야 함
                1-1-2-1. private 접근자로 외부에서 컬렉션에 직접 접근하지 못하도록 해야 함
            1-1-2. App에서 Calculator를 사용하도록 함

        1-2. 입력값 검증 & 변환을 Parser에 위임
        1-3. 계산기 수행 로직은 App에게 위임
            1-3-1. 무한루프 및 예외처리, Scanner 공유만 Main에서 담당

    2. Calculator의 연산 결과 삭제하도록 하기
        2-1. 연산 이후 연산결과를 삭제할 것인지 입력받을 것
        2-2. Calculator의 컬렉션에서 맨 처음 들어온 값을 삭제하도록 할 것

 */

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
