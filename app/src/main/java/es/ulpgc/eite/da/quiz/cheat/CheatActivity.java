package es.ulpgc.eite.da.quiz.cheat;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.quiz.R;


public class CheatActivity
    extends AppCompatActivity implements CheatContract.View {

    public static String TAG = "Quiz.CheatActivity";

    CheatContract.Presenter presenter;

    TextView confirmationField, answerField;
    Button yesButton, noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setTitle(R.string.cheat_screen_title);

        linkLayoutComponents();
        updateLayoutContent();
        initLayoutButtons();

        // do the setup
        CheatScreen.configure(this);

        // do some work
        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();
        }
    }

    private void linkLayoutComponents() {

        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        confirmationField = findViewById(R.id.confirmationField);
        answerField = findViewById(R.id.answerField);
    }

    private void updateLayoutContent() {
        yesButton.setText(getYesButtonLabel());
        noButton.setText(getNoButtonLabel());
        confirmationField.setText(getConfirmationButtonLabel());
    }

    private void initLayoutButtons() {

        yesButton.setOnClickListener(v -> presenter.yesButtonClicked());
        noButton.setOnClickListener(v -> presenter.noButtonClicked());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // do some work
        presenter.onResumeCalled();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        presenter.onBackButtonPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        presenter.onPauseCalled();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroyCalled();
    }


    @Override
    public void displayCheatData(CheatViewModel viewModel) {
        Log.e(TAG, "displayCheatData");

        // deal with the data
        answerField.setText(viewModel.answerText);

        yesButton.setEnabled(viewModel.yesButton);
        noButton.setEnabled(viewModel.noButton);

    }

    @Override
    public void finishView() {
        finish();
    }

    private String getYesButtonLabel() {
        return getResources().getString(R.string.yes_button_label);
    }

    private String getNoButtonLabel() {
        return getResources().getString(R.string.no_button_label);
    }

    private String getConfirmationButtonLabel() {
        return getResources().getString(R.string.confirmation_text);
    }


    @Override
    public void injectPresenter(CheatContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
