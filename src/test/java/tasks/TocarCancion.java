package tasks;

import interactions.ClickearTecla;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.Instrumented;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TocarCancion implements Task {

    private final List<String> notas;

    public TocarCancion(List<String> notas) {
        this.notas = notas;
    }

    /**
     * Tarea estática principal (usa una lista de notas).
     */
    public static Task conNotas(List<String> notas) {
        return Task.where("{0} toca la canción con las notas: " + notas.toString(),
                new TocarCancion(notas));
    }

    /**
     * Tarea auxiliar (usa una secuencia de texto separada por comas).
     */
    public static Task conLaSecuencia(String secuencia) {
        List<String> notas = Arrays.stream(secuencia.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        return Task.where("{0} toca la secuencia \'" + secuencia + "\'",
                new TocarCancion(notas));
    }

    // ... (El método performAs no cambia y se omite por brevedad)
    @Override
    @Step("{0} toca la canción")
    public <T extends Actor> void performAs(T actor) {
        for (String nota : notas) {

            Target tecla = Target.the("la tecla de piano con data-note " + nota)
                    .located(By.cssSelector(String.format(".key[data-note='%s']", nota)));

            actor.attemptsTo(
                    ClickearTecla.en(tecla)
            );
        }
    }
}