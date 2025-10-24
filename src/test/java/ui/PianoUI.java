package ui;

import net.serenitybdd.screenplay.targets.Target;

public class PianoUI {

    // Tecla genérica por data-note (blancas o negras)
    public static final Target TECLA_NOTA_GENERICA =
            Target.the("la tecla del piano con data-note {0}")
                    .locatedBy("span.white-key[data-note='{0}'], span.black-key[data-note='{0}']");


}
