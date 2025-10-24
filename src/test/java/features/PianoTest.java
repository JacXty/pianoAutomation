package test;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import org.junit.Before; // Importación para el setup
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import tasks.TocarCancion;
import questions.LaTeclaEstaMarcada;
import ui.PianoUI;

import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 🛑 Garantiza el orden 00, 01, 02, 03
@RunWith(SerenityRunner.class)
public class PianoTest {

    @Managed(driver = "chrome")
    WebDriver browser;

    Actor usuario = Actor.named("Test");

    // --------------------------------------------------------------------------------------------------
    // CONSTANTES DE NOTAS (Octava 2/3 para el Himno de la Alegría)
    // --------------------------------------------------------------------------------------------------

    // Secuencia Base del Himno de la Alegría: si, si, do, re, re, do, si, la, sol, sol, la, si, si, la, la
    private final List<String> HIMNO_ALEGRIA_BASE = List.of(
            "2b", "2b", "2c", "2d", "2d", "2c", "2b", "2a",
            "2g", "2g", "2a", "2b", "2b", "2a", "2a"
    );

    // Secuencia Compleja: si, si, do, re, re, do, si, la, sol, sol, la, si, la, sol, sol, la, si, sol, la, si, do, si sol, la, si, do, si, sol, sol, fa, re
    private final List<String> SECUENCIA_COMPLEJA = List.of(
            "2b", "2b", "2c", "2d", "2d", "2c", "2b", "2a",
            "2g", "2g", "2a", "2b", "2a", "2g", "2g", "2a",
            "2b", "2g", "2a", "2b", "3c", "2b", "2g", "2a",
            "2b", "3c", "2b", "2g", "2g", "2f", "2d" // Última nota: 2d
    );

    // --------------------------------------------------------------------------------------------------
    // CONFIGURACIÓN (SETUP)
    // --------------------------------------------------------------------------------------------------

    @Before // 🛑 Se ejecuta antes de CADA método @Test
    public void setupScenario() {
        usuario.can(BrowseTheWeb.with(browser));

        // 1. Abrir la página.
        givenThat(usuario).attemptsTo(
                Open.url("https://www.musicca.com/es/piano")
        );

   }

    // --------------------------------------------------------------------------------------------------
    // ESCENARIOS DE PRUEBA
    // --------------------------------------------------------------------------------------------------


    @Test
    public void _01_tocarSecuenciaBaseDelHimnoDeLaAlegria() throws InterruptedException {
        // La validación solo se centra en la última tecla presionada ('la' -> '2a')
        final String ultimaNota = "2a";

        when(usuario).attemptsTo(
                // 1. Ejecuta toda la secuencia (incluyendo repeticiones).
                TocarCancion.conSecuencia(HIMNO_ALEGRIA_BASE)
        );

    }

    @Test
    public void _02_repetirSecuenciaBaseDosVeces() {

        // Combina la secuencia base dos veces
        List<String> secuenciaDoble = new ArrayList<>(HIMNO_ALEGRIA_BASE);
        secuenciaDoble.addAll(HIMNO_ALEGRIA_BASE);


        when(usuario).attemptsTo(
                TocarCancion.conSecuencia(secuenciaDoble)
        );

    }

    @Test
    public void _03_tocarSecuenciaComplejaSeguidaDeSecuenciaBase() {

        // Combina la Secuencia Compleja y la Secuencia Base
        List<String> secuenciaFinal = new ArrayList<>(SECUENCIA_COMPLEJA);
        secuenciaFinal.addAll(HIMNO_ALEGRIA_BASE);

        when(usuario).attemptsTo(
                TocarCancion.conSecuencia(secuenciaFinal)
        );
    }
}