package com.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object class for feedback.html.
 */
public class FeedbackPage {

    private final WebDriver driver;

    // ─── Locators ───────────────────────────────────────────────────────────────
    private final By studentNameField  = By.id("studentName");
    private final By ratingDropdown    = By.id("rating");
    private final By commentsField     = By.id("comments");
    private final By confirmCheckbox   = By.id("confirm");
    private final By submitButton      = By.id("submitFeedback");
    private final By errorMessage      = By.id("fbErrorMsg");
    private final By successMessage    = By.id("fbSuccessMsg");

    // ─── Constructor ─────────────────────────────────────────────────────────────
    public FeedbackPage(WebDriver driver) {
        this.driver = driver;
    }

    // ─── Actions ─────────────────────────────────────────────────────────────────

    public String getPageTitle() { return driver.getTitle(); }

    public String getStudentNamePlaceholder() {
        return driver.findElement(studentNameField).getAttribute("placeholder");
    }

    public String getCommentsPlaceholder() {
        return driver.findElement(commentsField).getAttribute("placeholder");
    }

    public void enterStudentName(String name) {
        WebElement el = driver.findElement(studentNameField);
        el.clear();
        el.sendKeys(name);
        sleepOneSecond();
    }

    public void selectRating(String visibleText) {
        Select select = new Select(driver.findElement(ratingDropdown));
        select.selectByVisibleText(visibleText);
    }

    public void enterComments(String comments) {
        WebElement el = driver.findElement(commentsField);
        el.clear();
        el.sendKeys(comments);
        sleepOneSecond();
    }

    public void tickConfirmCheckbox() {
        WebElement cb = driver.findElement(confirmCheckbox);
        if (!cb.isSelected()) cb.click();
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElement(successMessage).isDisplayed();
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
