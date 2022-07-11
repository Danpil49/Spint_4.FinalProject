import PageObjects.OrderPageYandexScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.assertFalse;

public class OrderScooterInputsSecondPageCheck extends BaseTestClass {

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
