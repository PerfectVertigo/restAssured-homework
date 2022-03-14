import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SecondTest {

    @Test
    public void validateTheLastName(){
        given()
                .when()
                .get("https://chercher.tech/sample/api/product/read")
                .then()
                .assertThat()
                .body("records[-1].name", equalTo("cherchertech"));
    }

    @Test
    public void validateAllCreatedDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now().plusDays(1);

        JsonPath jsp = new JsonPath( given()
                .when()
                .get("https://chercher.tech/sample/api/product/read")
                .asString());

        // 2000
        int size = jsp.getInt("records.created.size()");

        for(int i = 0; i < size; i++)
            assertThat(jsp.get("records.created["+i+"]"), lessThan(dtf.format(now)));
    }

    @Test
    public void createRequest(){
        // using HashMap and then converting to JSONObject to avoid 'Unchecked Call' warning
        Map<String, Object> requestMap = new HashMap<>();
        Map<String, Object> bookingdatesMap = new HashMap<>();

        requestMap.put("firstname", "Emanuel"); // String
        requestMap.put("lastname", "Macaroni"); // String
        requestMap.put("totalprice", 420); // Number
        requestMap.put("depositpaid", true); // Boolean

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookingdatesMap.put("checkin", dtf.format(LocalDate.of(2021, 6, 15))); // Date
        bookingdatesMap.put("checkout", dtf.format(LocalDate.of(2022, 2, 13))); // Date

        JSONObject bookingdates = new JSONObject(bookingdatesMap);
        requestMap.put("bookingdates", bookingdates); // Object
        requestMap.put("additionalneeds", "Supper"); // String

        JSONObject request = new JSONObject(requestMap);

        given()
                .contentType(ContentType.JSON)
                .and()
                .body(request.toJSONString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log()
                .ifStatusCodeIsEqualTo(201);
    }
}
