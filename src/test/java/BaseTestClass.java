import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;

public class BaseTestClass {
    public WebDriver driver;
    public String homePageUrl;
    public String orderPageUrl;
    boolean useChrome = true;

    public void startUp() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/test/config.json"));
            JSONObject jsonObject = (JSONObject)obj;
            useChrome = (boolean)jsonObject.get("useChrome");
            homePageUrl = (String)jsonObject.get("homePageUrl");
            orderPageUrl = (String)jsonObject.get("orderPageUrl");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if(useChrome) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
    }
}
