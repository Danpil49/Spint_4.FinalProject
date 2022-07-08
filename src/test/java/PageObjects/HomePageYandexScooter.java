package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePageYandexScooter {
    private final WebDriver driver;
    private final By yandexLogo = By.xpath(".//img[@alt='Yandex']");
    private final By faqAccordion = By.className("accordion");
    private final By faqAccordionHeading = By.className("accordion__heading");
    private final By faqAccordionButton= By.className("accordion__button");
    private final By faqHiddenText = By.xpath(".//div[@class='accordion__panel']/p");
    private int timeoutDurationInSeconds;
    private final By orderButtons = By.xpath(".//button[text()='Заказать']");
    private final By orderCheckButton = By.xpath(".//button[text()='Статус заказа']");
    private final By orderNumberCheckInput = By.xpath(".//input[@placeholder='Введите номер заказа']");
    private final By orderCheckButtonGo = By.xpath(".//button[text()='Go!']");


    public HomePageYandexScooter(WebDriver driver) {
        this.driver = driver;
        this.timeoutDurationInSeconds = 3;
    }

    public WebElement getFaqAccordion() {
        return driver.findElement(faqAccordion);
    }

    public List<WebElement> getListOfFaqAccordionButtons() {
        return driver.findElements(faqAccordionButton);
    }

    public List<WebElement> getListOfFaqHiddenText() {
        return driver.findElements(faqHiddenText);
    }

    public void waitForLoadElement(WebElement waitElement) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutDurationInSeconds)).until(ExpectedConditions.visibilityOf(waitElement));
    }

    public void setTimeoutDurationInSeconds(int timeoutDurationInSeconds) {
        this.timeoutDurationInSeconds = timeoutDurationInSeconds;
    }

    public List<WebElement> getOrderButtons() {
        return driver.findElements(orderButtons);
    }

    public List<WebElement> getFaqAccordionHeading() {
        return driver.findElements(faqAccordionHeading);
    }

    public WebElement getYandexLogo() {
        return driver.findElement(yandexLogo);
    }

    public WebElement getOrderCheckButton() {
        return driver.findElement(orderCheckButton);
    }

    public WebElement getOrderNumberCheckInput() {
        return driver.findElement(orderNumberCheckInput);
    }

    public void setOrderNumberCheckInput(String value) {
        driver.findElement(orderNumberCheckInput).sendKeys(value);
    }

    public WebElement getOrderCheckButtonGo() {
        return driver.findElement(orderCheckButtonGo);
    }

    public void EnterTheTrack(String orderNumber) {
        waitForLoadElement(driver.findElement(orderCheckButton));
        getOrderCheckButton().click();
        waitForLoadElement(getOrderNumberCheckInput());
        setOrderNumberCheckInput(orderNumber);
        getOrderCheckButtonGo().click();
    }
}