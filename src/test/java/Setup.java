import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class Setup {

    @BeforeMethod
    public void setup(){
        RestAssured.baseURI = "http://ergast.com";
        RestAssured.basePath = "/api/f1/2017/circuits";
    }

}
