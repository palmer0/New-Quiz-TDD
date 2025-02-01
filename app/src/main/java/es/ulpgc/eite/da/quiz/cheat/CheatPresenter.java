package es.ulpgc.eite.da.quiz.cheat;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.quiz.app.QuestionToCheatState;


public class CheatPresenter implements CheatContract.Presenter {

    public static String TAG = "Quiz.CheatPresenter";

    private WeakReference<CheatContract.View> view;
    private CheatContract.Model model;
    private CheatState state;
    private AppMediator mediator;

    public CheatPresenter(AppMediator mediator) {
        this.mediator = mediator;
    }


    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled");

        // Inicializar el estado
        initializeState();

        // Recuperar el estado guardado de la pantalla anterior
        restoreStateFromPreviousScreen();
    }

    private void initializeState() {
        state = new CheatState();
        state.answerText = model.getEmptyAnswerText();
    }

    private void restoreStateFromPreviousScreen() {
        QuestionToCheatState savedState = mediator.getQuestionToCheatState();
        if (savedState != null) {
            state.answer = savedState.answer;
        }
    }

    /*
    @Override
    public void onCreateCalled() {
        Log.e(TAG, "onCreateCalled");

        // init the screen state
        state = new CheatState();
        state.answerText = model.getEmptyAnswerText();

        // get the saved state from previous screen
        QuestionToCheatState savedState = mediator.getQuestionToCheatState();
        if (savedState != null) {

            // update the current state
            state.answer = savedState.answer;
        }
    }
    */

    @Override
    public void onRecreateCalled() {
        Log.e(TAG, "onRecreateCalled");

        // restore the screen state
        state = mediator.getCheatState();
    }

    @Override
    public void onResumeCalled() {
        Log.e(TAG, "onResumeCalled");

        // refresh the display with updated state
        view.get().displayCheatData(state);
    }

    @Override
    public void onBackButtonPressed() {
        Log.e(TAG, "onBackButtonPressed");

    }

    @Override
    public void onPauseCalled() {
        Log.e(TAG, "onPauseCalled");

        // save the current state
        mediator.setCheatState(state);
    }

    @Override
    public void onDestroyCalled() {
        Log.e(TAG, "onDestroyCalled");

        // Reset current state
        //mediator.resetCheatState();
    }


    @Override
    public void yesButtonClicked() {
        Log.e(TAG, "yesButtonClicked");

        // save the state to previous screen
        saveStateToPreviousScreen(true);

        // update the current state
        if (state.answer) {
            state.answerText = model.getTrueAnswerText();

        } else {
            state.answerText = model.getFalseAnswerText();
        }

        state.yesButton = false;
        state.noButton = false;

        // refresh the display with updated state
        view.get().displayCheatData(state);

    }

    @Override
    public void noButtonClicked() {
        Log.e(TAG, "noButtonClicked");

        // save the state to previous screen
        saveStateToPreviousScreen(false);

        // finish the current screen
        view.get().finishView();
    }

    private void saveStateToPreviousScreen(boolean cheated) {
        Log.e(TAG, "saveStateToPreviousScreen");

        CheatToQuestionState newState = new CheatToQuestionState(cheated);
        mediator.setCheatToQuestionState(newState);
    }

    @Override
    public void injectView(WeakReference<CheatContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(CheatContract.Model model) {
        this.model = model;
    }
}
