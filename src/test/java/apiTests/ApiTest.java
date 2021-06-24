package apiTests;

import config.TestConfig;
import config.TestEndpoints;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class ApiTest extends TestConfig {

//    private static String requestBody = "{\n" +
//            "  \"title\": \"foo\",\n" +
//            "  \"body\": \"bar\",\n" +
//            "  \"userId\": \"1\" \n}";

//    @BeforeEach
//    public  void setup() {
//        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//    }
//
//    @Test
//    public void postRequest() {
//        Response response = given()
//                .header("Content-type", "application/json")
//                .and()
//                .body(requestBody)
//                .when()
//                .post("/posts")
//                .then()
//                .extract().response();
//
//        Assertions.assertEquals(201, response.statusCode());
//        Assertions.assertEquals("foo", response.jsonPath().getString("title"));
//        Assertions.assertEquals("bar", response.jsonPath().getString("body"));
//        Assertions.assertEquals("1", response.jsonPath().getString("userId"));
//        Assertions.assertEquals("101", response.jsonPath().getString("id"));
//    }


//    @Test
//    public void test(){
//        RestAssured.baseURI = getUrl;
//        given()
//                .when()
//                .post(EndpointUrl.USER.addPath(String.format("login: +79656230373")))
//                .then()
//                .statusCode(HTTP_OK)
//        ;
//
//    }


    @Test
    public void test() {
        given()
                .log().all().
                when().get("sergi").
                then().
                log().all();

    }

    @Test
    public void testWithSergi() {
        get(TestEndpoints.SERGI)
                .then().log().all();
    }

//    @Test
//    public void getInByJSON() {
//        String body = "{ident_method: \"PHONE\", login: \"+79501978905\"}";
//        given().
//                body(body).
//                when().
//                post(TestEndpoints.SERGI).
//                then();
//    }

}
