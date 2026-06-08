package com.testing.tests;

import com.testing.base.BaseTest;
import com.testing.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Paths;

/**
 * Test class for Login Page (index.html)
 * Test IDs: LP_01 through LP_08
 */
public class LoginPageTest extends BaseTest {

    private LoginPage loginPage;
    private String loginUrl;

    @BeforeMethod
    public void openLoginPage() {
        // Build absolute file URL to index.html
        String absolutePath = Paths.get("").toAbsolutePath()
                .getParent()  // go up to testingpoject root
                .resolve("student-portal-testing-project/index.html")
                .toUri()
                .toString();
        loginUrl = absolutePath;
        driver.get(absolutePath);
        loginPage = new LoginPage(driver);
    }

    /** LP_01: Verify login page title */
    @Test(description = "LP_01 - Verify login page title")
    public void verifyLoginPageTitle() {
        String title = loginPage.getPageTitle();
        Assert.assertEquals(title, "Student Portal Login", "Page title mismatch");
    }

    /** LP_02: Verify username placeholder */
    @Test(description = "LP_02 - Verify username placeholder")
    public void verifyUsernamePlaceholder() {
        String placeholder = loginPage.getUsernamePlaceholder();
        Assert.assertEquals(placeholder, "Enter username", "Username placeholder mismatch");
    }

    /** LP_03: Verify password placeholder */
    @Test(description = "LP_03 - Verify password placeholder")
    public void verifyPasswordPlaceholder() {
        String placeholder = loginPage.getPasswordPlaceholder();
        Assert.assertEquals(placeholder, "Enter password", "Password placeholder mismatch");
    }

    /** LP_04: Login without username (click with empty fields) */
    @Test(description = "LP_04 - Login without username")
    public void loginWithoutUsername() {
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error, "Username is required", "Error message mismatch for empty username");
    }

    /** LP_05: Login without password (enter username only) */
    @Test(description = "LP_05 - Login without password")
    public void loginWithoutPassword() {
        loginPage.enterUsername("student");
        pauseOneSecond();
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error, "Password is required", "Error message mismatch for empty password");
    }

    /** LP_06: Login with invalid username */
    @Test(description = "LP_06 - Login with invalid username")
    public void loginWithInvalidUsername() {
        loginPage.login("wronguser", "Student@123");
        pauseOneSecond();
        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error, "Invalid username", "Error message mismatch for invalid username");
    }

    /** LP_07: Login with invalid password */
    @Test(description = "LP_07 - Login with invalid password")
    public void loginWithInvalidPassword() {
        loginPage.login("student", "wrongpassword");
        pauseOneSecond();
        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error, "Invalid password", "Error message mismatch for invalid password");
    }

    /** LP_08: Login with valid credentials — should navigate to dashboard */
    @Test(description = "LP_08 - Login with valid credentials")
    public void loginWithValidCredentials() {
        loginPage.login("student", "Student@123");
        pauseOneSecond();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard.html"),
                "Should navigate to dashboard.html after successful login. Current URL: " + currentUrl);
    }
}
