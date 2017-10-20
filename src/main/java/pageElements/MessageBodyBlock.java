package pageElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import pages.InboxPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;

public class MessageBodyBlock extends InboxPage {
    private final String CTRL_ENTER = Keys.chord(Keys.CONTROL, Keys.ENTER);

    //Message recioient field
    @FindBy(xpath = "//div[@class='compose-head__field']")
    @CacheLookup
    private SelenideElement msgRecipient;

    //Message theme field
    @FindBy(xpath = "//input[@class='b-input']")
    @CacheLookup
    private SelenideElement msgTheme;

    //Message body container
    @FindBy(xpath = "//*[@class='compose__editor']")
    @CacheLookup
    private SelenideElement bodyContainer;


    /**
     * Sends a message
     *
     * @param recipient
     * @param theme
     * @return
     */
    public InboxPage sendingMsg(String recipient, String theme) {
        $x("//div[@id='b-compose']").shouldBe(enabled);
        actions().moveToElement(msgRecipient).sendKeys(recipient).sendKeys(Keys.ENTER).build().perform();
        actions().moveToElement(msgTheme).click().sendKeys(theme).build().perform();
        executeJavaScript("tinyMCE.activeEditor.setContent('<h1>HELLO!</h1> This is test text')");
        actions().sendKeys(CTRL_ENTER).build().perform();
        return page(InboxPage.class);
    }


}
