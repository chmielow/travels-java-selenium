package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.SingUpPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
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

        String lastName = "Tester";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(),"Hi, Marcin Tester");

    }
    @Test
    public void signUpWithoutFillTest() {

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream().map(WebElement::getText).toList();
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

        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        driver.findElement(By.name("firstname")).sendKeys("Marcin");
        driver.findElement(By.name("lastname")).sendKeys("Tester");
        driver.findElement(By.name("phone")).sendKeys("123456789");
        driver.findElement(By.name("email")).sendKeys("email");
        driver.findElement(By.name("password")).sendKeys("Test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("Test123");
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream().map(WebElement::getText).toList();
        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));
    }
}
