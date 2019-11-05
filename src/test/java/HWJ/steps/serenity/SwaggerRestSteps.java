package HWJ.steps.serenity;

import HWJ.pages.SwaggerStore;
import HWJ.pages.dto.OrderDTO;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class SwaggerRestSteps {

    private SwaggerStore swaggerStore = new SwaggerStore();

    private OrderDTO orderDTO;

    @Step
    public void isGetInventoryStatusCodeOk() {
        assertEquals(getStatusCode(swaggerStore.getInventory()), SC_OK);
    }

    @Step
    public void isGetOrderStatusCodeOk() {
        assertEquals(getStatusCode(swaggerStore.getAnOrder(orderDTO.getId())), SC_OK);
    }

    @Step
    public void isPostOrderStatusCodeOk() {
        assertEquals(getStatusCode(swaggerStore.placeAnOrder(orderDTO)), SC_OK);
    }

    @Step
    public void isDeleteOrderStatusCodeOk() {
        assertEquals(getStatusCode(swaggerStore.deleteAnOrder(orderDTO.getId())), SC_OK);
    }

    @Step
    public void isHeadersCorrect(String method) {
        switch (method.toLowerCase()) {
            case "get inventory":
                headersCheck(swaggerStore.getInventory());
                break;
            case "post order":
                headersCheck(swaggerStore.placeAnOrder(orderDTO));
                break;
            case "get order":
                headersCheck(swaggerStore.getAnOrder(orderDTO.getId()));
                break;
            case "delete order":
                headersCheck(swaggerStore.deleteAnOrder(orderDTO.getId()));
                break;
        }
    }

    @Step
    public void isBodyContainsFieldWithValue(String field, int value) {
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
        return orderDTO;
    }
}
