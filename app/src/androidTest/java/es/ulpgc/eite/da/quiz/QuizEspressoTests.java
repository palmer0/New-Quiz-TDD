package es.ulpgc.eite.da.quiz;

import android.os.RemoteException;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import es.ulpgc.eite.da.quiz.question.QuestionActivity;

// Project: Quiz
// Created by Luis Hernandez 
// Copyright (c) 2025 EITE (ULPGC)
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuizEspressoTests {

    @Rule
    public ActivityScenarioRule<QuestionActivity> activityRule =
        new ActivityScenarioRule<>(QuestionActivity.class);

    private void rotateScreen() {

        // Obtener el dispositivo UI Automator
        UiDevice device =
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        try {

            // Simular giro de pantalla a landscape
            device.setOrientationLeft();

            // Restaurar la orientación natural de la pantalla
            device.setOrientationNatural();

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }

    // Verifica que al responder correctamente, se muestra el texto adecuado
    @Test
    public void test01_AnswerQuestionCorrectly() {
        // Simula hacer clic en el botón de respuesta "True"
        Espresso.onView(ViewMatchers.withId(R.id.trueButton)).perform(ViewActions.click());

        // Verifica que el campo de resultado muestra "Correcto"
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.correct_text)));
    }

    // Verifica que al responder incorrectamente, se muestra el texto de respuesta incorrecta
    @Test
    public void test02_AnswerQuestionIncorrectly() {
        // Simula hacer clic en el botón de respuesta "False"
        Espresso.onView(ViewMatchers.withId(R.id.falseButton)).perform(ViewActions.click());

        // Verifica que el campo de resultado muestra "Incorrecto"
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.incorrect_text)));
    }

    // Verifica que al hacer trampa y regresar, se avanza automáticamente a la siguiente pregunta
    @Test
    public void test03_CheatAndReturn_MovesToNextQuestion() {
        // Navega a la pantalla de trampa
        Espresso.onView(ViewMatchers.withId(R.id.cheatButton)).perform(ViewActions.click());

        // Simula hacer clic en el botón "Yes" para ver la respuesta
        Espresso.onView(ViewMatchers.withId(R.id.yesButton)).perform(ViewActions.click());

        // Verifica que la respuesta se muestra en la pantalla de trampa
        Espresso.onView(ViewMatchers.withId(R.id.answerField))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Regresa a la pantalla de pregunta
        Espresso.pressBack();

        // Verifica que se ha avanzado a la siguiente pregunta
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.empty_text)));
    }

    // Verifica que el estado de la pantalla se mantiene después de la rotación
    @Test
    public void test04_ScreenRotationMaintainsState() {
        // Responder la pregunta actual
        Espresso.onView(ViewMatchers.withId(R.id.trueButton)).perform(ViewActions.click());

        // Rotar la pantalla
        rotateScreen();
        //activityRule.getScenario().recreate();

        // Verificar que la respuesta se mantiene
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.correct_text)));
    }

    // Verifica que al responder la segunda pregunta y rotar la pantalla, se mantiene la respuesta
    @Test
    public void test05_RotationAfterAnsweringSecondQuestion() {
        // Avanzar a la segunda pregunta
        Espresso.onView(ViewMatchers.withId(R.id.nextButton)).perform(ViewActions.click());

        // Responder la segunda pregunta
        Espresso.onView(ViewMatchers.withId(R.id.trueButton)).perform(ViewActions.click());

        // Rotar la pantalla
        rotateScreen();
        //activityRule.getScenario().recreate();

        // Verificar que la respuesta se mantiene
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.correct_text)));
    }

    // Verifica que al entrar en la pantalla de trampa y regresar sin hacer trampa,
    // se pueden responder las preguntas
    @Test
    public void test06_GoToCheatScreenAndReturnWithoutCheating() {
        // Navega a la pantalla de trampa sin hacer trampa
        Espresso.onView(ViewMatchers.withId(R.id.cheatButton)).perform(ViewActions.click());
        Espresso.pressBack();

        // Verifica que los botones siguen habilitados
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.falseButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }

    // Verifica que al entrar en la pantalla de trampa, hacer trampa y regresar,
    // se avanza a la siguiente pregunta
    @Test
    public void test07_GoToCheatScreenAndReturnWithCheating() {
        // Navega a la pantalla de trampa
        Espresso.onView(ViewMatchers.withId(R.id.cheatButton)).perform(ViewActions.click());

        // Simula hacer clic en el botón "Yes" para ver la respuesta
        Espresso.onView(ViewMatchers.withId(R.id.yesButton)).perform(ViewActions.click());

        // Verifica que la respuesta se muestra antes de regresar
        Espresso.onView(ViewMatchers.withId(R.id.answerField))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Regresa a la pantalla de pregunta
        Espresso.pressBack();

        // Verifica que se ha avanzado a la siguiente pregunta
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.empty_text)));
    }

}
