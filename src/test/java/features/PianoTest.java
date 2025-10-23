package features;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.Interaction; // ¡Importante para EsperarCarga!
import net.serenitybdd.screenplay.Tasks;      // ¡Importante para EsperarCarga!
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

// Imports de Serenity BDD
import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static net.serenitybdd.screenplay.GivenWhenThen.then;

import tasks.TocarCancion;

@ExtendWith(SerenityJUnit5Extension.class)
public class PianoTest {

    WebDriver driver; // Inyectado por Serenity
    private Actor pianista;

    // Clase auxiliar para la pausa fija (lo ideal sería en un archivo separado)
    public static class EsperarCarga implements Interaction {
        private final int milisegundos;

        public EsperarCarga(int milisegundos) {
            this.milisegundos = milisegundos;
        }

        public static EsperarCarga por(int milisegundos) {
            return Tasks.instrumented(EsperarCarga.class, milisegundos);
        }

        @Override
        public <T extends Actor> void performAs(T actor) {
            try {
                Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @BeforeEach
    public void setup() {
        pianista = Actor.named("El Pianista QA")
                .can(BrowseTheWeb.with(driver));

        // Abrir la URL y luego esperar 1.5 segundos (1500ms) para la carga.
        givenThat(pianista).attemptsTo(
                Open.url("https://www.musicca.com/es/piano"),
                EsperarCarga.por(1500)
        );
    }

    // --- ESCENARIO 1: Secuencia Base ---
    @Test
    public void escenario1_tocarSecuenciaBaseDelHimnoDeLaAlegria() {
        String secuenciaBase = "si, si, do, re, re, do, si, la, sol, sol, la, si, si, la, la";

        when(pianista).attemptsTo(
                TocarCancion.conLaSecuencia(secuenciaBase)
        );

        then(pianista).should();
    }

    // --- ESCENARIO 2: Repetir Secuencia ---
    @Test
    public void escenario2_repetirSecuenciaBaseDosVeces() {
        String secuenciaBase = "si, si, do, re, re, do, si, la, sol, sol, la, si, si, la, la";
        String secuenciaRepetida = secuenciaBase + ", " + secuenciaBase;

        when(pianista).attemptsTo(
                TocarCancion.conLaSecuencia(secuenciaRepetida)
        );

        then(pianista).should();
    }

    // --- ESCENARIO 3: Secuencia Completa ---
    @Test
    public void escenario3_tocarSecuenciaCompleta() {
        String parteA = "si, si, do, re, re, do, si, la, sol, sol, la, si, la, sol, sol";
        String parteB = "la, si, sol, la, si, do, si, sol, la, si, do, si, sol, sol, fa, re";
        String parteC = "si, si, do, re, re, do, si, la, sol, sol, la, si, si, la, la";

        String secuenciaCompleta = parteA + ", " + parteB + ", " + parteC;

        when(pianista).attemptsTo(
                TocarCancion.conLaSecuencia(secuenciaCompleta)
        );

        then(pianista).should();
    }
}