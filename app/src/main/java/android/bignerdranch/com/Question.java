package android.bignerdranch.com;

public class Question {
    protected String text;
    protected boolean answer;

    protected Question(String text, boolean answer){
        this.text = text;
        this.answer = answer;
    }

    protected String getText(){
        return text;
    }
    protected boolean getAnswer(){
        return answer;
    }

    public boolean isAnswerTrue() {
        return answer;
    }
}
