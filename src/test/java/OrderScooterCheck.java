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
        Order order = new Order();
        OrderPageYandexScooter objOrderPage = new OrderPageYandexScooter(driver);
        objOrderPage.setPersonalInfo(order.firstName, order.secondName, order.address, order.phoneNumber);
        objOrderPage.setRentalInfo(order.datePicker, order.courierComment);

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
