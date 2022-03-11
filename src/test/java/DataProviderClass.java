import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name="circuitId and country")
    public static Object[][] data() {
        return new Object[][] {
                {"americas", "USA"},
                {"hungaroring", "Hungary"}
        };
    }
}
