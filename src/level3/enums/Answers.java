package level3.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Answers {
    YES("yes", new ArrayList<>(Arrays.asList("yes", "y", "예"))),
    NO("no", new ArrayList<>(Arrays.asList("no", "n", "아니오", "아니요"))),
    EXIT("exit", new ArrayList<>(Arrays.asList("exit", "ext", "e", "끝", "종료"))),
    DELETE("delete", new ArrayList<>(Arrays.asList("delete", "del", "d", "삭제"))),
    COMPARE("compare", new ArrayList<>(Arrays.asList("compare", "comp", "c", "비교", "큰 수 비교"))),
    INACTION("inAction", new ArrayList<>(Arrays.asList("none", "no", "n", "없음", "없어요")));
    
    private final String meaning;
    private final List<String> answerList;

    Answers(String meaning, List<String> answerList) {
        this.meaning = meaning;
        this.answerList = answerList;
    }

    public String getMeaning() {
        return meaning;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public boolean matchAnswer(String input) {
        for(String s : answerList) {
            if(s.equalsIgnoreCase(input)) return true;
        }
        return false;
    }

    /*
    입력이 들어옴 -> 입력에 맞는 ENUM 상수 얻기 -> ENUM 상수의 의미 값에 따라 작업 수행
    입력에 맞는 ENUM 상수가 없어서 null이 반환되면?
    -> 입력 시 상수들을 순회하며 맞는 의미가 있다면 반환하고 그렇지 않다면 빈 문자열을 반환하는 걸로 변경하기
     */
    public static String getMeaningForAnswer(String input) {
        String lowerInput = input.toLowerCase();

        for(Answers a : values()) {
            if(a.getAnswerList().contains(lowerInput)) {
                return a.getMeaning();
            }
        }
        return "";
    }
}
