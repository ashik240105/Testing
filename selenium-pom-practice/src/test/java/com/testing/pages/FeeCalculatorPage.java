package com.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object class for fee-calculator.html.
 */
public class FeeCalculatorPage {

    private final WebDriver driver;

    // ─── Locators ───────────────────────────────────────────────────────────────
    private final By courseDropdown    = By.id("courseSelect");
    private final By discountField     = By.id("discount");
    private final By calculateButton   = By.id("calculateFee");
    private final By errorMessage      = By.id("fcErrorMsg");
    private final By resultBox         = By.id("resultBox");
    private final By resultText        = By.id("fcResult");

    // ─── Constructor ─────────────────────────────────────────────────────────────
    public FeeCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    // ─── Actions ─────────────────────────────────────────────────────────────────

    public String getPageTitle() { return driver.getTitle(); }

    public String getDiscountPlaceholder() {
        return driver.findElement(discountField).getAttribute("placeholder");
    }

    public void selectCourse(String visibleText) {
        Select select = new Select(driver.findElement(courseDropdown));
        select.selectByVisibleText(visibleText);
    }

    public void enterDiscount(String discount) {
        WebElement el = driver.findElement(discountField);
        el.clear();
        el.sendKeys(discount);
        sleepOneSecond();
    }

    public void clickCalculate() {
        driver.findElement(calculateButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public String getResultText() {
        return driver.findElement(resultText).getText();
    }

    public boolean isResultDisplayed() {
        return driver.findElement(resultBox).isDisplayed();
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
