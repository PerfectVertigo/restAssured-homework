import Models.Requests.BookingDatesReq;
import Models.Requests.BooksRequest;
import Models.Responses.BooksResponse;
import Steps.StepsClass;
import Utils.DateUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class FourthTest {
    StepsClass steps = new StepsClass();
    BooksRequest booksReq;
    BookingDatesReq bookingDatesReq;
    Response response;

    @Test
    public void createRequest() throws ParseException {
        bookingDatesReq = new BookingDatesReq(DateUtils.stringToDate("2021-6-15"), DateUtils.stringToDate("2022-2-13"));
        booksReq = new BooksRequest("Emanuel", "Macaroni", 420, true,
                bookingDatesReq, "Supper");
        response = steps.getResponse(booksReq, "https://reqres.in/api/users");
        steps.validateResponse(response);
    }
}
