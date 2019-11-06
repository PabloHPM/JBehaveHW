package HWJ.difinition;

import HWJ.steps.SwaggerRestSteps;
import lombok.extern.log4j.Log4j2;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

@Log4j2
public class SwaggerDefinition {

    @Steps
    SwaggerRestSteps swaggerRestSteps;

    @Then("I check status code for '$method'")
    public void ICheckStatusCode(String method) {
        switch (method.toLowerCase()) {
            case "get inventory":
                swaggerRestSteps.isGetInventoryStatusCodeOk();
                break;
            case "post order":
                swaggerRestSteps.isPostOrderStatusCodeOk();
                break;
            case "get order":
                swaggerRestSteps.isGetOrderStatusCodeOk();
                break;
            case "delete order":
                swaggerRestSteps.isDeleteOrderStatusCodeOk();
                break;
            default:
                log.fatal(String.format("Method '%s' not be found", method));
                new IllegalStateException(String.format("Method '%s' not be found", method));
                break;
        }
    }

    @When("I check call headers for '$method'")
    public void iCheckCallHeaders(String method) {
        swaggerRestSteps.isHeadersCorrect(method);
    }

    @Given("I check that call body contains '$field' with '$value' value")
    public void iCheckThatCallBodyContainsFieldWithValue(String field, int value) {
        swaggerRestSteps.isBodyContainsFieldWithValue(field, value);
    }

    @Given("I place an order to swagger store with '$id', '$quantity'")
    public void iPlaceAnOrderToSwaggerStore(int id, int quantity) {
        swaggerRestSteps.placeAnOrderToStore(id, quantity);
    }

    @Then("I delete an order from the store with id '$id'")
    public void iPlaceAnOrderToSwaggerStore(int id) {
        swaggerRestSteps.deleteAnOrder(id);
    }

    @AfterScenario
    public void cleanUp() {
        swaggerRestSteps.cleanUp();
    }
}
