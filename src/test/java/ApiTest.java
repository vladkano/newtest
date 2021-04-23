import api.EndpointUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest extends TestBase {

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
    public void test(){
        RestAssured.baseURI = getUrl;

        Map<String, Object> map =  new HashMap<>();

        map.put("ident_method", "PHONE");
        map.put("login", "+79063327418");
        System.out.println(map);

    }


}
