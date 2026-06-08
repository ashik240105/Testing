package com.testing.tests;

import com.testing.base.BaseTest;
import com.testing.pages.FeedbackPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/**
 * Test class for Feedback Page (feedback.html)
 * Test IDs: FB_01 through FB_08
 */
public class FeedbackPageTest extends BaseTest {

    private FeedbackPage feedbackPage;

    @BeforeMethod
    public void openPage() {
        String absolutePath = Paths.get("").toAbsolutePath()
                .getParent()
                .resolve("student-portal-testing-project/feedback.html")
                .toUri()
                .toString();
        driver.get(absolutePath);
        feedbackPage = new FeedbackPage(driver);
    }

    /** FB_01: Verify page title */
    @Test(description = "FB_01 - Verify Feedback page title")
    public void verifyPageTitle() {
        Assert.assertEquals(feedbackPage.getPageTitle(), "Student Feedback Form");
    }

    /** FB_02: Verify student name placeholder */
    @Test(description = "FB_02 - Verify student name placeholder")
    public void verifyStudentNamePlaceholder() {
        Assert.assertEquals(feedbackPage.getStudentNamePlaceholder(), "Enter student name");
    }

    /** FB_03: Verify comments placeholder */
    @Test(description = "FB_03 - Verify comments placeholder")
    public void verifyCommentsPlaceholder() {
        Assert.assertEquals(feedbackPage.getCommentsPlaceholder(), "Enter your comments");
    }

    /** FB_04: Submit without student name */
    @Test(description = "FB_04 - Submit without student name")
    public void submitWithoutStudentName() {
        feedbackPage.clickSubmit();
        Assert.assertEquals(feedbackPage.getErrorMessage(), "Student name is required");
    }

    /** FB_05: Submit without rating */
    @Test(description = "FB_05 - Submit without rating")
    public void submitWithoutRating() {
        feedbackPage.enterStudentName("Jane Doe");
        pauseOneSecond();
        feedbackPage.clickSubmit();
        Assert.assertEquals(feedbackPage.getErrorMessage(), "Rating is required");
    }

    /** FB_06: Submit with comment shorter than 10 characters */
    @Test(description = "FB_06 - Submit comment shorter than 10 characters")
    public void submitShortComment() {
        feedbackPage.enterStudentName("Jane Doe");
        pauseOneSecond();
        feedbackPage.selectRating("⭐⭐⭐⭐ Good");
        pauseOneSecond();
        feedbackPage.enterComments("Short");   // < 10 chars
        pauseOneSecond();
        feedbackPage.clickSubmit();
        Assert.assertEquals(feedbackPage.getErrorMessage(), "Comments should be minimum 10 characters");
    }

    /** FB_07: Submit without ticking the confirmation checkbox */
    @Test(description = "FB_07 - Submit without confirmation checkbox")
    public void submitWithoutConfirmation() {
        feedbackPage.enterStudentName("Jane Doe");
        pauseOneSecond();
        feedbackPage.selectRating("⭐⭐⭐⭐ Good");
        pauseOneSecond();
        feedbackPage.enterComments("This is a valid comment with enough length.");
        pauseOneSecond();
        // Intentionally do NOT tick the checkbox
        feedbackPage.clickSubmit();
        Assert.assertEquals(feedbackPage.getErrorMessage(), "Please confirm your feedback");
    }

    /** FB_08: Submit valid feedback */
    @Test(description = "FB_08 - Submit valid feedback")
    public void submitValidFeedback() {
        feedbackPage.enterStudentName("Jane Doe");
        pauseOneSecond();
        feedbackPage.selectRating("⭐⭐⭐⭐⭐ Excellent");
        pauseOneSecond();
        feedbackPage.enterComments("The course was very well structured and informative.");
        pauseOneSecond();
        feedbackPage.tickConfirmCheckbox();
        pauseOneSecond();
        feedbackPage.clickSubmit();
        Assert.assertEquals(feedbackPage.getSuccessMessage(), "Feedback submitted successfully");
        Assert.assertTrue(feedbackPage.isSuccessMessageDisplayed(), "Success message should be visible");
    }
}
