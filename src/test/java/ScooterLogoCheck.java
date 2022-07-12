import PageObjects.OrderPageYandexScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScooterLogoCheck extends BaseTestClass {

    @Before
    public void testConf(){
        startUp();
    }

    @Test
    public void clickOnScooterLogo() {
        driver.get(homePageUrl);
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
