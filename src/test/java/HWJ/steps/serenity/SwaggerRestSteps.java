package HWJ.steps.serenity;

import HWJ.pages.SwaggerStore;
import io.restassured.http.Headers;
import net.thucydides.core.annotations.Step;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class SwaggerRestSteps {

    private SwaggerStore swaggerStore;

    @Step
    public void isStatusCodeOk() {
        assertEquals(swaggerStore.getInventory().statusCode(), SC_OK);
    }

    @Step
    public void isHeadersCorrect() {
        Headers headers = swaggerStore.getInventory().headers();
        assertEquals("*", headers.get("Access-Control-Allow-Origin").getValue());
        assertEquals("GET, POST, DELETE, PUT", headers.get("Access-Control-Allow-Methods").getValue());
    }

    @Step
    public void isBodyContainsFieldWithValue(String field, int value){
        assertEquals(value, swaggerStore.getInventory().getBody().jsonPath().getInt(field));
    }
}
