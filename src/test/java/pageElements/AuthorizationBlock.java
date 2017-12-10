package pageElements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;


public class AuthorizationBlock extends ElementsContainer {

    //Login field
    @FindBy(id = "mailbox:login")
    @CacheLookup
    private SelenideElement logField;

    //Password field
    @FindBy(id = "mailbox:password")
    @CacheLookup
    private SelenideElement pswField;

    //Submit button
    @FindBy(xpath = "//label[@id='mailbox:submit']")
    @CacheLookup
    private SelenideElement authBtn;

    /**
     * Inputs login
     *
     * @param login
     */
    public void inputLogin(String login) {
        $(logField).val(login);
    }

    /**
     * Inputs password
     *
     * @param psw
     */
    public void inputPsw(String psw) {
        $(pswField).val(psw);
    }

    /**
     * Clicks on "entry" btn
     */
    public void clickOnAuthBtn() {
        actions().moveToElement($(authBtn)).click().build().perform();
    }
}
