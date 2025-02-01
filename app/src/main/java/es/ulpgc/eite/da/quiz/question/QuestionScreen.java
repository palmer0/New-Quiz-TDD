package es.ulpgc.eite.da.quiz.question;


import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.quiz.R;
import es.ulpgc.eite.da.quiz.app.AppMediator;


public class QuestionScreen {


    public static void configure(QuestionContract.View view) {

        WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        QuestionContract.Presenter presenter = new QuestionPresenter(mediator);

        QuestionModel model = new QuestionModel();
        model.setEmptyResultText(context.get().getString(R.string.empty_text));
        model.setCorrectResultText(context.get().getString(R.string.correct_text));
        model.setIncorrectResultText(context.get().getString(R.string.incorrect_text));

        presenter.injectView(new WeakReference<>(view));
        presenter.injectModel(model);
        view.injectPresenter(presenter);

    }

}
