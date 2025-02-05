package es.ulpgc.eite.da.quiz.question;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.quiz.app.QuestionToCheatState;


public class QuestionPresenter implements QuestionContract.Presenter {

    public static String TAG = "Quiz.QuestionPresenter";

    private WeakReference<QuestionContract.View> view;
    private QuestionState state;
    private QuestionContract.Model model;
    private AppMediator mediator;

    public QuestionPresenter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled");

        initializeState();
    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled");

        state = mediator.getQuestionState();
        model.setCurrentIndex(state.quizIndex);
    }

    private void initializeState() {
        state = new QuestionState();
        state.resultText = model.getEmptyResultText();
        state.questionText = model.getCurrentQuestion();
    }

    /*
    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled");

        // init the screen state
        state = new QuestionState();
        state.resultText = model.getEmptyResultText();
        state.questionText = model.getCurrentQuestion();
    }

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled");

        // restore the screen state
        state = mediator.getQuestionState();

        // update the model
        model.setCurrentIndex(state.quizIndex);

    }
    */

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled");

        // Obtener el estado guardado desde la pantalla Cheat
        CheatToQuestionState savedState = mediator.getCheatToQuestionState();
        if (savedState != null) {
            // Si el usuario vio la respuesta
            if (savedState.cheated) {
                // Si estamos en la última pregunta, solo deshabilitar botones
                if (model.isLastQuestion()) {
                    disableAnswerButtons();
                    state.nextButton = false; // Mantener deshabilitado

                } else {
                    // Avanzar a la siguiente pregunta si no es la última
                    nextButtonClicked();
                    return;
                }

            }
        }

        // Refrescar la pantalla con el estado actualizado
        view.get().displayQuestionData(state);
    }


    /*
    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled");

        handleCheatState();
        view.get().displayQuestionData(state);
    }


    private void handleCheatState() {
        CheatToQuestionState savedState = mediator.getCheatToQuestionState();
        if (savedState != null && savedState.cheated) {
            nextButtonClicked();
        }
    }
    */


    /*
    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled");

        // get the saved state from the next screen
        CheatToQuestionState savedState = mediator.getCheatToQuestionState();
        if (savedState != null) {

            // update the current state
            if (savedState.cheated) {
                nextButtonClicked();
                return;
            }
        }

        // refresh the display with updated state
        view.get().displayQuestionData(state);
    }
    */

    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled");

        // save the current state
        mediator.setQuestionState(state);
    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled");

        // Reset current state
        //mediator.resetCheatState();
    }

    private void updateQuestionData(boolean userAnswer) {
        boolean currentAnswer = model.getCurrentAnswer();
        state.resultText = (currentAnswer == userAnswer)
            ? model.getCorrectResultText()
            : model.getIncorrectResultText();

        disableAnswerButtons();
        state.nextButton = !model.isLastQuestion();

        view.get().displayQuestionData(state);
    }

    /*
    private void updateQuestionData(boolean userAnswer) {

        // update the current state

        boolean currentAnswer = model.getCurrentAnswer();

        if (currentAnswer == userAnswer) {
            state.resultText = model.getCorrectResultText();

        } else {
            state.resultText = model.getIncorrectResultText();
        }

        state.falseButton = false;
        state.trueButton = false;
        state.cheatButton = false;

        if (model.isLastQuestion()) {
            state.nextButton = false;

        } else {
            state.nextButton = true;
        }

        // refresh the display with updated state
        view.get().displayQuestionData(state);
    }
    */


    @Override
    public void trueButtonClicked() {
        Log.e(TAG, "trueButtonClicked");

        updateQuestionData(true);
    }

    @Override
    public void falseButtonClicked() {
        Log.e(TAG, "falseButtonClicked");

        updateQuestionData(false);
    }

    @Override
    public void cheatButtonClicked() {
        Log.e(TAG, "cheatButtonClicked");

        // save the state to next screen
        boolean answer = model.getCurrentAnswer();
        QuestionToCheatState newState = new QuestionToCheatState(answer);
        mediator.setQuestionToCheatState(newState);

        // navigate to next screen
        view.get().navigateToCheatScreen();
    }

    @Override
    public void nextButtonClicked() {
        Log.e(TAG, "nextButtonClicked");

        // update the current state

        model.incrQuizIndex();
        state.quizIndex  = model.getCurrentIndex();

        state.questionText = model.getCurrentQuestion();
        state.resultText = model.getEmptyResultText();

        //state.falseButton = true;
        //state.trueButton = true;
        //state.cheatButton = true;
        enableAnswerButtons();
        state.nextButton = false;

        // refresh the display with updated state
        view.get().displayQuestionData(state);
    }

    private void enableAnswerButtons() {
        state.falseButton = true;
        state.trueButton = true;
        state.cheatButton = true;
    }

    private void disableAnswerButtons() {
        state.falseButton = false;
        state.trueButton = false;
        state.cheatButton = false;
    }

    @Override
    public void injectView(WeakReference<QuestionContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(QuestionContract.Model model) {
        this.model = model;
    }

}
