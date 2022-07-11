import PageObjects.HomePageYandexScooter;
import PageObjects.OrderPageYandexScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OrderScooterCheck extends BaseTestClass {

    @Before
    public void testConf(){
        startUp();
    }

    @Test
    public void orderFlowTest(){
        driver.get(homePageUrl);
        // Нажатие на кнопку "Заказать"
        HomePageYandexScooter objHomePage = new HomePageYandexScooter(driver);
        objHomePage.getOrderButtons().get(0).click();

        // Заполнение информации о заказчике
        OrderPageYandexScooter objOrderPage = new OrderPageYandexScooter(driver);
        objOrderPage.setPersonalInfo("Тест", "Тестов", "Москва, Новослободская 228", "12345678909");
        objOrderPage.setRentalInfo("07.07.2022", "тестовый коммент");

        //Подтверждаем заказ. Вынес отдельно, чтобы удобно было тестить, так как на Хроме это не работает
        objOrderPage.getConfirmButton().click();

        //Проверяем появилось ли сообщение об успешном заказе
        objOrderPage.waitForLoadLocator(objOrderPage.getSuccessMassage());
        assertTrue("Сообщение о успешном заказе не появилось на UI", driver.findElement(objOrderPage.getSuccessMassage()).isDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
