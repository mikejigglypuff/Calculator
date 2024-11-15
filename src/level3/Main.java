package level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        App app = new App();
        // 예외처리 및 무한루프는 메인함수에서 담당
        while(true) {
            try {
                app.start(br);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("\n" + e.getMessage() + "\n");
                // 입력이 잘못된 경우이므로 다시 입력받도록 함
                continue;
            }

            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료): ");
            // 사용자 편의를 위해 대소문자 구별하지 않고 입력받음
            if(br.readLine().trim().equalsIgnoreCase("exit")) {
                break;
            }
        }
    }
}
