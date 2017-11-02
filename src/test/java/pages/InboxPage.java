package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import pageElements.MessageBodyBlock;

import static com.codeborne.selenide.Selenide.$;

public class InboxPage {
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
    public MessageBodyBlock sendBtnClick() {
        $(sendBtn).click();
        return Selenide.page(MessageBodyBlock.class);
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
