package level3;

public class Main {
    public static void main(String[] args){
        App app = new App();
        // 예외처리 및 무한루프는 메인함수에서 담당
        boolean continueAppRunning = true;
        while(continueAppRunning) {
            try {
                continueAppRunning = app.start();
            } catch (ArithmeticException e) {
                System.out.println("잘못된 연산: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("문제 발생\n" + e.getMessage() + "\n");
                // 입력이 잘못된 경우이므로 다시 입력받도록 함
            }
        }
    }
}
