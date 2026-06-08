package com.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object class for dashboard.html.
 * Contains all locators and action methods for the Dashboard page.
 */
public class DashboardPage {

    private final WebDriver driver;

    // ─── Locators ───────────────────────────────────────────────────────────────
    private final By appNameElement   = By.id("appName");
    private final By courseLink       = By.id("courseLink");
    private final By feeLink          = By.id("feeLink");
    private final By feedbackLink     = By.id("feedbackLink");
    private final By logoutLink       = By.id("logoutLink");
    private final By courseCardBtn    = By.id("courseCardBtn");
    private final By feeCardBtn       = By.id("feeCardBtn");
    private final By feedbackCardBtn  = By.id("feedbackCardBtn");

    // ─── Constructor ─────────────────────────────────────────────────────────────
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // ─── Actions ─────────────────────────────────────────────────────────────────

    /** Returns the browser page title. */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /** Returns the visible text of the app name element. */
    public String getAppName() {
        return driver.findElement(appNameElement).getText();
    }

    /** Clicks the Course Enquiry navbar link. */
    public void clickCourseLink() {
        driver.findElement(courseLink).click();
    }

    /** Clicks the Fee Calculator navbar link. */
    public void clickFeeLink() {
        driver.findElement(feeLink).click();
    }

    /** Clicks the Feedback navbar link. */
    public void clickFeedbackLink() {
        driver.findElement(feedbackLink).click();
    }

    /** Clicks the Logout link. */
    public void clickLogoutLink() {
        driver.findElement(logoutLink).click();
    }

    /** Clicks the Course Enquiry card button. */
    public void clickCourseCardBtn() {
        driver.findElement(courseCardBtn).click();
    }

    /** Clicks the Fee Calculator card button. */
    public void clickFeeCardBtn() {
        driver.findElement(feeCardBtn).click();
    }

    /** Clicks the Feedback card button. */
    public void clickFeedbackCardBtn() {
        driver.findElement(feedbackCardBtn).click();
    }

    /** Returns the current URL (useful for navigation checks). */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
