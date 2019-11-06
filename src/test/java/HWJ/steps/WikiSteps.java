package HWJ.steps;

import HWJ.pages.DictionaryPage;
import lombok.extern.log4j.Log4j2;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static utils.EnvironmentPropertyLoader.getProperty;

@Log4j2
public class WikiSteps {

    DictionaryPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void startsSearch() {
        dictionaryPage.lookupTerms();
    }

    @Step
    public void shouldSeeDefinition() {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(getProperty("definition"))));
    }

    @Step
    public void isTheHomePage() {
        dictionaryPage.open();
    }

    @Step
    public void looksFor() {
        log.info(String.format("Search a %s through Wikipedia", getProperty("word")));
        enters(getProperty("word"));
        startsSearch();
    }
}