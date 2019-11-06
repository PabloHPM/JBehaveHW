package HWJ.difinition;

import HWJ.steps.WikiSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class WikiDefinition {

    @Steps
    WikiSteps endUser;

    @Given("the user is on the Wikionary home page")
    public void givenTheUserIsOnTheWikionaryHomePage() {
        endUser.isTheHomePage();
    }

    @When("the user looks up the definition of the word '$word'")
    public void whenTheUserLooksUpTheDefinitionOf() {
        endUser.looksFor();
    }

    @Then("they should see the definition")
    public void thenTheyShouldSeeADefinitionContainingTheWords() {
        endUser.shouldSeeDefinition();
    }
}
