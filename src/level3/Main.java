package level3;

import level3.enums.Answers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        App app = new App();
        // 예외처리 및 무한루프는 메인함수에서 담당
        boolean continueAppRunning = true;
        while(continueAppRunning) {
            try {
                continueAppRunning = app.start();
            } catch (ArithmeticException e) {
                System.out.println("연산 문제: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("문제 발생\n" + e.getMessage() + "\n");
                // 입력이 잘못된 경우이므로 다시 입력받도록 함
            }
        }
    }
}
