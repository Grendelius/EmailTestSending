package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import valueObjects.User;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    //Login field
    @FindBy(id = "mailbox:login")
    @CacheLookup
    private SelenideElement logField;

    //Password field
    @FindBy(id = "mailbox:password")
    @CacheLookup
    private SelenideElement pswField;

    //Submit button
    @FindBy(className = "o-control")
    @CacheLookup
    private SelenideElement authBtn;


    /**
     * E-mail logging
     *
     * @param user
     * @return
     */
    public InboxPage authorization(User user) {
        $(logField).setValue(user.getName());
        $(pswField).setValue(user.getPassword());
        $(authBtn).submit();
        return page(InboxPage.class);
    }

}
