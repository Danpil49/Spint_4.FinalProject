import PageObjects.OrderPageYandexScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.assertFalse;

public class OrderScooterInputsSecondPageCheck extends BaseTestClass {
    protected String firstName = "Тест";
    protected String secondName = "Тестов";
    protected String address = "Москва, Новослободская 228";
    protected String phoneNumber = "12345678909";
    protected String datePicker = "08.07.2021";

    @Before
    public void testConf(){
        startUp();
    }
    boolean isDisplayed(By targetLocator) {
        try {
            return driver.findElement(targetLocator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    @Test
    public void orderFlowInputTest() {
        driver.get(orderPageUrl);
        OrderPageYandexScooter orderPage = new OrderPageYandexScooter(driver);
        //Пропускаем первую страницу
        orderPage.setPersonalInfo(firstName, secondName, address, phoneNumber);

        //Вводим дату и не вводим срок аренды
        orderPage.getDatePickerField().sendKeys(datePicker);
        orderPage.getOrderButton().click();

        //Проверяем что поле с подтверждением не появилось
        assertFalse("Модальное окно с подтверждением заказа появилось", isDisplayed(orderPage.getConfirmModalForm()));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
