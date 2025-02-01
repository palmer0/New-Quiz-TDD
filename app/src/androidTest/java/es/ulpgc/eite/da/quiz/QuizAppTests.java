package es.ulpgc.eite.da.quiz;


import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Activity;
import android.content.Intent;
import android.os.RemoteException;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import es.ulpgc.eite.da.quiz.question.QuestionActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings("ALL")
public class QuizAppTests {


    @Rule
    public ActivityTestRule<QuestionActivity> testRule =
            new ActivityTestRule<>(QuestionActivity.class);

    public QuizAppSteps steps;
    private Activity activity;


    @Before
    public void setUp() {

        try {

            UiDevice device = UiDevice.getInstance(getInstrumentation());
            device.setOrientationNatural();

        } catch (RemoteException e) {
        }

        testRule.launchActivity(new Intent());
        activity = testRule.getActivity();

        steps = new QuizAppSteps(activity);
    }

    @After
    public void tearDown() {

        try {

            UiDevice device = UiDevice.getInstance(getInstrumentation());
            device.setOrientationNatural();

        } catch (RemoteException e) {
        }

        testRule.finishActivity();
    }


    
    @Test
    public void test01_ResponderAPreguntaEnPantallaQuestion() {
        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question>"
        steps.mostrarPregunta(1);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton "<button>"
        steps.pulsarBotonTrue();
        // And mostrar resultado "<result>" a respuesta "<answer>"
        steps.mostrarResultadoARespuesta(activity.getString(R.string.correct_text));
        // And mostrar botones True y False y Cheat desactivados
        steps.mostrarBotonesTrueYFalseYCheatDesactivados();
        // And mostrar boton Next activado
        steps.mostrarBotonNextActivado();
        // When girar pantalla
        steps.girarPantalla();
        // Then mostrar resultado "<result>" a respuesta "<answer>"
        steps.mostrarResultadoARespuesta(activity.getString(R.string.correct_text));
        // And mostrar botones True y False y Cheat desactivados
        steps.mostrarBotonesTrueYFalseYCheatDesactivados();
        // And mostrar boton Next activado
        steps.mostrarBotonNextActivado();


//        Examples:
//        | question           | button | answer | result    |
//        | Question #1: True  | True   | True   | Correct   |
//        | Question #1: True  | False  | True   | Incorrect |
        
    }

    
    @Test
    public void test02_PasarAPantallaCheatSinResponderAPreguntaEnPantallaQuestion() {
        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question>"
        steps.mostrarPregunta(1);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton Cheat
        steps.pulsarBotonCheat();
        // And iniciar pantalla Cheat
        steps.iniciarPantallaCheat();
        // And mostrar mensaje Warning
        steps.mostrarMensajeWarning();
        // And ocultar respuesta
        steps.ocultarRespuesta();
        // And mostrar botones Yes y No activados
        steps.mostrarBotonesYesYNoActivados();
        // When girar pantalla
        steps.girarPantalla();
        // Then mostrar mensaje Warning
        steps.mostrarMensajeWarning();
        // And ocultar respuesta
        steps.ocultarRespuesta();
        // And mostrar botones Yes y No activados
        steps.mostrarBotonesYesYNoActivados();
        // And pulsar boton Back
        steps.pulsarBotonBack();

//        Examples:
//      | question          |
//      | Question #1: True |
            
    }


//    Scenario Outline: volver a pantalla Question sin mostrar respuesta en pantalla Cheat
//
//    Given iniciar pantalla Question
//    And mostrar pregunta "<question>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//    And pulsar boton Cheat
//    And iniciar pantalla Cheat
//    And mostrar mensaje Warning
//    And ocultar respuesta
//    And mostrar botones Yes y No activados
//    And pulsar boton No
//    And finalizar pantalla Cheat
//    And resumir pantalla Question
//    And mostrar pregunta "<question>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//    When girar pantalla
//    Then mostrar pregunta "<question>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//
//    Examples:
//        | question          |
//        | Question #1: True |

    @Test
    public void test03_VolverAPantallaquizQuestionsinMostrarRespuestaEnPantallaCheat() {
        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question>"
        steps.mostrarPregunta(1);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton Cheat
        steps.pulsarBotonCheat();
        // And iniciar pantalla Cheat
        steps.iniciarPantallaCheat();
        // And mostrar mensaje Warning
        steps.mostrarMensajeWarning();
        // And ocultar respuesta
        steps.ocultarRespuesta();
        // And mostrar botones Yes y No activados
        steps.mostrarBotonesYesYNoActivados();
        // When pulsar boton No
        steps.pulsarBotonNo();
        // Then finalizar pantalla Cheat
        steps.finalizarPantallaCheat();
        // And resumir pantalla Question
        steps.resumirPantallaQuestion();
        // And mostrar pregunta "<question>"
        steps.mostrarPregunta(1);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();


        //    When girar pantalla
        steps.girarPantalla();
        //    Then mostrar pregunta "<question>"
        //    And ocultar resultado
        //    And mostrar botones True y False y Cheat activados
        //    And mostrar boton Next desactivado

//        Examples:
//      | question          |
//      | Question #1: True |
        
    }


//    Scenario Outline: mostrar respuesta en pantalla Cheat
//
//    Given iniciar pantalla Question
//    And mostrar pregunta "<question>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//    And pulsar boton Cheat
//    And iniciar pantalla Cheat
//    And mostrar mensaje Warning
//    And ocultar respuesta
//    And mostrar botones Yes y No activados
//    And pulsar boton Yes
//    And mostrar respuesta "<answer>" a pregunta "<question>"
//    And mostrar botones Yes y No desactivados
//    When girar pantalla
//    Then mostrar respuesta "<answer>" a pregunta "<question>"
//    And mostrar botones Yes y No desactivados
//    And pulsar boton Back
//
//    Examples:
//        | question          | answer |
//        | Question #1: True | True   |


