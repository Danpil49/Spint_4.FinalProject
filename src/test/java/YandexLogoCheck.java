import PageObjects.HomePageYandexScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class YandexLogoCheck extends BaseTestClass {

    @Before
    public void testConf(){
        startUp();
    }

    public void switchToWindow(int numberWindow) {
        String handle = driver.getWindowHandles().toArray()[numberWindow]
                .toString();
        driver.switchTo().window(handle);
    }

    @Test
    public void clickOnYandexLogo() {
        driver.get(homePageUrl);
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
