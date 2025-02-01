package es.ulpgc.eite.da.quiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import android.widget.Button;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.app.CheatToQuestionState;
import es.ulpgc.eite.da.quiz.app.QuestionToCheatState;
import es.ulpgc.eite.da.quiz.question.QuestionActivity;

@RunWith(RobolectricTestRunner.class)
//@Config(manifest=Config.NONE)
@Config(sdk = 28)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuestionActivityTests {



    private String[] quizQuestions = {
        "Christian Bale played Batman in 'The Dark Knight Rises'?", // 1
        "The Gremlins movie was released in 1986?",  // 2
        "Brad Pitt played Danny Ocean in Ocean's Eleven, Ocean's Twelve and Ocean's Thirteen?",  // 3
        "A spoon full of sugar' came from the 1964 movie Mary Poppins?",  // 4
//      "The song “I don't want to miss a thing” featured in Armageddon?", // 5
//      "Will Smith has a son called Jaden?", // 6
//      "Mark Ruffalo played Teddy Daniels in Shutter Island?", // 7
//      "Mike Myers starred in the 'Cat in the Hat' 2003 children's movie?", // 8
//      "Ryan Reynolds is married to Scarlett Johansson?", // 9
//      "The movie 'White House Down' was released in 2014?",  // 10
//      "Michael Douglas starred in Basic Instinct, Falling Down and The Game?", // 11
//      "Colin Firth won an Oscar for his performance in the historical movie 'The King's Speech'?",  // 12
//      "Cameron Diaz and Ashton Kutcher starred in the movie 'What happens in Vegas'?", // 13
//      "Arnold Schwarzenegger played lead roles in Rocky, Rambo and Judge Dredd?", // 14
//      "The Titanic movie featured the song 'My Heart Will Go On'?", // 15
//      "Eddie Murphy narrates the voice of Donkey in the Shrek movies?", // 16
//      "Nicole Kidman played Poison Ivy in 'Batman and Robin'?", // 17
//      "The Lara Croft: Tomb Raider movie was released in 2003?", // 18
//      "Hallie Berry played the character Rogue in X Men?", // 19
        "The Teenage Mutant Ninja Turtles are named after famous artists?" // 20
    };

    private boolean[] quizAnswers = {
        true, // 1
        false, // 2
        false, // 3
        true, // 4
//      true, // 5
//      true, // 6
//      false, // 7
//      true, // 8
//      false, // 9
//      false, // 10
//      true, // 11
//      true, // 12
//      true, // 13
//      false, // 14
//      true, // 15
//      true, // 16
//      false, // 17
//      false, // 18
//      false, // 19
        true // 20
    };


    @Test
    public void test01ActivityInitialization() {
        ActivityScenario<QuestionActivity> scenario =
            ActivityScenario.launch(QuestionActivity.class);

        scenario.onActivity(activity -> {
            TextView resultTextView = activity.findViewById(R.id.resultField);
            TextView questionTextView = activity.findViewById(R.id.questionField);
            Button trueButton = activity.findViewById(R.id.trueButton);
            Button falseButton = activity.findViewById(R.id.falseButton);
            Button cheatButton = activity.findViewById(R.id.cheatButton);
            Button nextButton = activity.findViewById(R.id.nextButton);


            // Verify the first question is shown
            assertEquals(
                quizQuestions[0], // expected
                questionTextView.getText().toString() // actual
            );

            // Verify the empty result is shown
            assertEquals(
                activity.getString(R.string.empty_text), // expected
                resultTextView.getText().toString() // actual
            );

            // Verify the Next button is disabled
            assertFalse(nextButton.isEnabled());
            assertTrue(trueButton.isEnabled());
            assertTrue(falseButton.isEnabled());
            assertTrue(cheatButton.isEnabled());
        });
    }

    @Test
    public void test02AnswerCorrectly() {
        ActivityScenario<QuestionActivity> scenario =
            ActivityScenario.launch(QuestionActivity.class);

        scenario.onActivity(activity -> {
            TextView resultTextView = activity.findViewById(R.id.resultField);
            TextView questionTextView = activity.findViewById(R.id.questionField);
            Button trueButton = activity.findViewById(R.id.trueButton);
            Button falseButton = activity.findViewById(R.id.falseButton);
            Button cheatButton = activity.findViewById(R.id.cheatButton);
            Button nextButton = activity.findViewById(R.id.nextButton);


            // Answer the first question correctly
            trueButton.performClick();

            // Verify the first question is shown
            assertEquals(quizQuestions[0], questionTextView.getText().toString());

            // Verify the first result is shown
            assertEquals(
                activity.getString(R.string.correct_text),
                resultTextView.getText().toString()
            );

            // Verify the Next button is enabled
            assertTrue(nextButton.isEnabled());
            assertFalse(trueButton.isEnabled());
            assertFalse(falseButton.isEnabled());
            assertFalse(cheatButton.isEnabled());
        });
    }

    @Test
    public void test03AnswerIncorrectly() {
        ActivityScenario<QuestionActivity> scenario =
            ActivityScenario.launch(QuestionActivity.class);

        scenario.onActivity(activity -> {
            TextView resultTextView = activity.findViewById(R.id.resultField);
            TextView questionTextView = activity.findViewById(R.id.questionField);
            Button trueButton = activity.findViewById(R.id.trueButton);
            Button falseButton = activity.findViewById(R.id.falseButton);
            Button cheatButton = activity.findViewById(R.id.cheatButton);
            Button nextButton = activity.findViewById(R.id.nextButton);

            // Verify the first question is shown
            assertEquals(quizQuestions[0], questionTextView.getText().toString());

            // Answer the first question incorrectly
            falseButton.performClick();

            // Verify the first question is shown
            assertEquals(quizQuestions[0], questionTextView.getText().toString());

            // Verify the first result is shown
            assertEquals(
                activity.getString(R.string.incorrect_text),
                resultTextView.getText().toString()
            );

            // Verify the Next button is enabled
            assertTrue(nextButton.isEnabled());
            assertFalse(trueButton.isEnabled());
            assertFalse(falseButton.isEnabled());
            assertFalse(cheatButton.isEnabled());
        });
    }

    @Test
    public void test04NextQuestion() {
        ActivityScenario<QuestionActivity> scenario =
            ActivityScenario.launch(QuestionActivity.class);

        scenario.onActivity(activity -> {
            TextView resultTextView = activity.findViewById(R.id.resultField);
            TextView questionTextView = activity.findViewById(R.id.questionField);
            Button trueButton = activity.findViewById(R.id.trueButton);
            Button falseButton = activity.findViewById(R.id.falseButton);
            Button cheatButton = activity.findViewById(R.id.cheatButton);
            Button nextButton = activity.findViewById(R.id.nextButton);


            // Verify the first question is shown
            assertEquals(quizQuestions[0], questionTextView.getText().toString());

            // Answer the first question
            trueButton.performClick();

            // Move to the next question
            nextButton.performClick();

            // Verify the second question is shown
            assertEquals(quizQuestions[1], questionTextView.getText().toString());

            // Verify the empty result is shown
            assertEquals(
                activity.getString(R.string.empty_text),
                resultTextView.getText().toString()
            );

            // Verify the buttons are enabled for the next question
            assertTrue(trueButton.isEnabled());
            assertTrue(falseButton.isEnabled());
            assertTrue(cheatButton.isEnabled());
            assertFalse(nextButton.isEnabled());

            // Answer the second question incorrectly
            trueButton.performClick();

            // Verify the second result is shown
            assertEquals(
                activity.getString(R.string.incorrect_text),
                resultTextView.getText().toString()
            );

            // Verify the Next button is enabled
            assertTrue(nextButton.isEnabled());
            assertFalse(trueButton.isEnabled());
            assertFalse(falseButton.isEnabled());
            assertFalse(cheatButton.isEnabled());

        });
    }


    @Test
    public void test05RotateScreen() {
        ActivityScenario<QuestionActivity> scenario =
                ActivityScenario.launch(QuestionActivity.class);

        scenario.onActivity(activity -> {
            TextView resultTextView = activity.findViewById(R.id.resultField);
            TextView questionTextView = activity.findViewById(R.id.questionField);
            Button trueButton = activity.findViewById(R.id.trueButton);
            Button falseButton = activity.findViewById(R.id.falseButton);
            Button cheatButton = activity.findViewById(R.id.cheatButton);
            Button nextButton = activity.findViewById(R.id.nextButton);

            // Verify the first question is shown
            assertEquals(quizQuestions[0], questionTextView.getText().toString());

            // Answer the first question
            trueButton.performClick();

            // Move to the next question
            nextButton.performClick();

            // Answer the second question incorrectly
            trueButton.performClick();

            // Rotate screen
            activity.recreate();

            // Verify the second question is shown
            assertEquals(quizQuestions[1], questionTextView.getText().toString());

            // Verify the second result is shown
            assertEquals(
                activity.getString(R.string.incorrect_text),
                resultTextView.getText().toString()
            );

            // Verify the Next button is enabled
            assertTrue(nextButton.isEnabled());
            assertFalse(trueButton.isEnabled());
            assertFalse(falseButton.isEnabled());
            assertFalse(cheatButton.isEnabled());

        });
    }


    @Test
    public void test06LastQuestion() {
        ActivityScenario<QuestionActivity> scenario =
                ActivityScenario.launch(QuestionActivity.class);

        scenario.onActivity(activity -> {
            TextView resultTextView = activity.findViewById(R.id.resultField);
            TextView questionTextView = activity.findViewById(R.id.questionField);
            Button trueButton = activity.findViewById(R.id.trueButton);
            Button falseButton = activity.findViewById(R.id.falseButton);
            Button cheatButton = activity.findViewById(R.id.cheatButton);
            Button nextButton = activity.findViewById(R.id.nextButton);


            // Verify the first question is shown
            assertEquals(quizQuestions[0], questionTextView.getText().toString());

            // Move to the last question
            for (int i = 0; i < quizQuestions.length-1; i++) {
                trueButton.performClick(); // Answer the question
                nextButton.performClick(); // Move to the next question

            }

            // Verify the last question is shown
            assertEquals(
                quizQuestions[quizQuestions.length-1],
                questionTextView.getText().toString()
            );

            // Verify the buttons are enabled for the next question
            assertTrue(trueButton.isEnabled());
            assertTrue(falseButton.isEnabled());
            assertTrue(cheatButton.isEnabled());
            assertFalse(nextButton.isEnabled());

            // Answer the last question correctly
            trueButton.performClick();

            // Verify the last result is shown
            assertEquals(
                activity.getString(R.string.correct_text),
                resultTextView.getText().toString()
            );

            // Verify the Next button is enabled
            assertFalse(nextButton.isEnabled()); // last question
            assertFalse(trueButton.isEnabled());
            assertFalse(falseButton.isEnabled());
            assertFalse(cheatButton.isEnabled());

        });
    }



    @Test
    public void test07NavigationToCheatActivity() {
        ActivityScenario<QuestionActivity> scenario =
            ActivityScenario.launch(QuestionActivity.class);

        scenario.onActivity(activity -> {
            Button cheatButton = activity.findViewById(R.id.cheatButton);

            // Press Cheat button
            cheatButton.performClick();

            QuestionToCheatState nextState =
                    AppMediator.getInstance().getQuestionToCheatState();

            assertEquals(quizAnswers[0], nextState.answer);
        });
    }



    @Test
    public void test08ResumeActivityWithCurrentQuestion() {
        ActivityScenario<QuestionActivity> scenario =
            ActivityScenario.launch(QuestionActivity.class);

        scenario.onActivity(activity -> {
            TextView resultTextView = activity.findViewById(R.id.resultField);
            TextView questionTextView = activity.findViewById(R.id.questionField);
            Button trueButton = activity.findViewById(R.id.trueButton);
            Button falseButton = activity.findViewById(R.id.falseButton);
            Button cheatButton = activity.findViewById(R.id.cheatButton);
            Button nextButton = activity.findViewById(R.id.nextButton);

            CheatToQuestionState savedState =
                    AppMediator.getInstance().getCheatToQuestionState();

            assertNull(savedState);

            // Verify the first question is shown
            assertEquals(quizQuestions[0], questionTextView.getText().toString());

            // Verify the empty result is shown
            assertEquals(
                activity.getString(R.string.empty_text),
                resultTextView.getText().toString()
            );

            // Verify the Next button is disabled
            assertFalse(nextButton.isEnabled());
            assertTrue(trueButton.isEnabled());
            assertTrue(falseButton.isEnabled());
            assertTrue(cheatButton.isEnabled());

        });
    }


    @Test
    public void test09ResumeActivityWithNextQuestion() {

        CheatToQuestionState savedState = new CheatToQuestionState(true);
        AppMediator.getInstance().setCheatToQuestionState(savedState);

        ActivityScenario<QuestionActivity> scenario =
            ActivityScenario.launch(QuestionActivity.class);

        scenario.onActivity(activity -> {
            TextView resultTextView = activity.findViewById(R.id.resultField);
            TextView questionTextView = activity.findViewById(R.id.questionField);
            Button trueButton = activity.findViewById(R.id.trueButton);
            Button falseButton = activity.findViewById(R.id.falseButton);
            Button cheatButton = activity.findViewById(R.id.cheatButton);
            Button nextButton = activity.findViewById(R.id.nextButton);

            // Verify the second question is shown
            assertEquals(quizQuestions[1], questionTextView.getText().toString());

            // Verify the empty result is shown
            assertEquals(
                activity.getString(R.string.empty_text),
                resultTextView.getText().toString()
            );

            // Verify the Next button is disabled
            assertFalse(nextButton.isEnabled());
            assertTrue(trueButton.isEnabled());
            assertTrue(falseButton.isEnabled());
            assertTrue(cheatButton.isEnabled());

        });
    }
}
