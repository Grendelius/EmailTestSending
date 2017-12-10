package pageElements;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.io.FileInputStream;
import java.io.IOException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;

public class MessageBodyBlock extends ElementsContainer {

    //Message recipient field
    @FindBy(xpath = ".//div[@class='compose-head__field']")
    @CacheLookup
    private SelenideElement msgRecipient;

    //Message theme field
    @FindBy(xpath = ".//input[@class='b-input']")
    @CacheLookup
    private SelenideElement msgTheme;


    /**
     * Fill a message
     *
     * @param recipient
     * @param theme
     * @return
     */
    public void fillTheMsgFromFile(String recipient, String theme) throws IOException {
        String text = getTextFromFile("path_to_txt");
        $x("//div[@id='b-compose']").shouldBe(enabled);
        actions().moveToElement(msgRecipient).sendKeys(recipient).sendKeys(Keys.ENTER).build().perform();
        actions().moveToElement(msgTheme).click().sendKeys(theme).build().perform();
        executeJavaScript("tinymce.activeEditor.setContent('" + text + "')");
    }

    /**
     * Get text from .txt file
     *
     * @param pathToTextFile
     * @return
     * @throws IOException
     */
    private String getTextFromFile(String pathToTextFile) throws IOException {
        try {
            FileInputStream inFile = new FileInputStream(pathToTextFile);
            byte[] str = new byte[inFile.available()];
            inFile.read(str);
            String text = new String(str);
            return text;
        } catch (IOException exc) {
            System.out.println("Text file not found! Returning NULL!");
            return null;
        }
    }
}
