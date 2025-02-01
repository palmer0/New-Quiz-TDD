package es.ulpgc.eite.da.quiz.app;


import es.ulpgc.eite.da.quiz.cheat.CheatState;
import es.ulpgc.eite.da.quiz.question.QuestionState;

public class AppMediator {

    private static AppMediator INSTANCE;
    private QuestionState questionState;
    private CheatState cheatState;
    private CheatToQuestionState cheatToQuestionState;
    private QuestionToCheatState questionToCheatState;

    private AppMediator() {
        //questionState = new QuestionState();
        //cheatState = new CheatState();
    }

    public static AppMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppMediator();
        }

        return INSTANCE;
    }

    public static void resetInstance() {
        INSTANCE = null;
    }


    public void resetCheatState() {
        cheatState = new CheatState();
    }

    public CheatState getCheatState() {
        return cheatState;
    }

    public void setCheatState(CheatState state) {
        cheatState = state;
    }

    public QuestionState getQuestionState() {
        return questionState;
    }

    public void setQuestionState(QuestionState state) {
        questionState = state;
    }

    public CheatToQuestionState getCheatToQuestionState() {
        CheatToQuestionState state = cheatToQuestionState;
        cheatToQuestionState = null;
        return state;
    }

    public void setCheatToQuestionState(CheatToQuestionState state) {
        this.cheatToQuestionState = state;
    }

    public QuestionToCheatState getQuestionToCheatState() {
        QuestionToCheatState state = questionToCheatState;
        questionToCheatState = null;
        return state;
    }

    public void setQuestionToCheatState(QuestionToCheatState state) {
        this.questionToCheatState = state;
    }

}
