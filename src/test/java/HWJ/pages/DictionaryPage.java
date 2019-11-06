package HWJ.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("http://en.wiktionary.org/wiki/Wiktionary")
public class DictionaryPage extends PageObject {

    @FindBy(name = "search")
    private WebElement searchTerms;

    @FindBy(name = "go")
    private WebElement lookupButton;

    @FindBy(css = ".mw-parser-output  > ol:nth-child(12) >li")
    private List<WebElement> definitionsList;

    public void enter_keywords(String keyword) {
        searchTerms.sendKeys(keyword);
    }

    public void lookupTerms() {
        lookupButton.click();
    }

    public List<String> getDefinitions() {
        return definitionsList.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}