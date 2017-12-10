package pages;

import org.openqa.selenium.support.FindBy;
import pageElements.AuthorizationBlock;
import valueObjects.User;

import static com.codeborne.selenide.Selenide.page;

/**
 * Using for structure description of main page on Mail.Ru
 */
public class MainPage {
    public static final String PAGE_URL = "https://mail.ru/";

    @FindBy(xpath = "//div[@class='mailbox__body']")
    private AuthorizationBlock authorizationBlock;

    /**
     * User authorization
     * @param user
     * @return
     */
    public InboxPage authorization(User user) {
        authorizationBlock.inputLogin(user.getName());
        authorizationBlock.inputPsw(user.getPassword());
        authorizationBlock.clickOnAuthBtn();
        return page(InboxPage.class);
    }

}
