package HWJ.difinition;

import HWJ.steps.serenity.SwaggerRestSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class SwaggerDifinition {

    @Steps
    SwaggerRestSteps swaggerRestSteps;

    @Given("I check Inventory status code")
    public void ICheckInventoryStatusCode() {
        swaggerRestSteps.isStatusCodeOk();
    }

    @When("I check headers of the call")
    public void iCheckHeadresOfTheCall(){
        swaggerRestSteps.isHeadersCorrect();
    }

    @Then("I check that call body contains '$field' with '$value' value")
    public void iCheckThatCallBodyContainsFieldWithValue(String field, int value){
        swaggerRestSteps.isBodyContainsFieldWithValue(field, value);
    }

}
