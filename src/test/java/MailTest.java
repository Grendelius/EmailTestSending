import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InboxPage;
import pages.MainPage;
import valueObjects.User;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;


public class MailTest {
    private MainPage mainPage;
    private InboxPage inboxPage;

    @BeforeTest
    public void beforeTest() {
        browser = "chrome";
        timeout = 8000;
        holdBrowserOpen = true;
    }

    @AfterTest
    public void afterTest() {
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
        mainPage = open("https://mail.ru/", MainPage.class);
        inboxPage = mainPage.authorization(user).sendBtnClick();
        inboxPage.sendMsg(recipient, theme);
        inboxPage.result().shouldHave(text("Ваше письмо отправлено. Перейти во Входящие"));
        inboxPage.logOut();
    }
}


