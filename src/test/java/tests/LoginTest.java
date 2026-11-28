package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void loginUserEntrance() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void loginError() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");//Логин с большой буквы(нужно маленькая)
        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void passwordTest() {
        loginPage.open();
        loginPage.onlyPassword("standard_user");
        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Username is required");
    }

    @Test
    public void passwordError() {
        loginPage.open();
        loginPage.loginOnly("problem_user");
        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Password is required");
    }

    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.pageTitleVisible());
        assertEquals(productsPage.getTitleText(), "Products");
        assertEquals(productsPage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        assertTrue(productsPage.getCurrentUrl().contains("inventory.html"));
    }
}
