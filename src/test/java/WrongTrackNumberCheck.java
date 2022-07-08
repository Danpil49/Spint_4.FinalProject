import PageObjects.HomePageYandexScooter;
import PageObjects.TrackCheckYandexScooter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class WrongTrackNumberCheck {
    private WebDriver driver;

    @Before
    public void startUp() {
        //Не совсем уверен в строке ниже, действительно ли она запускает тот драйвер который лежит в пакете или тот который у меня прописан в PATH)
        System.setProperty("webdriver.chrome.driver", "../resource/chromedriver.exe");
        boolean useChrome = true;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/test/config.json"));
            JSONObject jsonObject = (JSONObject)obj;
            useChrome = (boolean)jsonObject.get("useChrome");
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
    boolean isDisplayed(By targetLocator) {
        try {
            return driver.findElement(targetLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Test
    public void TrackNumberCheckIsNotFound() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePageYandexScooter homePage = new HomePageYandexScooter(driver);
        homePage.EnterTheTrack("228");

        TrackCheckYandexScooter trackCheckPage = new TrackCheckYandexScooter(driver);
        //Жду и проверяю что отображается картинка "Такого заказа нет"
        trackCheckPage.waitForLoadElement(driver.findElement(trackCheckPage.getNotFoundImg()));
        assertTrue("Не отобразилась картинка 'Такого заказа нет'",isDisplayed(trackCheckPage.getNotFoundImg()));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
