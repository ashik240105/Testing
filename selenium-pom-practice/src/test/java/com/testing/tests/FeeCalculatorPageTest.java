package com.testing.tests;

import com.testing.base.BaseTest;
import com.testing.pages.FeeCalculatorPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/**
 * Test class for Fee Calculator Page (fee-calculator.html)
 * Test IDs: FC_01 through FC_09
 */
public class FeeCalculatorPageTest extends BaseTest {

    private FeeCalculatorPage feeCalcPage;

    @BeforeMethod
    public void openPage() {
        String absolutePath = Paths.get("").toAbsolutePath()
                .getParent()
                .resolve("student-portal-testing-project/fee-calculator.html")
                .toUri()
                .toString();
        driver.get(absolutePath);
        feeCalcPage = new FeeCalculatorPage(driver);
    }

    /** FC_01: Verify page title */
    @Test(description = "FC_01 - Verify Fee Calculator page title")
    public void verifyPageTitle() {
        Assert.assertEquals(feeCalcPage.getPageTitle(), "Course Fee Calculator");
    }

    /** FC_02: Verify discount placeholder */
    @Test(description = "FC_02 - Verify discount placeholder")
    public void verifyDiscountPlaceholder() {
        Assert.assertEquals(feeCalcPage.getDiscountPlaceholder(), "Enter discount percentage");
    }

    /** FC_03: Calculate without course */
    @Test(description = "FC_03 - Calculate without selecting course")
    public void calculateWithoutCourse() {
        feeCalcPage.clickCalculate();
        Assert.assertEquals(feeCalcPage.getErrorMessage(), "Course is required");
    }

    /** FC_04: Calculate without discount */
    @Test(description = "FC_04 - Calculate without entering discount")
    public void calculateWithoutDiscount() {
        feeCalcPage.selectCourse("Java Full Stack (Rs.30000)");
        pauseOneSecond();
        feeCalcPage.clickCalculate();
        Assert.assertEquals(feeCalcPage.getErrorMessage(), "Discount is required");
    }

    /** FC_05: Enter alphabet in discount */
    @Test(description = "FC_05 - Enter alphabet in discount field")
    public void enterAlphabetDiscount() {
        feeCalcPage.selectCourse("Java Full Stack (Rs.30000)");
        pauseOneSecond();
        feeCalcPage.enterDiscount("abc");
        pauseOneSecond();
        feeCalcPage.clickCalculate();
        Assert.assertEquals(feeCalcPage.getErrorMessage(), "Discount should be a number");
    }

    /** FC_06: Enter discount below 0 */
    @Test(description = "FC_06 - Enter discount below 0")
    public void enterDiscountBelowZero() {
        feeCalcPage.selectCourse("Java Full Stack (Rs.30000)");
        pauseOneSecond();
        feeCalcPage.enterDiscount("-5");
        pauseOneSecond();
        feeCalcPage.clickCalculate();
        Assert.assertEquals(feeCalcPage.getErrorMessage(), "Discount should be between 0 and 50");
    }

    /** FC_07: Enter discount above 50 */
    @Test(description = "FC_07 - Enter discount above 50")
    public void enterDiscountAboveFifty() {
        feeCalcPage.selectCourse("Java Full Stack (Rs.30000)");
        pauseOneSecond();
        feeCalcPage.enterDiscount("60");
        pauseOneSecond();
        feeCalcPage.clickCalculate();
        Assert.assertEquals(feeCalcPage.getErrorMessage(), "Discount should be between 0 and 50");
    }

    /** FC_08: Calculate Java Full Stack fee with 10% discount */
    @Test(description = "FC_08 - Calculate Java Full Stack fee with 10% discount")
    public void calculateJavaFullStackFee() {
        feeCalcPage.selectCourse("Java Full Stack (Rs.30000)");
        pauseOneSecond();
        feeCalcPage.enterDiscount("10");
        pauseOneSecond();
        feeCalcPage.clickCalculate();
        String result = feeCalcPage.getResultText();
        Assert.assertEquals(result, "Final Fee: Rs.27000.0",
                "Expected final fee to be Rs.27000. Got: " + result);
    }

    /** FC_09: Calculate Software Testing fee with 20% discount */
    @Test(description = "FC_09 - Calculate Software Testing fee with 20% discount")
    public void calculateSoftwareTestingFee() {
        feeCalcPage.selectCourse("Software Testing (Rs.25000)");
        pauseOneSecond();
        feeCalcPage.enterDiscount("20");
        pauseOneSecond();
        feeCalcPage.clickCalculate();
        String result = feeCalcPage.getResultText();
        Assert.assertEquals(result, "Final Fee: Rs.20000.0",
                "Expected final fee to be Rs.20000. Got: " + result);
    }
}
