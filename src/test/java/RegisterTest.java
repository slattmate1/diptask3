import browsers.Browser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
public class RegisterTest {
    private static String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    private static String password = RandomStringUtils.randomNumeric(9);
    private static String notСorrectPassword = RandomStringUtils.randomNumeric(5);
    private static String name = RandomStringUtils.randomAlphabetic(5);
    private WebDriver webDriver;
    private Browser browser = new Browser();
    @Before
    public void setUp() {
        webDriver = browser.getWebDriver();
    }
    @Test
    @DisplayName("Регистрация с валидными значениями(позитив)")
    @Description("Проверка успешной регистрации(позитив)")
    public void registrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.openPage();
        registrationPage.enterFieldName(name);
        registrationPage.enterFieldEmail(email);
        registrationPage.enterFieldPassword(password);
        registrationPage.clickButtonRegistrationInForm();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        MainPage mainPage = new MainPage(webDriver);
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }
    @Test
    @DisplayName("Регистрация с невалидными значениями(негатив)")
    @Description("Проверка невозможности зарегистировать пользователя с некорректным паролем(негатив) ")
    public void errorIncorrectPasswordTest() {
        RegistrationPage registrationPage = new RegistrationPage(webDriver);
        registrationPage.openPage();
        registrationPage.enterFieldName(name);
        registrationPage.enterFieldEmail(email);
        registrationPage.enterFieldPassword(notСorrectPassword);
        registrationPage.clickButtonRegistrationInForm();
        Assert.assertTrue(registrationPage.incorrectPasswordLabel());
    }
    @After
    public void tearDown() {
        webDriver.quit();
    }
}
