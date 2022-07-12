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
            QuestionsAndAnswers questionsAndAnswers = new QuestionsAndAnswers();
            return new Object[][] {
                    {0, questionsAndAnswers.questions[0], questionsAndAnswers.answers[0]},
                    {1, questionsAndAnswers.questions[1], questionsAndAnswers.answers[1]},
                    {2, questionsAndAnswers.questions[2], questionsAndAnswers.answers[2]},
                    {3, questionsAndAnswers.questions[3], questionsAndAnswers.answers[3]},
                    {4, questionsAndAnswers.questions[4], questionsAndAnswers.answers[4]},
                    {5, questionsAndAnswers.questions[5], questionsAndAnswers.answers[5]},
                    {6, questionsAndAnswers.questions[6], questionsAndAnswers.answers[6]},
                    {7, questionsAndAnswers.questions[7], questionsAndAnswers.answers[7]}
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