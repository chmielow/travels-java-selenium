package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SingUpPage {
    private WebDriver driver;


    public SingUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(name = "firstname")
    private WebElement firstName;
    @FindBy(name = "lastname")
    private WebElement lastName;
    @FindBy(name = "phone")
    private WebElement phone;
    @FindBy(name = "email")
    private WebElement email;
    @FindBy(name = "password")
    private WebElement password;
    @FindBy(name = "confirmpassword")
    private WebElement confirmPassword;
    @FindBy(xpath = "//button[@type='submit' and text()=' Sign Up']")
    private WebElement singUpButton;
    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> alertDanger;


    public SingUpPage setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;
    }

    public SingUpPage setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public SingUpPage setPhone(String phone) {
        this.phone.sendKeys(phone);
        return this;
    }

    public SingUpPage setEmail(String email) {
        int randomNumber = (int) (Math.random() * 1000);
        email = randomNumber + email;
        this.email.sendKeys(email);
        return this;
    }

    public SingUpPage setPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public SingUpPage setConfirmPassword(String confirmPassword) {
        this.confirmPassword.sendKeys(confirmPassword);
        return this;
    }

    public LoggedUserPage clickSingUpButton() {
        this.singUpButton.click();
        return new LoggedUserPage(driver);
    }

    public List<String> getAlertDanger() {
        return alertDanger.stream().map(WebElement::getText).toList();
    }
/*
    public void fillSingUpForm(User user){
        firstName.sendKeys(user.getFirstName());
        lastName.sendKeys(user.getLastName());
        phone.sendKeys(user.getPhone());
        email.sendKeys(user.getEmail());
        password.sendKeys(user.getPassword());
        confirmPassword.sendKeys(user.getPassword());
    }*/

}
