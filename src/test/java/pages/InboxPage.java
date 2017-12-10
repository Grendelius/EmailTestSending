package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import pageElements.MessageBodyBlock;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class InboxPage {
    private static final String CTRL_ENTER = Keys.chord(Keys.CONTROL, Keys.ENTER);

    @FindBy(xpath = "//div[@id='b-compose']")
    private MessageBodyBlock msgBlock;

    //"Write a letter" button
    @FindBy(xpath = "//div[@class='b-toolbar__item']")
    @CacheLookup
    private SelenideElement sendBtn;

    //LogOut link
    @FindBy(css = "#PH_logoutLink")
    @CacheLookup
    private SelenideElement logOutLink;

    /**
     * Clicks on "write a letter" button
     *
     * @return MessageBodyBlock element
     */
    public InboxPage sendBtnClick() {
        $(sendBtn).click();
        return page(this);
    }

    /**
     * Sends a message and submit it
     *
     * @param recipient
     * @param theme
     * @throws IOException
     */
    public void sendMsg(String recipient, String theme) throws IOException {
        msgBlock.fillTheMsgFromFile(recipient, theme);
        actions().sendKeys(CTRL_ENTER).build().perform();
    }

    public void logOut() {
        $(logOutLink).click();
    }

    /**
     * Checks an element on the page
     * @return
     */
    public SelenideElement result() {
        return $("div.message-sent__title");
    }
}
