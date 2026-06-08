package com.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object class for course-enquiry.html.
 */
public class CourseEnquiryPage {

    private final WebDriver driver;

    // ─── Locators ───────────────────────────────────────────────────────────────
    private final By studentNameField  = By.id("studentName");
    private final By emailField        = By.id("email");
    private final By mobileField       = By.id("mobile");
    private final By courseDropdown    = By.id("course");
    private final By modeOnline        = By.id("modeOnline");
    private final By modeOffline       = By.id("modeOffline");
    private final By submitButton      = By.id("submitEnquiry");
    private final By errorMessage      = By.id("ceErrorMsg");
    private final By successMessage    = By.id("ceSuccessMsg");

    // ─── Constructor ─────────────────────────────────────────────────────────────
    public CourseEnquiryPage(WebDriver driver) {
        this.driver = driver;
    }

    // ─── Actions ─────────────────────────────────────────────────────────────────

    public String getPageTitle() { return driver.getTitle(); }

    public String getStudentNamePlaceholder() {
        return driver.findElement(studentNameField).getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return driver.findElement(emailField).getAttribute("placeholder");
    }

    public String getMobilePlaceholder() {
        return driver.findElement(mobileField).getAttribute("placeholder");
    }

    public void enterStudentName(String name) {
        WebElement el = driver.findElement(studentNameField);
        el.clear();
        el.sendKeys(name);
        sleepOneSecond();
    }

    public void enterEmail(String email) {
        WebElement el = driver.findElement(emailField);
        el.clear();
        el.sendKeys(email);
        sleepOneSecond();
    }

    public void enterMobile(String mobile) {
        WebElement el = driver.findElement(mobileField);
        el.clear();
        el.sendKeys(mobile);
        sleepOneSecond();
    }

    public void selectCourse(String visibleText) {
        org.openqa.selenium.support.ui.Select select =
            new org.openqa.selenium.support.ui.Select(driver.findElement(courseDropdown));
        select.selectByVisibleText(visibleText);
    }

    public void selectOnlineMode() {
        driver.findElement(modeOnline).click();
    }

    public void selectOfflineMode() {
        driver.findElement(modeOffline).click();
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
        WebElement el = driver.findElement(successMessage);
        return el.isDisplayed();
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
