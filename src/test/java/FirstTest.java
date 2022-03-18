import Data.DataProviderClass;
import io.restassured.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class FirstTest{

    @BeforeMethod
    public void setup(){
        RestAssured.baseURI = "http://ergast.com";
        RestAssured.basePath = "/api/f1/2017/circuits";
    }

    public ArrayList<Integer> indexes;
    public List<Map<String, ?>> circuits;

    @Test
    public void getCircuits() {
        circuits = new ArrayList<>();
        indexesInit();
        for (Integer index : indexes) {
            circuits.add(RestAssured
                .given()
                .when()
                .get(".json")
                .then()
                .extract()
                .path("MRData.CircuitTable.Circuits["+index+"]"));
        }
    }

    @Test(dataProvider="circuitId and country", dataProviderClass = DataProviderClass.class)
    public void validateCircuits(String circuitId, String country) {
        given()
                .pathParam("circuitId", circuitId)
                .when()
                .get("/{circuitId}.json")
                .then()
                .assertThat()
                .body("MRData.CircuitTable.Circuits.Location[0].country", equalTo(country));
    }

    public void indexesInit(){
        indexes = new ArrayList<>();
        indexes.add(1);
        indexes.add(5);
    }
}
