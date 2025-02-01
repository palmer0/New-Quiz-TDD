package es.ulpgc.eite.da.quiz.cheat;

import java.lang.ref.WeakReference;


public interface CheatContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayCheatData(CheatViewModel viewModel);

        void finishView();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onPauseCalled();

        void onDestroyCalled();

        void yesButtonClicked();

        void noButtonClicked();

        void onResumeCalled();

        void onCreateCalled();

        void onRecreateCalled();

        void onBackButtonPressed();
    }

    interface Model {

        String getEmptyAnswerText();

        void setEmptyAnswerText(String text);

        String getFalseAnswerText();

        void setFalseAnswerText(String text);

        String getTrueAnswerText();

        void setTrueAnswerText(String text);
    }
}