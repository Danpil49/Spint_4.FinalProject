import PageObjects.OrderPageYandexScooter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class OrderScooterInputsFirstPageCheck {


    @RunWith(Parameterized.class)
    public static class OrderInputsCheckWithParamsFirstPage {
        private WebDriver driver;
        private final int index;
        private final String value;

        public OrderInputsCheckWithParamsFirstPage(int index, String value) {
            this.index = index;
            this.value = value;
        }

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

        @Parameterized.Parameters(name = "{index}: index = {0}, value = {1}")
        public static Object[][] getTestData() {
            return new Object[][] {
                    {0, "FirstName"},
                    {1, "SecondName"},
                    {2, "Address"},
                    {3, "MetroDropdown"},
                    {4, "PhoneNumber"}
            };
        }

        @Test
        public void OrderFlowInputTest() {
            driver.get("https://qa-scooter.praktikum-services.ru/order");
            OrderPageYandexScooter orderPage = new OrderPageYandexScooter(driver);
            //Массив веб элементов input
            WebElement[] inputsWebElements = new WebElement[] {orderPage.getFirstNameField(), orderPage.getSecondNameField(),
                    orderPage.getAddressField(), orderPage.getMetroDropdownField(), orderPage.getPhoneNumberField()};
            //Заполнение веб элемента тестовыми данными и нажатие на кнопку "Далее"
            orderPage.waitForLoadElement(inputsWebElements[index]);
            inputsWebElements[index].sendKeys(value);
            orderPage.getNextButton().click();

            //Массив ошибок под каждый элемент выше
            WebElement[] inputsErrorsWebElements = new WebElement[] {orderPage.getFirstNameErrorField(), orderPage.getSecondNameErrorField(),
                    orderPage.getAddressErrorField(), orderPage.getMetroDropdownErrorField(), orderPage.getPhoneNumberErrorField()};

            //Проверка что сообщение об ошибке отображается
            try {
                orderPage.waitForLoadElement(inputsErrorsWebElements[index]);
                assertTrue("Сообщение об ошибке не отобразилось", inputsErrorsWebElements[index].isDisplayed());
            } catch (TimeoutException e) {
                fail("Сообщение об ошибке не отобразилось");
            }
        }

        @After
        public void teardown() {
            driver.quit();
        }
    }
}
