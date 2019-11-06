package HWJ.steps;

import HWJ.pages.SwaggerStore;
import HWJ.pages.dto.OrderDTO;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import net.thucydides.core.annotations.Step;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

@Log4j2
public class SwaggerRestSteps {

    private SwaggerStore swaggerStore = new SwaggerStore();

    private OrderDTO orderDTO;

    @Step
    public void isGetInventoryStatusCodeOk() {
        statusCodeCheck(getStatusCode(swaggerStore.getInventory()));
    }

    @Step
    public void isGetOrderStatusCodeOk() {
        statusCodeCheck(getStatusCode(swaggerStore.getAnOrder(orderDTO.getId())));
    }

    @Step
    public void isPostOrderStatusCodeOk() {
        statusCodeCheck(getStatusCode(swaggerStore.placeAnOrder(orderDTO)));
    }

    @Step
    public void isDeleteOrderStatusCodeOk() {
        statusCodeCheck(getStatusCode(swaggerStore.deleteAnOrder(orderDTO.getId())));
    }

    @Step
    public void isHeadersCorrect(String method) {
        switch (method.toLowerCase()) {
            case "get inventory":
                log.info(String.format("Headers check for - %s", method));
                headersCheck(swaggerStore.getInventory());
                break;
            case "post order":
                log.info(String.format("Headers check for - %s", method));
                headersCheck(swaggerStore.placeAnOrder(orderDTO));
                break;
            case "get order":
                log.info(String.format("Headers check for - %s", method));
                headersCheck(swaggerStore.getAnOrder(orderDTO.getId()));
                break;
            case "delete order":
                log.info(String.format("Headers check for - %s", method));
                headersCheck(swaggerStore.deleteAnOrder(orderDTO.getId()));
                break;
        }
    }

    @Step
    public void isBodyContainsFieldWithValue(String field, int value) {
        log.info(String.format("Check that Inventory contains field '%s' with value '%d'", field, value));
        assertEquals(field, swaggerStore.getInventory().getBody().jsonPath().getString((field)));
        assertEquals(value, swaggerStore.getInventory().getBody().jsonPath().getInt(String.valueOf(value)));
    }

    @Step
    public void placeAnOrderToStore(int id, int quantity) {
        orderDTO = generateOrderDTO(id, quantity);

        Response postResponse = swaggerStore.placeAnOrder(orderDTO);
        Response getResponse = swaggerStore.getAnOrder(orderDTO.getId());

        assertEquals(orderDTO, postResponse.as(OrderDTO.class));
        assertEquals(orderDTO, getResponse.as(OrderDTO.class));

        int actualStatusCode = getStatusCode(postResponse);
        if (id > 0 && id <= 10 || quantity > 0 && quantity <= 10) {
            assertEquals(SC_OK, actualStatusCode);
        } else {
            assertEquals(SC_BAD_REQUEST, actualStatusCode);
        }
    }

    @Step
    public void deleteAnOrder(int id) {
        assertEquals(SC_OK, getStatusCode(swaggerStore.deleteAnOrder(id)));
    }

    @Step
    public void cleanUp() {
        swaggerStore.deleteAnOrder(orderDTO.getId());
    }

    private void statusCodeCheck(int statusCode) {
        log.info(String.format("Status code of the call: %d", statusCode));
        assertEquals(SC_OK, statusCode);
    }

    private void headersCheck(Response response) {
        Headers headers = response.headers();
        assertEquals("*", headers.get("Access-Control-Allow-Origin").getValue());
        assertEquals("GET, POST, DELETE, PUT", headers.get("Access-Control-Allow-Methods").getValue());
    }

    private int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    private OrderDTO generateOrderDTO(int id, int quantity) {
        orderDTO = new OrderDTO.OrderDTOBuilder()
                .withId(id)
                .withPetId()
                .withQuantity(quantity)
                .withShipData()
                .withComplete()
                .withStatus()
                .build();

        log.info(String.format("Order was created with such fields: %s", orderDTO.toString()));

        return orderDTO;
    }
}
