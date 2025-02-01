package es.ulpgc.eite.da.quiz;

import android.os.RemoteException;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.question.QuestionActivity;

// Project: Quiz
// Created by Luis Hernandez 
// Copyright (c) 2025 EITE (ULPGC)
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewQuizAppTests {

    @Before
    public void setUp() {
        // Reiniciar el estado antes de cada test
        AppMediator.resetInstance();
    }


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

    @Test
    // Verifica que al responder correctamente, se muestra el texto adecuado
    public void test01_InitialState() {

        // Iniciar la actividad
        ActivityScenario.launch(QuestionActivity.class);

        // Verificar que la primera pregunta se muestra correctamente
        Espresso.onView(ViewMatchers.withId(R.id.questionField))
            .check(ViewAssertions.matches(ViewMatchers.withText(
                "Christian Bale played Batman in 'The Dark Knight Rises'?"
            )));

        // Verificar que los botones de respuesta están habilitados
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.falseButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));

        // Verificar que el botón "Next" está deshabilitado
        Espresso.onView(ViewMatchers.withId(R.id.nextButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
    }

    @Test
    // Verifica que al responder incorrectamente,
    // se muestra el texto de respuesta incorrecta
    public void test02_AnswerQuestionAndRotate() {

        // Iniciar la actividad
        ActivityScenario.launch(QuestionActivity.class);

        // Responder correctamente a la primera pregunta
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .perform(ViewActions.click());

        // Verificar que el resultado es correcto
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText("Correct!")));

        // Girar la pantalla
        rotateScreen();

        // Verificar que el estado se conserva después del giro
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText("Correct!")));
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.falseButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.nextButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }

    @Test
    // Verifica que el estado de la pantalla se mantiene después de una rotación real
    public void test03_CheatAndRotate() {

        // Iniciar la actividad
        ActivityScenario.launch(QuestionActivity.class);

        // Navegar a la pantalla de "Cheat"
        Espresso.onView(ViewMatchers.withId(R.id.cheatButton))
            .perform(ViewActions.click());

        // Hacer clic en "Yes" para ver la respuesta
        Espresso.onView(ViewMatchers.withId(R.id.yesButton))
            .perform(ViewActions.click());

        // Girar la pantalla
        rotateScreen();

        // Verificar que el estado se conserva después del giro
        Espresso.onView(ViewMatchers.withId(R.id.answerField))
            .check(ViewAssertions.matches(ViewMatchers.withText("True")));
        Espresso.onView(ViewMatchers.withId(R.id.yesButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.noButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
    }


    @Test
    // Verifica que al hacer trampa y regresar,
    // se avanza automáticamente a la siguiente pregunta
    public void test04_LastQuestionAndRotate() {

        // Iniciar la actividad
        ActivityScenario.launch(QuestionActivity.class);

        // Navegar a la última pregunta suponiendo que hay 4 preguntas
        for (int i = 0; i < 4; i++) {
            Espresso.onView(ViewMatchers.withId(R.id.trueButton))
                .perform(ViewActions.click());
            Espresso.onView(ViewMatchers.withId(R.id.nextButton))
                .perform(ViewActions.click());
        }

        // Verificar que la ultima pregunta se muestra correctamente
        Espresso.onView(ViewMatchers.withId(R.id.questionField))
            .check(ViewAssertions.matches(ViewMatchers.withText(
                "The Teenage Mutant Ninja Turtles are named after famous artists?"
            )));

        // Responder a la última pregunta
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .perform(ViewActions.click());

        // Girar la pantalla
        rotateScreen();

        // Verificar que el estado se conserva después del giro
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText("Correct!")));
        Espresso.onView(ViewMatchers.withId(R.id.nextButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
    }


    // Verifica que el estado en la pantalla Cheat se mantiene tras girar la pantalla
    @Test
    public void test05_CheatScreenRotationMaintainsState() {

        // Iniciar la actividad
        ActivityScenario.launch(QuestionActivity.class);

        Espresso.onView(ViewMatchers.withId(R.id.cheatButton)).perform(ViewActions.click());

        // Girar la pantalla
        rotateScreen();

        Espresso.onView(ViewMatchers.withId(R.id.confirmationField))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    // Verifica que al regresar de la pantalla Cheat sin hacer trampa,
    // los botones de respuesta siguen habilitados
    @Test
    public void test06_GoToCheatScreenAndReturnWithoutCheating() {

        // Iniciar la actividad
        ActivityScenario.launch(QuestionActivity.class);


        Espresso.onView(ViewMatchers.withId(R.id.cheatButton)).perform(ViewActions.click());
        Espresso.pressBack();
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.falseButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }


    @Test
    // Verifica que desde la última pregunta se puede acceder
    // a la pantalla Cheat y ver la respuesta
    public void test07_LastQuestionAndCheat() {


        // Iniciar la actividad
        ActivityScenario.launch(QuestionActivity.class);

        // Navegar a la última pregunta suponiendo que hay 4 preguntas
        for (int i = 0; i < 4; i++) {
            Espresso.onView(ViewMatchers.withId(R.id.trueButton))
                .perform(ViewActions.click());
            Espresso.onView(ViewMatchers.withId(R.id.nextButton))
                .perform(ViewActions.click());
        }

        // Verificar que la ultima pregunta se muestra correctamente
        Espresso.onView(ViewMatchers.withId(R.id.questionField))
            .check(ViewAssertions.matches(ViewMatchers.withText(
                "The Teenage Mutant Ninja Turtles are named after famous artists?"
            )));

        // Acceder a la pantalla Cheat desde la última pregunta
        Espresso.onView(ViewMatchers.withId(R.id.cheatButton)).perform(ViewActions.click());

        // Ver la respuesta en la pantalla Cheat
        Espresso.onView(ViewMatchers.withId(R.id.yesButton)).perform(ViewActions.click());

        // Verificar que la respuesta se muestra en la pantalla Cheat
        Espresso.onView(ViewMatchers.withId(R.id.answerField))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }


    // Verifica que desde la última pregunta se puede acceder
    // a la pantalla Cheat y ver la respuesta, y que al regresar
    // a la pantalla Question se actualiza correctamente el estado
    @Test
    public void test08_LastQuestionAndCheatAndBack() {

        // Iniciar la actividad
        ActivityScenario.launch(QuestionActivity.class);


        // Navegar a la última pregunta suponiendo que hay 4 preguntas
        for (int i = 0; i < 4; i++) {
            Espresso.onView(ViewMatchers.withId(R.id.trueButton))
                .perform(ViewActions.click());
            Espresso.onView(ViewMatchers.withId(R.id.nextButton))
                .perform(ViewActions.click());
        }

        // Verificar que la ultima pregunta se muestra correctamente
        Espresso.onView(ViewMatchers.withId(R.id.questionField))
            .check(ViewAssertions.matches(ViewMatchers.withText(
                "The Teenage Mutant Ninja Turtles are named after famous artists?"
            )));

        // Acceder a la pantalla Cheat desde la última pregunta
        Espresso.onView(ViewMatchers.withId(R.id.cheatButton)).perform(ViewActions.click());

        // Ver la respuesta en CheatActivity
        Espresso.onView(ViewMatchers.withId(R.id.yesButton)).perform(ViewActions.click());

        // Verificar que la respuesta se muestra en CheatActivity
        Espresso.onView(ViewMatchers.withId(R.id.answerField))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Regresar a la pantalla Question
        Espresso.pressBack();

        // Verificar que la pregunta sigue visible
        Espresso.onView(ViewMatchers.withId(R.id.questionField))
            .check(ViewAssertions.matches(ViewMatchers.withText(
                "The Teenage Mutant Ninja Turtles are named after famous artists?"
            )));

        // Verificar que los botones de respuesta están deshabilitados
        // después de ver la  respuesta
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.falseButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));

        // Verificar que el botón "Next" sigue deshabilitado (ya que es la última pregunta)
        Espresso.onView(ViewMatchers.withId(R.id.nextButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));

        // Verificar que el botón "Cheat" sigue deshabilitado
        // (ya que se ha visto la  respuesta a la  pregunta)
        Espresso.onView(ViewMatchers.withId(R.id.nextButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
    }


}
