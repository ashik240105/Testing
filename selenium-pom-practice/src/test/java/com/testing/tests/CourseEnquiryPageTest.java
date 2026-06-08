package com.testing.tests;

import com.testing.base.BaseTest;
import com.testing.pages.CourseEnquiryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

/**
 * Test class for Course Enquiry Page (course-enquiry.html)
 * Test IDs: CE_01 through CE_11
 */
public class CourseEnquiryPageTest extends BaseTest {

    private CourseEnquiryPage enquiryPage;

    @BeforeMethod
    public void openPage() {
        String absolutePath = Paths.get("").toAbsolutePath()
                .getParent()
                .resolve("student-portal-testing-project/course-enquiry.html")
                .toUri()
                .toString();
        driver.get(absolutePath);
        enquiryPage = new CourseEnquiryPage(driver);
    }

    /** CE_01: Verify page title */
    @Test(description = "CE_01 - Verify Course Enquiry page title")
    public void verifyPageTitle() {
        Assert.assertEquals(enquiryPage.getPageTitle(), "Course Enquiry Form");
    }

    /** CE_02: Verify student name placeholder */
    @Test(description = "CE_02 - Verify student name placeholder")
    public void verifyStudentNamePlaceholder() {
        Assert.assertEquals(enquiryPage.getStudentNamePlaceholder(), "Enter student name");
    }

    /** CE_03: Verify email placeholder */
    @Test(description = "CE_03 - Verify email placeholder")
    public void verifyEmailPlaceholder() {
        Assert.assertEquals(enquiryPage.getEmailPlaceholder(), "Enter email");
    }

    /** CE_04: Verify mobile placeholder */
    @Test(description = "CE_04 - Verify mobile placeholder")
    public void verifyMobilePlaceholder() {
        Assert.assertEquals(enquiryPage.getMobilePlaceholder(), "Enter mobile number");
    }

    /** CE_05: Submit without student name */
    @Test(description = "CE_05 - Submit without student name")
    public void submitWithoutStudentName() {
        enquiryPage.clickSubmit();
        Assert.assertEquals(enquiryPage.getErrorMessage(), "Student name is required");
    }

    /** CE_06: Submit without email (student name filled) */
    @Test(description = "CE_06 - Submit without email")
    public void submitWithoutEmail() {
        enquiryPage.enterStudentName("John Doe");
        pauseOneSecond();
        enquiryPage.clickSubmit();
        Assert.assertEquals(enquiryPage.getErrorMessage(), "Email is required");
    }

    /** CE_07: Submit with invalid email (no @ symbol) */
    @Test(description = "CE_07 - Submit invalid email")
    public void submitInvalidEmail() {
        enquiryPage.enterStudentName("John Doe");
        pauseOneSecond();
        enquiryPage.enterEmail("invalidemail.com");
        pauseOneSecond();
        enquiryPage.clickSubmit();
        Assert.assertEquals(enquiryPage.getErrorMessage(), "Invalid email format");
    }

    /** CE_08: Submit with invalid mobile (less or more than 10 digits) */
    @Test(description = "CE_08 - Submit invalid mobile number")
    public void submitInvalidMobile() {
        enquiryPage.enterStudentName("John Doe");
        pauseOneSecond();
        enquiryPage.enterEmail("john@example.com");
        pauseOneSecond();
        enquiryPage.enterMobile("12345");  // less than 10 digits
        pauseOneSecond();
        enquiryPage.clickSubmit();
        Assert.assertEquals(enquiryPage.getErrorMessage(), "Mobile number should be exactly 10 digits");
    }

    /** CE_09: Submit without course */
    @Test(description = "CE_09 - Submit without course selection")
    public void submitWithoutCourse() {
        enquiryPage.enterStudentName("John Doe");
        pauseOneSecond();
        enquiryPage.enterEmail("john@example.com");
        pauseOneSecond();
        enquiryPage.enterMobile("9876543210");
        pauseOneSecond();
        enquiryPage.clickSubmit();
        Assert.assertEquals(enquiryPage.getErrorMessage(), "Course is required");
    }

    /** CE_10: Submit without learning mode */
    @Test(description = "CE_10 - Submit without learning mode")
    public void submitWithoutLearningMode() {
        enquiryPage.enterStudentName("John Doe");
        pauseOneSecond();
        enquiryPage.enterEmail("john@example.com");
        pauseOneSecond();
        enquiryPage.enterMobile("9876543210");
        pauseOneSecond();
        enquiryPage.selectCourse("Java Full Stack");
        pauseOneSecond();
        enquiryPage.clickSubmit();
        Assert.assertEquals(enquiryPage.getErrorMessage(), "Learning mode is required");
    }

    /** CE_11: Submit with all valid fields */
    @Test(description = "CE_11 - Submit valid enquiry")
    public void submitValidEnquiry() {
        enquiryPage.enterStudentName("John Doe");
        pauseOneSecond();
        enquiryPage.enterEmail("john@example.com");
        pauseOneSecond();
        enquiryPage.enterMobile("9876543210");
        pauseOneSecond();
        enquiryPage.selectCourse("Java Full Stack");
        pauseOneSecond();
        enquiryPage.selectOnlineMode();
        pauseOneSecond();
        enquiryPage.clickSubmit();
        Assert.assertEquals(enquiryPage.getSuccessMessage(), "Course enquiry submitted successfully");
        Assert.assertTrue(enquiryPage.isSuccessMessageDisplayed(), "Success message should be visible");
    }
}
