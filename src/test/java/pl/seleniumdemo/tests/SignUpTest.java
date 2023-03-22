package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SingUpPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        String lastName = "Tester";

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSingUpForm()
                .setFirstName("Marcin")
                .setLastName(lastName)
                .setPhone("123456789")
                .setEmail("marcin@mail.pl")
                .setPassword("Test123")
                .setConfirmPassword("Test123")
                .clickSingUpButton();

        //Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        //Assert.assertEquals(loggedUserPage.getHeadingText(),"Hi, Marcin Tester");

    }

    @Test
    public void signUpWithoutFillTest() {

        SingUpPage singUpPage = new HotelSearchPage(driver).openSingUpForm();

        singUpPage.clickSingUpButton();
        List<String> errors = singUpPage.getAlertDanger();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();

    }

    @Test
    public void signUpWithInvalidEmailTest() {


        SingUpPage singUpPage = new HotelSearchPage(driver).openSingUpForm()
                .setFirstName("Marcin")
                .setLastName("Tester")
                .setPhone("123456789")
                .setEmail("marcin")
                .setPassword("Test123")
                .setConfirmPassword("Test123");

        singUpPage.clickSingUpButton();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        List<String> errors = singUpPage.getAlertDanger();
        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));
    }
}
