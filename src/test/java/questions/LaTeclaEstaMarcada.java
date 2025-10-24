package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import ui.PianoUI;

public class LaTeclaEstaMarcada implements Question<Boolean> {

    private final String nota;

    public LaTeclaEstaMarcada(String nota) {
        this.nota = nota;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return PianoUI.TECLA_NOTA_GENERICA.of(nota)
                .resolveFor(actor)
                .getAttribute("class")
                .contains("marked");
    }

    public static LaTeclaEstaMarcada conNota(String nota) {
        return new LaTeclaEstaMarcada(nota);
    }
}