package es.ulpgc.eite.da.quiz;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.ulpgc.eite.da.quiz.app.AppMediator;
import es.ulpgc.eite.da.quiz.question.QuestionActivity;


// Project: Quiz
// Created by Luis Hernandez 
// Copyright (c) 2025 EITE (ULPGC)
@RunWith(AndroidJUnit4.class)
public class QuestionActivityTest {

    @Before
    public void setUp() {
        // Reiniciar el estado del AppMediator antes de cada test
        AppMediator.resetInstance();

        // Iniciar la actividad antes de cada test
        ActivityScenario.launch(QuestionActivity.class);
    }


    @Test
    public void testInitialState() {
        // Verificar que la pregunta inicial se muestra correctamente
        Espresso.onView(ViewMatchers.withId(R.id.questionField))
            .check(ViewAssertions.matches(ViewMatchers.withText(
                "Christian Bale played Batman in 'The Dark Knight Rises'?"
            )));

        // Verificar que los botones están habilitados
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.falseButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.cheatButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));

        // Verificar que el botón "Next" está deshabilitado inicialmente
        Espresso.onView(ViewMatchers.withId(R.id.nextButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
    }


    @Test
    public void testTrueButtonCorrectAnswer() {
        // Hacer clic en el botón "True"
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .perform(ViewActions.click());

        // Verificar que el resultado es correcto
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText("Correct!")));

        // Verificar que los botones de respuesta están deshabilitados
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.falseButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));

        // Verificar que el botón "Next" está habilitado
        Espresso.onView(ViewMatchers.withId(R.id.nextButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }

    @Test
    public void testFalseButtonIncorrectAnswer() {
        // Hacer clic en el botón "False"
        Espresso.onView(ViewMatchers.withId(R.id.falseButton))
            .perform(ViewActions.click());

        // Verificar que el resultado es incorrecto
        Espresso.onView(ViewMatchers.withId(R.id.resultField))
            .check(ViewAssertions.matches(ViewMatchers.withText("Incorrect!")));

        // Verificar que los botones de respuesta están deshabilitados
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.falseButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));

        // Verificar que el botón "Next" está habilitado
        Espresso.onView(ViewMatchers.withId(R.id.nextButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }

    @Test
    public void testNextButton() {
        // Responder a la pregunta actual
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .perform(ViewActions.click());

        // Hacer clic en el botón "Next"
        Espresso.onView(ViewMatchers.withId(R.id.nextButton))
            .perform(ViewActions.click());

        // Verificar que la nueva pregunta se muestra correctamente
        Espresso.onView(ViewMatchers.withId(R.id.questionField))
            .check(ViewAssertions.matches(ViewMatchers.withText(
                "The Gremlins movie was released in 1986?"
            )));

        // Verificar que los botones de respuesta están habilitados nuevamente
        Espresso.onView(ViewMatchers.withId(R.id.trueButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.falseButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));

        // Verificar que el botón "Next" está deshabilitado
        Espresso.onView(ViewMatchers.withId(R.id.nextButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
    }

    @Test
    public void testCheatButtonNavigation() {
        // Hacer clic en el botón "Cheat" en QuestionActivity
        Espresso.onView(ViewMatchers.withId(R.id.cheatButton))
            .perform(ViewActions.click());

        // Verificar que se navega a la pantalla de "Cheat" (CheatActivity)
        Espresso.onView(ViewMatchers.withId(R.id.confirmationField))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Verificar que el texto de confirmación se muestra correctamente
        Espresso.onView(ViewMatchers.withId(R.id.confirmationField))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.confirmation_text)));

        // Verificar que los botones "Yes" y "No" están habilitados
        Espresso.onView(ViewMatchers.withId(R.id.yesButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.noButton))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()));

        // Verificar que el campo de respuesta está vacío inicialmente
        Espresso.onView(ViewMatchers.withId(R.id.answerField))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.empty_text)));

        // Hacer clic en el botón "Yes" para mostrar la respuesta
        Espresso.onView(ViewMatchers.withId(R.id.yesButton))
            .perform(ViewActions.click());

        // Verificar que el campo de respuesta muestra la respuesta correcta o incorrecta
        Espresso.onView(ViewMatchers.withId(R.id.answerField))
            .check(ViewAssertions.matches(
                Matchers.anyOf( // Usar Hamcrest Matchers.anyOf
                    ViewMatchers.withText(R.string.true_text),
                    ViewMatchers.withText(R.string.false_text)
                )
            ));

        // Verificar que los botones "Yes" y "No" están deshabilitados después del clic en "Yes"
        Espresso.onView(ViewMatchers.withId(R.id.yesButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
        Espresso.onView(ViewMatchers.withId(R.id.noButton))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
    }

}