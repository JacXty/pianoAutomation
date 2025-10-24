package tasks;

import interactions.ClickearTecla;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import ui.PianoUI;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class TocarCancion implements Task {

    private final List<String> secuenciaNotas;
    private static final Map<String, String> NOTE_MAPPING = new HashMap<>();

    static {
        NOTE_MAPPING.put("do", "1c");
        NOTE_MAPPING.put("re", "1d");
        NOTE_MAPPING.put("mi", "1e");
        NOTE_MAPPING.put("fa", "1f");
        NOTE_MAPPING.put("sol", "1g");
        NOTE_MAPPING.put("la", "2a");
        NOTE_MAPPING.put("si", "2b");
        // Black keys (assuming 'is' for sharp, 'es' for flat, based on common notation and error message)
        NOTE_MAPPING.put("do#", "1cis");
        NOTE_MAPPING.put("re#", "1dis");
        NOTE_MAPPING.put("fa#", "1fis");
        NOTE_MAPPING.put("sol#", "1gis");
        NOTE_MAPPING.put("la#", "2ais");
    }

    public TocarCancion(List<String> secuenciaNotas) {
        this.secuenciaNotas = secuenciaNotas;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        for (String nota : secuenciaNotas) {
            String dataNote;
            if (nota.matches("[0-9][a-g](is|es)?")) {
                dataNote = nota.toLowerCase();
            } else {
                dataNote = NOTE_MAPPING.get(nota.toLowerCase());
            }

            if (dataNote == null) {
                System.out.println("Warning: No mapping found for note: " + nota);
                continue;
            }

            Target tecla = PianoUI.TECLA_NOTA_GENERICA.of(dataNote);

            actor.attemptsTo(
                    WaitUntil.the(tecla, isVisible()).forNoMoreThan(10).seconds(),
                    ClickearTecla.enElPiano(tecla)
            );
        }
    }

    public static TocarCancion conSecuencia(List<String> secuenciaNotas) {
        return Tasks.instrumented(TocarCancion.class, secuenciaNotas);
    }
}
