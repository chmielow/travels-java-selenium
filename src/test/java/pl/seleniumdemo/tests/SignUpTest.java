package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SingUpPage;
import pl.seleniumdemo.pages.model.User;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        String lastName = "Tester";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.setFirstName("Marcin");
        singUpPage.setLastName("Tester");
        singUpPage.setPhone("123456789");
        singUpPage.setEmail("marcin@mail.pl");
        singUpPage.setPassword("Test123");
        singUpPage.setConfirmPassword("Test123");
        singUpPage.clickSingUpButton();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Marcin Tester");

    }

    @Test
    public void signUpTest2() {

        User user = new User();
        user.setFirstName("Marcin");
        user.setLastName("Tester");
        user.setPhone("123456789");
        user.setEmail("marcin@mail.com");
        user.setPassword("Test123");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.fillSingUpForm(user);
        singUpPage.clickSingUpButton();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(user.getLastName()));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Marcin Tester");

    }

    @Test
    public void signUpWithoutFillTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
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

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SingUpPage singUpPage = new SingUpPage(driver);
        singUpPage.setFirstName("Marcin");
        singUpPage.setLastName("Tester");
        singUpPage.setPhone("123456789");
        singUpPage.setEmail("marcin");
        singUpPage.setPassword("Test123");
        singUpPage.setConfirmPassword("Test123");
        singUpPage.clickSingUpButton();

        List<String> errors = singUpPage.getAlertDanger();
        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));
    }
}
