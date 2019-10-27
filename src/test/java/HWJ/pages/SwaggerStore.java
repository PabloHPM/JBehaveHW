package HWJ.pages;

import HWJ.pages.dto.OrderDTO;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SwaggerStore {

    private static final String PETSTORE_SWAGGER_HOME_URL = "https://petstore.swagger.io/v2/";
    private static final String GET_INVENTORY = "store/inventory";
    private static final String PLACE_ORDER = "/store/order";
    private static final String ORDER_ID = "/store/order/{orderId}";


    public Response getInventory() {
        return spec()
                .when()
                .get(GET_INVENTORY)
                .then()
                .extract()
                .response();
    }

    public Response placeAnOrder(OrderDTO orderDto) {
        return spec()
                .body(orderDto)
                .when()
                .post(PLACE_ORDER)
                .then()
                .extract()
                .response();
    }

    public Response getAnOrder(int id) {
        return spec()
                .pathParam("orderId",id)
                .when()
                .get(ORDER_ID)
                .then()
                .extract()
                .response();
    }

    public Response deleteAnOrder(int id) {
        return spec()
                .pathParam("orderId", id)
                .when()
                .delete(ORDER_ID)
                .then()
                .extract()
                .response();
    }


    private RequestSpecification spec() {
        return given()
                .baseUri(PETSTORE_SWAGGER_HOME_URL)
                .contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
    }
}
