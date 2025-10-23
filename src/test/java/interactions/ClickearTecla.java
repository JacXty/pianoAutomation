package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.core.steps.Instrumented;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import net.serenitybdd.core.Serenity; // Necesitas esto para obtener el WebDriver

public class ClickearTecla implements Interaction {

    private final Target target;

    public ClickearTecla(Target target) {
        this.target = target;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Obtener la instancia del WebDriver actual
        WebDriver driver = Serenity.getWebdriverManager().getCurrentDriver();

        // 1. Encontrar el elemento usando el Target
        // Usamos resolveFor(actor) para obtener un WebElementFacade
        // y luego getWrappedElement() para obtener el WebElement de Selenium puro
        org.openqa.selenium.WebElement elemento = target.resolveFor(actor).getWrappedElement();

        // 2. Ejecutar el clic por JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", elemento);

        // Opcional: Agregar una pequeña espera después del clic
        // para asegurarte de que el sonido se registra si es necesario.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static ClickearTecla en(Target target) {
        return Instrumented.instanceOf(ClickearTecla.class).withProperties(target);
    }
}