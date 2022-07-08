import PageObjects.OrderPageYandexScooter;
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

import static org.junit.Assert.assertFalse;

public class OrderScooterInputsSecondPageCheck {
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
    public void OrderFlowInputTest() {
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        OrderPageYandexScooter orderPage = new OrderPageYandexScooter(driver);
        //Пропускаем первую страницу
        orderPage.setPersonalInfo("Тест", "Тестов", "Москва, Новослободская 228", "12345678909");

        //Вводим дату и не вводим срок аренды
        orderPage.getDatePickerField().sendKeys("08.07.2021");
        orderPage.getOrderButton().click();

        //Проверяем что поле с подтверждением не появилось
        assertFalse("Модальное окно с подтверждением заказа появилось", isDisplayed(orderPage.getConfirmModalForm()));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
