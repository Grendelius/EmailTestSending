import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InboxPage;
import pages.MainPage;
import valueObjects.User;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.open;


public class MailTest {
    private MainPage mainPage;
    private InboxPage inboxPage;

    @BeforeTest
    public void beforeTest() {
        browser = "chrome";
        timeout = 4000;
        mainPage = open(MainPage.PAGE_URL, MainPage.class);
    }

    @AfterTest
    public void afterTest() {
        inboxPage.logOut();
        mainPage = null;
        inboxPage = null;
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{new User("login", "password"), "email@gmail.com", "Theme#1"},
        };
    }

    @Test(dataProvider = "testData")
    public void testSendMail(User user, String recipient, String theme) throws IOException {
        inboxPage = mainPage.authorization(user).sendBtnClick();
        inboxPage.sendMsg(recipient, theme);
        inboxPage.sendingResult().shouldHave(text("Ваше письмо отправлено. Перейти во Входящие"));
    }
}


