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
    INACTION("inAction", new ArrayList<>(Arrays.asList("none", "없음", "없어요")));

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

    // 사용자의 입력이 어떤 카테고리에 속하는지를 반환
    public static String getMeaningForAnswer(String input) {
        String lowerInput = input.toLowerCase().trim();

        for (Answers a : values()) {
            if (a.getAnswerList().contains(lowerInput)) {
                return a.getMeaning();
            }
        }
        return "";
    }
}
