package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passField = $("[data-test-id=password] input");
    private final SelenideElement button = $("[data-test-id=action-login]");
    private final SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public void verifyErrorMessage() {
        errorMessage.should(visible);
    }

    public  VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passField.setValue(info.getPass());
        button.click();
        return new VerificationPage();
    }
}