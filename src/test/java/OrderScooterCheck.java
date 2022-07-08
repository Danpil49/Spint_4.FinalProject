import PageObjects.HomePageYandexScooter;
import PageObjects.OrderPageYandexScooter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class OrderScooterCheck {
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
    public void OrderFlowTest(){
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // Нажатие на кнопку "Заказать"
        HomePageYandexScooter objHomePage = new HomePageYandexScooter(driver);
        objHomePage.getOrderButtons().get(0).click();

        // Заполнение информации о заказчике
        OrderPageYandexScooter objOrderPage = new OrderPageYandexScooter(driver);
        objOrderPage.setPersonalInfo("Тест", "Тестов", "Москва, Новослободская 228", "12345678909");
        objOrderPage.setRentalInfo("07.07.2022", "тестовый коммент");

        //Подтверждаем заказ. Вынес отдельно, чтобы удобно было тестить, так как на Хроме это не работает
        objOrderPage.getConfirmButton().click();

        //Проверяем появилось ли сообщение о успешном заказе
        try {
            objOrderPage.waitForLoadLocator(objOrderPage.getSuccessMassage());
            assertTrue("Сообщение о успешном заказе не появилось на UI", driver.findElement(objOrderPage.getSuccessMassage()).isDisplayed());
        } catch (TimeoutException e) {
            fail("Сообщение о успешном заказе не появилось на UI");
        }
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
