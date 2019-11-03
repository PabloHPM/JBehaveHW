package HWJ.difinition;

import HWJ.steps.serenity.SwaggerRestSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

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
    public void cleanUp(){
        swaggerRestSteps.cleanUp();
    }

}
