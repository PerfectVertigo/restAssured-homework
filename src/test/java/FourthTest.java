import Requests.BookingDatesReq;
import Requests.BooksRequest;
import Responses.BooksResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class FourthTest {
    BooksRequest booksReq;
    BookingDatesReq bookingDatesReq;

    @Test
    public void createRequest(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        bookingDatesReq = new BookingDatesReq(dtf.format(LocalDate.of(2021, 6, 15)),
                dtf.format(LocalDate.of(2022, 2, 13)));

        booksReq = new BooksRequest("Emanuel", "Macaroni", 420, true,
                bookingDatesReq, "Supper");

        Response response = given()
                .contentType("application/json")
                .body(booksReq)
                .when()
                .post("https://reqres.in/api/users");

        validateResponse(response);
    }

    public void validateResponse(Response response){
        if(response.statusCode() == 200 || response.statusCode() == 201) {
            BooksResponse booksResponse = response.body().as(BooksResponse.class);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Assert.assertEquals("Emanuel", booksResponse.firstName());
            Assert.assertEquals("Macaroni", booksResponse.lastName());
            Assert.assertEquals(420, booksResponse.totalPrice());
            Assert.assertTrue(booksResponse.depositPaid());
            Assert.assertEquals( dtf.format(LocalDate.of(2021, 6, 15)),
                    booksResponse.bookingDatesRes().checkIn());
            Assert.assertEquals(dtf.format(LocalDate.of(2022, 2, 13)),
                    booksResponse.bookingDatesRes().checkOut());
        } else {
            System.out.println("Serialization has failed!");
        }
    }
}
