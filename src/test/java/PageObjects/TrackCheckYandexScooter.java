package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrackCheckYandexScooter {
    private final WebDriver driver;
    int timeoutDurationInSeconds;

    //Локатор на картинку "Такого заказа нет"
    private final By notFoundImg = By.xpath(".//img[@alt='Not found']");

    public TrackCheckYandexScooter(WebDriver driver) {
        this.driver = driver;
        this.timeoutDurationInSeconds = 3;
    }

    public By getNotFoundImg() {
        return notFoundImg;
    }
    public void waitForLoadElement(WebElement waitElement) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutDurationInSeconds)).until(ExpectedConditions.visibilityOf(waitElement));
    }

}
