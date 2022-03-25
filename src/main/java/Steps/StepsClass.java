package Steps;

import Models.Responses.BooksResponse;
import Utils.DateUtils;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;

import java.text.ParseException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class StepsClass {

    @Step("Get Response")
    public Response getResponse(Object req, String URI){
        return given()
                .filter(new AllureRestAssured())
                .contentType("application/json")
                .body(req)
                .when()
                .post(URI);
    }

    @Step("Validate Response")
    public void validateResponse(Response response) throws ParseException {
            BooksResponse booksResponse = response.body().as(BooksResponse.class);
            assertThat(response.statusCode(), Matchers.either(Matchers.is(200)).or(Matchers.is(201)));
            Assert.assertEquals("Emanuel", booksResponse.firstName());
            Assert.assertEquals("Macaroni", booksResponse.lastName());
            Assert.assertEquals(420, booksResponse.totalPrice());
            Assert.assertTrue(booksResponse.depositPaid());
            Assert.assertEquals(DateUtils.stringToDate("2021-6-15"), booksResponse.bookingDatesRes().checkIn());
            Assert.assertEquals(DateUtils.stringToDate("2022-2-13"), booksResponse.bookingDatesRes().checkOut());
    }
}
