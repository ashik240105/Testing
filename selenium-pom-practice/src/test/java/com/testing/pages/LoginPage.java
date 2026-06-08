package com.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object class for index.html (Login Page).
 * Contains all locators and action methods for the Login page.
 */
public class LoginPage {

    private final WebDriver driver;

    // ─── Locators ───────────────────────────────────────────────────────────────
    private final By usernameField  = By.id("username");
    private final By passwordField  = By.id("password");
    private final By loginButton    = By.id("loginBtn");
    private final By errorMessage   = By.id("errorMsg");

    // ─── Constructor ─────────────────────────────────────────────────────────────
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ─── Actions ─────────────────────────────────────────────────────────────────

    /** Returns the page title (browser tab title). */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /** Returns the placeholder attribute of the username input. */
    public String getUsernamePlaceholder() {
        return driver.findElement(usernameField).getAttribute("placeholder");
    }

    /** Returns the placeholder attribute of the password input. */
    public String getPasswordPlaceholder() {
        return driver.findElement(passwordField).getAttribute("placeholder");
    }

    /** Types into the username field. */
    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        sleepOneSecond();
    }

    /** Types into the password field. */
    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        sleepOneSecond();
    }

    /** Clicks the Login button. */
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    /** Returns the text of the error message element. */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    /** Full login helper: clears fields, enters credentials, clicks Login. */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // small helper to pause between typing into fields
    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
