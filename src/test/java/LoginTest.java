import api.UserClient;
import api.UserSteps;
import browsers.Browser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RecoveryPage;
import pageobject.RegistrationPage;
public class LoginTest {
    private static String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    private static String password = RandomStringUtils.randomNumeric(9);
    private static String name = RandomStringUtils.randomAlphabetic(8);
    private WebDriver webDriver;
    private UserSteps userSteps;
    private Browser browser = new Browser();
    @Before
    public void setUp() {
        webDriver = browser.getWebDriver();
        userSteps = new UserSteps(new UserClient());
        ValidatableResponse validatableResponse = userSteps.create(email, password, name);
        userSteps.getAccessToken(validatableResponse);
    }
    @Test
    @DisplayName("Вход по кнопке «войти в аккаунт» на главной")
    @Description("Проверка возможности входа по кнопке «Войти в аккаунт» на главной")
    public void loginButtonSignInAccountTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonSignInAccount();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }
    @Test
    @DisplayName("Вход по кнопке «Личный кабинет»")
    @Description("Проверка возможности входа по кнопке «Личный кабинет»")
    public void loginButtonPersonalAreaTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonPersonalArea();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }
    @Test
    @DisplayName("Вход по кнопке в форме восстановления пароля")
    @Description("Проверка возможности входа по кнопке в формке восстановления пароля")
    public void loginInFormForgotPasswordTest() {
        RecoveryPage recoveryPage = new RecoveryPage(webDriver);
        recoveryPage.openPage();
        recoveryPage.clickButtonSignIn();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        MainPage mainPage = new MainPage(webDriver);
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }
    @Test
    @DisplayName("Вход по кнопке в форме регистрации")
    @Description("Проверка возможности входа по кнопке в форме регистрации")
    public void loginInFormRegisterTest() {
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.openPage();
        registrationPage.clickButtonSignIn();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        MainPage mainPage = new MainPage(webDriver);
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }
    @After
    public void close() {
        userSteps.delete();
        webDriver.quit();
    }
}
