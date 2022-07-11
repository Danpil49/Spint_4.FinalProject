import PageObjects.OrderPageYandexScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;

import static org.junit.Assert.assertTrue;


public class OrderScooterInputsFirstPageCheck {


    @RunWith(Parameterized.class)
    public static class OrderInputsCheckWithParamsFirstPage extends BaseTestClass {
        private final int index;
        private final String value;

        public OrderInputsCheckWithParamsFirstPage(int index, String value) {
            this.index = index;
            this.value = value;
        }

        @Before
        public void testConf(){
            startUp();
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
        public void orderFlowInputTest() {
            driver.get(orderPageUrl);
            OrderPageYandexScooter orderPage = new OrderPageYandexScooter(driver);
            //Массив веб элементов input
            WebElement[] inputsWebElements = new WebElement[]{orderPage.getFirstNameField(), orderPage.getSecondNameField(),
                    orderPage.getAddressField(), orderPage.getMetroDropdownField(), orderPage.getPhoneNumberField()};
            //Заполнение веб элемента тестовыми данными и нажатие на кнопку "Далее"
            orderPage.waitForLoadElement(inputsWebElements[index]);
            inputsWebElements[index].sendKeys(value);
            orderPage.getNextButton().click();

            //Массив ошибок под каждый элемент выше
            WebElement[] inputsErrorsWebElements = new WebElement[]{orderPage.getFirstNameErrorField(), orderPage.getSecondNameErrorField(),
                    orderPage.getAddressErrorField(), orderPage.getMetroDropdownErrorField(), orderPage.getPhoneNumberErrorField()};

            //Проверка, что сообщение об ошибке отображается
            orderPage.waitForLoadElement(inputsErrorsWebElements[index]);
            assertTrue("Сообщение об ошибке не отобразилось", inputsErrorsWebElements[index].isDisplayed());
        }

        @After
        public void teardown() {
            driver.quit();
        }
    }
}
