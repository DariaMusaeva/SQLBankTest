package test;

import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.cleanDB;

public class LoginTest {

    @AfterAll
    static void teardown() {
        cleanDB();
    }

    @Test
    @DisplayName("authorization should be successful with valid data")
    void shouldSuccessfullyLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPage();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validCode(verificationCode.getCode());
    }

    @Test
    @DisplayName("should get error message if user is not exist in database")
    void shouldGetErrorMessageIfUserIsNotExist() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var randomUser = DataHelper.generateUser();
        loginPage.validLogin(new DataHelper.AuthInfo(randomUser.getLogin(), randomUser.getPass()));
        loginPage.verifyErrorMessage();
    }

    @Test
    @DisplayName("should get error message if user is exist but code is wrong")
    void shotGetErrorMessageWithWrongCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPage();
        var randomVerificationCode = DataHelper.getVerificationCode();
        verificationPage.verify(randomVerificationCode.getCode());
        verificationPage.verifyErrorMessage();
    }
}
