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

    @BeforeTest
    public static void beforeTest() {
        browser = "chrome";
        timeout = 12000;
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{new User("login", "password"), "email@gmail.com", "Theme#1"},
        };
    }

    @Test(dataProvider = "testData")
    public void testSendMail(User user, String recipient, String theme) throws IOException {
        MainPage mainPage = open("https:/mail.ru", MainPage.class);
        InboxPage inboxPage = mainPage.authorization(user).sendBtnClick().sendingMsg(recipient, theme);
        inboxPage.result().shouldHave(text("Ваше письмо отправлено. Перейти во Входящие"));
        inboxPage.logOut();
    }
}


