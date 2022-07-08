import PageObjects.HomePageYandexScooter;
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

public class YandexLogoCheck {
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

    public void switchToWindow(int numberWindow) {
        String handle = driver.getWindowHandles().toArray()[numberWindow]
                .toString();
        driver.switchTo().window(handle);
    }

    @Test
    public void ClickOnYandexLogo() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePageYandexScooter objHomePage = new HomePageYandexScooter(driver);
        //Задаю ожидаемый url
        String expectedUrl = "https://yandex.ru/";
        //Кликаю на логотип яндекса
        objHomePage.getYandexLogo().click();
        //Переключаюсь на новую вкладку (точнее просто на 2ую)
        switchToWindow(1);

        assertEquals("Не смог перейти на страницу yandex.ru. URl не совпадает", expectedUrl, driver.getCurrentUrl());

    }

    @After
    public void teardown() {
        driver.quit();
    }
}
