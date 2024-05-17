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
import pageobject.PersonalPage;
public class TransitionTest {
    private static String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    private static String password = RandomStringUtils.randomNumeric(7);
    private static String name = RandomStringUtils.randomAlphabetic(7);
    public WebDriver webDriver;
    private Browser browser = new Browser();
    private UserSteps userSteps;

    @Before
    public void setUp() {
        userSteps = new UserSteps(new UserClient());
        ValidatableResponse validatableResponse = userSteps.create(email, password, name);
        userSteps.getAccessToken(validatableResponse);
        webDriver = browser.getWebDriver();
    }
    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка возможности перехода в личный кабинет")
    public void personalAreaSwitchTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonSignInAccount();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        mainPage.clickButtonPersonalArea();
        Assert.assertTrue(mainPage.accountTextIsVisible());
    }
    @Test
    @DisplayName("Переход в конструктор")
    @Description("Проверка возможности перехода из личного кабинета по клику на Конструктор")
    public void constructorSwitchTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonSignInAccount();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        mainPage.clickButtonPersonalArea();
        PersonalPage personalPage = new PersonalPage(webDriver);
        personalPage.clickButtonConstructor();
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверка возможности перехода из личного кабинета по клику на лого ")
    public void logoSwitchTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonSignInAccount();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        mainPage.clickButtonPersonalArea();
        PersonalPage personalPage = new PersonalPage(webDriver);
        personalPage.clickButtonLogo();
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }
    @After
    public void close() {
        userSteps.delete();
        webDriver.quit();
    }
}
