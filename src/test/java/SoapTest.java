import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
public class SoapTest {
    XmlPath xmlPath;
    Response response;
    List<String> continents;
    List<String> sNameValues;
    @Test
    public void test(){
        response = given()
                .when()
                .get("http://webservices.oorsprong.org/websamples.countryinfo" +
                        "/CountryInfoService.wso/ListOfContinentsByName");

        xmlPath = new XmlPath(response.asString());

        // Validate count of sName
        sNameValues = xmlPath.getList("ArrayOftContinent.tContinent.sName");
        Assert.assertEquals(6, sNameValues.size());

        // Validate sName values
        // equals() method needs correct ordering as well, while containsAll() does not
        setContinents();
        Assert.assertTrue(sNameValues.containsAll(continents) && continents.containsAll(sNameValues));

        // Validate sName value where sCode equals 'AN'
        Assert.assertEquals(xmlPath.get("ArrayOftContinent.tContinent.find{it.sCode == 'AN'}.sName"),
                "Antarctica");

        // Validate the last sName value
        Assert.assertEquals(xmlPath.get("ArrayOftContinent.tContinent.sName[-1]"), "The Americas");
    }

    public void setContinents(){
        continents = new ArrayList<>();
        continents.add("Africa");
        continents.add("Antarctica");
        continents.add("Asia");
        continents.add("Europe");
        continents.add("The Americas");
        continents.add("Ocenania");
    }
}
