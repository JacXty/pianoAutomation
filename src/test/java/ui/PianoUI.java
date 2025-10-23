package ui;

import net.serenitybdd.screenplay.targets.Target;
// IMPORTANTE: NO uses 'import org.openqa.selenium.By;' en esta definición si usas locatedBy.

public class PianoUI {

    // CAMBIO CRÍTICO: Usar .locatedBy() en lugar de .located(By.cssSelector(...))
    // El método .locatedBy() acepta una cadena de selector que puede contener {0} para sustitución.
    public static final Target TECLA_NOTA_GENERICA =
            Target.the("la tecla de piano con data-note {0}")
                    .locatedBy(".key[data-note='{0}']");
}