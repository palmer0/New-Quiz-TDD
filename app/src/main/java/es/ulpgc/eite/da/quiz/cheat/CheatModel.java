package es.ulpgc.eite.da.quiz.cheat;

/**
 * Created by Luis on March, 2021
 */
public class CheatModel implements CheatContract.Model {

    public static String TAG = "Quiz.CheatModel";

    private String falseAnswerText, trueAnswerText;
    private String emptyAnswerText;


    @Override
    public String getEmptyAnswerText() {
        return emptyAnswerText;
    }

    @Override
    public void setEmptyAnswerText(String text) {
        this.emptyAnswerText = text;
    }


    @Override
    public String getFalseAnswerText() {
        return falseAnswerText;
    }

    @Override
    public void setFalseAnswerText(String text) {
        falseAnswerText = text;
    }

    @Override
    public String getTrueAnswerText() {
        return trueAnswerText;
    }

    @Override
    public void setTrueAnswerText(String text) {
        trueAnswerText = text;
    }

}