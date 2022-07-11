import PageObjects.HomePageYandexScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

public class FAQAccordionCheck {

    @RunWith(Parameterized.class)
    public static class AccordionItemsTextCheck extends BaseTestClass {
        private final int accordionItemIndex;
        private final String expectedAccordionItemText;
        private final String expectedAccordionItemHiddenText;

        public AccordionItemsTextCheck(int accordionItemIndex, String expectedAccordionItemText, String expectedAccordionItemHiddenText) {
            this.accordionItemIndex = accordionItemIndex;
            this.expectedAccordionItemText = expectedAccordionItemText;
            this.expectedAccordionItemHiddenText = expectedAccordionItemHiddenText;

        }

        @Before
        public void testConf(){
            startUp();
        }

        @Parameterized.Parameters(name = "{index}: accordionItemText = {1}")
        public static Object[][] getTestData() {
            return new Object[][] {
                    {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                    {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                    {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                    {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                    {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                    {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                    {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                    {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
            };
        }

        @Test
        public void accordionItemTextTest() {
            driver.get(homePageUrl);

            HomePageYandexScooter objHomePage = new HomePageYandexScooter(driver);
            objHomePage.waitForLoadElement(objHomePage.getFaqAccordion());

            // Записываем в переменную вопрос из FAQ
            String actualAccordionText = objHomePage.getListOfFaqAccordionButtons().get(accordionItemIndex).getText();

            // Кликаем на вопрос из FAQ
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", objHomePage.getFaqAccordionHeading().get(accordionItemIndex));
            objHomePage.getFaqAccordionHeading().get(accordionItemIndex).click();

            // Ждем пока появится ответ и записываем ответ в переменную
            objHomePage.waitForLoadElement(objHomePage.getListOfFaqHiddenText().get(accordionItemIndex));
            String actualAccordionItemHiddenText = objHomePage.getListOfFaqHiddenText().get(accordionItemIndex).getText();

            assertEquals("Не совпадает вопрос из меню FAQ с предполагаемым результатом", expectedAccordionItemText, actualAccordionText);
            assertEquals("Не совпадает появляющийся ответ на вопрос из меню FAQ с предполагаемым результатом", expectedAccordionItemHiddenText, actualAccordionItemHiddenText);
        }

        @After
        public void teardown() {
            driver.quit();
        }

    }
}