    @Test
    public void test04_MostrarRespuestaEnPantallaCheat() {
        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question>"
        steps.mostrarPregunta(1);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton Cheat
        steps.pulsarBotonCheat();
        // And iniciar pantalla Cheat
        steps.iniciarPantallaCheat();
        // And mostrar mensaje Warning
        steps.mostrarMensajeWarning();
        // And ocultar respuesta
        steps.ocultarRespuesta();
        // And mostrar botones Yes y No activados
        steps.mostrarBotonesYesYNoActivados();
        // When pulsar boton Yes
        steps.pulsarBotonYes();
        // Then mostrar respuesta "<answer>" a pregunta "<question>"
        steps.mostrarRespuestaAPregunta(1);
        // And mostrar botones Yes y No desactivados
        steps.mostrarBotonesYesYNoDesactivados();

        // When girar pantalla
        steps.girarPantalla();
        // Then mostrar respuesta "<answer>" a pregunta "<question>"
        steps.mostrarRespuestaAPregunta(1);
        // And mostrar botones Yes y No desactivados
        steps.mostrarBotonesYesYNoDesactivados();

        // And pulsar boton Back
        steps.pulsarBotonBack();

//        Examples:
//      | question          | answer |
//      | Question #1: True | True   |
        
    }


//    Scenario Outline: volver a pantalla Question mostrando respuesta en pantalla Cheat
//
//    Given iniciar pantalla Question
//    And mostrar pregunta "<question1>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//    And pulsar boton Cheat
//    And iniciar pantalla Cheat
//    And mostrar mensaje Warning
//    And ocultar respuesta
//    And mostrar botones Yes y No activados
//    And pulsar boton Yes
//    And mostrar botones Yes y No desactivados
//    And mostrar respuesta "<answer>" a pregunta "<question1>"
//    And girar pantalla
//    And pulsar boton Back
//    And finalizar pantalla Cheat
//    And resumir pantalla Question
//    And mostrar pregunta "<question2>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado
//    When girar pantalla
//    Then mostrar pregunta "<question2>"
//    And ocultar resultado
//    And mostrar botones True y False y Cheat activados
//    And mostrar boton Next desactivado

    @Test
    public void test05_VolverAPantallaQuestionMostrandoRespuestaEnPantallaCheat() {
        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question1>"
        steps.mostrarPregunta(1);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton Cheat
        steps.pulsarBotonCheat();
        // And iniciar pantalla Cheat
        steps.iniciarPantallaCheat();
        // And mostrar mensaje Warning
        steps.mostrarMensajeWarning();
        // And ocultar respuesta
        steps.ocultarRespuesta();
        // And mostrar botones Yes y No activados
        steps.mostrarBotonesYesYNoActivados();
        // And pulsar boton Yes
        steps.pulsarBotonYes();
        // And mostrar botones Yes y No desactivados
        steps.mostrarBotonesYesYNoDesactivados();
        // And mostrar respuesta "<answer>" a pregunta "<question1>"
        steps.mostrarRespuestaAPregunta(1);
        // When pulsar boton Back
        steps.pulsarBotonBack();
        // Then finalizar pantalla Cheat
        steps.finalizarPantallaCheat();
        // And resumir pantalla Question
        steps.resumirPantallaQuestion();
        // And mostrar pregunta "<question2>"
        steps.mostrarPregunta(2);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();

        // When girar pantalla
        steps.girarPantalla();
        // And mostrar pregunta "<question2>"
        steps.mostrarPregunta(2);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();


//        Examples:
//      | question1         | answer | question2          |
//      | Question #1: True | True   | Question #2: False |
            
    }

    @Test
    public void test06_PasarASiguientePreguntaEnPantallaQuestion() {
        // Given iniciar pantalla Question
        steps.iniciarPantallaQuestion();
        // And mostrar pregunta "<question1>"
        steps.mostrarPregunta(1);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // And pulsar boton "<button>"
        steps.pulsarBotonTrue();
        // And mostrar resultado "<result>" a respuesta "<answer>"
        steps.mostrarResultadoARespuesta(activity.getString(R.string.correct_text));
        // And mostrar botones True y False y Cheat desactivados
        steps.mostrarBotonesTrueYFalseYCheatDesactivados();
        // And mostrar boton Next activado
        steps.mostrarBotonNextActivado();
        // And girar pantalla
        steps.girarPantalla();
        // And mostrar resultado "<result>" a respuesta "<answer>"
        steps.mostrarResultadoARespuesta(activity.getString(R.string.correct_text));
        // And mostrar botones True y False y Cheat desactivados
        steps.mostrarBotonesTrueYFalseYCheatDesactivados();
        // And mostrar boton Next activado
        steps.mostrarBotonNextActivado();
        // And pulsar boton Next
        steps.pulsarBotonNext();;
        // And mostrar pregunta "<question2>"
        steps.mostrarPregunta(2);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();
        // When girar pantalla
        steps.girarPantalla();
        // Then mostrar pregunta "<question2>"
        steps.mostrarPregunta(2);
        // And ocultar resultado
        steps.ocultarResultado();
        // And mostrar botones True y False y Cheat activados
        steps.mostrarBotonesTrueYFalseYCheatActivados();
        // And mostrar boton Next desactivado
        steps.mostrarBotonNextDesactivado();

//        Examples:
//      | question1         | button  | answer  | result    | question2          |
//      | Question #1: True | True    | True    | Correct   | Question #2: False |
//      | Question #1: True | False   | True    | Incorrect | Question #2: False |
            
    }
}