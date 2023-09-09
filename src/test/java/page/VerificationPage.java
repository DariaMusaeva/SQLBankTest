package page;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private static SelenideElement codeVerify = $("[data-test-id=code] input");
    private static SelenideElement buttonVerify = $("[data-test-id=action-verify]");
    private static SelenideElement errorMessage = $("[data-test-id=error-notification]");

    public void verifyVerificationPage() {
        codeVerify.should(visible);
    }

    public void verifyErrorMessage() {
        errorMessage.should(visible);
    }

    public DashboardPage validCode(String verificationCode) {
        codeVerify.setValue(verificationCode);
        buttonVerify.click();
        return new DashboardPage();
    }

    public void verify(String randomVerificationCode) {
        codeVerify.setValue(randomVerificationCode);
        buttonVerify.click();
    }
}