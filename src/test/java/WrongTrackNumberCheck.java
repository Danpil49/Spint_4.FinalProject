import PageObjects.HomePageYandexScooter;
import PageObjects.TrackCheckYandexScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.assertTrue;

public class WrongTrackNumberCheck extends BaseTestClass {

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
    public void trackNumberCheckIsNotFound() {
        driver.get(homePageUrl);
        HomePageYandexScooter homePage = new HomePageYandexScooter(driver);
        homePage.enterTheTrack("228");

        TrackCheckYandexScooter trackCheckPage = new TrackCheckYandexScooter(driver);
        //Жду и проверяю что отображается картинка "Такого заказа нет"
        trackCheckPage.waitForLoadElement(driver.findElement(trackCheckPage.getNotFoundImg()));
        assertTrue("Не отобразилась картинка 'Такого заказа нет'",isDisplayed(trackCheckPage.getNotFoundImg()));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
