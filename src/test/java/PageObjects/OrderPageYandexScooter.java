package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPageYandexScooter {
    private final WebDriver driver;
    private final By scooterLogo = By.xpath(".//img[@alt='Scooter']");
    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By firstNameErrorField = By.xpath(".//div[text()='Введите корректное имя']");
    private final By secondNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By secondNameErrorField = By.xpath(".//div[text()='Введите корректную фамилию']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By addressErrorField = By.xpath(".//div[text()='Введите корректный адрес']");
    private final By metroDropdownField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By metroDropdownErrorField = By.xpath(".//div[text()='Выберите станцию']");
    private final By secondMetroInDropdownField = By.cssSelector("li.select-search__row[data-index='1']>button");
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By phoneNumberErrorField = By.xpath(".//div[text()='Введите корректный номер']");
    private final  By nextButton = By.xpath(".//button[text()='Далее']");
    private final  By confirmButton = By.xpath(".//button[text()='Да']");

    private final By datePickerField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.xpath(".//div[text()='* Срок аренды']/..//span");
    private final By twoDaysRentalField = By.xpath(".//div[text()='двое суток']");
    private final By blackScooterCheckbox = By.xpath(".//input[@id='black']");
    private final By greyScooterCheckbox = By.xpath(".//input[@id='grey']");
    private final By courierCommentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By confirmModalForm = By.cssSelector(".Order_Modal__YZ-d3");
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private final By successMassage = By.xpath(".//div[text()='Заказ оформлен']");
    private final int timeoutDurationInSeconds;

    public OrderPageYandexScooter(WebDriver driver) {
        this.driver = driver;
        this.timeoutDurationInSeconds = 3;
    }

    public void waitForLoadLocator(By waitLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutDurationInSeconds)).until(ExpectedConditions.visibilityOfElementLocated(waitLocator));
    }

    public void waitForLoadElement(WebElement waitWebElement) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutDurationInSeconds)).until(ExpectedConditions.visibilityOf(waitWebElement));
    }

    public void setFirstNameField(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public WebElement getFirstNameField() {
        return driver.findElement(firstNameField);
    }
    public void setSecondNameField(String secondName) {
        driver.findElement(secondNameField).sendKeys(secondName);
    }
    public void setAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    public void setPhoneNumberField(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void setDatePickerField(String datePicker) {
        driver.findElement(datePickerField).sendKeys(datePicker);
    }

    public void setCourierCommentField(String courierComment) {
        driver.findElement(courierCommentField).sendKeys(courierComment);
    }
    public WebElement getConfirmButton() {
        return driver.findElement(confirmButton);
    }
    public WebElement getNextButton() {
        return driver.findElement(nextButton);
    }
    public By getSuccessMassage() {
        return successMassage;
    }
    public WebElement getScooterLogo() {
        return driver.findElement(scooterLogo);
    }
    public WebElement getFirstNameErrorField() {
        return driver.findElement(firstNameErrorField);
    }

    public WebElement getSecondNameErrorField() {
        return driver.findElement(secondNameErrorField);
    }

    public WebElement getAddressErrorField() {
        return driver.findElement(addressErrorField);
    }

    public WebElement getMetroDropdownErrorField() {
        return driver.findElement(metroDropdownErrorField);
    }

    public WebElement getPhoneNumberErrorField() {
        return driver.findElement(phoneNumberErrorField);
    }

    public WebElement getSecondNameField() {
        return driver.findElement(secondNameField);
    }

    public WebElement getAddressField() {
        return driver.findElement(addressField);
    }

    public WebElement getMetroDropdownField() {
        return driver.findElement(metroDropdownField);
    }

    public WebElement getPhoneNumberField() {
        return driver.findElement(phoneNumberField);
    }

    public WebElement getDatePickerField() {
        return driver.findElement(datePickerField);
    }

    public WebElement getOrderButton() {
        return driver.findElement(orderButton);
    }

    public By getConfirmModalForm() {
        return confirmModalForm;
    }

    public void setPersonalInfo(String firstName, String secondName, String address, String phoneNumber) {
        setFirstNameField(firstName);
        setSecondNameField(secondName);
        setAddressField(address);
        driver.findElement(metroDropdownField).click();
        waitForLoadLocator(secondMetroInDropdownField);
        driver.findElement(secondMetroInDropdownField).click();
        setPhoneNumberField(phoneNumber);
        driver.findElement(nextButton).click();
    }

    public void setRentalInfo(String datePicker, String courierComment) {
        setDatePickerField(datePicker);
        driver.findElement(rentalPeriodField).click();
        waitForLoadLocator(twoDaysRentalField);
        driver.findElement(twoDaysRentalField).click();
        driver.findElement(blackScooterCheckbox).click();
        driver.findElement(greyScooterCheckbox).click();
        setCourierCommentField(courierComment);
        driver.findElement(orderButton).click();
    }
}
