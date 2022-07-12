package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPageYandexScooter {
    private final WebDriver driver;

    //Локатор логотипа "Самокат"
    private final By scooterLogo = By.xpath(".//img[@alt='Scooter']");

    //Локатор на input ввода Имени
    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']");

    //Локатор на div с ошибкой "Введите корректное имя"
    private final By firstNameErrorField = By.xpath(".//div[text()='Введите корректное имя']");

    //Локатор на input ввода Фамилии
    private final By secondNameField = By.xpath(".//input[@placeholder='* Фамилия']");

    //Локатор на div с ошибкой "Введите корректную фамилию"
    private final By secondNameErrorField = By.xpath(".//div[text()='Введите корректную фамилию']");

    //Локатор на input ввода Адреса
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Локатор на div с ошибкой "Введите корректный адрес"
    private final By addressErrorField = By.xpath(".//div[text()='Введите корректный адрес']");

    //Локатор на input ввода Станции метро
    private final By metroDropdownField = By.xpath(".//input[@placeholder='* Станция метро']");

    //Локатор на div с ошибкой "Выберите станцию"
    private final By metroDropdownErrorField = By.xpath(".//div[text()='Выберите станцию']");

    //Локатор на второй элемент в dropdown списке выбора Станции метро
    private final By secondMetroInDropdownField = By.cssSelector("li.select-search__row[data-index='1']>button");

    //Локатор на input ввода Номера телефона
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Локатор на div с ошибкой "Введите корректный номер"
    private final By phoneNumberErrorField = By.xpath(".//div[text()='Введите корректный номер']");

    //Локатор на кнопку "Далее"
    private final  By nextButton = By.xpath(".//button[text()='Далее']");

    //Локатор на кнопку "Да"
    private final  By confirmButton = By.xpath(".//button[text()='Да']");

    //Локатор на input указания Даты доставки
    private final By datePickerField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //Локатор на dropdown с выбором Срока аренды
    private final By rentalPeriodField = By.xpath(".//div[text()='* Срок аренды']/..//span");

    //Локатор на элемент dropdown списка со значением "двое суток"
    private final By twoDaysRentalField = By.xpath(".//div[text()='двое суток']");

    //Локатор на чекбокс "чёрный жемчуг", в блоке "Цвет самоката"
    private final By blackScooterCheckbox = By.xpath(".//input[@id='black']");

    //Локатор на чекбокс "серая безысходность", в блоке "Цвет самоката"
    private final By greyScooterCheckbox = By.xpath(".//input[@id='grey']");

    //Локатор на input указания "Комментарий для курьера"
    private final By courierCommentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Локатор на форму подтверждения заказа
    private final By confirmModalForm = By.cssSelector(".Order_Modal__YZ-d3");

    //Локатор на кнопку "Заказать"
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    //Локатор на сообщение об успешном заказе
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
