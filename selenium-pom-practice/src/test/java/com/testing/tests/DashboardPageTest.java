package com.testing.tests;

import com.testing.base.BaseTest;
import com.testing.pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/**
 * Test class for Dashboard Page (dashboard.html)
 * Test IDs: DB_01 through DB_09
 */
public class DashboardPageTest extends BaseTest {

    private DashboardPage dashboardPage;

    @BeforeMethod
    public void openDashboard() {
        String absolutePath = Paths.get("").toAbsolutePath()
                .getParent()
                .resolve("student-portal-testing-project/dashboard.html")
                .toUri()
                .toString();
        driver.get(absolutePath);
        dashboardPage = new DashboardPage(driver);
    }

    /** DB_01: Verify dashboard page title */
    @Test(description = "DB_01 - Verify dashboard page title")
    public void verifyDashboardTitle() {
        String title = dashboardPage.getPageTitle();
        Assert.assertEquals(title, "Student Dashboard", "Dashboard title mismatch");
    }

    /** DB_02: Verify app name text */
    @Test(description = "DB_02 - Verify app name")
    public void verifyAppName() {
        String appName = dashboardPage.getAppName();
        Assert.assertEquals(appName, "Student Portal", "App name mismatch");
    }

    /** DB_03: Click Course Enquiry navbar link */
    @Test(description = "DB_03 - Navigate to course-enquiry.html via navbar")
    public void clickCourseNavLink() {
        dashboardPage.clickCourseLink();
        pauseOneSecond();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("course-enquiry.html"),
            "Should navigate to course-enquiry.html");
    }

    /** DB_04: Click Fee Calculator navbar link */
    @Test(description = "DB_04 - Navigate to fee-calculator.html via navbar")
    public void clickFeeNavLink() {
        dashboardPage.clickFeeLink();
        pauseOneSecond();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("fee-calculator.html"),
            "Should navigate to fee-calculator.html");
    }

    /** DB_05: Click Feedback navbar link */
    @Test(description = "DB_05 - Navigate to feedback.html via navbar")
    public void clickFeedbackNavLink() {
        dashboardPage.clickFeedbackLink();
        pauseOneSecond();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("feedback.html"),
            "Should navigate to feedback.html");
    }

    /** DB_06: Click Logout link */
    @Test(description = "DB_06 - Navigate to index.html via logout")
    public void clickLogoutLink() {
        dashboardPage.clickLogoutLink();
        pauseOneSecond();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("index.html"),
            "Should navigate to index.html after logout");
    }

    /** DB_07: Click Course Enquiry card button */
    @Test(description = "DB_07 - Navigate to course-enquiry.html via card button")
    public void clickCourseCardButton() {
        dashboardPage.clickCourseCardBtn();
        pauseOneSecond();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("course-enquiry.html"),
            "Course card button should navigate to course-enquiry.html");
    }

    /** DB_08: Click Fee Calculator card button */
    @Test(description = "DB_08 - Navigate to fee-calculator.html via card button")
    public void clickFeeCardButton() {
        dashboardPage.clickFeeCardBtn();
        pauseOneSecond();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("fee-calculator.html"),
            "Fee card button should navigate to fee-calculator.html");
    }

    /** DB_09: Click Feedback card button */
    @Test(description = "DB_09 - Navigate to feedback.html via card button")
    public void clickFeedbackCardButton() {
        dashboardPage.clickFeedbackCardBtn();
        pauseOneSecond();
        Assert.assertTrue(dashboardPage.getCurrentUrl().contains("feedback.html"),
            "Feedback card button should navigate to feedback.html");
    }
}
