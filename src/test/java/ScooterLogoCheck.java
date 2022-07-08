import PageObjects.OrderPageYandexScooter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ScooterLogoCheck {
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

    @Test
    public void ClickOnScooterLogo() {
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        OrderPageYandexScooter objOrderPage = new OrderPageYandexScooter(driver);
        //Задаю ожидаемый url
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/";
        //Кликаю на логотип самоката
        objOrderPage.getScooterLogo().click();

        assertEquals("Не смог перейти на главную страницу самоката. URl не совпадает", expectedUrl, driver.getCurrentUrl());

    }

    @After
    public void teardown() {
        driver.quit();
    }
}
