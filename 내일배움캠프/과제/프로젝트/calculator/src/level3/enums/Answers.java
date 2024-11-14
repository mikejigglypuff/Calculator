package level3.enums;

public enum Answers {
    YES(new String[]{"yes", "y", "예"}),
    EXIT(new String[]{"exit", "e", "no", "n", "아니오"});

    private final String[] answerList;

    Answers(String[] answerList) {
        this.answerList = answerList;
    }

    public boolean matchAnswer(String input) {
        for(String s : answerList) {
            if(s.equalsIgnoreCase(input)) return true;
        }
        return false;
    }
}
