package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.actions.Scroll.to;

public class ClickearTecla implements Interaction {

    private final Target tecla;

    public ClickearTecla(Target tecla) {
        this.tecla = tecla;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        actor.attemptsTo(
                WaitUntil.the(tecla, isClickable()).forNoMoreThan(30).seconds(),
                to(tecla)
        );
        new Actions(driver).moveToElement(tecla.resolveFor(actor)).click().build().perform();
    }

    public static ClickearTecla enElPiano(Target tecla) {
        return Tasks.instrumented(ClickearTecla.class, tecla);
    }
}