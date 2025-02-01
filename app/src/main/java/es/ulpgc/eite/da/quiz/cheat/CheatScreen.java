package es.ulpgc.eite.da.quiz.cheat;


import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.quiz.R;
import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.question.QuestionModel;


public class CheatScreen {


    public static void configure(CheatContract.View view) {

        WeakReference<FragmentActivity> context =
            new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = AppMediator.getInstance();
        CheatContract.Presenter presenter = new CheatPresenter(mediator);

        CheatModel model = new CheatModel();
        model.setEmptyAnswerText(context.get().getString(R.string.empty_text));
        model.setTrueAnswerText(context.get().getString(R.string.true_text));
        model.setFalseAnswerText(context.get().getString(R.string.false_text));

        presenter.injectView(new WeakReference<>(view));
        presenter.injectModel(model);
        view.injectPresenter(presenter);

    }

}
